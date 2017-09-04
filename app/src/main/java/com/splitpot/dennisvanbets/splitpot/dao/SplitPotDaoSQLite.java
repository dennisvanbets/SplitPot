package com.splitpot.dennisvanbets.splitpot.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.splitpot.dennisvanbets.splitpot.model.Participant;
import com.splitpot.dennisvanbets.splitpot.model.Pot;
import com.splitpot.dennisvanbets.splitpot.model.Round;
import com.splitpot.dennisvanbets.splitpot.model.User;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Singleton;

@Singleton
public class SplitPotDaoSQLite implements SplitPotDao {
    private static SplitPotDaoSQLite instance;

    private SQLiteDatabase database;
    private SplitPotDbHelper dbHelper;

    private SplitPotDaoSQLite(Context context) {
        this.dbHelper = new SplitPotDbHelper(context);
        this.database = dbHelper.getWritableDatabase();
    }


    public static synchronized SplitPotDaoSQLite getInstance(Context context) {
        if (instance == null){
            instance = new SplitPotDaoSQLite(context);
        };
        return instance;
    }

    @Override
    public void onUpgrade(int i, int j){
        dbHelper.onUpgrade(database, i, j);
    }

    @Override
    public void close() {
        dbHelper.close();
    }


    @Override
    public Set<User> getAllUsers() {
        String[] projection = SplitPotContract.UserEntry.ALL_COLUMNS;
        Cursor cursor = database.query(
                SplitPotContract.UserEntry.TABLE_NAME,
                projection,null,null,null,null,null);
        Set<User> users = new HashSet<>();
        while (cursor.moveToNext()){
            users.add(new User(
                    cursor.getLong(0),
                    cursor.getString(cursor.getColumnIndex(SplitPotContract.UserEntry.COLUMN_FIRSTNAME)),
                    cursor.getString(cursor.getColumnIndex(SplitPotContract.UserEntry.COLUMN_LAST_NAME)),
                    cursor.getInt(cursor.getColumnIndex(SplitPotContract.UserEntry.COLUMN_AVATAR)),
                    cursor.getString(cursor.getColumnIndex(SplitPotContract.UserEntry.COLUMN_BANK_INFO))
            ));
        }
        cursor.close();
        return users;
    }

    @Override
    public User getUser(long id) {
        String[] projection = SplitPotContract.UserEntry.ALL_COLUMNS;
        String selection = SplitPotContract.UserEntry._ID + " = ?";
        String[] selectionArgs = {id + ""};
        Cursor cursor = database.query(
                SplitPotContract.UserEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs, null, null, null, "1"
        );
        if (cursor.getCount() == 1) {
            cursor.moveToFirst();
            User user = new User(
                    cursor.getLong(0),
                    cursor.getString(cursor.getColumnIndex(SplitPotContract.UserEntry.COLUMN_FIRSTNAME)),
                    cursor.getString(cursor.getColumnIndex(SplitPotContract.UserEntry.COLUMN_LAST_NAME)),
                    cursor.getColumnIndex(SplitPotContract.UserEntry.COLUMN_AVATAR),
                    cursor.getString(cursor.getColumnIndex(SplitPotContract.UserEntry.COLUMN_BANK_INFO)));
            cursor.close();
            return user;

        }
        cursor.close();
        return null;
    }

    @Override
    public User insertUser(String firstname, String lastname, int avaterRes) {
        return this.insertUser(firstname, lastname, avaterRes, "");
    }

    @Override
    public User insertUser(String firstname, String lastname, int avaterRes, String bankInfo) {
        ContentValues values = new ContentValues();
        values.put(SplitPotContract.UserEntry.COLUMN_FIRSTNAME, firstname);
        values.put(SplitPotContract.UserEntry.COLUMN_LAST_NAME, lastname);
        values.put(SplitPotContract.UserEntry.COLUMN_AVATAR, avaterRes);
        values.put(SplitPotContract.UserEntry.COLUMN_BANK_INFO, bankInfo);

        long id = database.insert(
                SplitPotContract.UserEntry.TABLE_NAME,
                null,
                values);

        return new User(id, firstname, lastname, avaterRes, bankInfo);
    }

    @Override
    public boolean updateUser(long id, String firstname, String lastname, int avaterRes, String bankInfo) {
        ContentValues values = new ContentValues();
        values.put(SplitPotContract.UserEntry.COLUMN_FIRSTNAME, firstname);
        values.put(SplitPotContract.UserEntry.COLUMN_LAST_NAME, lastname);
        values.put(SplitPotContract.UserEntry.COLUMN_AVATAR, avaterRes);
        values.put(SplitPotContract.UserEntry.COLUMN_BANK_INFO, bankInfo);

        String selection = SplitPotContract.UserEntry._ID + "LIKE ?";
        String[] selectionArgs = {id + ""};

        return database.update(
                SplitPotContract.UserEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs
        ) == 1;
    }

    @Override
    public boolean deleteUser(long id) {
        String selection = SplitPotContract.UserEntry._ID + " LIKE ?";
        String[] selectionsArgs = {id + ""};
        return database.delete(
                SplitPotContract.UserEntry.TABLE_NAME,
                selection,
                selectionsArgs
        ) == 1;
    }

    @Override
    public Set<Participant> getParticipantsOfRound(long roundid) {
        String[] projection = SplitPotContract.ParticipantEntry.ALL_COLUMNS;
        String selection = SplitPotContract.ParticipantEntry.COLUMN_ROUND_ID + " = ? ";
        String[] selectionArgs = {String.valueOf(roundid)};

        Cursor cursor = database.query(
                SplitPotContract.ParticipantEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null, null);

        Set<Participant> pars = new HashSet<>(cursor.getCount());
        while (cursor.moveToNext()) {
            pars.add(new Participant(
                    cursor.getLong(0),
                    cursor.getLong(cursor.getColumnIndex(SplitPotContract.ParticipantEntry.COLUMN_ROUND_ID)),
                    getUser(cursor.getLong(cursor.getColumnIndex(SplitPotContract.ParticipantEntry.COLUMN_USER))),
                    cursor.getDouble(cursor.getColumnIndex(SplitPotContract.ParticipantEntry.COLUMN_AMOUNT)),
                    cursor.getInt(cursor.getColumnIndex(SplitPotContract.ParticipantEntry.COLUMN_ISPAYER)) == 1,
                    cursor.getInt(cursor.getColumnIndex(SplitPotContract.ParticipantEntry.COLUMN_HASPAID)) == 1
            ));
        }
        cursor.close();
        return pars;
    }

    @Override
    public Participant getParticipant(String id) {
        String[] projection = SplitPotContract.ParticipantEntry.ALL_COLUMNS;
        String selection = SplitPotContract.ParticipantEntry._ID + " = ?";
        String[] selectionArgs = {id + ""};
        Cursor cursor = database.query(
                SplitPotContract.ParticipantEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs, null, null, null, "1");
        if (cursor.getCount() == 1) {
            cursor.moveToFirst();
            Participant participant = new Participant(
                    cursor.getLong(0),
                    cursor.getLong(1),
                    this.getUser(cursor.getLong(2)),
                    cursor.getDouble(cursor.getColumnIndex(SplitPotContract.ParticipantEntry.COLUMN_AMOUNT)),
                    cursor.getInt(cursor.getColumnIndex(SplitPotContract.ParticipantEntry.COLUMN_ISPAYER)) == 1,
                    cursor.getInt(cursor.getColumnIndex(SplitPotContract.ParticipantEntry.COLUMN_HASPAID)) == 1
            );
            cursor.close();
            return participant;
        }
        cursor.close();
        return null;
    }

    @Override
    public Participant insertParticipant(long roundid, long userid, double debt, boolean isPayer, boolean hasPaid) {
        ContentValues values = new ContentValues();
        values.put(SplitPotContract.ParticipantEntry.COLUMN_ROUND_ID, roundid);
        values.put(SplitPotContract.ParticipantEntry.COLUMN_USER, userid);
        values.put(SplitPotContract.ParticipantEntry.COLUMN_AMOUNT, debt);
        values.put(SplitPotContract.ParticipantEntry.COLUMN_ISPAYER, isPayer);
        values.put(SplitPotContract.ParticipantEntry.COLUMN_HASPAID, hasPaid);

        long id = database.insert(
                SplitPotContract.ParticipantEntry.TABLE_NAME,
                null,
                values
        );

        return new Participant(id, roundid, getUser(userid), debt, isPayer, hasPaid);
    }

    @Override
    public boolean updateParticipant(long participantid, double debt, boolean isPayer, boolean hasPaid) {
        ContentValues values = new ContentValues();
        values.put(SplitPotContract.ParticipantEntry.COLUMN_AMOUNT, debt);
        values.put(SplitPotContract.ParticipantEntry.COLUMN_ISPAYER, isPayer);
        values.put(SplitPotContract.ParticipantEntry.COLUMN_HASPAID, hasPaid);
        String selection = SplitPotContract.ParticipantEntry._ID + " LIKE ?";
        String[] selectionArgs = {String.valueOf(participantid)};

        return database.update(
                SplitPotContract.ParticipantEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs
        ) == 1;
    }

    @Override
    public boolean deleteParticipant(long paricipantid) {
        String selection = SplitPotContract.ParticipantEntry._ID + " LIKE ? ";
        String[] selectionArgs = {String.valueOf(paricipantid)};
        return database.delete(
                SplitPotContract.ParticipantEntry.TABLE_NAME,
                selection,
                selectionArgs
        ) == 1;
    }

    @Override
    public List<Round> getAllRounds() {
        String[] projection = SplitPotContract.RoundEntry.ALL_COLUMNS;
        Cursor cursor = database.query(
                SplitPotContract.RoundEntry.TABLE_NAME,
                projection,
                null,null,null,null,null);
        List<Round> rounds = new ArrayList<>(cursor.getCount());
        while (cursor.moveToNext()){
            rounds.add(new Round(
                    cursor.getLong(0),
                    cursor.getLong(1),
                    cursor.getString(cursor.getColumnIndex(SplitPotContract.RoundEntry.COLUMN_NAME)),
                    getParticipantsOfRound(cursor.getLong(0)),
                    new Date(cursor.getColumnIndex(SplitPotContract.RoundEntry.COLUMN_TIMESTAMP))
            ));
        }
        cursor.close();
        return rounds;
    }


    @Override
    public Round getRound(long id) {
        String[] projection = SplitPotContract.RoundEntry.ALL_COLUMNS;
        String selection = SplitPotContract.RoundEntry._ID + " = ? ";
        String[] selectionArgs = {String.valueOf(id)};

        Cursor cursor = database.query(
                SplitPotContract.RoundEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null,
                "1"
        );
        cursor.moveToFirst();
        Round round = new Round(
                cursor.getLong(0),
                cursor.getLong(1),
                cursor.getString(cursor.getColumnIndex(SplitPotContract.RoundEntry.COLUMN_NAME)),
                getParticipantsOfRound(cursor.getLong(0)),
                new Date(cursor.getColumnIndex(SplitPotContract.RoundEntry.COLUMN_TIMESTAMP))
        );
        cursor.close();
        return round;
    }

    @Override
    public Set<Round> getAllRoundsOfPot(long potId) {
        String[] projection = SplitPotContract.RoundEntry.ALL_COLUMNS;
        String selection = SplitPotContract.RoundEntry.COLUMN_POT_ID + " = ? ";
        String[] selectionArgs = {String.valueOf(potId)};
        String sortOrder = SplitPotContract.RoundEntry.COLUMN_TIMESTAMP + " DESC";

        Cursor cursor = database.query(
                SplitPotContract.RoundEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );
        Set<Round> set = new HashSet<>(cursor.getCount());
        while (cursor.moveToNext()) {
            Round round = new Round(
                    cursor.getLong(0),
                    cursor.getLong(1),
                    cursor.getString(cursor.getColumnIndex(SplitPotContract.RoundEntry.COLUMN_NAME)),
                    getParticipantsOfRound(cursor.getLong(0)),
                    new Date(cursor.getLong(cursor.getColumnIndex(SplitPotContract.RoundEntry.COLUMN_TIMESTAMP)))
            );
            set.add(round);
        }

        cursor.close();
        return set;
    }

    @Override
    public Round insertRound(long potId, String name, Date timestamp) {
        ContentValues values = new ContentValues();
        values.put(SplitPotContract.RoundEntry.COLUMN_POT_ID, potId);
        values.put(SplitPotContract.RoundEntry.COLUMN_NAME, name);
        values.put(SplitPotContract.RoundEntry.COLUMN_TIMESTAMP, timestamp.getTime());

        long id = database.insert(SplitPotContract.RoundEntry.TABLE_NAME, null, values);
        return new Round(id, potId, name, new HashSet<>(), timestamp);
    }

    @Override
    public boolean updateRound(long roundid, String name) {
        ContentValues values = new ContentValues();
        values.put(SplitPotContract.RoundEntry.COLUMN_NAME, name);

        String selection = SplitPotContract.RoundEntry.COLUMN_NAME + " LIKE ? ";
        String[] selectionArgs = {String.valueOf(roundid)};
        return database.update(
                SplitPotContract.RoundEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs
        ) == 1;
    }

    @Override
    public boolean deleteRound(long roundid) {
        Set<Participant> participants = getParticipantsOfRound(roundid);
        for (Participant p : participants) {
            deleteParticipant(p.getId());
        }
        String selection = SplitPotContract.RoundEntry._ID + " LIKE ? ";
        String[] selectionArgs = {String.valueOf(roundid)};

        return database.delete(
                SplitPotContract.RoundEntry.TABLE_NAME,
                selection,
                selectionArgs
        ) == 1;
    }

    @Override
    public Pot insertPot(String name) {
        long time = Calendar.getInstance().getTime().getTime();
        ContentValues values = new ContentValues();
        values.put(SplitPotContract.PotEntry.COLUMN_NAME, name);
        values.put(SplitPotContract.PotEntry.COLUMN_STARTDATE, time);

        long id = database.insert(
                SplitPotContract.PotEntry.TABLE_NAME,
                null,
                values
        );
        return new Pot(id, name, new HashSet<>(), new Date(time));
    }

    @Override
    public boolean updatePot(long id, String name) {
        ContentValues values = new ContentValues();
        values.put(SplitPotContract.PotEntry.COLUMN_NAME, name);

        String selection = SplitPotContract.PotEntry._ID + " LIKE ?";
        String[] selectionArgs = {String.valueOf(id)};

        return database.update(
                SplitPotContract.PotEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs
        ) == 1;
    }

    @Override
    public boolean deletePot(long id) {
        Set<Round> rounds = getAllRoundsOfPot(id);
        for (Round r : rounds) {
            deleteRound(r.getId());
        }
        String selection = SplitPotContract.PotEntry._ID + " LIKE ? ";
        String[] selectionArgs = {String.valueOf(id)};

        database.delete(
                SplitPotContract.PotEntry.TABLE_NAME,
                selection,
                selectionArgs
        );
        return false;
    }

    @Override
    public List<Pot> getAllPots() {
        String[] projection = SplitPotContract.PotEntry.ALL_COLUMNS;
        String sortOrder = SplitPotContract.PotEntry.COLUMN_STARTDATE + " DESC";
        Cursor cursor = database.query(
                SplitPotContract.PotEntry.TABLE_NAME,
                projection, null, null, null, null, sortOrder
        );
        List<Pot> pots = new ArrayList<>(cursor.getCount());
        while (cursor.moveToNext()) {
            Date enddate;
            long enddatelong = cursor.getLong(cursor.getColumnIndex(SplitPotContract.PotEntry.COLUMN_ENDDATE));
            if (enddatelong == 0) enddate = null;
            else enddate = new Date(cursor.getLong(cursor.getColumnIndex(SplitPotContract.PotEntry.COLUMN_ENDDATE)));

            pots.add(new Pot(
                    cursor.getLong(0),
                    getAllRoundsOfPot(cursor.getLong(cursor.getColumnIndex(SplitPotContract.PotEntry._ID))),
                    cursor.getString(cursor.getColumnIndex(SplitPotContract.PotEntry.COLUMN_NAME)),
                    new Date(cursor.getLong(cursor.getColumnIndex(SplitPotContract.PotEntry.COLUMN_STARTDATE))),
                    enddate
            ));

        }
        cursor.close();
        return pots;
    }
}
