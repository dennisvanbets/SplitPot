package com.splitpot.dennisvanbets.splitpot.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.splitpot.dennisvanbets.splitpot.R;

import java.util.List;

/**
 * Created by DennisVanBets on 27/08/2017.
 */

public class ParticipantAdapter extends RecyclerView.Adapter<ParticipantAdapter.ParticipantViewHolder> {
    private List<Long> userIds;
    private List<String> participantNames;
    private List<Double> debts;
    private List<Integer> roundCounts;

    public ParticipantAdapter(List<String> participantNames, List<Long> userIds, List<Double> debts, List<Integer> roundCounts) {
        this.participantNames = participantNames;
        this.userIds = userIds;
        this.debts = debts;
        this.roundCounts = roundCounts;
    }

    @Override
    public ParticipantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.participant_pot_list_item, parent, false);
        return new ParticipantViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ParticipantViewHolder holder, int position) {
        holder.participantName.setText(participantNames.get(position));
        holder.potBalance.setText(String.format("%.2f", debts.get(position)));
        holder.roundCount.setText(String.format("Part of %d rounds", roundCounts.get(position)));
        //if (p.hasPaid()) holder.hasPaid.setColorFilter(R.color.successGreen, PorterDuff.Mode.MULTIPLY);
        //else holder.hasPaid.clearColorFilter();
        //todo
    }


    @Override
    public int getItemCount() {
        return participantNames.size();
    }

    public static class ParticipantViewHolder extends RecyclerView.ViewHolder {
        ImageView avatar;
        TextView participantName;
        TextView potBalance;
        TextView roundCount;
        ImageView hasPaid;

        public ParticipantViewHolder(View itemView) {
            super(itemView);
            avatar = (ImageView) itemView.findViewById(R.id.participantAvatar);
            participantName = (TextView) itemView.findViewById(R.id.participantName);
            potBalance = (TextView) itemView.findViewById(R.id.potBalance);
            roundCount = (TextView) itemView.findViewById(R.id.roundCount);
            hasPaid = (ImageView) itemView.findViewById(R.id.hasPaid);
        }
    }
}
