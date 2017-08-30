package com.splitpot.dennisvanbets.splitpot;

import com.splitpot.dennisvanbets.splitpot.model.Participant;
import com.splitpot.dennisvanbets.splitpot.model.Round;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class RoundTest {
    private Round round;
    private Participant user1;
    private Participant user2;
    private Participant user3;

    @Before
    public void init(){
        user1 = mock(Participant.class);
        user2 = mock(Participant.class);
        user3 = mock(Participant.class);

       Set<Participant> participants = new HashSet<>(3);
        participants.add(user1);
        participants.add(user2);
        participants.add(user3);


        round = new Round(1,1,"round1",participants, Calendar.getInstance().getTime());
    }
    @Test
    public void calcTotalIsCorrect(){
        when(user1.getAmount()).thenReturn(5.55);
        when(user2.getAmount()).thenReturn(4.45);
        when(user3.getAmount()).thenReturn(7.25);
        assertEquals(round.getTotalRoundCost(), 17.25, .001);

        when(user1.isPayer()).thenReturn(false);
        when(user2.isPayer()).thenReturn(false);
        when(user3.isPayer()).thenReturn(false);
        assertEquals(round.isFinished(), false);

        when(user1.isPayer()).thenReturn(true);
        assertEquals(round.isFinished(), false);
        assertEquals(round.getTotalRoundCost(),17.25, .001);

        when(user3.getId()).thenReturn(1L);
        round.removeParticipant(user3);
        assertEquals(round.getUserCount(), 2);
        assertEquals(round.getTotalRoundCost(),10.00, .001);

        Participant user4 = mock(Participant.class);
        when(user4.getAmount()).thenReturn(5.52);
        round.addParticipant(user4);
        assertEquals(round.getTotalRoundCost(),15.52, .001);

        when(user1.hasPaid()).thenReturn(true);
        when(user1.isPayer()).thenReturn(false);
        when(user2.hasPaid()).thenReturn(true);
        when(user2.isPayer()).thenReturn(false);
        when(user4.isPayer()).thenReturn(true);
        assertEquals(round.isFinished(), true);
    }
}