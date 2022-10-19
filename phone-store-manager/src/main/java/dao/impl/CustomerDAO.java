package dao.impl;

import dao.ICustomerDAO;
import model.Customer;
import utils.AppUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO implements ICustomerDAO {
    private static CustomerDAO instance;

    private CustomerDAO() {
    }

    public static CustomerDAO getInstance() {
        if (instance == null) instance = new CustomerDAO();
        return instance;
    }

    private final ConnectionDAO connectionDAO = ConnectionDAO.getInstance();

    private static final String INSERT_CUSTOMER =
            "INSERT INTO customer(id, full_name, email, phone_number, address, image, password, created_time) VALUE (?,?,?,?,?,?,?,?);";

    private static final String SELECT_CUSTOMER_BY_ID =
            "SELECT * FROM customer WHERE id =?;";

    private static final String SELECT_ALL_CUSTOMERS =
            "SELECT * FROM customer;";

    private static final String DELETE_CUSTOMER =
            "DELETE FROM customer WHERE id = ?;";

    private static final String UPDATE_CUSTOMER =
            "UPDATE customer SET full_name =?, email =?, phone_number =?, address =?, image =?,password =?, updated_time =? WHERE id = ?;";

    private static final String SELECT_USER_BY_PHONE_NUMBER =
            "SELECT * FROM customer WHERE phone_number =?;";

    private static final String SELECT_USER_BY_EMAIL =
            "SELECT * FROM customer WHERE email =?;";

    private static final String SELECT_ALL_CUSTOMER_PAGGING_FILLTER =
            "SELECT SQL_CALC_FOUND_ROWS * FROM customer WHERE id LIKE ? OR full_name LIKE ? OR email LIKE ? OR phone_number LIKE ? OR address LIKE ? limit ?,?;";

    private static final String SELECT_CUSTOMER_BY_EMAIL_PASSWORD =
            "SELECT * FROM customer WHERE email = ? AND password = ?";
    private int noOfRecords;

    public int getNoOfRecords() {
        return noOfRecords;
    }

    public void setNoOfRecords(int noOfRecords) {
        this.noOfRecords = noOfRecords;
    }

    @Override
    public void insertCustomer(Customer customer) {
        try (Connection connection = connectionDAO.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CUSTOMER)) {
            preparedStatement.setString(1, customer.getId());
            preparedStatement.setString(2, customer.getFullName());
            preparedStatement.setString(3, customer.getEmail());
            preparedStatement.setString(4, customer.getPhoneNumber());
            preparedStatement.setString(5, customer.getAddress());
            preparedStatement.setString(6, customer.getImage());
            preparedStatement.setString(7, customer.getPassword());
            preparedStatement.setString(8, AppUtils.localDateTimeToString(customer.getCreatedTime()));
            System.out.println(this.getClass() + " insertCustomer " + preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            AppUtils.printSQLException(e);
        }
    }

    @Override
    public Customer selectCustomer(String id) {
        try {
            Connection connection = connectionDAO.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CUSTOMER_BY_ID);
            preparedStatement.setString(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println(this.getClass() + " selectCustomer: " + preparedStatement);
            while (rs.next()) {
                return getCustomerFromResultSet(rs);
            }
        } catch (SQLException e) {
            AppUtils.printSQLException(e);
        }
        return null;
    }

    @Override
    public Customer selectCustomerByPhoneNumber(String phoneNumberStr) {
        try {
            Connection connection = connectionDAO.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_PHONE_NUMBER);
            preparedStatement.setString(1, phoneNumberStr);
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println(this.getClass() + " selectCustomerByPhoneNumber: " + preparedStatement);
            while (rs.next()) {
                return getCustomerFromResultSet(rs);
            }
        } catch (SQLException e) {
            AppUtils.printSQLException(e);
        }
        return null;
    }

    @Override
    public Customer selectCustomerByEmail(String emailStr) {
        try {
            Connection connection = connectionDAO.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_EMAIL);
            preparedStatement.setString(1, emailStr);
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println(this.getClass() + " selectCustomerByEmail: " + preparedStatement);
            while (rs.next()) {
                return getCustomerFromResultSet(rs);
            }
        } catch (SQLException e) {
            AppUtils.printSQLException(e);
        }
        return null;
    }

    @Override
    public List<Customer> selectAllCustomer() {
        List<Customer> customerList = new ArrayList<>();
        try (Connection connection = connectionDAO.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CUSTOMERS)) {
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println(this.getClass() + " selectAllCustomer " + preparedStatement);
            while (rs.next()) {
                Customer customer = getCustomerFromResultSet(rs);
                customerList.add(customer);
            }
        } catch (SQLException e) {
            AppUtils.printSQLException(e);
        }
        return customerList;
    }

    @Override
    public boolean deleteCustomer(String id) {
        boolean rowDeleted = false;
        try (Connection connection = connectionDAO.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CUSTOMER)) {
            preparedStatement.setString(1, id);
            System.out.println(this.getClass() + " deleteCustomer " + preparedStatement);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            AppUtils.printSQLException(e);
        }
        return rowDeleted;
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        boolean rowUpdated = false;
        try (Connection connection = connectionDAO.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CUSTOMER)) {
            preparedStatement.setString(1, customer.getFullName());
            preparedStatement.setString(2, customer.getEmail());
            preparedStatement.setString(3, customer.getPhoneNumber());
            preparedStatement.setString(4, customer.getAddress());
            preparedStatement.setString(5, customer.getImage());
            preparedStatement.setString(6, customer.getPassword());
            preparedStatement.setString(7, AppUtils.localDateTimeToString(customer.getUpdatedTime()));
            preparedStatement.setString(8, customer.getId());
            System.out.println(this.getClass() + " updateCustomer " + preparedStatement);
            rowUpdated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            AppUtils.printSQLException(ex);
        }
        return rowUpdated;
    }

    @Override
    public List<Customer> selectAllCustomersPaggingFilter(int offset, int noOfRecords, String q) {
        List<Customer> customerList = new ArrayList<>();
        PreparedStatement preparedStatement;
        try {
            Connection connection = connectionDAO.getConnection();
            preparedStatement = connection.prepareCall(SELECT_ALL_CUSTOMER_PAGGING_FILLTER);
            preparedStatement.setString(1, "%" + q + "%");
            preparedStatement.setString(2, "%" + q + "%");
            preparedStatement.setString(3, "%" + q + "%");
            preparedStatement.setString(4, "%" + q + "%");
            preparedStatement.setString(5, "%" + q + "%");
            preparedStatement.setInt(6, offset);
            preparedStatement.setInt(7, noOfRecords);

            System.out.println(this.getClass() + " selectAllCustomersPaggingFilter " + preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Customer customer = getCustomerFromResultSet(rs);
                customerList.add(customer);
            }
            rs = preparedStatement.executeQuery("SELECT FOUND_ROWS();");
            if (rs.next())
                this.noOfRecords = rs.getInt(1);
            return customerList;
        } catch (SQLException ex) {
            AppUtils.printSQLException(ex);
        }
        return null;
    }

    @Override
    public Customer selectCustomerByEmailAndPassword(String email, String password) {
        try (Connection connection = connectionDAO.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CUSTOMER_BY_EMAIL_PASSWORD)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println(this.getClass() + " selectCustomerByEmailAndPassword: " + preparedStatement);
            while (rs.next()) {
                return getCustomerFromResultSet(rs);
            }
        } catch (SQLException e) {
            AppUtils.printSQLException(e);
        }
        return null;
    }

    private Customer getCustomerFromResultSet(ResultSet rs) throws SQLException {
        String id = rs.getString("id");
        String fullName = rs.getString("full_name");
        String email = rs.getString("email");
        String phoneNumber = rs.getString("phone_number");
        String address = rs.getString("address");
        String image = rs.getString("image");
        String password = rs.getNString("password");
        Customer customer = new Customer(id, fullName,  email, phoneNumber, address, image, password);
        LocalDateTime createdTime = AppUtils.stringToLocalDateTime(rs.getString("created_time"));
        customer.setCreatedTime(createdTime);
        if (rs.getString("updated_time") != null) {
            LocalDateTime updatedTime = AppUtils.stringToLocalDateTime(rs.getString("updated_time"));
            customer.setUpdatedTime(updatedTime);
        }
        return customer;
    }
}
