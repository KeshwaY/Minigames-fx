package projekt.GUI;

import projekt.client.Client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;

import java.io.IOException;


public class MainFX extends Application {

    public static Client client;
    static {
        try {
            client = new Client("localhost", 8080);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not connect to the server!");
        }
    }

    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/Login.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch();
    }

}