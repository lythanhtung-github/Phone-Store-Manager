package dao;


import model.User;

import java.util.List;

public interface IUserDAO {
    void insertUser(User user);

    User selectUser(String id);

    User selectUserByPhoneNumber(String phoneNumber);

    User selectUserByEmail(String emailStr);

    List<User> selectAllUsersPaggingFilter(int offset, int noOfRecords, String q, int roleId);

    List<User> selectAllUser();

    boolean deleteUser(String id);

    boolean updateUser(User user);

    List<User> searchUser(String searchStr);

    public int getNoOfRecords();

    User selectUserByEmailAndPassword(String email, String password);
}
