package com.av.mygames.mygames.myGames;

/**
 * Created by Verou on 1/4/18.
 */

public class MyGamesPresenter {
    IMyGamesView view;

    public MyGamesPresenter(IMyGamesView view) {
        this.view = view;
    }

    public void onAddGameRequested(String name) {
        view.switchToAddGame(name);
    }

}
