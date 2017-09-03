package com.splitpot.dennisvanbets.splitpot.ui.pot;

import android.app.Activity;


import android.content.Context;
import android.os.Bundle;


import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.splitpot.dennisvanbets.splitpot.R;
import com.splitpot.dennisvanbets.splitpot.adapter.PotAdapter;
import com.splitpot.dennisvanbets.splitpot.dao.SplitPotDao;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

/**
 * Created by DennisVanBets on 30/08/2017.
 */

public class PotListFragment extends Fragment {
    @Inject
    SplitPotDao db;
    private LinearLayoutManager llm;
    private RecyclerView potListRecyclerView;

    @Override
    public void onAttach(Context context) {
        AndroidInjection.inject(this);
        super.onAttach(context);
    }


    public static PotListFragment newInstance() {
        return new PotListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.pot_list_fragment, container, false);

        potListRecyclerView = (RecyclerView) container.findViewById(R.id.potlistRecyclerView);
        potListRecyclerView.setHasFixedSize(true);
        llm = new LinearLayoutManager(getActivity());
        potListRecyclerView.setLayoutManager(llm);

        PotAdapter potAdapter = new PotAdapter(db.getAllPots(), getActivity());
        potListRecyclerView.setAdapter(potAdapter);

        return rootView;
    }
}
