package com.av.mygames.mygames.myGames;

import java.util.List;

/**
 * Created by Verou on 1/4/18.
 */

public interface IMyGamesView {

    void switchToAddGame(String name);

    void displayNames(List<String> itemNames);

    int getDisplay();

    void setDisplay(int displayGamesMode);
}
