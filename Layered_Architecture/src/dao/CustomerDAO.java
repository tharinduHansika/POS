package dao;

import model.CustomerDTO;

import java.util.ArrayList;

public interface CustomerDAO extends CrudDAO<CustomerDTO,String>{
    public ArrayList<CustomerDTO> getAllCustomerByAddress(String address);

}
