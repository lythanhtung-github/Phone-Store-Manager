package dao.dto.impl;

import dao.dto.IOrderItemDTODAO;
import dao.impl.ConnectionDAO;
import model.dto.OrderItemDTO;
import utils.AppUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDTODAO implements IOrderItemDTODAO {
    private static OrderItemDTODAO instance;

    private OrderItemDTODAO() {
    }

    public static OrderItemDTODAO getInstance() {
        if (instance == null) instance = new OrderItemDTODAO();
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

    private static final String SELECT_ALL_ITEM_BY_ORDER_ID =
            " SELECT SQL_CALC_FOUND_ROWS \n" +
                    " order_item.id AS order_item_id\n" +
                    " , order_item.product_id AS product_id\n" +
                    " , product.name AS product_name\n" +
                    " , order_item.price AS product_price\n" +
                    " , order_item.quantity AS quantity\n" +
                    " , (order_item.price*order_item.quantity) AS total\n" +
                    " , order_item.order_id AS order_id\n" +
                    " FROM order_item JOIN product\n" +
                    " ON order_item.product_id = product.id\n" +
                    " WHERE order_id = ?";

    @Override
    public List<OrderItemDTO> selectOrderItemByOrderId(String orderId) {
        List<OrderItemDTO> orderItemDTOList = new ArrayList<>();
        PreparedStatement preparedStatement;
        try {
            Connection connection = connectionDAO.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_ALL_ITEM_BY_ORDER_ID);
            preparedStatement.setString(1, orderId);
            System.out.println(this.getClass() + " selectAllUsersPaggingFilter " + preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                OrderItemDTO orderItemDTO = getOrderItemDTOFromResultSet(rs);
                orderItemDTOList.add(orderItemDTO);
            }
            rs = preparedStatement.executeQuery("SELECT FOUND_ROWS();");
            if (rs.next())
                this.noOfRecords = rs.getInt(1);
            return orderItemDTOList;
        } catch (SQLException ex) {
            AppUtils.printSQLException(ex);
        }
        return null;
    }

    private OrderItemDTO getOrderItemDTOFromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("order_item_id");
        String productId = rs.getString("product_id");
        String productName = rs.getString("product_name");
        int price = rs.getInt("product_price");
        int quantity = rs.getInt("quantity");
        int total = rs.getInt("total");
        String orderId = rs.getString("order_id");

        OrderItemDTO orderItemDTO = new OrderItemDTO(
                id,
                productId,
                productName,
                price,
                quantity,
                total,
                orderId
        );
        return orderItemDTO;
    }
}
