package dao;

import model.Role;

import java.util.List;

public interface IRoleDAO {
    Role selectRole(int id);

    List<Role> selectAllRole();
}
