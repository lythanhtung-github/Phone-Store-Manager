package dao;

import model.User;
import utils.AppUtils;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IUserDAO {
    private static UserDAO instance;

    private UserDAO() {
    }

    public static UserDAO getInstance() {
        if (instance == null) instance = new UserDAO();
        return instance;
    }

    private final ConnectionDAO connectionDAO = ConnectionDAO.getInstance();
    private static final String INSERT_USER = " INSERT INTO user(id, full_name, birthday, phone_number, email, address, image, role_id, password, created_time) VALUE (?,?,?,?,?,?,?,?,?,?);";
    private static final String SELECT_USER_BY_ID = "SELECT * FROM user WHERE id =?;";
    private static final String SELECT_ALL_USERS = "SELECT * FROM user;";
    private static final String DELETE_USER = "DELETE FROM user WHERE id = ?;";
    private static final String UPDATE_USER = "UPDATE user SET full_name =?, birthday =?, phone_number =?, email =?, address =?, image=?, role_id=?,password=?, updated_time=? WHERE id = ?;";
    private static final String SEARCH_USER = "{CALL search_User(?)}";
    private static final String SELECT_USER_BY_PHONE_NUMBER = "SELECT * FROM user WHERE phone_number =?;";
    private static final String SELECT_USER_BY_EMAIL = "SELECT * FROM user WHERE email =?;";

    private static final String SELECT_ALL_USER_PAGGING_FILLTER =
            "SELECT SQL_CALC_FOUND_ROWS * FROM user WHERE (id LIKE ? OR full_name LIKE ? OR birthday LIKE ? OR phone_number LIKE ? OR email LIKE ? OR address LIKE ?) AND role_id = ? limit ?,?;";

    private static final String SELECT_ALL_USER_PAGGING_FILLTER_ALL =
            "SELECT SQL_CALC_FOUND_ROWS * FROM user WHERE id LIKE ? OR full_name LIKE ? OR birthday LIKE ? OR phone_number LIKE ? OR email LIKE ? OR address LIKE ? limit ?,?;";

    private int noOfRecords;

    public int getNoOfRecords() {
        return noOfRecords;
    }

    public void setNoOfRecords(int noOfRecords) {
        this.noOfRecords = noOfRecords;
    }

    @Override
    public void insertUser(User user) {
        try (Connection connection = connectionDAO.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER)) {
            preparedStatement.setString(1, user.getId());
            preparedStatement.setString(2, user.getFullName());
            preparedStatement.setString(3, AppUtils.localDateToString(user.getBirthDay()));
            preparedStatement.setString(4, user.getPhoneNumber());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getAddress());
            preparedStatement.setString(7, user.getImage());
            preparedStatement.setInt(8, user.getRole());
            preparedStatement.setString(9, user.getPassword());
            preparedStatement.setString(10, AppUtils.localDateTimeToString(user.getCreatedTime()));
            System.out.println(this.getClass() + " insertUser " + preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            AppUtils.printSQLException(e);
        }
    }

    @Override
    public User selectUser(String id) {
        try {
            Connection connection = connectionDAO.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);
            preparedStatement.setString(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println(this.getClass() + " selectUser: " + preparedStatement);
            while (rs.next()) {
                String userId = rs.getString("id");
                String fullName = rs.getString("full_name");
                LocalDate birthDay = AppUtils.stringToLocalDate(rs.getString("birthday"));
                String phoneNumber = rs.getString("phone_number");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String image = rs.getString("image");
                int roleId = rs.getInt("role_id");
                String password = rs.getString("password");
                User user = new User(userId, fullName, birthDay, phoneNumber, email, address, image, roleId, password);
                LocalDateTime createdTime = AppUtils.stringToLocalDateTime(rs.getString("created_time"));
                user.setCreatedTime(createdTime);
                if (rs.getString("updated_time") != null) {
                    LocalDateTime updatedTime = AppUtils.stringToLocalDateTime(rs.getString("updated_time"));
                    user.setUpdatedTime(updatedTime);
                }
                return user;
            }
        } catch (SQLException e) {
            AppUtils.printSQLException(e);
        }
        return null;
    }

    @Override
    public User selectUserByPhoneNumber(String phoneNumberStr) {
        try {
            Connection connection = connectionDAO.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_PHONE_NUMBER);
            preparedStatement.setString(1, phoneNumberStr);
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println(this.getClass() + " selectUser: " + preparedStatement);
            while (rs.next()) {
                String userId = rs.getString("id");
                String fullName = rs.getString("full_name");
                LocalDate birthDay = AppUtils.stringToLocalDate(rs.getString("birthday"));
                String phoneNumber = rs.getString("phone_number");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String image = rs.getString("image");
                int roleId = rs.getInt("role_id");
                String password = rs.getString("password");
                User user = new User(userId, fullName, birthDay, phoneNumber, email, address, image, roleId, password);
                LocalDateTime createdTime = AppUtils.stringToLocalDateTime(rs.getString("created_time"));
                user.setCreatedTime(createdTime);
                if (rs.getString("updated_time") != null) {
                    LocalDateTime updatedTime = AppUtils.stringToLocalDateTime(rs.getString("updated_time"));
                    user.setUpdatedTime(updatedTime);
                }
                return user;
            }
        } catch (SQLException e) {
            AppUtils.printSQLException(e);
        }
        return null;
    }

    @Override
    public User selectUserByEmail(String emailStr) {
        try {
            Connection connection = connectionDAO.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_EMAIL);
            preparedStatement.setString(1, emailStr);
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println(this.getClass() + " selectUser: " + preparedStatement);
            while (rs.next()) {
                String userId = rs.getString("id");
                String fullName = rs.getString("full_name");
                LocalDate birthDay = AppUtils.stringToLocalDate(rs.getString("birthday"));
                String phoneNumber = rs.getString("phone_number");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String image = rs.getString("image");
                int roleId = rs.getInt("role_id");
                String password = rs.getString("password");
                User user = new User(userId, fullName, birthDay, phoneNumber, email, address, image, roleId, password);
                LocalDateTime createdTime = AppUtils.stringToLocalDateTime(rs.getString("created_time"));
                user.setCreatedTime(createdTime);
                if (rs.getString("updated_time") != null) {
                    LocalDateTime updatedTime = AppUtils.stringToLocalDateTime(rs.getString("updated_time"));
                    user.setUpdatedTime(updatedTime);
                }
                return user;
            }
        } catch (SQLException e) {
            AppUtils.printSQLException(e);
        }
        return null;
    }

    @Override
    public List<User> selectAllUser() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = connectionDAO.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS)) {
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println(this.getClass() + " selectAllUser " + preparedStatement);
            while (rs.next()) {
                User user = getUserFromResultSet(rs);
                userList.add(user);
            }
        } catch (SQLException e) {
            AppUtils.printSQLException(e);
        }
        return userList;
    }

    @Override
    public boolean deleteUser(String id) {
        boolean rowDeleted = false;
        try (Connection connection = connectionDAO.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER)) {
            preparedStatement.setString(1, id);
            System.out.println(this.getClass() + " deleteUser " + preparedStatement);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            AppUtils.printSQLException(e);
        }
        return rowDeleted;
    }

    @Override
    public boolean updateUser(User user) {
        boolean rowUpdated = false;
        try (Connection connection = connectionDAO.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER)) {
            preparedStatement.setString(1, user.getFullName());
            preparedStatement.setString(2, AppUtils.localDateToString(user.getBirthDay()));
            preparedStatement.setString(3, user.getPhoneNumber());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getAddress());
            preparedStatement.setString(6, user.getImage());
            preparedStatement.setInt(7, user.getRole());
            preparedStatement.setString(8, user.getPassword());
            preparedStatement.setString(9, AppUtils.localDateTimeToString(user.getUpdatedTime()));
            preparedStatement.setString(10, user.getId());
            System.out.println(this.getClass() + " updateUser " + preparedStatement);
            rowUpdated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            AppUtils.printSQLException(ex);
        }
        return rowUpdated;
    }

    @Override
    public List<User> searchUser(String searchStr) {
        List<User> users = new ArrayList<>();
        try (Connection connection = connectionDAO.getConnection();
             CallableStatement callableStatement = connection.prepareCall(SEARCH_USER)) {
            callableStatement.setString(1, "%" + searchStr + "%");
            ResultSet rs = callableStatement.executeQuery();
            System.out.println(this.getClass() + " searchUser " + callableStatement);
            while (rs.next()) {
                User user = getUserFromResultSet(rs);
                users.add(user);
            }
        } catch (SQLException ex) {
            AppUtils.printSQLException(ex);
        }
        return users;
    }

    @Override
    public List<User> selectAllUsersPaggingFilter(int offset, int noOfRecords, String q, int roleId) {
        List<User> userList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        try {
            Connection connection = connectionDAO.getConnection();
            if (roleId != -1) {
                preparedStatement = connection.prepareStatement(SELECT_ALL_USER_PAGGING_FILLTER);
                preparedStatement.setString(1, "%" + q + "%");
                preparedStatement.setString(2, "%" + q + "%");
                preparedStatement.setString(3, "%" + q + "%");
                preparedStatement.setString(4, "%" + q + "%");
                preparedStatement.setString(5, "%" + q + "%");
                preparedStatement.setString(6, "%" + q + "%");
                preparedStatement.setInt(7, roleId);
                preparedStatement.setInt(8, offset);
                preparedStatement.setInt(9, noOfRecords);
            } else {
                preparedStatement = connection.prepareCall(SELECT_ALL_USER_PAGGING_FILLTER_ALL);
                preparedStatement.setString(1, "%" + q + "%");
                preparedStatement.setString(2, "%" + q + "%");
                preparedStatement.setString(3, "%" + q + "%");
                preparedStatement.setString(4, "%" + q + "%");
                preparedStatement.setString(5, "%" + q + "%");
                preparedStatement.setString(6, "%" + q + "%");
                preparedStatement.setInt(7, offset);
                preparedStatement.setInt(8, noOfRecords);
            }
            System.out.println(this.getClass() + " selectAllUsersPaggingFilter " + preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                User user = getUserFromResultSet(rs);
                userList.add(user);
            }
            rs = preparedStatement.executeQuery("SELECT FOUND_ROWS();");
            if (rs.next())
                this.noOfRecords = rs.getInt(1);

            return userList;
        } catch (SQLException ex) {
            AppUtils.printSQLException(ex);
        }
        return null;
    }

    private User getUserFromResultSet(ResultSet rs) throws SQLException {
        String id = rs.getString("id");
        String fullName = rs.getString("full_name");
        LocalDate birthDay = AppUtils.stringToLocalDate(rs.getString("birthday"));
        String phoneNumber = rs.getString("phone_number");
        String email = rs.getString("email");
        String address = rs.getString("address");
        int role = rs.getInt("role_id");
        String image = rs.getString("image");
        User user = new User(id, fullName, birthDay, phoneNumber, email, address, image, role);
        LocalDateTime createdTime = AppUtils.stringToLocalDateTime(rs.getString("created_time"));
        user.setCreatedTime(createdTime);
        if (rs.getString("updated_time") != null) {
            LocalDateTime updatedTime = AppUtils.stringToLocalDateTime(rs.getString("updated_time"));
            user.setUpdatedTime(updatedTime);
        }
        return user;
    }
}