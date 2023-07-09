package estore;

import java.awt.Desktop;
import java.io.File;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class UserController implements Initializable {

    @FXML
    private Button btnabout;
    @FXML
    private Label lblnameuser;
    @FXML
    private Button btnchangepass;
    @FXML
    private TextField txtidprod;
    @FXML
    private TextField txtnameprod;
    @FXML
    private TextField txtaddressprod;
    @FXML
    private ImageView imageview;
    @FXML
    private Button btnloadimage;
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
    private TableView<products> tableview;
    @FXML
    private TableColumn<products, Integer> colidprod;
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
    private Label lbladdress2222;
    @FXML
    private Button btnweb;
    @FXML
    private Button btnsave;
    @FXML
    private Button btndelete;
    @FXML
    private Button btnnew;
    @FXML
    private Button btnexit;

    @FXML
    private Button btnsearch;

    private String imgPath;
    @FXML
    private Label lbluserid;
    @FXML
    private Label txtuserid;

    protected void getName(String message) {
        this.lblnameuser.setText(message);
    }

    protected void getId(String message) {
        this.lbluserid.setText(message);
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

    private void fillCurrncy() {
        this.compo.getItems().clear();
        ObservableList<Currency> item = FXCollections.observableArrayList(new Currency(1, "دولار"), new Currency(2, "شيقل"), new Currency(3, "دينار"));
        this.compo.setItems(item);
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
//                new products(1, "asd", "asd", "asd", "12-12-2012", 10.4, 44.1, 1.4, 144.5)
//        );
        colidprod.setCellValueFactory(new PropertyValueFactory("prNO"));
        colname.setCellValueFactory(new PropertyValueFactory("prName"));
        coldate.setCellValueFactory(new PropertyValueFactory("prData"));
        colprice.setCellValueFactory(new PropertyValueFactory("prPrice"));
        colcurrncy.setCellValueFactory(new PropertyValueFactory("prcurrency"));
        colquantity.setCellValueFactory(new PropertyValueFactory("prQuantety"));
        coltotal.setCellValueFactory(new PropertyValueFactory("prTotal"));

        try {
            JDBC jdbc = new JDBC();
            jdbc.getproduct("WHERE product.u_id=" + LoginController.r.getString("u_id") + ";", tableview);
        } catch (Exception ex) {
            System.out.println(ex.getMessage() + " fgfgfff");
        }
    }

    protected static int addproducts(products p) {
        JDBC jdbc = new JDBC();
        return JDBC.addproducts(p);
    }

    protected static int updateRecord(products p) {
        JDBC jdbc = new JDBC();
        return JDBC.updateproduct(p);
    }

    private boolean validation() {
        boolean status = true;
        if (this.txtidprod.getText().trim().length() == 0) {
            status = false;
        }
        if (this.txtnameprod.getText().trim().length() == 0) {
            status = false;
        }
        if (this.txtQuantity.getText().trim().length() == 0) {
            status = false;
        }
        if (this.txtaddressprod.getText().trim().length() == 0) {
            status = false;
        }
        if (this.datepicprod.getValue() == null) {
            status = false;
        }
        if (this.compo.getValue() == null) {
            status = false;
        }
        if (this.txtQuantity.getText().trim().length() == 0) {
            status = false;
        }
        return status;
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
        this.btnsave.setText("حفظ");
        this.btndelete.setDisable(true);
        this.txtidprod.setText(null);
        this.txtnameprod.setText(null);
        this.txtQuantity.setText(null);
        this.txtaddressprod.setText(null);
        this.txtprice.setText(null);
        this.datepicprod.setValue(null);
        this.compo.setValue(null);
        this.imageview.setImage(null);
        this.txttotal.setText(null);

    }

    @FXML
    public void handleMouseAction(MouseEvent event) {
        newRecord();
        products pr = this.tableview.getSelectionModel().getSelectedItem();
        this.txtidprod.setText(pr.getPrNO().toString());
        this.txtnameprod.setText(pr.getPrName());
        this.txtaddressprod.setText(pr.getPrAddress());
        this.datepicprod.setValue(LocalDate.parse(pr.getPrData()));
        this.txtQuantity.setText(pr.getPrQuantety().toString());
        this.txtprice.setText(pr.getPrPrice().toString());
        this.txttotal.setText(pr.getPrTotal().toString());
        this.btnsave.setText("تعديل");
        this.btndelete.setDisable(false);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        fillCurrncy();
        fillTable();
        btnnew.setOnAction(e -> {
            newRecord();
        });

        btndelete.setOnAction(e -> {
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

        btnsave.setOnAction(e -> {

            if (validation() == true) {
                products pr = new products();

                pr.setPrNO(Integer.valueOf(this.txtidprod.getText().trim()));
                pr.setPrName(this.txtnameprod.getText().trim());
                pr.setPrPrice(Double.valueOf(this.txtprice.getText().trim()));
                pr.setPrTotal(Double.valueOf(this.txtprice.getText().trim()) * Double.valueOf(this.txtQuantity.getText().trim()));
                pr.setPrData(this.datepicprod.getValue().toString());
                pr.setPrcurrency(Double.valueOf(this.compo.getValue().toString().substring(0, 3)));
                pr.setPrAddress(this.txtaddressprod.getText());
                pr.setPrQuantety(Double.valueOf(txtQuantity.getText().trim()));
                pr.setUserId(Integer.valueOf(lbluserid.getText()));

                pr.setPrpic(imgPath);
                if (btnsave.getText().equals("حفظ")) {
                    if (addproducts(pr) == 1) {
                        fillTable();
                        maseg("حفظ السجل", "تمت عملية الحفظ بنجاح");
                        this.btnsave.setText("تعديل");
                    }
                    this.btndelete.setDisable(false);
                } else {
                    if (updateRecord(pr) == 1) {
                        fillTable();
                        maseg("تحديث السجل", "تمت عملية التحديث بنجاح");
                        this.btndelete.setDisable(false);

                    }
                }
            } else {

                maseg("خطأ في عملية الحفظ", "لا يمكن الحفظ بسبب عدم إكتمال البيانات");

            }
            newRecord();

        });
        btnweb.setOnAction(e -> {

            Desktop d = Desktop.getDesktop();
            try {
                d.browse(new URI(this.txtaddressprod.getText()));
            } catch (URISyntaxException | IOException ex) {
            }

        });

        btnloadimage.setOnAction(e -> {
            FileChooser file = new FileChooser();
            file.setTitle("إختر الصورة");
            file.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg"));
            File selectedFile = file.showOpenDialog(null);
            if (selectedFile != null) {
                imageview.setImage(new Image(selectedFile.toURI().toString()));
                imgPath = selectedFile.toURI().toString();
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
                this.txtaddressprod.setText(pr.getPrAddress());
                this.txtidprod.setText(pr.getPrNO().toString());
                this.txtQuantity.setText(pr.getPrQuantety().toString());
                this.txtprice.setText(pr.getPrPrice().toString());
                this.txttotal.setText(pr.getPrTotal().toString());

                this.btndelete.setDisable(false);
                this.btnsave.setText("تعديل");
            } else {
                maseg("إدخال خاطئ", "يرجى الإدخال مرة أخرى");

            }
        });

        btnchangepass.setOnAction(e -> {
            try {
                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("changepass.fxml"));
                Parent root = loader.load();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception ex) {
                System.out.println(ex.getMessage() + " ddd");
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
                System.out.println(ex.getMessage() + " tttt");
            }
        });

        btnexit.setOnAction(e -> {
            exit();
        });

    }

}
