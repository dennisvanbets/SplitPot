package com.splitpot.dennisvanbets.splitpot.ui.user;

import android.content.Context;
import android.os.Bundle;


import android.app.Fragment;
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
    @Inject
    SplitPotDaoSQLite db;
    private RecyclerView userListRecView;
    private RecyclerView.LayoutManager manager;
    private RecyclerView.Adapter adapter;

    @Override
    public void onAttach(Context context) {
        AndroidInjection.inject(this);
        super.onAttach(context);
    }

    public static UserListFragment newInstance(){
        return new UserListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.pot_list_fragment, container, false);

        userListRecView = (RecyclerView) rootView.findViewById(R.id.user_list_recycler_view);
        userListRecView.setHasFixedSize(true);

        manager = new LinearLayoutManager(getActivity());
        userListRecView.setLayoutManager(manager);


        adapter = new UserAdapter(new ArrayList<User>(db.getAllUsers()));
        userListRecView.setAdapter(adapter);

        return rootView;
    }
}
