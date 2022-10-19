package dao.dto.impl;

import dao.dto.ICustomerDTODAO;
import dao.impl.ConnectionDAO;
import model.dto.CustomerDTO;
import utils.AppUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDTODAO implements ICustomerDTODAO {
    private static CustomerDTODAO instance;

    private CustomerDTODAO() {
    }

    public static CustomerDTODAO getInstance() {
        if (instance == null) instance = new CustomerDTODAO();
        return instance;
    }

    private final ConnectionDAO connectionDAO = ConnectionDAO.getInstance();

    private static final String SELECT_CUSTOMER_RANK =
            "SELECT customer.image as customer_image\n" +
                    ", customer.full_name AS customer_full_name\n" +
                    ", customer.address AS customer_address\n" +
                    ", customer.phone_number AS customer_phone_number\n" +
                    ", COUNT(p_order.customer_id) AS order_count\n" +
                    "FROM customer\n" +
                    "JOIN p_order\n" +
                    "ON p_order.customer_id = customer.id\n" +
                    "GROUP BY p_order.customer_id \n" +
                    "ORDER BY COUNT(p_order.customer_id) DESC \n" +
                    "LIMIT 0,5;";

    @Override
    public List<CustomerDTO> selectCustomerRank() {
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        try {
            Connection connection = connectionDAO.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CUSTOMER_RANK);
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println(this.getClass() + " selectCustomerRank " + preparedStatement);
            while (rs.next()) {
                CustomerDTO customerDTO = getCustomerDTOFromResultSet(rs);
                customerDTOList.add(customerDTO);
            }
            return customerDTOList;
        } catch (SQLException e) {
            AppUtils.printSQLException(e);
        }
        return null;
    }

    private CustomerDTO getCustomerDTOFromResultSet(ResultSet rs) throws SQLException {
        String customerImage = rs.getString("customer_image");
        String customerFullName = rs.getString("customer_full_name");
        String customerAddress = rs.getString("customer_address");
        String customerPhoneNumber = rs.getString("customer_phone_number");
        int orderCount = rs.getInt("order_count");
        CustomerDTO customerDTO = new CustomerDTO(
                customerImage,
                customerFullName,
                customerAddress,
                customerPhoneNumber,
                orderCount
        );
        return customerDTO;
    }
}