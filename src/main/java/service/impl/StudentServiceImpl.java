package service.impl;

import dao.impl.StudentDao;
import exception.DataAccessException;
import exception.WrongDataException;
import model.Student;
import service.StudentService;

import java.sql.SQLException;
import java.util.List;

public class StudentServiceImpl implements StudentService {
    private final StudentDao studentDao;

    public StudentServiceImpl() {
        studentDao = new StudentDao();
    }

    @Override
    public List<Student> getAll() throws DataAccessException, SQLException, ClassNotFoundException {
        try {
            return studentDao.getAll();
        } catch (WrongDataException e) {
            throw new DataAccessException("Can`t get access to students data");
        }
    }
}
