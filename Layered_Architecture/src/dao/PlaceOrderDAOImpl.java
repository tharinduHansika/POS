package dao;

import db.DBConnection;
import javafx.scene.control.Alert;
import model.CustomerDTO;
import model.ItemDTO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlaceOrderDAOImpl {

    public CustomerDTO selectedCustomer(String newValue) throws SQLException, ClassNotFoundException {
        /*Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Customer WHERE id=?");
        pstm.setString(1, newValue + "");
        ResultSet rst = pstm.executeQuery();*/
        ResultSet rst =SQLUtil.executeQuery("SELECT * FROM Customer WHERE id=?",newValue);
        rst.next();

        return new CustomerDTO(rst.getString(1),rst.getString(2), rst.getString(3));
    }

    public ItemDTO selectedItem(String newValue) throws SQLException, ClassNotFoundException {
        /*Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Item WHERE code=?");
        pstm.setString(1, newItemCode + "");
        ResultSet rst = pstm.executeQuery();
        rst.next();*/
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM Item WHERE code=?",newValue);
        rst.next();

        return new ItemDTO(rst.getString(1), rst.getString(2), rst.getBigDecimal(3), rst.getInt(4));
    }

    public boolean existsCustomer(String id) throws SQLException, ClassNotFoundException {
        /*Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT id FROM Customer WHERE id=?");
        pstm.setString(1, id);
        return pstm.executeQuery().next();*/
        ResultSet rst = SQLUtil.executeQuery("SELECT id FROM Customer WHERE id=?",id);
        return rst.next();
    }

    public boolean existsItem(String code) throws SQLException, ClassNotFoundException {
        /*Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT code FROM Item WHERE code=?");
        pstm.setString(1, code);
        return pstm.executeQuery().next();*/
        ResultSet rst = SQLUtil.executeQuery("SELECT code FROM Item WHERE code=?",code);
        return rst.next();
    }

    public String generateNewOrderID() throws SQLException, ClassNotFoundException {
        /*Connection connection = DBConnection.getDbConnection().getConnection();
            Statement stm = connection.createStatement();
            ResultSet rst = stm.executeQuery("SELECT oid FROM `Orders` ORDER BY oid DESC LIMIT 1;");

            return rst.next() ? String.format("OID-%03d", (Integer.parseInt(rst.getString("oid").replace("OID-", "")) + 1)) : "OID-001"*/
        ResultSet rst = SQLUtil.executeQuery("SELECT oid FROM `Orders` ORDER BY oid DESC LIMIT 1;");
        return rst.next() ? String.format("OID-%03d", (Integer.parseInt(rst.getString("oid").replace("OID-", "")) + 1)) : "OID-001";
    }

    public ArrayList<CustomerDTO> getAllCustomerID() throws SQLException, ClassNotFoundException {
        /*Connection connection = DBConnection.getDbConnection().getConnection();
            Statement stm = connection.createStatement();
            ResultSet rst = stm.executeQuery("SELECT * FROM Customer");

            while (rst.next()) {
                cmbCustomerId.getItems().add(rst.getString("id"));*/
        ResultSet rst =SQLUtil.executeQuery("SELECT * FROM Customer");
        ArrayList<CustomerDTO> allCustomers=new ArrayList<>();
        while (rst.next()){
            String id=rst.getString(1);
            String name=rst.getString(2);
            String address=rst.getString(3);
            allCustomers.add(new CustomerDTO(id, name, address));
        }
        return allCustomers;
    }

    public ArrayList<ItemDTO> getAllItemCode() throws SQLException, ClassNotFoundException {
        /*Connection connection = DBConnection.getDbConnection().getConnection();
            Statement stm = connection.createStatement();
            ResultSet rst = stm.executeQuery("SELECT * FROM Item");
            while (rst.next()) {
                cmbItemCode.getItems().add(rst.getString("code"));*/
        ResultSet rst=SQLUtil.executeQuery("SELECT * FROM Item");

        ArrayList<ItemDTO> allItems=new ArrayList<>();
        while(rst.next()){
            String code=rst.getString(1);
            String description=rst.getString(2);
            BigDecimal unitprice=rst.getBigDecimal(3);
            int qtyOnHand=rst.getInt(4);
            allItems.add(new ItemDTO(code,description,unitprice,qtyOnHand));
        }
        return allItems;

    }

    /*public boolean orderIdExists(String orderId) throws SQLException, ClassNotFoundException {
        connection = DBConnection.getDbConnection().getConnection();
            PreparedStatement stm = connection.prepareStatement("SELECT oid FROM `Orders` WHERE oid=?");
            stm.setString(1, orderId);

        ResultSet rst = SQLUtil.executeQuery("SELECT oid FROM `Orders` WHERE oid=?",orderId);
        return rst.next();
    }*/

    /*public void saveOrderID(String orderID,String orderDate,String customerId){
        /*connection.setAutoCommit(false);
            stm = connection.prepareStatement("INSERT INTO `Orders` (oid, date, customerID) VALUES (?,?,?)");
            stm.setString(1, orderId);
            stm.setDate(2, Date.valueOf(orderDate));
            stm.setString(3, customerId);

            if (stm.executeUpdate() != 1) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;

        if (stm.executeUpdate() != 1) {
            connection.rollback();
            connection.setAutoCommit(true);
            return false;
    }*/

    public ArrayList<ItemDTO> findItem(String newValue) throws SQLException, ClassNotFoundException {
        /*Connection connection = DBConnection.getDbConnection().getConnection();
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Item WHERE code=?");
            pstm.setString(1, code);
            ResultSet rst = pstm.executeQuery();
            rst.next();*/

        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM Item WHERE code=?",newValue);
        rst.next();

        ArrayList<ItemDTO> items=new ArrayList<>();
        while(rst.next()){
            String code=rst.getString(1);
            String description=rst.getString(2);
            BigDecimal unitprice=rst.getBigDecimal(3);
            int qtyOnHand=rst.getInt(4);
            items.add(new ItemDTO(code,description,unitprice,qtyOnHand));
        }
        return items;

    }

}
