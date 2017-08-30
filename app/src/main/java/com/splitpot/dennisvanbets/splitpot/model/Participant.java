package com.splitpot.dennisvanbets.splitpot.model;

import java.util.Objects;

/**
 * Created by DennisVanBets on 23/08/2017.
 */

public class Participant {
    private long id;
    private long roundId;
    private User user;
    private double amount;
    private boolean isPayer;
    private boolean hasPaid;

    public Participant(long id, long roundId, User user, double amount, boolean isPayer, boolean hasPaid) {
        this.id = id;
        this.roundId = roundId;
        this.user = user;
        this.amount = amount;
        this.isPayer = isPayer;
        this.hasPaid = hasPaid;
    }

    public long getId() {
        return id;
    }

    public long getRoundId() {
        return roundId;
    }

    public User getUser() {
        return user;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isHasPaid() {
        return hasPaid;
    }

    public boolean isPayer() {
        return isPayer;
    }

    public void setPayer(boolean payer) {
        isPayer = payer;
    }

    public boolean hasPaid() {
        return hasPaid;
    }

    public void setHasPaid(boolean hasPaid) {
        this.hasPaid = hasPaid;
    }
}
