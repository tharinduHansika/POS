package dao;

import model.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDAO {
    ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException;

    boolean saveCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;

    boolean upadteCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;

    boolean existsCustomer(String id) throws SQLException, ClassNotFoundException;

    boolean btnDeleteCustomerOnAction(String id) throws SQLException, ClassNotFoundException;

    String generateNewID() throws SQLException, ClassNotFoundException;
}
