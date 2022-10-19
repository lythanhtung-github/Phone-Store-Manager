package dao.dto;

import model.dto.OrderDayDTO;

import java.util.List;

public interface IOrderDayDTODAO {
    List<OrderDayDTO> selectOrderDayRank();
}
