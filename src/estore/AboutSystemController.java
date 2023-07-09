package estore;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

public class AboutSystemController implements Initializable {

    @FXML
    private Button btnexit;

    protected void getMesage(String message) {
        this.btnexit.setText(message);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnexit.setOnAction(e -> {
            Dialog d = new Dialog();
            d.setHeaderText("خروج");
            d.setTitle(EStore.title);
            d.setContentText("هل تريد بالتأكيد الخروج من الواجهة؟");
            ButtonType btnyes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType btnno = new ButtonType("No", ButtonBar.ButtonData.NO);
            d.getDialogPane().getButtonTypes().addAll(btnyes, btnno);
            if (d.showAndWait().get() == btnyes) {
                Platform.exit();
                d.show();
            }
        });

    }

}
