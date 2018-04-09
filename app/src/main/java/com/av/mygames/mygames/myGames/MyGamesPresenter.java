package com.av.mygames.mygames.myGames;

import com.av.mygames.mygames.model.MyGamesModel;

import java.util.ArrayList;
import java.util.List;

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
        itemsIds = MyGamesModel.getInstance().getAllGames();
        for (Integer itemId : itemsIds) {
            itemNames.add(MyGamesModel.getInstance().getGameName(itemId));
        }

        view.displayNames(itemNames);
    }
}
