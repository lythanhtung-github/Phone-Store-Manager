package dao.dto.impl;

import dao.dto.IOrderDayDTODAO;
import dao.impl.ConnectionDAO;
import model.dto.CustomerDTO;
import model.dto.OrderDayDTO;
import utils.AppUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderDayDTODAO implements IOrderDayDTODAO {
    private static OrderDayDTODAO instance;

    private OrderDayDTODAO() {
    }

    public static OrderDayDTODAO getInstance() {
        if (instance == null) instance = new OrderDayDTODAO();
        return instance;
    }

    private final ConnectionDAO connectionDAO = ConnectionDAO.getInstance();

    private static final String SELECT_ORDER_DAY =
            "SELECT customer.full_name as customer_full_name\n" +
                    ", customer.address as customer_address\n" +
                    ", p_order.grand_total as order_grand_total\n" +
                    ", status.type as status_type\n" +
                    "FROM p_order\n" +
                    "JOIN customer \n" +
                    "ON p_order.customer_id = customer.id\n" +
                    "JOIN status\n" +
                    " ON p_order.status_id = status.id\n" +
                    "WHERE DATE(p_order.create_time) = ?\n";

    @Override
    public List<OrderDayDTO> selectOrderDayRank() {
        List<OrderDayDTO> orderDayDTOList = new ArrayList<>();
        try {
            Connection connection = connectionDAO.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ORDER_DAY);
            LocalDate now = LocalDate.now();
            preparedStatement.setString(1, AppUtils.localDateToString(now));
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println(this.getClass() + " selectOrderDayRank " + preparedStatement);
            while (rs.next()) {
                OrderDayDTO orderDayDTO = getOrderDayDTOFromResultSet(rs);
                orderDayDTOList.add(orderDayDTO);
            }
            return orderDayDTOList;
        } catch (SQLException e) {
            AppUtils.printSQLException(e);
        }
        return null;
    }

    private OrderDayDTO getOrderDayDTOFromResultSet(ResultSet rs) throws SQLException {
        String customerFullName = rs.getString("customer_full_name");
        String customerAddress = rs.getString("customer_address");
        int orderGrandTotal = rs.getInt("order_grand_total");
        String statusType = rs.getString("status_type");
        OrderDayDTO orderDayDTO = new OrderDayDTO(
                customerFullName,
                customerAddress,
                orderGrandTotal,
                statusType
        );
        return orderDayDTO;
    }
}

