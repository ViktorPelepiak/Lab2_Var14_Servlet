package service;

import exception.DataAccessException;
import model.Student;

import java.sql.SQLException;
import java.util.List;

public interface StudentService {
    List<Student> getAll() throws DataAccessException, SQLException, ClassNotFoundException;
}
