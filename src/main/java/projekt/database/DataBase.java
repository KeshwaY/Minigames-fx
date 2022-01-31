package projekt.database;


import projekt.server.game.GameType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public interface DataBase {

    boolean execute(String sql) throws SQLException;
    ResultSet executeQuery(String sql) throws SQLException;
    ResultSet getPlayer(String username) throws SQLException;

    boolean addPlayer(String name, String password) throws SQLException;
    int addGame(GameType gameType, LocalDateTime timeCreated) throws SQLException;

    boolean addResult(int gameId, int playerId, int winner) throws SQLException;
}
