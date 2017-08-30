package com.splitpot.dennisvanbets.splitpot.dao;

import android.provider.BaseColumns;


/**
 * Created by DennisVanBets on 22/08/2017.
 */

public final class SplitPotContract {
    private SplitPotContract(){

    }
    public static class PotEntry implements BaseColumns{
        public static final String TABLE_NAME = "POT";
        public static final String COLUMN_NAME = "NAME";
        public static final String COLUMN_STARTDATE = "STARTDATE";
        public static final String COLUMN_ENDDATE = "ENDDATE";

        public static final String[] ALL_COLUMNS = {
                _ID,
                COLUMN_NAME,
                COLUMN_STARTDATE,
                COLUMN_ENDDATE
        };
    }

    public static class RoundEntry implements BaseColumns{
        public static final String TABLE_NAME = "ROUND";
        public static final String COLUMN_POT_ID = "POT_ID";
        public static final String COLUMN_NAME = "NAME";
        public static final String COLUMN_TIMESTAMP = "TIMESTAMP";

        public static final String[] ALL_COLUMNS = {
                _ID,
                COLUMN_POT_ID,
                COLUMN_NAME,
                COLUMN_TIMESTAMP
        };
    }

    public static class ParticipantEntry implements BaseColumns{
        public static final String TABLE_NAME = "PARTICIPANT";
        public static final String COLUMN_ROUND_ID = "ROUND_ID";
        public static final String COLUMN_USER = "USER";
        public static final String COLUMN_AMOUNT = "AMOUNT";
        public static final String COLUMN_ISPAYER = "ISPAYER";
        public static final String COLUMN_HASPAID = "HASPAID";

            public static final String[] ALL_COLUMNS = {
                    _ID,
                    COLUMN_ROUND_ID,
                    COLUMN_USER,
                    COLUMN_AMOUNT,
                    COLUMN_ISPAYER,
                    COLUMN_HASPAID
            };
    }

    public static class UserEntry implements BaseColumns{
        public static final String TABLE_NAME = "USER";
        public static final String COLUMN_FIRSTNAME = "FIRST_NAME";
        public static final String COLUMN_LAST_NAME = "LAST_NAME";
        public static final String COLUMN_AVATAR = "AVATAR";
        public static final String COLUMN_BANK_INFO = "BANK_INFO";

        public static final String[] ALL_COLUMNS = {
                _ID,
                COLUMN_FIRSTNAME,
                COLUMN_LAST_NAME,
                COLUMN_AVATAR,
                COLUMN_BANK_INFO
        };
    }

}
