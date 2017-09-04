package com.splitpot.dennisvanbets.splitpot.ui.pot;

import android.app.Activity;


import android.content.Context;
import android.os.Bundle;


import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.splitpot.dennisvanbets.splitpot.R;
import com.splitpot.dennisvanbets.splitpot.adapter.PotAdapter;
import com.splitpot.dennisvanbets.splitpot.dao.SplitPotDao;
import com.splitpot.dennisvanbets.splitpot.dao.SplitPotDaoSQLite;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

/**
 * Created by DennisVanBets on 30/08/2017.
 */

public class PotListFragment extends Fragment {
    private SplitPotDao db;
    private LinearLayoutManager llm;
    private RecyclerView potListRecyclerView;

    public PotListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.pot_list_fragment, container, false);

        potListRecyclerView = (RecyclerView) rootView.findViewById(R.id.potlistRecyclerView);
        llm = new LinearLayoutManager(getActivity());
        potListRecyclerView.setLayoutManager(llm);

        db = SplitPotDaoSQLite.getInstance(getContext());
        PotAdapter potAdapter = new PotAdapter(db.getAllPots(), getActivity());
        potListRecyclerView.setAdapter(potAdapter);

        return rootView;
    }
}
