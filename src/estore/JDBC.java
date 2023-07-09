package estore;

import java.sql.*;
import javafx.scene.control.TableView;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

public class JDBC {

    private static Connection con;
    private static Statement stmt;
    private static String SQL;

    public JDBC() {
        try {
            String url = "jdbc:mysql://127.0.0.1/store?user=root&password=";
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url);
            stmt = con.createStatement();
        } catch (Exception ex) {
            System.out.println("the error is :" + ex.getMessage());
        }

    }

    protected static int addusers(Users u) {
        int rowstatus = 0;
        try {
            SQL = "INSERT INTO user (u_id,u_name,u_password)";
            SQL += "VALUES(" + u.getUserid() + ",'" + u.getUsername() + "','" + u.getUserpassword() + "');";
            try {
                if (con != null && !con.isClosed()) {
                    rowstatus = stmt.executeUpdate(SQL);
                } else {
                    System.out.println("Error in connection O_O");
                }

            } catch (Exception ex) {
                System.out.println(ex.getMessage() + " qqqqqqqqqq");
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage() + " erererereer");
        }
        return rowstatus;

    }

    protected static int addproducts(products p) {
        int rowStatus = 0;
        try {

            SQL = "INSERT INTO product(prod_id,prod_name,prod_web,prod_pic,prod_data,prod_price,prod_currncy,prod_quantity,prod_total,u_id)";
            SQL += "VALUES(" + p.getPrNO() + ",'" + p.getPrName() + "','" + p.getPrAddress() + "','" + p.getPrpic() + "',";
            SQL += "'" + p.getPrData() + "'," + p.getPrPrice() + "," + p.getPrcurrency() + "," + p.getPrQuantety() + "," + p.getPrTotal() + "," + p.getUserId() + ");";
            
            try {
                if (con != null && !con.isClosed()) {
                    rowStatus = stmt.executeUpdate(SQL);
                } else {
                    System.out.println("Error in connection O_O");
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage() + "dsds");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage() + "asdasd");
        }
        return rowStatus;
    }

    protected static int updateproduct(products p) {
        int rowStatus = 0;
        SQL = "UPDATE  product SET prod_name='" + p.getPrName() + "',prod_web='" + p.getPrAddress() + "',"
                + "prod_pic='" + p.getPrpic() + "',prod_data='" + p.getPrData() + "',prod_price=" + p.getPrPrice() + ",   "
                + " prod_currncy=" + p.getPrcurrency() + ",prod_quantity=" + p.getPrQuantety() + ",prod_total=" + p.getPrTotal() + " "
                + " WHERE prod_id=" + p.getPrNO() + ";";
        try {
            if (con != null && !con.isClosed()) {
                rowStatus = stmt.executeUpdate(SQL);
            } else {
                System.out.println("Error in connection O_O");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage() + "fgfgfg");
        }
        return rowStatus;
    }

    protected static int updatepassworduser(Users u) {
        int rowStatus = 0;

        SQL = "UPDATE  user SET u_name='" + u.getUsername() + "',u_password='" + u.getUserpassword() + "'WHERE u_id=" + u.getUserid() + ";";
        try {
            if (con != null && !con.isClosed()) {
                rowStatus = stmt.executeUpdate(SQL);
            } else {
                System.out.println("Error in connection O_O");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage() + "uuuu");
        }
        return rowStatus;
    }

    protected static int deleteproduct(int prNo) {
        int rowStatus = 0;
        try {
            if (con != null && !con.isClosed()) {
                rowStatus = stmt.executeUpdate("DELETE FROM  product  WHERE prod_id=" + prNo + ";");
            } else {
                System.out.println("Error in connection O_O");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage() + "ghghgh");
        }
        return rowStatus;

    }

    protected void getproduct(String exp, TableView tb) {
        ObservableList<products> item = FXCollections.observableArrayList();
        try {

            ResultSet r = stmt.executeQuery("SELECT * FROM product " + exp);
            while (r.next()) {
                products pr = new products(
                        Integer.valueOf(r.getString("prod_id")), r.getString("prod_name"), r.getString("prod_web"), r.getString("prod_pic"), r.getString("prod_data"),
                        Double.valueOf(r.getString("prod_price")), Double.valueOf(r.getString("prod_currncy")), Double.valueOf(r.getString("prod_quantity")),
                        Double.valueOf(r.getString("prod_total")), Integer.valueOf(r.getString("u_id")));
                item.add(pr);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage() + "hjhjhj");
        }
        tb.setItems(item);
    }

    protected static products getproductByNo(int prNo) {
        products pr = new products();
        try {
            ResultSet r = stmt.executeQuery("SELECT * FROM product WHERE prod_id=" + prNo);
            while (r.next()) {
                pr.setPrNO(Integer.valueOf(r.getString("prod_id")));
                pr.setPrName(r.getString("prod_name"));
                pr.setPrAddress(r.getString("prod_web"));
                pr.setPrPrice(Double.valueOf(r.getString("prod_price")));
                pr.setPrQuantety(Double.valueOf(r.getString("prod_quantity")));
                pr.setPrTotal(Double.valueOf(r.getString("prod_total")));
                pr.setPrcurrency(Double.valueOf(r.getString("prod_currncy")));

            }
        } catch (NumberFormatException | SQLException ex) {
            System.out.println(ex.getMessage() + " llllllloooooooooooooooooo");
        }
        return pr;
    }

}
