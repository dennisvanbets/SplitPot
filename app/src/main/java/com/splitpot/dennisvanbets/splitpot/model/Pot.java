package com.splitpot.dennisvanbets.splitpot.model;

import android.util.Log;

import com.splitpot.dennisvanbets.splitpot.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by DennisVanBets on 22/08/2017.
 */

public class Pot {
    private long id;
    private Set<Round> rounds;
    private String name;
    private Date startDate;
    private Date endDate;

    public Pot(long id, String name, Set<Round> rounds, Date startDate) {
        this.id = id;
        this.name = name;
        this.rounds = rounds;
        this.startDate = startDate;
    }

    public Pot(long id, Set<Round> rounds, String name, Date startDate, Date endDate) {
        this.id = id;
        this.rounds = rounds;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public double getPotTotal() {
        double total = 0.00;
        for (Round round : rounds) {
            total += round.getTotalRoundCost();
        }
        return total;
    }

    public double getPotPaidTotal() {
        double total = 0.00;
        for (Round r: rounds) {
            total += r.getTotalRoundPaid();
        }
        return total;
    }

    public boolean isFinished(){
        boolean done = true;
        for (Round r: rounds) {
            if (!r.isFinished()){
                done = false;
            }
        }
        return done;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Set<Round> getRounds() {
        return rounds;
    }

    public String getName() {
        return name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public long getId() {
        return id;
    }

    public String getEndDateString(SimpleDateFormat format) {
        String s;
        if (this.endDate == null) return "Still ongoing";
        else return format.format(endDate);
    }

    public String getStartDateString(SimpleDateFormat format) {
        return format.format(startDate);
    }

    public List<Participant> getAllParticipants() {
        List<Participant> allParticipants = new ArrayList<>();
        for (Round r: rounds){
            allParticipants.addAll(r.getParticipants());
        }
        return allParticipants;
    }

    public double getTotalDebtForUser(long userid) {
        double total = 0.00;

        for (Round r: rounds){
            for (Participant p: r.getParticipants()){
                boolean found = false;
                if (p.getUser().getId() == userid){
                    total += p.getAmount();
                    found = true;
                }
                if (found) break;
            }
        }

        return total;
    }

    public int getTotalRoundCountsForUser(long userid) {
        int rc = 0;
        for (Round r: rounds){
            for (Participant p: r.getParticipants()){
                boolean found = false;
                if (p.getUser().getId() == userid){
                    rc += 1;
                    found = true;
                }
                if (found) break;
            }
        }

        return rc;
    }

    public List<String> getUserNames() {
        List<String> names = new ArrayList<>();

        for (Round r: rounds){
            for (Participant p: r.getParticipants()){
                String name = p.getUser().getFirstName() + " " + p.getUser().getLastName();
                if (!names.contains(name)){
                    names.add(name);
                }
            }
        }

        return names;
    }

    public List<Long> getUserIds() {
        List<Long> userIds = new ArrayList<>();
        for (Round r: rounds){
            for (Participant p: r.getParticipants()){
                long userid = p.getUser().getId();
                if (!userIds.contains(userid)){
                    userIds.add(userid);
                }
            }
        }
        return userIds;
    }
}
