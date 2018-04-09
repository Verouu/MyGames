package com.av.mygames.mygames.myGames;

import com.av.mygames.mygames.model.MyGamesModel;

import java.util.ArrayList;
import java.util.List;

import static com.av.mygames.mygames.myGames.MyGameActivity.DISPLAY_GAMES;
import static com.av.mygames.mygames.myGames.MyGameActivity.DISPLAY_GENRES;
import static com.av.mygames.mygames.myGames.MyGameActivity.DISPLAY_PLATFORMS;

/**
 * Created by Verou on 1/4/18.
 */

public class MyGamesPresenter {
    IMyGamesView view;

    List<Integer> itemsIds = new ArrayList<>();
    List<String> itemNames = new ArrayList<>();


    public MyGamesPresenter(IMyGamesView view) {
        this.view = view;
    }

    public void onAddGameRequested(String name) {
        view.switchToAddGame(name);
    }

    public void onViewStarted() {
        int currentMode = view.getDisplay();
        checkDisplayContent(currentMode);
    }

    private void checkDisplayContent(int currentMode) {
        switch (currentMode) {
            case DISPLAY_GAMES:
                itemsIds = MyGamesModel.getInstance().getAllGames();
                for (Integer itemId : itemsIds) {
                    itemNames.add(MyGamesModel.getInstance().getGameName(itemId));
                }
                view.displayNames(itemNames);
                break;
            case DISPLAY_PLATFORMS:
                itemNames.clear();
                itemNames = MyGamesModel.getInstance().getAllPlatforms();
                view.displayNames(itemNames);
                break;
            case DISPLAY_GENRES:
                itemNames.clear();
                itemNames = MyGamesModel.getInstance().getAllGenres();
                view.displayNames(itemNames);
                break;
        }
        view.setDisplay(currentMode);
    }

    public void onDisplayModeSelected(int displayGamesMode) {
        int currentMode = view.getDisplay();
        if (displayGamesMode != currentMode) {
            checkDisplayContent(displayGamesMode);
        }
    }

    public void onShowNameRequested(int position) {
        int currentMode = view.getDisplay();
        switch (currentMode){
            case DISPLAY_GAMES:
                return;
            case DISPLAY_PLATFORMS:
                itemsIds = MyGamesModel.getInstance().getGamesWithPlatform(itemNames.get(position));
                break;
            case DISPLAY_GENRES:
                itemsIds = MyGamesModel.getInstance().getGamesWithGenre(itemNames.get(position));
        }
        view.setDisplay(DISPLAY_GAMES);

        itemNames.clear();
        for (Integer itemId : itemsIds) {
            itemNames.add(MyGamesModel.getInstance().getGameName(itemId));
        }
        view.displayNames(itemNames);
    }
}
