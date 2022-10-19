package dao.impl;

import dao.IOrderDAO;
import model.Order;
import utils.AppUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO implements IOrderDAO {
    private static OrderDAO instance;

    private OrderDAO() {
    }

    public static OrderDAO getInstance() {
        if (instance == null) instance = new OrderDAO();
        return instance;
    }

    private final ConnectionDAO connectionDAO = ConnectionDAO.getInstance();

    private static final String INSERT_ORDER =
            "INSERT INTO p_order(id, create_time, grand_total, status_id, customer_id) VALUE (?,?,?,?,?);";

    private static final String SELECT_ORDER_BY_ID =
            "SELECT * FROM p_order WHERE id =?;";

    private static final String SELECT_ALL_ORDERS =
            "SELECT * FROM p_order;";

    private static final String SELECT_ALL_ORDERS_BY_STATUS =
            "SELECT * FROM p_order WHERE status_id = ?;";

    private static final String DELETE_ORDER =
            "DELETE FROM p_order WHERE id = ?;";

    private static final String UPDATE_ORDER =
            "UPDATE p_order SET grand_total =?, status_id =?, customer_id =?, user_id =? WHERE id = ?;";
    private static final String UPDATE_STATUS_AND_CONFIRM =
            "UPDATE p_order SET  status_id =?,user_id =? WHERE id = ?;";

    private int noOfRecords;

    public int getNoOfRecords() {
        return noOfRecords;
    }

    public void setNoOfRecords(int noOfRecords) {
        this.noOfRecords = noOfRecords;
    }

    @Override
    public void insertOrder(Order order) {
        try (Connection connection = connectionDAO.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDER)) {
            preparedStatement.setString(1, order.getId());
            preparedStatement.setString(2, AppUtils.localDateTimeToString(order.getCreatedTime()));
            preparedStatement.setInt(3, order.getGrandTotal());
            preparedStatement.setInt(4, order.getStatusId());
            preparedStatement.setString(5, order.getCustomerId());
            System.out.println(this.getClass() + " insertOrder " + preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            AppUtils.printSQLException(e);
        }
    }

    @Override
    public Order selectOrder(String id) {
        try {
            Connection connection = connectionDAO.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ORDER_BY_ID);
            preparedStatement.setString(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println(this.getClass() + " selectOrder: " + preparedStatement);
            while (rs.next()) {
                return getOrderFromResultSet(rs);
            }
        } catch (SQLException e) {
            AppUtils.printSQLException(e);
        }
        return null;
    }

    @Override
    public List<Order> selectAllOrder() {
        List<Order> orderList = new ArrayList<>();
        try (Connection connection = connectionDAO.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ORDERS)) {
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println(this.getClass() + " selectAllOrder " + preparedStatement);
            while (rs.next()) {
                Order order = getOrderFromResultSet(rs);
                orderList.add(order);
            }
        } catch (SQLException e) {
            AppUtils.printSQLException(e);
        }
        return orderList;
    }

    @Override
    public List<Order> selectAllOrderByStatus(int statusId) {
        List<Order> orderList = new ArrayList<>();
        try (Connection connection = connectionDAO.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ORDERS_BY_STATUS)) {
            preparedStatement.setInt(1, statusId);
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println(this.getClass() + " selectAllOrderByStatus " + preparedStatement);
            while (rs.next()) {
                Order order = getOrderFromResultSet(rs);
                orderList.add(order);
            }
        } catch (SQLException e) {
            AppUtils.printSQLException(e);
        }
        return orderList;
    }

    @Override
    public boolean deleteOrder(String id) {
        boolean rowDeleted = false;
        try (Connection connection = connectionDAO.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ORDER)) {
            preparedStatement.setString(1, id);
            System.out.println(this.getClass() + " deleteOrder " + preparedStatement);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            AppUtils.printSQLException(e);
        }
        return rowDeleted;
    }

    @Override
    public void updateOrder(Order order) {
        try (Connection connection = connectionDAO.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ORDER)) {
            preparedStatement.setInt(1, order.getGrandTotal());
            preparedStatement.setInt(2, order.getStatusId());
            preparedStatement.setString(3, order.getCustomerId());
            preparedStatement.setString(4, order.getUserId());
            preparedStatement.setString(5, order.getId());
            System.out.println(this.getClass() + " updateOrder " + preparedStatement);
             preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            AppUtils.printSQLException(ex);
        }
    }

    @Override
    public void updateStatusAndConfirm(String orderId,String userId, int status) {
        try (Connection connection = connectionDAO.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STATUS_AND_CONFIRM)) {
            preparedStatement.setString(3, orderId);
            preparedStatement.setString(2, userId);
            preparedStatement.setInt(1, status);
            System.out.println(this.getClass() + " updateStatus " + preparedStatement);
              preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            AppUtils.printSQLException(ex);
        }
    }

    private Order getOrderFromResultSet(ResultSet rs) throws SQLException {
        String id = rs.getString("id");
        LocalDateTime createdTime = AppUtils.stringToLocalDateTime(rs.getString("create_time"));
        int grandTotal = rs.getInt("grand_total");
        int statusId = rs.getInt("status_id");
        String customerId = rs.getString("customer_id");
        Order order = new Order(id, createdTime, grandTotal, statusId, customerId);
        if (rs.getString("user_id") != null) {
            String userId = rs.getString("user_id");
            order.setUserId(userId);
        }
        return order;
    }
}
