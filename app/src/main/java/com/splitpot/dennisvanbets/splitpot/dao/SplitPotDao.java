package com.splitpot.dennisvanbets.splitpot.dao;

import com.splitpot.dennisvanbets.splitpot.dao.di.DbModule;
import com.splitpot.dennisvanbets.splitpot.model.Participant;
import com.splitpot.dennisvanbets.splitpot.model.Pot;
import com.splitpot.dennisvanbets.splitpot.model.Round;
import com.splitpot.dennisvanbets.splitpot.model.User;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by DennisVanBets on 23/08/2017.
 */
public interface SplitPotDao {
    void onUpgrade(int i, int j);

    void close();

    Set<User> getAllUsers();
    User getUser(long id);
    User insertUser(String firstname, String lastname, int avaterRes);
    User insertUser(String firstname, String lastname, int avaterRes, String bankInfo);
    boolean updateUser(long id, String firstname, String lastname, int avaterRes, String bankInfo);
    boolean deleteUser(long id);

    Set<Participant> getParticipantsOfRound(long roundid);
    Participant getParticipant(String id);
    Participant insertParticipant(long roundid, long userid, double debt, boolean isPayer, boolean hasPaid);
    boolean updateParticipant(long participantid, double debt, boolean isPayer, boolean hasPaid);
    boolean deleteParticipant(long paricipantid);

    List<Round> getAllRounds();
    Round getRound(long id);
    Set<Round> getAllRoundsOfPot(long potId);
    Round insertRound(long potId, String name, Date timestamp);
    boolean updateRound(long roundid, String name);
    boolean deleteRound(long roundid);

    Pot insertPot(String name);
    boolean updatePot(long id, String name);
    boolean deletePot(long id);
    List<Pot> getAllPots();
}
