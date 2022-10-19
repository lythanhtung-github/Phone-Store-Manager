package dao.impl;

import dao.IOrderItemDAO;
import model.OrderItem;
import utils.AppUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDAO implements IOrderItemDAO {
    private static OrderItemDAO instance;
    private OrderItemDAO() {
    }
    public static OrderItemDAO getInstance() {
        if (instance == null) instance = new OrderItemDAO();
        return instance;
    }
    private final ConnectionDAO connectionDAO = ConnectionDAO.getInstance();

    private static final String INSERT_ORDER_ITEM =
            "INSERT INTO order_item(product_id, price, quantity, order_id) VALUE (?,?,?,?);";

    private static final String SELECT_ORDER_ITEM_BY_ID =
            "SELECT * FROM order_item WHERE id = ?;";

    private static final String DELETE_ORDER_ITEM =
            "DELETE FROM order_item WHERE id = ?;";

    private static final String SELECT_ORDER_ITEM_BY_ORDER_ID =
            "SELECT * FROM order_item WHERE order_id = ?;";

    private int noOfRecords;

    @Override
    public int getNoOfRecords() {
        return noOfRecords;
    }

    public void setNoOfRecords(int noOfRecords) {
        this.noOfRecords = noOfRecords;
    }

    @Override
    public void insertOrderItem(OrderItem orderItem) {
        try (Connection connection = connectionDAO.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDER_ITEM)) {

            preparedStatement.setString(1, orderItem.getProductId());
            preparedStatement.setInt(2, orderItem.getPrice());
            preparedStatement.setInt(3,orderItem.getQuantity());
            preparedStatement.setString(4,orderItem.getOrderId());

            System.out.println(this.getClass() + " insertOrderItem " + preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            AppUtils.printSQLException(e);
        }
    }

    @Override
    public OrderItem selectOrderItem(int id) {
        try {
            Connection connection = connectionDAO.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ORDER_ITEM_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println(this.getClass() + " selectOrderItem: " + preparedStatement);
            while (rs.next()) {
                return getOrderItemFromResultSet(rs);
            }
        } catch (SQLException e) {
            AppUtils.printSQLException(e);
        }
        return null;
    }

    private OrderItem getOrderItemFromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String productId = rs.getString("product_id");
        int price = rs.getInt("price");
        int quantity = rs.getInt("quantity");
        String orderId = rs.getString("order_id");
        OrderItem orderItem = new OrderItem(id, productId, price, quantity, orderId);
        return orderItem;
    }

    @Override
    public boolean deleteOrderItem(int id) {
        boolean rowDeleted = false;
        try (Connection connection = connectionDAO.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ORDER_ITEM)) {
            preparedStatement.setInt(1, id);
            System.out.println(this.getClass() + " deleteOrderItem " + preparedStatement);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            AppUtils.printSQLException(e);
        }
        return rowDeleted;
    }

    @Override
    public List<OrderItem> selectOrderItemByOrderId(String orderId) {
        List<OrderItem> orderItemList = new ArrayList<>();
        try (Connection connection = connectionDAO.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ORDER_ITEM_BY_ORDER_ID)) {
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println(this.getClass() + " selectOrderItemByOrderId " + preparedStatement);
            while (rs.next()) {
                OrderItem orderItem = getOrderItemFromResultSet(rs);
                orderItemList.add(orderItem);
            }
        } catch (SQLException e) {
            AppUtils.printSQLException(e);
        }
        return orderItemList;
    }
}
