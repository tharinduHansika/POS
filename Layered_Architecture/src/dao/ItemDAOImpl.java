package dao;

import db.DBConnection;
import model.ItemDTO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ItemDAOImpl {
    public ArrayList <ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT * FROM Item");
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
}
