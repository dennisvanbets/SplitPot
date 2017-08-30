package com.splitpot.dennisvanbets.splitpot.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Calendar;


/**
 * Created by DennisVanBets on 22/08/2017.
 */

public class SplitPotDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "SplitPot.db";

    private static final String SQL_CREATE_TABLE_POT =
            "CREATE TABLE " + SplitPotContract.PotEntry.TABLE_NAME + "(" +
                    SplitPotContract.PotEntry._ID  + " INTEGER PRIMARY KEY," +
                    SplitPotContract.PotEntry.COLUMN_NAME + " TEXT," +
                    SplitPotContract.PotEntry.COLUMN_STARTDATE  + " NUMERIC," +
                    SplitPotContract.PotEntry.COLUMN_ENDDATE + " NUMERIC)";
    private static final String SQL_DROP_TABLE_POT =
            "DROP TABLE IF EXISTS " + SplitPotContract.PotEntry.TABLE_NAME;

    private static final String SQL_CREATE_TABLE_ROUND =
            "CREATE TABLE " + SplitPotContract.RoundEntry.TABLE_NAME + "(" +
                    SplitPotContract.RoundEntry._ID  + " INTEGER PRIMARY KEY," +
                    SplitPotContract.RoundEntry.COLUMN_POT_ID  + " INTEGER," +
                    SplitPotContract.RoundEntry.COLUMN_NAME + " TEXT," +
                    SplitPotContract.RoundEntry.COLUMN_TIMESTAMP + " NUMERIC)";
    private static final String SQL_DROP_TABLE_ROUND =
            "DROP TABLE IF EXISTS " + SplitPotContract.RoundEntry.TABLE_NAME;

    private static final String SQL_CREATE_TABLE_USER =
            "CREATE TABLE " + SplitPotContract.UserEntry.TABLE_NAME + "(" +
                    SplitPotContract.UserEntry._ID  + " INTEGER PRIMARY KEY," +
                    SplitPotContract.UserEntry.COLUMN_FIRSTNAME + " TEXT," +
                    SplitPotContract.UserEntry.COLUMN_LAST_NAME + " TEXT," +
                    SplitPotContract.UserEntry.COLUMN_AVATAR + " INTEGER," +
                    SplitPotContract.UserEntry.COLUMN_BANK_INFO + " TEXT)";

    private static final String SQL_DROP_TABLE_USER =
            "DROP TABLE IF EXISTS " + SplitPotContract.UserEntry.TABLE_NAME;

    private static final String SQL_CREATE_TABLE_PARTICIPANT =
            "CREATE TABLE " + SplitPotContract.ParticipantEntry.TABLE_NAME + "(" +
                    SplitPotContract.ParticipantEntry._ID  + " INTEGER PRIMARY KEY," +
                    SplitPotContract.ParticipantEntry.COLUMN_ROUND_ID + " INTEGER," +
                    SplitPotContract.ParticipantEntry.COLUMN_USER + " INTEGER," +
                    SplitPotContract.ParticipantEntry.COLUMN_AMOUNT + " REAL," +
                    SplitPotContract.ParticipantEntry.COLUMN_ISPAYER + " INTEGER, " +
                    SplitPotContract.ParticipantEntry.COLUMN_HASPAID + " INTEGER)";

    private static final String SQL_DROP_TABLE_PARTICIPANT =
            "DROP TABLE IF EXISTS " + SplitPotContract.ParticipantEntry.TABLE_NAME;


    public SplitPotDbHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_USER);
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_POT);
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_PARTICIPANT);
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_ROUND);
        fillDb(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DROP_TABLE_PARTICIPANT);
        sqLiteDatabase.execSQL(SQL_DROP_TABLE_ROUND);
        sqLiteDatabase.execSQL(SQL_DROP_TABLE_POT);
        sqLiteDatabase.execSQL(SQL_DROP_TABLE_USER);
        onCreate(sqLiteDatabase);
    }

    private void fillDb(SQLiteDatabase db) {
        ContentValues pot1 = new ContentValues();
        pot1.put(SplitPotContract.PotEntry.COLUMN_NAME,"Cafe avond");
        pot1.put(SplitPotContract.PotEntry.COLUMN_STARTDATE, Calendar.getInstance().getTime().getTime());
        long pot1id = db.insert(SplitPotContract.PotEntry.TABLE_NAME,null, pot1);

        ContentValues user1 = new ContentValues();
        user1.put(SplitPotContract.UserEntry.COLUMN_FIRSTNAME,"Bob");
        user1.put(SplitPotContract.UserEntry.COLUMN_LAST_NAME,"De Tester");
        user1.put(SplitPotContract.UserEntry.COLUMN_AVATAR, 1);
        user1.put(SplitPotContract.UserEntry.COLUMN_BANK_INFO, "BE00 0000 0000 0001");
        long userid1= db.insert(SplitPotContract.UserEntry.TABLE_NAME,null,user1);

        ContentValues user2 = new ContentValues();
        user2.put(SplitPotContract.UserEntry.COLUMN_FIRSTNAME,"Jos");
        user2.put(SplitPotContract.UserEntry.COLUMN_LAST_NAME,"Vermeulen");
        user2.put(SplitPotContract.UserEntry.COLUMN_AVATAR, 2);
        user2.put(SplitPotContract.UserEntry.COLUMN_BANK_INFO, "BE00 0000 0000 0002");
        long userid2 = db.insert(SplitPotContract.UserEntry.TABLE_NAME,null,user2);

        ContentValues user3 = new ContentValues();
        user3.put(SplitPotContract.UserEntry.COLUMN_FIRSTNAME,"Pieter");
        user3.put(SplitPotContract.UserEntry.COLUMN_LAST_NAME,"Post");
        user3.put(SplitPotContract.UserEntry.COLUMN_AVATAR, 1);
        user3.put(SplitPotContract.UserEntry.COLUMN_BANK_INFO, "BE00 0000 0000 0003");
        long userid3 = db.insert(SplitPotContract.UserEntry.TABLE_NAME,null,user3);

        ContentValues round1 = new ContentValues();
        round1.put(SplitPotContract.RoundEntry.COLUMN_POT_ID, pot1id);
        round1.put(SplitPotContract.RoundEntry.COLUMN_NAME,"3 bier");
        round1.put(SplitPotContract.RoundEntry.COLUMN_TIMESTAMP, Calendar.getInstance().getTime().getTime());
        long round1id = db.insert(SplitPotContract.RoundEntry.TABLE_NAME,null,round1);

        ContentValues round2 = new ContentValues();
        round2.put(SplitPotContract.RoundEntry.COLUMN_POT_ID, pot1id);
        round2.put(SplitPotContract.RoundEntry.COLUMN_NAME,"2 cocktails");
        round2.put(SplitPotContract.RoundEntry.COLUMN_TIMESTAMP, Calendar.getInstance().getTime().getTime() - 10000);
        long round2id = db.insert(SplitPotContract.RoundEntry.TABLE_NAME,null,round2);

        ContentValues par1 = new ContentValues();
        par1.put(SplitPotContract.ParticipantEntry.COLUMN_ROUND_ID,round1id);
        par1.put(SplitPotContract.ParticipantEntry.COLUMN_USER,userid1);
        par1.put(SplitPotContract.ParticipantEntry.COLUMN_AMOUNT, 2.25);
        par1.put(SplitPotContract.ParticipantEntry.COLUMN_ISPAYER, false);
        par1.put(SplitPotContract.ParticipantEntry.COLUMN_HASPAID, false);
        long par1id = db.insert(SplitPotContract.ParticipantEntry.TABLE_NAME,null,par1);

        ContentValues par2 = new ContentValues();
        par2.put(SplitPotContract.ParticipantEntry.COLUMN_ROUND_ID,round1id);
        par2.put(SplitPotContract.ParticipantEntry.COLUMN_USER,userid2);
        par2.put(SplitPotContract.ParticipantEntry.COLUMN_AMOUNT, 2.25);
        par2.put(SplitPotContract.ParticipantEntry.COLUMN_ISPAYER, false);
        par2.put(SplitPotContract.ParticipantEntry.COLUMN_HASPAID, true);
        long par2id = db.insert(SplitPotContract.ParticipantEntry.TABLE_NAME,null,par2);

        ContentValues par3 = new ContentValues();
        par3.put(SplitPotContract.ParticipantEntry.COLUMN_ROUND_ID,round1id);
        par3.put(SplitPotContract.ParticipantEntry.COLUMN_USER,userid3);
        par3.put(SplitPotContract.ParticipantEntry.COLUMN_AMOUNT, 2.25);
        par3.put(SplitPotContract.ParticipantEntry.COLUMN_ISPAYER, true);
        par3.put(SplitPotContract.ParticipantEntry.COLUMN_HASPAID, false);
        long par3id = db.insert(SplitPotContract.ParticipantEntry.TABLE_NAME,null,par3);

        ContentValues par4 = new ContentValues();
        par4.put(SplitPotContract.ParticipantEntry.COLUMN_ROUND_ID,round2id);
        par4.put(SplitPotContract.ParticipantEntry.COLUMN_USER,userid1);
        par4.put(SplitPotContract.ParticipantEntry.COLUMN_AMOUNT, 8.50);
        par4.put(SplitPotContract.ParticipantEntry.COLUMN_ISPAYER, true);
        par4.put(SplitPotContract.ParticipantEntry.COLUMN_HASPAID, false);
        long par4id = db.insert(SplitPotContract.ParticipantEntry.TABLE_NAME,null,par4);

        ContentValues par5 = new ContentValues();
        par5.put(SplitPotContract.ParticipantEntry.COLUMN_ROUND_ID,round2id);
        par5.put(SplitPotContract.ParticipantEntry.COLUMN_USER,userid2);
        par5.put(SplitPotContract.ParticipantEntry.COLUMN_AMOUNT, 11.25);
        par5.put(SplitPotContract.ParticipantEntry.COLUMN_ISPAYER, false);
        par5.put(SplitPotContract.ParticipantEntry.COLUMN_HASPAID, true);
        long par5id = db.insert(SplitPotContract.ParticipantEntry.TABLE_NAME,null,par5);
    }
}
