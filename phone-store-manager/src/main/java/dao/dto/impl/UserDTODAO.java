package dao.dto.impl;

import dao.dto.IUserDTODAO;
import dao.impl.ConnectionDAO;
import model.dto.CustomerDTO;
import model.dto.UserDTO;
import utils.AppUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDTODAO implements IUserDTODAO {
    private static UserDTODAO instance;

    private UserDTODAO() {
    }

    public static UserDTODAO getInstance() {
        if (instance == null) instance = new UserDTODAO();
        return instance;
    }

    private final ConnectionDAO connectionDAO = ConnectionDAO.getInstance();

    private static final String SELECT_USER_RANK =
            "SELECT user.image as user_image\n" +
                    ", user.full_name as user_full_name\n" +
                    ", user.address as user_address\n" +
                    ", user.phone_number as user_phone_number\n" +
                    ", COUNT(p_order.user_id) as order_count\n" +
                    "FROM  user \n" +
                    "JOIN p_order\n" +
                    "ON p_order.user_id = user.id\n" +
                    "GROUP BY p_order.user_id \n" +
                    "ORDER BY COUNT(p_order.user_id) DESC \n" +
                    "LIMIT 0,5;";

    @Override
    public List<UserDTO> selectUserRank() {
        List<UserDTO> userDTOList = new ArrayList<>();
        try {
            Connection connection = connectionDAO.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_RANK);
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println(this.getClass() + " selectCustomerRank " + preparedStatement);
            while (rs.next()) {
                UserDTO userDTO = getUserDTOFromResultSet(rs);
                userDTOList.add(userDTO);
            }
            return userDTOList;
        } catch (SQLException e) {
            AppUtils.printSQLException(e);
        }
        return null;
    }

    private UserDTO getUserDTOFromResultSet(ResultSet rs) throws SQLException {
        String userImage = rs.getString("user_image");
        String userFullName = rs.getString("user_full_name");
        String userAddress = rs.getString("user_address");
        String userPhoneNumber = rs.getString("user_phone_number");
        int orderCount = rs.getInt("order_count");
        UserDTO userDTO = new UserDTO(
                userImage,
                userFullName,
                userAddress,
                userPhoneNumber,
                orderCount
        );
        return userDTO;
    }
}
