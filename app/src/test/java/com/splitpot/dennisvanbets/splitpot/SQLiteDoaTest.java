package com.splitpot.dennisvanbets.splitpot;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.splitpot.dennisvanbets.splitpot.dao.SplitPotContract;
import com.splitpot.dennisvanbets.splitpot.dao.SplitPotDao;
import com.splitpot.dennisvanbets.splitpot.dao.SplitPotDaoSQLite;
import com.splitpot.dennisvanbets.splitpot.dao.SplitPotDbHelper;
import com.splitpot.dennisvanbets.splitpot.model.Pot;
import com.splitpot.dennisvanbets.splitpot.model.Round;
import com.splitpot.dennisvanbets.splitpot.model.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by DennisVanBets on 23/08/2017.
 */

@Ignore
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class,sdk = 18,packageName = "com.splitpot.dennisvanbets.splitpot")
public class SQLiteDoaTest {
    private SplitPotDao db;

    @Before
    public void init(){
        this.db = SplitPotDaoSQLite.getInstance(RuntimeEnvironment.application);
    }
    @After
    public void tearDown() {
        db.onUpgrade(1,1);
    }

    @Test
    public void getAllUsers(){
        Set<User> users = db.getAllUsers();
        assertEquals(users.size(),3);
        for (User u :
                users) {
            assertNotNull(u.getFirstName());
            assertNotNull(u.getLastName());
            assertNotNull(u.getAvatarRes());
            assertNotNull(u.getId());
            assertNotNull(u.getBankInfo());
        }
    }

    @Test
    public void getAllRounds(){
        List<Round> rounds = db.getAllRounds();
        assertEquals(rounds.size(),2);
        for (Round r : rounds) {
            assertNotNull(r.getId());
            assertNotNull(r.getPotId());
            assertNotNull(r.getTimestamp());
            assertNotNull(r.getName());
            assertTrue(r.getParticipants().size()>0);
        }
    }

    @Test
    public void getAllPots(){
        List<Pot> pots = db.getAllPots();
        assertEquals(pots.size(),1);
        for (Pot pot :
                pots) {
            assertNotNull(pot.getId());
            assertNotNull(pot.getName());
            assertEquals(pot.getRounds().size(),2);
            assertNotNull(pot.getStartDate());
            assertNull(pot.getEndDate());
        }
    }

}
