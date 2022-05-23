package bo;

import dao.CrudDAO;
import dao.custom.*;
import dao.custom.impl.*;
import model.CustomerDTO;
import model.ItemDTO;
import model.OrderDTO;
import model.OrderDetailDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PurchaseOrderBOImpl {

    private CustomerDAO customerDAO = new CustomerDAOImpl();
    private ItemDAO itemDAO = new ItemDAOImpl();
    private OrderDAO orderDAO=new OrderDAOImpl();
    private OrderDetailDAO orderDetailsDAO = new OrderDetailDAOImpl();
    private QueryDAO queryDAO=new QueryDAOImpl();

    public boolean purchaseOrder(String orderId, LocalDate orderDate, String customerId, List<OrderDetailDTO> orderDetails) throws SQLException, ClassNotFoundException {
        Connection connection = null;

            CrudDAO<OrderDTO, String> orderDAO = new OrderDAOImpl();
            /*if order id already exist*/
            if (orderDAO.exist(orderId)) {

            }

            connection.setAutoCommit(false);

            //DI
            boolean save = orderDAO.save(new OrderDTO(orderId, orderDate, customerId));

            if (!save) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

            for (OrderDetailDTO detail : orderDetails) {
                boolean save1 = orderDetailsDAO.save(detail);
                if (!save1) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }

                //Search & Update Item
                //ItemDTO item = findItem(detail.getItemCode());
                ItemDTO item =null;
                item.setQtyOnHand(item.getQtyOnHand() - detail.getQty());

                //update item
                boolean update = itemDAO.update(new ItemDTO(item.getCode(), item.getDescription(), item.getUnitPrice(), item.getQtyOnHand()));

                if (!update) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }
            }

            connection.commit();
            connection.setAutoCommit(true);
            return true;
    }

    public CustomerDTO searchCustomer(String ID) throws SQLException, ClassNotFoundException {
        return customerDAO.search(ID);
    }

    public ItemDTO searchItem(String ID) throws SQLException, ClassNotFoundException {
        return itemDAO.search(ID);
    }

    public String generatteNewOrderID() throws SQLException, ClassNotFoundException {
        return orderDAO.generateNewID();
    }

    public ArrayList<CustomerDTO> loadAllCustomerID() throws SQLException, ClassNotFoundException {
        return customerDAO.getAll();
    }

    public ArrayList<ItemDTO> loadAllItemID() throws SQLException, ClassNotFoundException {
        return itemDAO.getAll();
    }

    public boolean isItemExists(String ID) throws SQLException, ClassNotFoundException {
        return itemDAO.exist(ID);
    }

    public boolean isCustomerExists(String ID) throws SQLException, ClassNotFoundException {
        return customerDAO.exist(ID);
    }
}
