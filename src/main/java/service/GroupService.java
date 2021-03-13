package service;

import model.Group;

import java.sql.SQLException;
import java.util.List;

public interface GroupService {
    public List<Group> getAll() throws SQLException, ClassNotFoundException;
}
