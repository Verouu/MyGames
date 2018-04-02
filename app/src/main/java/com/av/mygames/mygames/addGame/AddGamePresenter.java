package com.av.mygames.mygames.addGame;

import com.av.mygames.mygames.model.AllGameData;
import com.av.mygames.mygames.model.GameData;
import com.av.mygames.mygames.model.MyGamesModel;
import com.av.mygames.mygames.model.inetserver.ResponseReceiver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Verou on 1/4/18.
 */

public class AddGamePresenter {
    IAddGameView gameView;
    MyGamesModel model;
    int requestedIndex;
    List<GameData> games = new ArrayList<>();

    public AddGamePresenter(IAddGameView gameView, MyGamesModel model) {
        this.gameView = gameView;
        this.model = model;
    }

    public void setSearchedGameName(final String gameName) {
        gameView.showTitle(gameName);
        gameView.showSearchInProgress();
        model.findGames(gameName, new ResponseReceiver<List<AllGameData>>() {
            @Override
            public void onResponseReceived(List<AllGameData> response) {
                gameView.hideSearchInProgress();
                gamesFound(response);
            }

            @Override
            public void onErrorReceived(String message) {
                gameView.hideSearchInProgress();
                gameView.showError(message);
            }
        });
    }

    private void gamesFound(List<AllGameData> response) {
        List<String> games = new ArrayList<>();
        for (AllGameData gameData : response) {
            games.add(gameData.getGame().getName());
            this.games.add(gameData.getGame());
        }
        gameView.displayNames(games);

    }

    public void onAddGameRequested(int position) {
        requestedIndex = position;
        GameData gameData = games.get(requestedIndex);
        gameView.askGameInsertionConfirmation(gameData.getName(), gameData.getSummary());
    }


    public void onAddGameConfirmed() {
        model.insertGame(games.get(requestedIndex));
        gameView.switchToMyGames();
    }
}
