package dao;

import model.OrderItem;

import java.util.List;

public interface IOrderItemDAO {
    int getNoOfRecords();

    void insertOrderItem(OrderItem orderItem);

    OrderItem selectOrderItem(int id);

    boolean deleteOrderItem(int id);

    List<OrderItem> selectOrderItemByOrderId(String orderId);
}
