package projekt;

import projekt.GUI.MainFX;
import projekt.client.Client;
import projekt.database.DataBase;
import projekt.database.DataBaseImpl;
import projekt.server.core.ServerImpl;
import projekt.server.core.ThreadManagerImpl;
import projekt.server.game.GameCreatorImpl;
import projekt.server.game.LobbyManagerImpl;
import projekt.server.game.abstraction.Lobby;
import projekt.server.game.abstraction.LobbyManager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {



        AtomicInteger posOfStartFlag = new AtomicInteger(-1);
        IntStream.range(0, args.length).boxed()
                .forEach(i -> {
                    if (args[i].equals("-start")) {
                        posOfStartFlag.set(i);
                    }
                });
        String startType = posOfStartFlag.get() >= 0 ? args[posOfStartFlag.get() + 1].toLowerCase(Locale.ROOT) : "client";
        if (startType.equals("server")) {
            try {
                DataBase dataBase = new DataBaseImpl("jdbc:mysql://localhost:3306/","root","");
                ThreadManagerImpl threadManager = new ThreadManagerImpl(2);
                LobbyManager lobbyManager = new LobbyManagerImpl(dataBase, new ConcurrentHashMap<Integer, Lobby>(), new GameCreatorImpl());
                ServerImpl serverImpl = new ServerImpl(8080, threadManager, lobbyManager, dataBase);
                serverImpl.start();
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
        } else {
            MainFX.main(args);
        }
    }

}
