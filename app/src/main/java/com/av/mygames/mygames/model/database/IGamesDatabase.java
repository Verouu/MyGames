package com.av.mygames.mygames.model.database;

import com.av.mygames.mygames.model.GameData;
import com.av.mygames.mygames.model.GameRelationData;

import java.util.List;

/**
 * Created by jcamen on 17/02/18.
 */

public interface IGamesDatabase {
    List<Integer> getAllGames();
    List<String> getAllPlatforms();
    List<String> getAllGenres();
    List<Integer> getGamesWithPlatform(String platformName);
    List<Integer> getGamesWithGenre(String genreName);
    GameData getGameData(int id);

    void insertGameData(GameData gameData);
    void insertPlatformData(GameRelationData platformData);
    void insertGenreData(GameRelationData genreData);

    void changeState(int idGame, GameData.MyGameState state);
    void changeComment(int idGame, String comment);

    void deleteGame(int idGame);
}
