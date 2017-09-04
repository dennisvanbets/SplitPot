package com.splitpot.dennisvanbets.splitpot.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.splitpot.dennisvanbets.splitpot.R;
import com.splitpot.dennisvanbets.splitpot.model.User;

import java.util.List;

/**
 * Created by DennisVanBets on 30/08/2017.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private List<User> users;

    public UserAdapter(List<User> users) {
        this.users = users;
    }

    @Override
    public UserAdapter.UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.user_list_item, parent, false);
        return new UserViewHolder(v);
    }

    @Override
    public void onBindViewHolder(UserAdapter.UserViewHolder holder, int position) {
        holder.firstname.setText(users.get(position).getFirstName());
        holder.lastname.setText(users.get(position).getLastName());
        holder.desc.setText("info about pots here");
        holder.avatar.setColorFilter(R.color.colorAccent);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView firstname;
        TextView lastname;
        TextView desc;

        ImageView avatar;
        public UserViewHolder(View itemView) {
            super(itemView);
            firstname = itemView.findViewById(R.id.user_firstname);
            lastname = itemView.findViewById(R.id.user_lastname);
            desc = itemView.findViewById(R.id.user_desc);
            avatar = itemView.findViewById(R.id.user_avatar);
        }
    }
}
