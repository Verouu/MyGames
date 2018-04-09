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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.av.mygames.mygames.R;
import com.av.mygames.mygames.addGame.AddGameActivity;

import java.util.List;


public class MyGameActivity extends AppCompatActivity implements IMyGamesView, AskGameNameDialog.IGameNameListener{

    public static final int DISPLAY_GAMES = 1;
    public static final int DISPLAY_PLATFORMS = 2;
    public static final int DISPLAY_GENRES = 3;

    private MyGamesPresenter presenter;
    private TextView noNames;
    private ListView namesList;

    private int displayMode;

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

        if (savedInstanceState != null) {
            displayMode = savedInstanceState.getInt("DISPLAY_MODE", DISPLAY_GAMES);
        }else{
            displayMode = DISPLAY_GAMES;
        }
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
        if (id == R.id.action_games) {
            presenter.onDisplayModeSelected(DISPLAY_GAMES);
            return true;
        }else if (id == R.id.action_platforms){
            presenter.onDisplayModeSelected(DISPLAY_PLATFORMS);
            return true;
        }else if (id == R.id.action_genres){
            presenter.onDisplayModeSelected(DISPLAY_GENRES);
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

        namesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                presenter.onShowNameRequested(position);
            }
        });
    }

    @Override
    public int getDisplay() {
        return displayMode;
    }

    @Override
    public void setDisplay(int displayGamesMode) {
        displayMode = displayGamesMode;
        switch (displayMode){
            case DISPLAY_GAMES:
                noNames.setText("No games");
                break;
            case DISPLAY_GENRES:
                noNames.setText("No genres");
                break;
            case DISPLAY_PLATFORMS:
                noNames.setText("No platforms");
                break;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("DISPLAY_MODE", displayMode);
    }
}
