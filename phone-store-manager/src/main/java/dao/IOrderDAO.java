package dao;

import model.Order;

import java.util.List;

public interface IOrderDAO {
    public int getNoOfRecords();

    void insertOrder(Order order);

    Order selectOrder(String id);

    List<Order> selectAllOrder();

    List<Order> selectAllOrderByStatus(int statusId);

    boolean deleteOrder(String id);

    void updateOrder(Order order);

    void updateStatusAndConfirm(String orderId, String userId, int status);
}
