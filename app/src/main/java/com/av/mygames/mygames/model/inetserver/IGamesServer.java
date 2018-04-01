package com.av.mygames.mygames.model.inetserver;

import com.av.mygames.mygames.model.AllGameData;

import java.io.File;
import java.util.List;

/**
 * Created by jcamen on 17/02/18.
 */

public interface IGamesServer {

    void findGames(String gameName, ResponseReceiver<List<AllGameData>> receiver);

    void requestCover(String cover, String filename, ResponseReceiver<File> receiver);
}
