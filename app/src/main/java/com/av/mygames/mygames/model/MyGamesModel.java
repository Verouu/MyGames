package com.av.mygames.mygames.model;

import android.content.Context;

import com.av.mygames.mygames.model.database.IGamesDatabase;
import com.av.mygames.mygames.model.database.MockGamesDatabase;
import com.av.mygames.mygames.model.inetserver.IGamesServer;
import com.av.mygames.mygames.model.inetserver.MockGamesServer;
import com.av.mygames.mygames.model.inetserver.ResponseReceiver;

import java.util.List;

/**
 * Created by Verou on 1/4/18.
 */

public class MyGamesModel implements IMyGamesModel{
    private IGamesDatabase db;
    private IGamesServer gamesServer;

    private static MyGamesModel instance = null;

    public static MyGamesModel getInstance(){
        return instance;
    }

    public static MyGamesModel getInstance(Context context){
        if (instance == null)
            instance = new MyGamesModel(new MockGamesDatabase(), new MockGamesServer());
        return instance;
    }

    public MyGamesModel(IGamesDatabase db, IGamesServer gamesServer) {
        this.db = db;
        this.gamesServer = gamesServer;
    }

    @Override
    public void findGames(String gameName, ResponseReceiver<List<AllGameData>> receiver) {

    }

    @Override
    public void insertGame(GameData gameData) {
        GameRelationData gameRelationData = new GameRelationData(gameData.getName(), gameData.getId());

        db.insertGameData(gameData);
        db.insertPlatformData(gameRelationData);
        db.insertGenreData(gameRelationData);
    }
}
