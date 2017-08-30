package com.splitpot.dennisvanbets.splitpot.model;

/**
 * Created by DennisVanBets on 22/08/2017.
 */

public class User {
    private long id;
    private String firstName;
    private String lastName;
    private int avatarRes;
    private String bankInfo;

    public User(long id, String firstName, String lastName, int avatarRes, String bankId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatarRes = avatarRes;
        this.bankInfo = bankId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (!User.class.isAssignableFrom(o.getClass())) {
            return false;
        }
        final User other = (User) o;
        return (this.getId() == 0) ? other.getId() == 0 : this.getId() == other.getId();

    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAvatarRes() {
        return avatarRes;
    }

    public String getBankInfo() {
        return bankInfo;
    }

    public long getId() {
        return id;
    }
}
