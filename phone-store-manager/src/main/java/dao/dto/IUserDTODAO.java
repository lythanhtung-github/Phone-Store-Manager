package dao.dto;

import model.dto.UserDTO;

import java.util.List;

public interface IUserDTODAO {
    List<UserDTO> selectUserRank();
}
