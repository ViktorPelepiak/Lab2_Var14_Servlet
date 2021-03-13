package service.impl;

import dao.impl.GroupDao;
import model.Group;
import service.GroupService;

import java.sql.SQLException;
import java.util.List;

public class GroupServiceImpl implements GroupService {
    private final GroupDao groupDao;

    public GroupServiceImpl() {
        groupDao = new GroupDao();
    }

    @Override
    public List<Group> getAll() throws SQLException, ClassNotFoundException {
        return groupDao.getAll();
    }
}
