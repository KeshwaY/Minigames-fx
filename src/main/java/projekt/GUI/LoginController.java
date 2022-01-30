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
import projekt.client.Client;
import projekt.server.dto.LoginResponseDto;

import java.io.IOException;


public class LoginController {

    public LoginController() {
        try {
            MainFX.client = new Client("localhost", 8080);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not connect to the server!");
        }
    }

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Label notValid;

    public void login(ActionEvent actionEvent) throws IOException {


        String name = username.getText();
        String pwd = password.getText();
        // after
        System.out.println(pwd);
        System.out.println(name);
        LoginResponseDto loginResponseDto = MainFX.client.login(name, pwd);
        System.out.println(loginResponseDto.getSuccess());
        if(!loginResponseDto.getSuccess()) notValid.setText("Incorrect username or password!");
        else {
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/Menu.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void registerView(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Register.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
