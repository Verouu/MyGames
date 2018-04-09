package com.av.mygames.mygames.model;

import com.av.mygames.mygames.model.inetserver.ResponseReceiver;

import java.util.List;

/**
 * Created by Verou on 1/4/18.
 */

public interface IMyGamesModel {
    void findGames (String gameName, ResponseReceiver<List<AllGameData>> receiver);

    void insertGame(GameData gameData);

    List<Integer> getAllGames();

    String getGameName(Integer itemId);

    List<String> getAllPlatforms();

    List<String> getAllGenres();

    List<Integer> getGamesWithPlatform(String platform);

    List<Integer> getGamesWithGenre(String genre);
}
