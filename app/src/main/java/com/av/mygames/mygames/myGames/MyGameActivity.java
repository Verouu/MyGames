package com.av.mygames.mygames.myGames;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.av.mygames.mygames.R;
import com.av.mygames.mygames.addGame.AddGameActivity;

import java.util.List;


public class MyGameActivity extends AppCompatActivity implements IMyGamesView, AskGameNameDialog.IGameNameListener{
    private MyGamesPresenter presenter;
    private TextView noNames;
    private ListView namesList;

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onViewStarted();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_game);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AskGameNameDialog askGameNameDialog = new AskGameNameDialog();
                askGameNameDialog.show(getFragmentManager(), "AskGameName");
            }
        });

        namesList = findViewById(R.id.listGames);
        noNames = findViewById(R.id.noGames);
        presenter = new MyGamesPresenter(this);

        namesList.setEmptyView(noNames);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_game, menu);
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
    public void onNameInput(String name) {
        presenter.onAddGameRequested(name);
    }

    @Override
    public void switchToAddGame(String name) {
        Intent intent = new Intent(this, AddGameActivity.class);
        intent.putExtra(AddGameActivity.GAME_NAME, name);
        startActivity(intent);
    }

    @Override
    public void displayNames(List<String> itemNames) {
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemNames);
        namesList.setAdapter(adapter);
    }
}
