package dao.impl;

import dao.Dao;
import db.DBConnection;
import exception.WrongDataException;
import model.Group;
import model.Student;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class StudentDao implements Dao<Student> {
    private final String GET = "select * from public.students as s where s.id = ?";
    private final String GET_BY_NAMES = "select * from public.students as s where s.first_name = ? AND s.last_name = ? AND s.father_name = ?";
    private final String GET_ALL = "select * from public.students";
    private final String CREATE = "insert into public.students (first_name, last_name, father_name, group_id) values (?, ?, ?, ?)";
    private final String UPDATE = "update public.students set first_name  = ?, last_name  = ?, father_name  = ?, group_id = ? where id = ?";
    private final String DELETE = "delete from students where id = ?";

    private final GroupDao groupDao;

    public StudentDao() {
        groupDao = new GroupDao();
    }

    @Override
    public Optional<Student> get(long id) throws SQLException, WrongDataException, ClassNotFoundException {
        Student student;
        Class.forName("org.postgresql.Driver");
        try (Connection connection = DriverManager.getConnection(
                DBConnection.DATABASE_URL,
                DBConnection.DATABASE_USERNAME,
                DBConnection.DATABASE_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(GET)) {
            statement.setLong(1, id);
            ResultSet res = statement.executeQuery();
            res.next();
            student = new Student()
                    .setId(res.getLong("id"))
                    .setFirstName(res.getString("first_name"))
                    .setLastName(res.getString("last_name"))
                    .setFatherName(res.getString("father_name"));
            Group group = groupDao.get(res.getLong("group_id")).orElseThrow(WrongDataException::new);
            student.setGroup(group);
            statement.close();
            connection.close();
        }
        return Optional.ofNullable(student);
    }

    @Override
    public List<Student> getAll() throws SQLException, WrongDataException, ClassNotFoundException {
        List<Student> students = new LinkedList<>();
        Class.forName("org.postgresql.Driver");
        try (Connection connection = DriverManager.getConnection(
                DBConnection.DATABASE_URL,
                DBConnection.DATABASE_USERNAME,
                DBConnection.DATABASE_PASSWORD);
             Statement statement = connection.createStatement()) {
            ResultSet res = statement.executeQuery(GET_ALL);
            while (res.next()) {
                students.add(new Student()
                        .setId(res.getLong("id"))
                        .setFirstName(res.getString("first_name"))
                        .setLastName(res.getString("last_name"))
                        .setFatherName(res.getString("father_name"))
                        .setGroup(groupDao.get(
                                res.getLong("group_id"))
                                .orElseThrow(WrongDataException::new)
                        ));
            }
            statement.close();
            connection.close();
        }
        return students;
    }

    @Override
    public Student save(Student student) throws SQLException, WrongDataException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        try (Connection connection = DriverManager.getConnection(
                DBConnection.DATABASE_URL,
                DBConnection.DATABASE_USERNAME,
                DBConnection.DATABASE_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            student.setGroup(groupDao.getByGroupNumber(student.getGroup().getGroupNumber())
                    .orElseThrow(WrongDataException::new));
            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getLastName());
            statement.setString(3, student.getFatherName());
            statement.setLong(4, student.getGroup().getId());
            int id = statement.executeUpdate();

            if (id == 0) {
                throw new SQLException("Creating student failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    student.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating student failed, no ID obtained.");
                }
            }
            statement.close();
            connection.close();
        }

        return student;
    }

    @Override
    public Student update(Student student) throws SQLException, WrongDataException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        try (Connection connection = DriverManager.getConnection(
                DBConnection.DATABASE_URL,
                DBConnection.DATABASE_USERNAME,
                DBConnection.DATABASE_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setString(1, student.getFatherName());
            statement.setString(1, student.getLastName());
            statement.setString(1, student.getFatherName());
            statement.setLong(1, groupDao.getByGroupNumber(
                    student.getGroup().getGroupNumber()).orElseThrow(WrongDataException::new).getId()
            );
            statement.setLong(2, student.getId());
            statement.executeUpdate();
            statement.close();
            connection.close();
        }
        return student;
    }

    @Override
    public void deleteById(Long id) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        try (Connection connection = DriverManager.getConnection(
                DBConnection.DATABASE_URL,
                DBConnection.DATABASE_USERNAME,
                DBConnection.DATABASE_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(DELETE)) {
            statement.setLong(1, id);
            statement.executeUpdate();
            statement.close();
            connection.close();
        }
    }

    public Optional<Student> getByNames(String firstName, String lastName, String fatherName) throws SQLException, WrongDataException, ClassNotFoundException {
        Student student = null;
        Class.forName("org.postgresql.Driver");
        try (Connection connection = DriverManager.getConnection(
                DBConnection.DATABASE_URL,
                DBConnection.DATABASE_USERNAME,
                DBConnection.DATABASE_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(GET_BY_NAMES)) {
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, fatherName);
            ResultSet res = statement.executeQuery();
            res.next();
            student = new Student()
                    .setId(res.getLong("id"))
                    .setFirstName(res.getString("first_name"))
                    .setLastName(res.getString("last_name"))
                    .setFatherName(res.getString("father_name"))
                    .setGroup(groupDao.get(
                            res.getLong("group_id"))
                            .orElseThrow(WrongDataException::new)
                    );
            statement.close();
            connection.close();
        }
        return Optional.ofNullable(student);
    }
}
