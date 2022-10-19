package dao.dto;

import model.dto.CustomerDTO;

import java.util.List;

public interface ICustomerDTODAO {
    List<CustomerDTO> selectCustomerRank();
}
