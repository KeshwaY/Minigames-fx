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
import projekt.model.Lobby;
import projekt.model.Match;
import projekt.model.User;
import projekt.server.dto.ClientDto;
import projekt.server.dto.LobbiesDto;
import projekt.server.dto.LobbyDto;
import projekt.server.game.GameType;

import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LobbyController implements Initializable {


    @FXML
    public TableView<Lobby> tableView;

    public void join(ActionEvent actionEvent) throws IOException {

        Lobby selectedLobby = tableView.getSelectionModel().getSelectedItem();
        MainFX.client.joinGame(selectedLobby.getId());
        String[] split = selectedLobby.getSlots().split("/");
        int currentSize = Integer.parseInt(split[0].trim());
        int maxSize = Integer.parseInt(split[1].trim());
        if(currentSize < maxSize) {
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
        TableColumn<Lobby, String> ownerCol = new TableColumn<>("Owner");
        ownerCol.setCellValueFactory(new PropertyValueFactory<>("owner"));

        TableColumn<Lobby, String> gameTypeCol = new TableColumn<>("Game Type");
        gameTypeCol.setCellValueFactory(new PropertyValueFactory<>("gameType"));

        TableColumn<Lobby, String> descriptionCol = new TableColumn<>("Description");
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<Lobby, String> size = new TableColumn<>("Slots");
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("slots"));

        final ObservableList columns = tableView.getColumns();
        columns.add(ownerCol);
        columns.add(gameTypeCol);
        columns.add(descriptionCol);
        columns.add(size);

        try {
            LobbiesDto lobbiesDto = MainFX.client.getLobbies();
            lobbiesDto.getLobbies().values()
                    .forEach(l -> {
                        Lobby lobby = new Lobby();
                        lobby.setId(l.getId());
                        lobby.setOwner(l.getOwner().getUsername());
                        lobby.setGameType(l.getGameType());
                        lobby.setDescription(l.getDescription());
                        lobby.setSlots(l.getCurrentSize() + " / " + l.getMaxSize());
                        tableView.getItems().add(lobby);
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
