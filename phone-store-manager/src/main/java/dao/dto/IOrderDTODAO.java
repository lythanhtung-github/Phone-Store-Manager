package dao.dto;

import model.dto.OrderDTO;

import java.util.List;

public interface IOrderDTODAO {
    int getNoOfRecords();

    OrderDTO selectOrder(String id);


    List<OrderDTO> selectAllOrdersPaggingFilter(int offset, int noOfRecords, String q, int statusId);
}
