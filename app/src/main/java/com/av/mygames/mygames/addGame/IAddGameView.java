package com.av.mygames.mygames.addGame;

import java.util.List;

/**
 * Created by Verou on 1/4/18.
 */

public interface IAddGameView {
    void showTitle(String gameName);
    void showSearchInProgress();

    void hideSearchInProgress();

    void showError(String message);

    void displayNames(List<String> games);

    void askGameInsertionConfirmation(String name, String summary);

    void switchToMyGames();
}
