package com.splitpot.dennisvanbets.splitpot.ui.user;

import android.content.Context;
import android.os.Bundle;


import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.splitpot.dennisvanbets.splitpot.R;
import com.splitpot.dennisvanbets.splitpot.adapter.UserAdapter;
import com.splitpot.dennisvanbets.splitpot.dao.SplitPotDaoSQLite;
import com.splitpot.dennisvanbets.splitpot.model.User;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.android.AndroidInjection;


/**
 * Created by DennisVanBets on 30/08/2017.
 */

public class UserListFragment extends Fragment {
    private SplitPotDaoSQLite db;
    private RecyclerView userListRecView;
    private RecyclerView.LayoutManager manager;
    private RecyclerView.Adapter adapter;

    public UserListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.user_list_fragment, container, false);

        userListRecView = (RecyclerView) rootView.findViewById(R.id.user_list_recycler_view);

        manager = new LinearLayoutManager(getActivity());
        userListRecView.setLayoutManager(manager);

        db = SplitPotDaoSQLite.getInstance(getContext());
        adapter = new UserAdapter(new ArrayList<User>(db.getAllUsers()));
        userListRecView.setAdapter(adapter);

        return rootView;
    }
}
