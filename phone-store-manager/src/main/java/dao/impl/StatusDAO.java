package dao.impl;

import dao.IStatusDAO;
import model.Status;
import utils.AppUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatusDAO implements IStatusDAO {

    private static StatusDAO instance;

    private StatusDAO() {
    }

    public static StatusDAO getInstance() {
        if (instance == null) instance = new StatusDAO();
        return instance;
    }
    private final ConnectionDAO connectionDAO = ConnectionDAO.getInstance();
    private static final String SELECT_STATUS_BY_ID = "SELECT * FROM status WHERE id =?;";
    private static final String SELECT_ALL_STATUS = "SELECT * FROM status;";

    @Override
    public Status selectStatus(int id) {
        try {
            Connection connection = connectionDAO.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STATUS_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println(this.getClass() + " select-Status-by-Id " + preparedStatement);
            while (rs.next()) {
                int statusId = rs.getInt("id");
                String type = rs.getString("type");
                return new Status(statusId, type);
            }
        } catch (SQLException ex) {
            AppUtils.printSQLException(ex);
        }
        return null;
    }

    @Override
    public List<Status> selectAllStatus() {
        List<Status> statusList = new ArrayList<>();
        try{
            Connection connection = connectionDAO.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STATUS);
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println(this.getClass() + " selectAllSTATUS " + preparedStatement);
            while (rs.next()){
                int id = rs.getInt("id");
                String type = rs.getString("type");
                Status status = new Status(id, type);
                statusList.add(status);
            }
        }catch (SQLException ex){
            AppUtils.printSQLException(ex);
        }
        return statusList;
    }
}
