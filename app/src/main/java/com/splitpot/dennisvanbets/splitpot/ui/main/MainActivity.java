package com.splitpot.dennisvanbets.splitpot.ui.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.splitpot.dennisvanbets.splitpot.R;
import com.splitpot.dennisvanbets.splitpot.adapter.PotAdapter;
import com.splitpot.dennisvanbets.splitpot.dao.SplitPotDao;
import com.splitpot.dennisvanbets.splitpot.dao.SplitPotDaoSQLite;
import com.splitpot.dennisvanbets.splitpot.model.Pot;


import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class MainActivity extends AppCompatActivity{
    @Inject SplitPotDao db;
    private LinearLayoutManager llm;
    private List<Pot> potList;

    private RecyclerView potListRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        initDb();
        instantiateViews();
        createPotList();
    }

    private void initDb() {
        db = new SplitPotDaoSQLite(this.getApplicationContext());
        potList = db.getAllPots();
    }

    private void instantiateViews() {
        potListRecyclerView = (RecyclerView) findViewById(R.id.potlistRecyclerView);
        llm = new LinearLayoutManager(this);
        potListRecyclerView.setLayoutManager(llm);
    }

    private void createPotList() {
        PotAdapter potAdapter = new PotAdapter(potList, this);
        potListRecyclerView.setAdapter(potAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onDestroy() {
        db.close();
        super.onDestroy();
    }
}
