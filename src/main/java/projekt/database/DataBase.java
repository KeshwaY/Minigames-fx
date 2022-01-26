package projekt.database;


import java.sql.ResultSet;
import java.sql.SQLException;

public interface DataBase {

    boolean execute(String sql) throws SQLException;
    ResultSet executeQuery(String sql) throws SQLException;
    boolean checkIfPlayerExists(String playerId) throws SQLException;

    boolean addPlayer(String id, String name) throws SQLException;

    boolean savePlayerAnswer(String playerId, String gameId, String choiceId) throws SQLException;

}
