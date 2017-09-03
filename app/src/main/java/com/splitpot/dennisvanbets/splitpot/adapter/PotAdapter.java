package com.splitpot.dennisvanbets.splitpot.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.splitpot.dennisvanbets.splitpot.R;
import com.splitpot.dennisvanbets.splitpot.model.Pot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by DennisVanBets on 25/08/2017.
 */

public class PotAdapter extends RecyclerView.Adapter<PotAdapter.PotViewHolder> {
    private List<Pot> pots;
    private RecyclerView.LayoutManager layoutManager;

    public PotAdapter(List<Pot> pots, Context context) {
        this.pots = pots;
        this.layoutManager = new LinearLayoutManager(context);
    }

    @Override
    public PotViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.pot_list_item, parent, false);
        PotViewHolder pvh = new PotViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PotViewHolder holder, int position) {
        Pot p = pots.get(position);
        holder.potName.setText(p.getName());
        holder.progressPaid.setMax((int) p.getPotTotal() * 100);
        holder.progressPaid.setProgress((int) p.getPotTotal() * 100);
        SimpleDateFormat format = new SimpleDateFormat("EEE, dd-MM-yyyy HH:mm");
        String endDate = p.getEndDateString(format);
        String startDate = p.getStartDateString(format);
        holder.dates.setText(String.format("%s - %s", startDate, endDate));
        holder.potValue.setText(String.format("%.2f", p.getPotTotal()));

        holder.participantsLayout.setLayoutManager(layoutManager);
        List<String> participants = new ArrayList<>();
        List<Long> userIds = new ArrayList<>();
        List<Double> debts = new ArrayList<>();
        List<Integer> roundcounts = new ArrayList<>();

        participants.addAll(p.getUserNames());
        userIds.addAll(p.getUserIds());
        for (long id : userIds) {
            debts.add(p.getTotalDebtForUser(id));
            roundcounts.add(p.getTotalRoundCountsForUser(id));
        }
        ParticipantAdapter participantAdapter = new ParticipantAdapter(participants, userIds, debts, roundcounts);
        holder.participantsLayout.setAdapter(participantAdapter);
        holder.participantsLayout.setVisibility(View.GONE);
        holder.showMoreLessParticipants.setText(String.format("Show more (%d)",participants.size()));
    }

    @Override
    public int getItemCount() {
        return pots.size();
    }

    public static class PotViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView potName;
        TextView dates;
        TextView potValue;
        TextView showMoreLessParticipants;
        ProgressBar progressPaid;
        RecyclerView participantsLayout;


        public PotViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.potCard);
            potName = (TextView) itemView.findViewById(R.id.potNamePlaceholder);
            dates = (TextView) itemView.findViewById(R.id.dates);
            progressPaid = (ProgressBar) itemView.findViewById(R.id.progressPaid);
            participantsLayout = (RecyclerView) itemView.findViewById(R.id.potParticipantsLayout);
            potValue = (TextView) itemView.findViewById(R.id.potValue);
            showMoreLessParticipants = (TextView) itemView.findViewById(R.id.showMoreParticipantsLabel);

            itemView.findViewById(R.id.participantLabelLayout).setOnClickListener(v -> {
                int viewState = participantsLayout.getVisibility();
                if (viewState == View.GONE) {
                    ((ImageView) itemView.findViewById(R.id.participantLabelExpanderView)).setImageResource(R.drawable.ic_show_less);
                    participantsLayout.setVisibility(View.VISIBLE);
                    showMoreLessParticipants.setVisibility(View.GONE);
                } else {
                    participantsLayout.setVisibility(View.GONE);
                    ((ImageView) itemView.findViewById(R.id.participantLabelExpanderView)).setImageResource(R.drawable.ic_show_more);
                    showMoreLessParticipants.setVisibility(View.VISIBLE);
                }
            });
        }
    }
}
