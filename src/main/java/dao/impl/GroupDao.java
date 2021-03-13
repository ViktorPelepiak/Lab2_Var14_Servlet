package dao.impl;

import dao.Dao;
import db.DBConnection;
import model.Group;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class GroupDao implements Dao<Group> {

    private final String GET = "select * from groups where id = ?";
    private final String GET_BY_GROUP_NUMBER = "select * from groups where group_number = ?";
    private final String GET_ALL = "select * from groups order by id;";
    private final String SAVE = "insert into groups (group_number) values (?)";
    private final String UPDATE = "update groups set group_number = ? where id = ?";
    private final String DELETE = "delete from groups where id = ?";


    @Override
    public Optional<Group> get(long id) throws SQLException, ClassNotFoundException {
        Group group;
        Class.forName("org.postgresql.Driver");
        try (Connection connection = DriverManager.getConnection(
                DBConnection.DATABASE_URL,
                DBConnection.DATABASE_USERNAME,
                DBConnection.DATABASE_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(GET)) {
            statement.setLong(1, id);
            ResultSet res = statement.executeQuery();
            res.next();
            group = new Group()
                    .setId(res.getLong("id"))
                    .setGroupNumber(res.getString("group_number"));
            statement.close();
            connection.close();
        }
        return Optional.ofNullable(group);
    }

    @Override
    public List<Group> getAll() throws SQLException, ClassNotFoundException {
        List<Group> groups = new LinkedList<>();
        Class.forName("org.postgresql.Driver");
        try (Connection connection = DriverManager.getConnection(
                DBConnection.DATABASE_URL,
                DBConnection.DATABASE_USERNAME,
                DBConnection.DATABASE_PASSWORD);
             Statement statement = connection.createStatement()) {
            ResultSet res = statement.executeQuery(GET_ALL);
            while (res.next()) {
                groups.add(new Group()
                        .setId(res.getLong("id"))
                        .setGroupNumber(res.getString("group_number")));
            }
            statement.close();
            connection.close();
        }
        return groups;
    }

    @Override
    public Group save(Group group) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        try (Connection connection = DriverManager.getConnection(
                DBConnection.DATABASE_URL,
                DBConnection.DATABASE_USERNAME,
                DBConnection.DATABASE_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, group.getGroupNumber());
            int id = statement.executeUpdate();

            if (id == 0) {
                throw new SQLException("Creating group failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    group.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating group failed, no ID obtained.");
                }
            }
            statement.close();
            connection.close();
        }
        return group;
    }

    @Override
    public Group update(Group group) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        try (Connection connection = DriverManager.getConnection(
                DBConnection.DATABASE_URL,
                DBConnection.DATABASE_USERNAME,
                DBConnection.DATABASE_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setString(1, group.getGroupNumber());
            statement.setLong(2, group.getId());
            statement.executeUpdate();
            statement.close();
            connection.close();
        }
        return group;
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

    public Optional<Group> getByGroupNumber(String groupNumber) throws SQLException, ClassNotFoundException {
        Group group;
        Class.forName("org.postgresql.Driver");
        try (Connection connection = DriverManager.getConnection(
                DBConnection.DATABASE_URL,
                DBConnection.DATABASE_USERNAME,
                DBConnection.DATABASE_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(GET_BY_GROUP_NUMBER)) {
            statement.setString(1, groupNumber);
            ResultSet res = statement.executeQuery();
            res.next();
            group = new Group()
                    .setId(res.getLong("id"))
                    .setGroupNumber(res.getString("group_number"));
            statement.close();
            connection.close();
        }
        return Optional.ofNullable(group);
    }
}
