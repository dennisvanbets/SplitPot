package com.splitpot.dennisvanbets.splitpot.model;

import java.util.Date;

import java.util.Set;


/**
 * Created by DennisVanBets on 22/08/2017.
 */

public class Round {
    private long id;
    private long potId;
    private String name;
    private Set<Participant> participants;
    private Date timestamp;

    public Round(long id, long potId,String name, Set<Participant> participants, Date timestamp) {
        this.id = id;
        this.potId = potId;
        this.name = name;
        this.participants = participants;
        this.timestamp = timestamp;
    }

    public double getTotalRoundCost(){
        double total = 0.00;
        for (Participant participant: participants) {
            total += participant.getAmount();
        }
        return total;
    }

    public double getTotalRoundPaid(){
        double total = 0.00;
        for (Participant p: participants) {
            if (p.hasPaid()){
                total += p.getAmount();
            }
        }
        return total;
    }

    public long getPotId() {
        return potId;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public Set<Participant> getParticipants() {
        return participants;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFinished(){
        //streams only available in api 24
        //return participants.stream().filter(Participant::isPayer).findFirst().isPresent();
        boolean done = true;
        for (Participant p: participants) {
            if (!p.isPayer() && !p.hasPaid()){
                done = false;
            }
        }
        return done;
    }

    public void setParticipants(Set<Participant> participants) {
        this.participants = participants;
    }

    public void addParticipant(Participant par){
        participants.add(par);
    }

    public boolean removeParticipant(Participant participant){
        return participants.remove(participant);
    }

    public int getUserCount(){
        return participants.size();
    }

    public long getId() {
        return id;
    }
}
