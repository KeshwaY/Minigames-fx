package projekt.database;

import projekt.server.client.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
        execute("CREATE TABLE IF NOT EXISTS players ( ID INTEGER AUTO_INCREMENT PRIMARY KEY " +
                " username VARCHAR(40) NOT NULL), password VARCHAR(40) NOT NULL");
    }

    private void createResultTable() throws SQLException {
        execute("CREATE TABLE IF NOT EXISTS results ( ID INTEGER AUTO_INCREMENT PRIMARY KEY " +
                " FOREIGN KEY (playerID) REFERENCES players(ID), FOREIGN KEY (gameID) REFERENCES games(ID), winner BOOLEAN)");
    }

    private void createGameTable() throws SQLException {
        execute("CREATE TABLE IF NOT EXISTS games ( ID INTEGER AUTO_INCREMENT PRIMARY KEY " +
                " gameType VARCHAR(50) NOT NULL, created DATE)");
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
        return executeQuery("SELECT * FROM players WHERE username = " + username);
    }

    @Override
    public boolean addPlayer(String name, String password) throws SQLException {
        return execute(String.format("INSERT INTO players(username, password) VALUES ('%s', '%s')", name, password));
    }

//    @Override
//    public boolean savePlayerAnswer(String playerId, String gameId, String choiceId) throws SQLException{
//        //TODO: need some validation/logic here for answer (choice) and set the winner
//        return execute(String.format("INSERT INTO results(playerID, gameID, -----) VALUES ('%s', %s, %s)", playerId, gameId, //TODO: WHO IS THE WINNER?"));
//    }
}
