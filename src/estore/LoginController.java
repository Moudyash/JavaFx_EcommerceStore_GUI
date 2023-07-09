package estore;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
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

public class LoginController implements Initializable {

    @FXML
    private Label lbluser;
    @FXML
    private TextField txtuser;
    @FXML
    private Label lblpass;
    @FXML
    private TextField txtpass;
    @FXML
    private Button btnlog;
    @FXML
    private Button btncancel;
    private static Connection con;
    private static Statement stmt;
    public static ResultSet r;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String ur = "jdbc:mysql://127.0.0.1:3306/store?user=root&password=";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(ur);
            stmt = con.createStatement();
            r = stmt.executeQuery("SELECT * FROM user;");

            btnlog.setOnAction(e -> {

                try {

                    if (txtuser.getText().trim().equals("Admin") && txtpass.getText().trim().equals("123123")) {
                        System.out.println("❤");
                        Stage stage = new Stage();
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("Admin.fxml"));
                        Parent root = loader.load();
                        AdminController contr = loader.getController();
                        contr.getMesage(this.txtuser.getText());
                        stage.setScene(new Scene(root));
                        stage.show();
                    }

                    r.next();
//                    while (r.next()) {

                    if (txtuser.getText().trim().equals(r.getString("u_name")) && txtpass.getText().trim().equals(r.getString("u_password"))) {
                        System.out.println("❤");
                        Stage stage = new Stage();
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("User.fxml"));
                        Parent root = loader.load();
                        UserController contr = loader.getController();
                        contr.getName(this.txtuser.getText());
                        contr.getId(r.getString("u_id"));
                        stage.setScene(new Scene(root));
                        stage.show();

                    } //                    }
//                    else if (txtuser.getText().trim().compareTo(r.getString("u_name")) != 0) {
//                        Dialog d = new Dialog();
//                        d.setHeaderText("خطأ في اسم المستخدم");
//                        d.setTitle(EStore.title);'/

//                        d.setContentText("هل تريد إعادة المحاولة؟");
//                        ButtonType btnyes = new ButtonType("نعم", ButtonBar.ButtonData.YES);
//                        ButtonType btnno = new ButtonType("لا", ButtonBar.ButtonData.NO);
//                        d.getDialogPane().getButtonTypes().addAll(btnyes, btnno);
//                        if (d.showAndWait().get() == btnno) {
//                            Platform.exit();
//                            d.show();
//                        }
//                    } else if (txtpass.getText().trim().compareTo(r.getString("u_password")) != 0) {
//                        Dialog d = new Dialog();
//                        d.setHeaderText("خطأ في كلمة المرور");
//                        d.setTitle(EStore.title);
//                        d.setContentText("هل تريد إعادة المحاولة؟");
//                        ButtonType btnyes = new ButtonType("نعم", ButtonBar.ButtonData.YES);
//                        ButtonType btnno = new ButtonType("لا", ButtonBar.ButtonData.NO);
//                        d.getDialogPane().getButtonTypes().addAll(btnyes, btnno);
//                        if (d.showAndWait().get() == btnno) {
//                            Platform.exit();
//                            d.show();
//                        }
//
//                    }
//
//                    
                } catch (Exception ex) {
                    System.out.println(ex.getMessage() + "  dcdcpp");
                }
            });

        } catch (Exception ex) {
            System.out.println(ex.getMessage() + "        lllglghjkuu");
        }
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
