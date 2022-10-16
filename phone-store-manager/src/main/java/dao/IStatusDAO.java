package dao;

import model.Status;

import java.util.List;

public interface IStatusDAO {
    Status selectStatus(int id);

    List<Status> selectAllStatus();
}
