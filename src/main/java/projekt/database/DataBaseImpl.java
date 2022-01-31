package projekt.database;

import projekt.server.game.GameType;

import java.sql.*;
import java.time.LocalDateTime;

public class DataBaseImpl implements DataBase{

    private final Connection connection;

    public DataBaseImpl(String host, String user, String password) throws SQLException {
        this.connection = DriverManager.getConnection(host, user, password);
        createDatabase();
        createTables();
    }

    private void createDatabase() throws SQLException {
        execute("CREATE DATABASE IF NOT EXISTS minigames");
        execute("USE minigames");
    }

    private void createTables() throws SQLException {
        createPlayerTable();
        createGameTable();
        createResultTable();
    }

    private void createPlayerTable() throws SQLException {
        execute("CREATE TABLE IF NOT EXISTS players ( ID INTEGER AUTO_INCREMENT PRIMARY KEY, username VARCHAR(40) NOT NULL, password VARCHAR(40) NOT NULL)");
    }

    private void createResultTable() throws SQLException {
        execute("CREATE TABLE IF NOT EXISTS results ( ID INTEGER AUTO_INCREMENT PRIMARY KEY, playerID INTEGER NOT NULL, gameID INTEGER NOT NULL, FOREIGN KEY (playerID) REFERENCES players(ID), FOREIGN KEY (gameID) REFERENCES games(ID), winner BOOLEAN)");
    }

    private void createGameTable() throws SQLException {
        execute("CREATE TABLE IF NOT EXISTS games ( ID INTEGER AUTO_INCREMENT PRIMARY KEY, gameType VARCHAR(50) NOT NULL, created DATE)");
    }

    private boolean checkForValue(String sql) throws SQLException {
        ResultSet resultSet = executeQuery(sql);
        return resultSet.next();
    }
    @Override
    public boolean execute(String sql) throws SQLException {
        Statement statement = this.connection.createStatement();
        return statement.execute(sql);
    }
    @Override
    public ResultSet executeQuery(String sql) throws SQLException {
        Statement statement = this.connection.createStatement();
        return statement.executeQuery(sql);
    }

    @Override
    public ResultSet getPlayer(String username) throws SQLException {
        return executeQuery("SELECT * FROM players WHERE username = '" + username + "'");
    }

    @Override
    public boolean addPlayer(String name, String password) throws SQLException {
        return execute(String.format("INSERT INTO players(username, password) VALUES ('%s', '%s')", name, password));
    }

    @Override
    public int addGame(GameType gameType, LocalDateTime timeCreated) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(String.format("INSERT INTO games(gameType, created) VALUES ('%s', '%s')", gameType.toString(), timeCreated),
                Statement.RETURN_GENERATED_KEYS);
        statement.execute();

        ResultSet rs = statement.getGeneratedKeys();
        int generatedKey = 0;
        if (rs.next()) {
            generatedKey = rs.getInt(1);
        }
        return generatedKey;
    }

    @Override
    public boolean addResult(int gameId, int playerId, int winner) throws SQLException {
        return execute(String.format("INSERT INTO results(playerID, gameID, winner) VALUES (%d, %d, %d)", playerId, gameId, winner));
    }

}
