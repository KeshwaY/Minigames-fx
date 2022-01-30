package projekt.GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import projekt.model.Game;
import projekt.model.Match;
import projekt.model.User;
import projekt.server.dto.ClientDto;
import projekt.server.dto.LobbyDto;
import projekt.server.game.GameType;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LobbyController implements Initializable {


    @FXML
    public TableView<LobbyDto> tableView;

    public void join(ActionEvent actionEvent) throws IOException {

        LobbyDto selectedLobby = tableView.getSelectionModel().getSelectedItem();
        if(selectedLobby.getCurrentSize()<selectedLobby.getMaxSize()) {
            //wczytuje okno gry
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/CoinFlip.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);

            stage.show();
        }
    }

    public void create(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/MatchCreate.fxml"));
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void back(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Menu.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TableColumn<LobbyDto, String> ownerCol = new TableColumn<>("Owner");
        ownerCol.setCellValueFactory(new PropertyValueFactory<>("owner"));

        TableColumn<LobbyDto, String> gameTypeCol = new TableColumn<>("Game Type");
        gameTypeCol.setCellValueFactory(new PropertyValueFactory<>("gameType"));

        TableColumn<LobbyDto, String> descriptionCol = new TableColumn<>("Description");
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<LobbyDto, String> size = new TableColumn<>("Slots");
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("currentSize"));


//        getLobbies().forEach(lobbyDto ->
//                tableView.getItems().add(lobbyDto)
//                );


    }
}
