package projekt.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterController {
    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField confirmedPassword;

    @FXML
    private Label notValid;

    public void loginView(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Login.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void register(ActionEvent actionEvent){
        String name = username.getText();
        String pwd = password.getText();
        String pwd2 = confirmedPassword.getText();
        if (!pwd.equals(pwd2)) notValid.setText("Password does not match!");
        //TODO
        //use user.exists(name)
        else if(name.equals("exists")) notValid.setText("Username in use!");
        else{
            //if(!register(name, pwd))
            notValid.setText("Error. Something goes wrong!");
        }
    }

}
