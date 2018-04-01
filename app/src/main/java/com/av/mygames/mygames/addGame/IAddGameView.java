package com.av.mygames.mygames.addGame;

/**
 * Created by Verou on 1/4/18.
 */

public interface IAddGameView {
    void showTitle(String gameName);
    void showSearchInProgress();

    void hideSearchInProgress();

    void showError(String message);
}
