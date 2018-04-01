package com.av.mygames.mygames.addGame;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.av.mygames.mygames.R;
import com.av.mygames.mygames.model.MyGamesModel;

public class AddGameActivity extends AppCompatActivity implements IAddGameView{
    public static final String GAME_NAME = "gameName";
    private ListView game_list;
    private TextView not_found_text;
    private ProgressBar progress_bar;
    private AddGamePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_game);

        game_list = findViewById(R.id.game_list);
        not_found_text = findViewById(R.id.not_found_text);
        progress_bar = findViewById(R.id.progress_bar);
        game_list.setEmptyView(not_found_text);

        presenter = new AddGamePresenter(this, MyGamesModel.getInstance(this));

        Intent intent = getIntent();
        String gameName = intent.getStringExtra(GAME_NAME);

        presenter.setSearchedGameName(gameName);

    }

    @Override
    public void showTitle(String gameName) {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(gameName);
    }

    @Override
    public void showSearchInProgress() {
        game_list.setVisibility(View.GONE);
        not_found_text.setVisibility(View.GONE);
        progress_bar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideSearchInProgress() {
        
    }

    @Override
    public void showError(String message) {

    }


}
