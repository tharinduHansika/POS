package dao;

import db.DBConnection;
import model.CustomerDTO;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO{
    @Override
    public ArrayList<CustomerDTO> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public CustomerDTO search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exist(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomerByAddress(String address) {
        return null;
    }

    /*public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
            *//*Connection connection = DBConnection.getDbConnection().getConnection();
            Statement stm = connection.createStatement();
            ResultSet rst = stm.executeQuery("SELECT * FROM Customer");*//*
            ResultSet rst=SQLUtil.executeQuery("SELECT * FROM Customer");
            ArrayList<CustomerDTO> allCustomers=new ArrayList<>();
            while (rst.next()){
                String id=rst.getString(1);
                String name=rst.getString(2);
                String address=rst.getString(3);
                allCustomers.add(new CustomerDTO(id, name, address));
            }
            return allCustomers;
    }

    @Override
    public boolean saveCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {

        *//*Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO Customer (id,name, address) VALUES (?,?,?)");
        pstm.setString(1, customerDTO.getId());
        pstm.setString(2, customerDTO.getName());
        pstm.setString(3, customerDTO.getAddress()
        );
        return pstm.executeUpdate()>0;*//*
        return SQLUtil.executeUpdate("INSERT INTO Customer (id,name, address) VALUES (?,?,?)",customerDTO.getId(),customerDTO.getName(),customerDTO.getAddress());
    }

    @Override
    public boolean upadteCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        *//*Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("UPDATE Customer SET name=?, address=? WHERE id=?");
        pstm.setString(1, customerDTO.getName());
        pstm.setString(2, customerDTO.getAddress());
        pstm.setString(3, customerDTO.getId());
        return pstm.executeUpdate()>0;*//*
        return SQLUtil.executeUpdate("UPDATE Customer SET name=?, address=? WHERE id=?",customerDTO.getName(),customerDTO.getAddress(),customerDTO.getId());
    }

    @Override
    public boolean existsCustomer(String id) throws SQLException, ClassNotFoundException {
        *//*Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT id FROM Customer WHERE id=?");
        pstm.setString(1, id);
        return pstm.executeQuery().next();*//*
        ResultSet rst = SQLUtil.executeQuery("SELECT id FROM Customer WHERE id=?",id);
        return rst.next();
    }

    @Override
    public boolean btnDeleteCustomerOnAction(String id) throws SQLException, ClassNotFoundException {
        *//*Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("DELETE FROM Customer WHERE id=?");
        pstm.setString(1, id);
        return pstm.executeUpdate()>0;*//*
        return SQLUtil.executeUpdate("DELETE FROM Customer WHERE id=?");
    }*/

    /*@Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        *//*Connection connection = DBConnection.getDbConnection().getConnection();
        ResultSet rst = connection.createStatement().executeQuery("SELECT id FROM Customer ORDER BY id DESC LIMIT 1;");*//*
        ResultSet rst=SQLUtil.executeQuery("SELECT id FROM Customer ORDER BY id DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("id");
            int newCustomerId = Integer.parseInt(id.replace("C00-", "")) + 1;
            return String.format("C00-%03d", newCustomerId);
        }else {
            return "C00-001";
        }
    }*/

    //-------------------------------------------without unique methods code--------------------------------------------
    /*@Override
    public ArrayList<CustomerDTO> getAll() throws SQLException, ClassNotFoundException {
        *//*Connection connection = DBConnection.getDbConnection().getConnection();
            Statement stm = connection.createStatement();
            ResultSet rst = stm.executeQuery("SELECT * FROM Customer");*//*
        ResultSet rst=SQLUtil.executeQuery("SELECT * FROM Customer");
        ArrayList<CustomerDTO> allCustomers=new ArrayList<>();
        while (rst.next()){
            String id=rst.getString(1);
            String name=rst.getString(2);
            String address=rst.getString(3);
            allCustomers.add(new CustomerDTO(id, name, address));
        }
        return allCustomers;
    }

    @Override
    public boolean save(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        *//*Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO Customer (id,name, address) VALUES (?,?,?)");
        pstm.setString(1, customerDTO.getId());
        pstm.setString(2, customerDTO.getName());
        pstm.setString(3, customerDTO.getAddress()
        );
        return pstm.executeUpdate()>0;*//*
        return SQLUtil.executeUpdate("INSERT INTO Customer (id,name, address) VALUES (?,?,?)",customerDTO.getId(),customerDTO.getName(),customerDTO.getAddress());
    }

    @Override
    public CustomerDTO search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM Customer WHERE id=?", id);
        if (rst.next()) {
            return new CustomerDTO(rst.getString(1), rst.getString(2), rst.getString(3));
        }
        return null;
    }

    @Override
    public boolean update(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("UPDATE Customer SET name=?, address=? WHERE id=?",customerDTO.getName(),customerDTO.getAddress(),customerDTO.getId());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT id FROM Customer WHERE id=?",id);
        return rst.next();
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("DELETE FROM Customer WHERE id=?");
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst=SQLUtil.executeQuery("SELECT id FROM Customer ORDER BY id DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("id");
            int newCustomerId = Integer.parseInt(id.replace("C00-", "")) + 1;
            return String.format("C00-%03d", newCustomerId);
        }else {
            return "C00-001";
        }
    }*/

}
