package ui;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class LogIn {
    private Controller ctrl;
    public LogIn(Controller ctrl){
        this.ctrl = ctrl;
    }

    public Parent logInStage() {
        this.ctrl = ctrl;
        GridPane root = new GridPane();
        root.setPadding(new Insets(15, 15, 15, 25));
        root.setVgap(15);

        //Username TextField
        TextField username = new TextField();
        username.setPromptText("Username");
        GridPane.setConstraints(username, 1, 1);

        //Password TextField
        TextField password = new TextField();
        password.setPromptText("Password");
        GridPane.setConstraints(password, 1, 3);

        //Log In Button
        Button buttonLogIn = new Button("Log In");
        GridPane.setConstraints(buttonLogIn, 1, 5);
        GridPane.setHalignment(buttonLogIn, HPos.CENTER);
        buttonLogIn.setOnAction(E -> ctrl.logIn(username.getText(), password.getText(), ctrl));

        root.getChildren().addAll(username, password, buttonLogIn);
        return root;
    }
}
