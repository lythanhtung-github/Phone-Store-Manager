package dao;


import model.User;

import java.util.List;

public interface IUserDAO {
    void insertUser(User user);

    User selectUser(String id);

    User selectUserByPhoneNumber(String phoneNumber);

    User selectUserByEmail(String emailStr);

    List<User> selectAllUser();

    boolean deleteUser(String id);

    boolean updateUser(User user);

    List<User> searchUser(String searchStr);
}
