package estore;

import java.io.IOException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class EStore extends Application {

    public static String title = "eStore 2022";

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle(title);
        primaryStage.setResizable(false);
        primaryStage.setMaximized(false);
        primaryStage.setScene(scene);
        scene.setOnKeyPressed((KeyEvent t) -> {
            if (t.getCode() == KeyCode.ESCAPE) {
                Dialog d = new Dialog();
                d.setHeaderText("Exit..");
                d.setTitle("Fitness");
                d.setContentText("Are you sure?");
                ButtonType btnyes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
                ButtonType btnno = new ButtonType("No", ButtonBar.ButtonData.NO);
                d.getDialogPane().getButtonTypes().addAll(btnyes, btnno);
                if (d.showAndWait().get() == btnyes) {
                    primaryStage.close();
                    Platform.exit();
                    d.show();
                }
            }
        });
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
