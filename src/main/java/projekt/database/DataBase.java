package projekt.database;


import java.sql.ResultSet;
import java.sql.SQLException;

public interface DataBase {

    boolean execute(String sql) throws SQLException;
    ResultSet executeQuery(String sql) throws SQLException;
    ResultSet getPlayer(String username) throws SQLException;

    boolean addPlayer(String name, String password) throws SQLException;

   // boolean savePlayerAnswer(String playerId, String gameId, String choiceId) throws SQLException;

}
