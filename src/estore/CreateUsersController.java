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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CreateUsersController implements Initializable {

    @FXML
    private Label lbluser;
    @FXML
    private TextField txtuser;
    @FXML
    private Label lblpass;
    @FXML
    private TextField txtpass;
    @FXML
    private Button btnadd;
    @FXML
    private Button btncancel;

    private void maseg(String content, String message) {
        Dialog d = new Dialog();
        d.setTitle(EStore.title);
        d.setHeaderText(content);
        d.setContentText(message);
        ButtonType btnok = new ButtonType("موافق", ButtonBar.ButtonData.OK_DONE);
        d.getDialogPane().getButtonTypes().add(btnok);
        d.showAndWait();
    }

    protected static int createuser(Users u) {
        JDBC jdbc = new JDBC();
        return JDBC.addusers(u);
    }
    @FXML
    private Label lblid;
    @FXML
    private TextField txtuserid;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        btnadd.setOnAction(e -> {
            Users u = new Users();
            u.setUserid(Integer.valueOf(this.txtuserid.getText().trim()));
            u.setUsername(this.txtuser.getText().trim());
            u.setUserpassword(this.txtpass.getText().trim());
            if (createuser(u) == 1) {
                maseg("حفظ السجل", "تمت عملية الحفظ بنجاح");

            }else{
                maseg("حفظ السجل","هناك إدخال خاطئ");
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
