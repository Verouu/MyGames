package com.av.mygames.mygames.addGame;

import android.content.Intent;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.av.mygames.mygames.R;
import com.av.mygames.mygames.model.MyGamesModel;
import com.av.mygames.mygames.myGames.MyGameActivity;

import java.util.List;

public class AddGameActivity extends AppCompatActivity implements IAddGameView, AskGameConfirmationDialog.IConfirmedListener{
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

        game_list.setVisibility(View.VISIBLE);
        not_found_text.setVisibility(View.VISIBLE);
        progress_bar.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        Snackbar.make(findViewById(R.id.base_layout),message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void displayNames(List<String> names) {
        ListAdapter adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, names);
        game_list.setAdapter(adapter);
        game_list.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                presenter.onAddGameRequested(position);
            }
        });
    }

    @Override
    public void askGameInsertionConfirmation(String name, String summary) {
        AskGameConfirmationDialog askGameConfirmationDialog = AskGameConfirmationDialog.newInstance(name,summary);
        askGameConfirmationDialog.show(getFragmentManager(), "askGameConfirmationDialog");
    }

    @Override
    public void switchToMyGames() {
        Intent intent = new Intent(this, MyGameActivity.class);
        startActivity(intent);
    }


    @Override
    public void onActionConfirmed() {
        presenter.onAddGameConfirmed();
    }
}
