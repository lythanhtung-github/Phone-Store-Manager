package dao.dto;

import model.OrderItem;
import model.dto.OrderDTO;
import model.dto.OrderItemDTO;

import java.util.List;

public interface IOrderItemDTODAO {

    int getNoOfRecords();

    List<OrderItemDTO> selectOrderItemByOrderId(String orderId);
}
