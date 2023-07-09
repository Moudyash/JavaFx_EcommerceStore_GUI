package estore;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AdminController implements Initializable {

    @FXML
    private Button btnabout;
    @FXML
    private Label lblnameuser;
    @FXML
    private TableView<products> tableview;
    @FXML
    private TableColumn<products, Integer> colnum;
    @FXML
    private TableColumn<products, String> colname;
    @FXML
    private TableColumn<products, String> coldate;
    @FXML
    private TableColumn<products, Double> colprice;
    @FXML
    private TableColumn<products, Double> colcurrncy;
    @FXML
    private TableColumn<products, Double> colquantity;
    @FXML
    private TableColumn<products, Double> coltotal;
    @FXML
    private Button btnexit;
    @FXML
    private Button btncreateuser;
    @FXML
    private TextField txtidprod;
    @FXML
    private TextField txtnameprod;
    @FXML
    private DatePicker datepicprod;
    @FXML
    private TextField txtprice;
    @FXML
    private ComboBox<Currency> compo;
    @FXML
    private TextField txtQuantity;
    @FXML
    private Label txttotal;
    @FXML
    private Button btndelet;
    @FXML
    private Button btnnew;
    @FXML
    private Label lbladdress2222;
    @FXML
    private Button btnweb;
    @FXML
    private TextField txtaddressweb;
    @FXML
    private Button btnsearch;

    protected void getMesage(String message) {
        this.lblnameuser.setText(message);
    }

    private void maseg(String content, String message) {
        Dialog d = new Dialog();
        d.setTitle(EStore.title);
        d.setHeaderText(content);
        d.setContentText(message);
        ButtonType btnok = new ButtonType("موافق", ButtonBar.ButtonData.OK_DONE);
        d.getDialogPane().getButtonTypes().add(btnok);
        d.showAndWait();
    }

    private void exit() {
        Dialog d = new Dialog();
        d.setHeaderText("إنهاء النظام...");
        d.setTitle(EStore.title);
        d.setContentText("هل تريد بالتأكيد الخروج من النظام؟");
        ButtonType btnyes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType btnno = new ButtonType("No", ButtonBar.ButtonData.NO);
        d.getDialogPane().getButtonTypes().addAll(btnyes, btnno);
        if (d.showAndWait().get() == btnyes) {
            Platform.exit();
            d.show();
        }
    }

    private void fillTable() {
//        ObservableList<products> item = FXCollections.observableArrayList(
//                new products(1, "dd", "ss", "ff", "2020-02-02", 10.0, 120.0, 130.0, 1100.0)
//        );
        colnum.setCellValueFactory(new PropertyValueFactory("prNO"));
        colname.setCellValueFactory(new PropertyValueFactory("prName"));
        coldate.setCellValueFactory(new PropertyValueFactory("prData"));
        colprice.setCellValueFactory(new PropertyValueFactory("prPrice"));
        colcurrncy.setCellValueFactory(new PropertyValueFactory("prcurrency"));
        colquantity.setCellValueFactory(new PropertyValueFactory("prQuantety"));
        coltotal.setCellValueFactory(new PropertyValueFactory("prTotal"));
        JDBC jdbc = new JDBC();
        jdbc.getproduct("", tableview);
    }

    private void fillCurrncy() {

        this.compo.getItems().clear();
        ObservableList<Currency> item = FXCollections.observableArrayList(new Currency(1, "دولار"), new Currency(2, "شيقل"), new Currency(3, "دينار"));
        this.compo.setItems(item);
    }

    private void deleteProducts(int prNo) {
        Dialog d = new Dialog();
        d.setHeaderText("حذف سجل...");
        d.setTitle(EStore.title);
        d.setContentText("هل تريد بالتأكيد حذف السجل؟");
        ButtonType btnYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType btnNo = new ButtonType("No", ButtonBar.ButtonData.NO);
        d.getDialogPane().getButtonTypes().addAll(btnYes, btnNo);
        if (d.showAndWait().get() == btnYes) {
            JDBC jdbc = new JDBC();
            if (JDBC.deleteproduct(prNo) == 1) {
                maseg("حذف السجل", "تمت عملية الحذف بنجاح");
            }
        }
    }

    private void newRecord() {
        this.txtidprod.requestFocus();
        this.btndelet.setDisable(true);
        this.txtidprod.setText(null);
        this.txtaddressweb.setText(null);
        this.txttotal.setText(null);
        this.txtnameprod.setText(null);
        this.txtQuantity.setText(null);
        this.txtprice.setText(null);
        this.datepicprod.setValue(null);
        this.compo.setValue(null);

    }

    @FXML
    public void handleMouseAction(MouseEvent event) {
        newRecord();
        products pr = this.tableview.getSelectionModel().getSelectedItem();
        this.txtidprod.setText(pr.getPrNO().toString());
        this.txtnameprod.setText(pr.getPrName());
        this.datepicprod.setValue(LocalDate.parse(pr.getPrData()));
        this.txtaddressweb.setText(pr.getPrAddress());
        this.txtQuantity.setText(pr.getPrQuantety().toString());
        this.txtprice.setText(pr.getPrPrice().toString());
        this.txttotal.setText(pr.getPrTotal().toString());
        this.btndelet.setDisable(false);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        fillCurrncy();
        fillTable();

        btndelet.setOnAction(e -> {
            if (this.txtidprod.getText().trim().length() > 0) {
                int prNo = Integer.valueOf(this.txtidprod.getText().trim());
                if (prNo > 0) {
                    deleteProducts(prNo);
                    fillTable();
                    newRecord();
                }
            } else {
                System.out.println("o_o");
            }
        });

        btnweb.setOnAction(e -> {

            Desktop d = Desktop.getDesktop();
            try {
                d.browse(new URI(this.txtaddressweb.getText()));
            } catch (URISyntaxException | IOException ex) {
                System.out.println(ex.getMessage() + " dodo");
            }

        });

        btnsearch.setOnAction((e) -> {
            TextInputDialog td = new TextInputDialog();
            td.setHeaderText("بحث عن منتج");
            td.setTitle(EStore.title);
            td.setContentText("أدخل رقم المنتج ");
            td.showAndWait();
            String prNo1 = td.getEditor().getText().toString();
            if (prNo1.length() > 0) {
                int prNo = Integer.valueOf(td.getEditor().getText());
                products pr = new products();
                pr = JDBC.getproductByNo(prNo);

                this.txtnameprod.setText(pr.getPrName());
                this.txtaddressweb.setText(pr.getPrAddress());
                this.txtidprod.setText(pr.getPrNO().toString());
                this.txtQuantity.setText(pr.getPrQuantety().toString());
                this.txtprice.setText(pr.getPrPrice().toString());
                this.txttotal.setText(pr.getPrTotal().toString());
                this.btndelet.setDisable(false);
            } else {
                maseg("إدخال خاطئ", "يرجى الإدخال مرة أخرى");

            }
        });

        btnnew.setOnAction(e -> {
            newRecord();

        });
        btncreateuser.setOnAction(e -> {
            try {
                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("createUsers.fxml"));
                Parent root = loader.load();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception ex) {
                System.out.println(ex.getMessage() + " vvv");
            }
        });

        btnabout.setOnAction(e -> {
            try {
                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("aboutsystem.fxml"));
                Parent root = loader.load();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception ex) {
                System.out.println(ex.getMessage() + " ccc");
            }
        });
        btnexit.setOnAction(e -> {
            exit();
        });
    }

}
