package ui;

import controllerObserver.Observer;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Concurent;
import model.UpdatePoints;
import services.MyException;

import java.util.Observable;

class Concurs implements Observer {

    private Stage concursStage;
    private TableView<Concurent> table;
    private Controller ctrl;

    Concurs(String refName, Controller ctrl) throws MyException {
        this.ctrl = ctrl;
        //Close Button
        Button buttonClose = new Button("Close");
        GridPane.setConstraints(buttonClose, 2, 5);
        buttonClose.setOnAction(E -> concursStage.close());

        //Label Nume
        Label nameLabel = new Label(refName);
        GridPane.setConstraints(nameLabel, 0, 0);

        //Name Column
        TableColumn<Concurent, String> nameColumn = new TableColumn<>("Nume");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nume"));

        //Points Column
        TableColumn<Concurent, Integer> pointsColumn = new TableColumn<>("Punctaj");
        pointsColumn.setMinWidth(200);
        pointsColumn.setCellValueFactory(new PropertyValueFactory<>("total"));

        //Table
        table = new TableView<>();
        table.setItems(ctrl.getConcurenti());
        table.getColumns().addAll(nameColumn, pointsColumn);
        table.getSortOrder().add(nameColumn);
        GridPane.setConstraints(table, 3, 1);

        //Label Name
        Label compLabel = new Label("Nume concurent:");
        GridPane.setConstraints(compLabel, 0, 2);

        //TextField Name
        TextField compTextField = new TextField();
        GridPane.setConstraints(compTextField, 1, 2);

        //Label Points
        Label pointsLabel = new Label("Punctaj:");
        GridPane.setConstraints(pointsLabel, 0, 3);

        //TextField Points
        TextField pointsTextField = new TextField();
        GridPane.setConstraints(pointsTextField, 1, 3);

        //Button Points
        Button updateButton = new Button("Update");
        updateButton.setOnAction(E -> {
            try {
                ctrl.updatePoints(compTextField.getText(), Integer.parseInt(pointsTextField.getText()), refName);
            } catch (MyException e) {
                e.printStackTrace();
            }
            try {
                table.setItems(ctrl.getConcurenti());
            } catch (MyException e) {
                e.printStackTrace();
            }
        });
        GridPane.setConstraints(updateButton, 1, 4);

        //Grid Pane
        GridPane concursRoot = new GridPane();
        concursRoot.setPadding(new Insets(15));
        concursRoot.setVgap(15);
        concursRoot.getChildren().addAll(buttonClose, nameLabel, table, compLabel, compTextField, pointsLabel, pointsTextField, updateButton);

        //Scene
        Scene concursScene = new Scene(concursRoot, 800, 600);

        //Stage
        concursStage = new Stage();
        concursStage.initModality(Modality.APPLICATION_MODAL);
        concursStage.setTitle("ui.Concurs");
        concursStage.setScene(concursScene);
        concursStage.show();
    }

    @Override
    public void update() {
        Platform.runLater(() -> {
            try {
                table.setItems(ctrl.getConcurenti());
            } catch (MyException e) {
                e.printStackTrace();
            }
        });
    }
}