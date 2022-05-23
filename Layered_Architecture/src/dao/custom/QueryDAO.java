package dao.custom;

import model.CustomDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface QueryDAO {
    public ArrayList<CustomDTO> searchOrderByOrderID(String id) throws ClassNotFoundException, SQLException;
}
