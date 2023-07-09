package estore;

import static estore.UserController.updateRecord;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ChangepassController implements Initializable {

    @FXML
    private Label lbloldpass;
    @FXML
    private TextField txtoldpass;
    @FXML
    private Button btnchangepass;
    @FXML
    private Button btncancel;
    @FXML
    private Label lblusername;
    @FXML
    private TextField txtusername;
    @FXML
    private Label lbluserid;
    @FXML
    private TextField txtuserid;

    protected static int updatepass(Users u) {
        JDBC jdbc = new JDBC();
        return JDBC.updatepassworduser(u);
    }
    @FXML
    private Label newpassword;
    @FXML
    private TextField txtnewpass;

    private void maseg(String content, String message) {
        Dialog d = new Dialog();
        d.setTitle(EStore.title);
        d.setHeaderText(content);
        d.setContentText(message);
        ButtonType btnok = new ButtonType("موافق", ButtonBar.ButtonData.OK_DONE);
        d.getDialogPane().getButtonTypes().add(btnok);
        d.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        btnchangepass.setOnAction(e -> {
            Users u = new Users();
            u.setUserid(Integer.valueOf(this.txtuserid.getText().trim()));
            u.setUsername(this.txtusername.getText().trim());
            u.setUserpassword(this.txtnewpass.getText().trim());

            if (updatepass(u) == 1) {

                maseg("تحديث السجل", "تمت عملية التحديث بنجاح");

            } else {
                System.out.println("ddddd");

            }
        });

        btncancel.setOnAction(e -> {
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
