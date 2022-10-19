package dao.dto.impl;

import dao.dto.IOrderDTODAO;
import dao.impl.ConnectionDAO;
import model.dto.OrderDTO;
import utils.AppUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDTODAO implements IOrderDTODAO {
    private static OrderDTODAO instance;

    private OrderDTODAO() {
    }

    public static OrderDTODAO getInstance() {
        if (instance == null) instance = new OrderDTODAO();
        return instance;
    }

    private final ConnectionDAO connectionDAO = ConnectionDAO.getInstance();

    private int noOfRecords;

    @Override
    public int getNoOfRecords() {
        return noOfRecords;
    }

    public void setNoOfRecords(int noOfRecords) {
        this.noOfRecords = noOfRecords;
    }

    private static final String SELECT_ALL_ORDER_PAGGING_FILLTER =
            "SELECT SQL_CALC_FOUND_ROWS p_order.id as order_id\n" +
                    ", customer.full_name as customer_fullname\n" +
                    ", customer.email as customer_email\n" +
                    ", customer.phone_number as customer_phone\n" +
                    ", customer.address as customer_address\n" +
                    ", p_order.grand_total as order_total\n" +
                    ", status.type as status_type\n" +
                    ", p_order.create_time as order_createtime\n" +
                    ", p_order.user_id as user_id\n" +
                    "FROM p_order \n" +
                    "JOIN customer ON p_order.customer_id = customer.id\n" +
                    "JOIN status ON p_order.status_id = status.id\n" +
                    "WHERE (p_order.id LIKE ?\n" +
                    "OR customer.full_name LIKE ?\n" +
                    "OR customer.email LIKE ?\n" +
                    "OR customer.phone_number LIKE ?\n" +
                    "OR customer.address LIKE ?\n" +
                    "OR p_order.grand_total LIKE ?\n" +
                    "OR status.type LIKE ?)\n" +
                    "AND p_order.status_id = ?\n" +
                    "LIMIT ?,?";

    private static final String SELECT_ALL_ORDER_PAGGING_FILLTER_ALL =
            "SELECT SQL_CALC_FOUND_ROWS p_order.id as order_id\n" +
                    ", customer.full_name as customer_fullname\n" +
                    ", customer.email as customer_email\n" +
                    ", customer.phone_number as customer_phone\n" +
                    ", customer.address as customer_address\n" +
                    ", p_order.grand_total as order_total\n" +
                    ", status.type as status_type\n" +
                    ", p_order.create_time as order_createtime\n" +
                    ", p_order.user_id as user_id\n" +
                    "FROM p_order \n" +
                    "JOIN customer ON p_order.customer_id = customer.id\n" +
                    "JOIN status ON p_order.status_id = status.id\n" +
                    "WHERE p_order.id LIKE ?\n" +
                    "OR customer.full_name LIKE ?\n" +
                    "OR customer.email LIKE ?\n" +
                    "OR customer.phone_number LIKE ?\n" +
                    "OR customer.address LIKE ?\n" +
                    "OR p_order.grand_total LIKE ?\n" +
                    "OR status.type LIKE ?\n" +
                    "LIMIT ?,?";


    private static final String SELECT_ORDER_BY_ID =
            "SELECT SQL_CALC_FOUND_ROWS p_order.id as order_id\n" +
                    ", customer.full_name as customer_fullname\n" +
                    ", customer.email as customer_email\n" +
                    ", customer.phone_number as customer_phone\n" +
                    ", customer.address as customer_address\n" +
                    ", p_order.grand_total as order_total\n" +
                    ", status.type as status_type\n" +
                    ", p_order.create_time as order_createtime\n" +
                    ", p_order.user_id as user_id\n" +
                    "FROM p_order \n" +
                    "JOIN customer ON p_order.customer_id = customer.id\n" +
                    "JOIN status ON p_order.status_id = status.id\n" +
                    "WHERE p_order.id = ?;";

    @Override
    public OrderDTO selectOrder(String id) {
        try {
            Connection connection = connectionDAO.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ORDER_BY_ID);
            preparedStatement.setString(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println(this.getClass() + " selectOrder: " + preparedStatement);
            while (rs.next()) {
                return getOrderDTOFromResultSet(rs);
            }
        } catch (SQLException e) {
            AppUtils.printSQLException(e);
        }
        return null;
    }

    @Override
    public List<OrderDTO> selectAllOrdersPaggingFilter(int offset, int noOfRecords, String q, int statusId) {
        List<OrderDTO> orderDTOList = new ArrayList<>();
        PreparedStatement preparedStatement;
        try {
            Connection connection = connectionDAO.getConnection();
            if (statusId != -1) {
                preparedStatement = connection.prepareStatement(SELECT_ALL_ORDER_PAGGING_FILLTER);
                preparedStatement.setString(1, "%" + q + "%");
                preparedStatement.setString(2, "%" + q + "%");
                preparedStatement.setString(3, "%" + q + "%");
                preparedStatement.setString(4, "%" + q + "%");
                preparedStatement.setString(5, "%" + q + "%");
                preparedStatement.setString(6, "%" + q + "%");
                preparedStatement.setString(7, "%" + q + "%");
                preparedStatement.setInt(8, statusId);
                preparedStatement.setInt(9, offset);
                preparedStatement.setInt(10, noOfRecords);
            } else {
                preparedStatement = connection.prepareStatement(SELECT_ALL_ORDER_PAGGING_FILLTER_ALL);
                preparedStatement.setString(1, "%" + q + "%");
                preparedStatement.setString(2, "%" + q + "%");
                preparedStatement.setString(3, "%" + q + "%");
                preparedStatement.setString(4, "%" + q + "%");
                preparedStatement.setString(5, "%" + q + "%");
                preparedStatement.setString(6, "%" + q + "%");
                preparedStatement.setString(7, "%" + q + "%");
                preparedStatement.setInt(8, offset);
                preparedStatement.setInt(9, noOfRecords);
            }
            System.out.println(this.getClass() + " selectAllUsersPaggingFilter " + preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                OrderDTO orderDTO = getOrderDTOFromResultSet(rs);
                orderDTOList.add(orderDTO);
            }
            rs = preparedStatement.executeQuery("SELECT FOUND_ROWS();");
            if (rs.next())
                this.noOfRecords = rs.getInt(1);
            return orderDTOList;
        } catch (SQLException ex) {
            AppUtils.printSQLException(ex);
        }
        return null;
    }

    private OrderDTO getOrderDTOFromResultSet(ResultSet rs) throws SQLException {
        String id = rs.getString("order_id");
        String customerFullName = rs.getString("customer_fullname");
        String customerEmail = rs.getString("customer_email");
        String customerPhone = rs.getString("customer_phone");
        String customerAddress = rs.getString("customer_address");
        int grandTotal = rs.getInt("order_total");
        String type = rs.getString("status_type");
        String createdTime = rs.getString("order_createtime");
        String userId = rs.getString("user_id");

        OrderDTO orderDTO = new OrderDTO(
                id,
                customerFullName,
                customerEmail,
                customerPhone,
                customerAddress,
                grandTotal,
                type,
                createdTime,
                userId
        );
        return orderDTO;
    }
}