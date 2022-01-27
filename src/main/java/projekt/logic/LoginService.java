package projekt.logic;

import projekt.database.DataBaseImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginService {

    private final DataBaseImpl dataBase;

    public LoginService(DataBaseImpl dataBase) {
        this.dataBase = dataBase;
    }

    public boolean checkCredintials(String username, String password) throws SQLException {
        ResultSet player = dataBase.getPlayer(username);
        if(!player.next()) {
            throw new SQLException("Missing results");
        }
        String passwordFromDatabase = player.getString("password");
        return passwordFromDatabase.equals(password);
    }

    public boolean registerUser(String username, String password) throws SQLException {
        return dataBase.addPlayer(username, password);
    }
}
