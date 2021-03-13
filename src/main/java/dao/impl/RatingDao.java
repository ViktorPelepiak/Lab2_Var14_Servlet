package dao.impl;

import dao.Dao;
import db.DBConnection;
import dto.StatisticDto;
import exception.WrongDataException;
import model.Rating;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class RatingDao implements Dao<Rating> {
    private final String GET = "select * from public.ratings as s where s.id = ?";
    private final String GET_ALL = "select * from public.ratings";
    private final String CREATE = "insert into public.ratings (student_id, sub1, sub2, sub3) values (?, ?, ?, ?)";
    private final String UPDATE = "update public.ratings set student_id  = ?, sub1 = ?, sub2 = ?, sub3 = ? where rating_id = ?";
    private final String DELETE = "delete from ratings where rating_id = ?";
    private final String GET_QUANTITY_BY_SUBJECT_AND_RATING[] = new String[]{
            "select count(*) from ratings where sub1=?",
            "select count(*) from ratings where sub2=?",
            "select count(*) from ratings where sub3=?"
    };
    private final String GET_AVG_VALUE_BY_SUBJECT_PART1 = "select avg(";
    private final String GET_AVG_VALUE_BY_SUBJECT_PART2 = ") from ratings";

    private final StudentDao studentDao;

    public RatingDao() {
        studentDao = new StudentDao();
    }

    @Override
    public Optional<Rating> get(long id) throws SQLException, WrongDataException, ClassNotFoundException {
        Rating rating;
        Class.forName("org.postgresql.Driver");
        try (Connection connection = DriverManager.getConnection(
                DBConnection.DATABASE_URL,
                DBConnection.DATABASE_USERNAME,
                DBConnection.DATABASE_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(GET)) {
            statement.setLong(1, id);
            ResultSet res = statement.executeQuery();
            res.next();
            rating = new Rating()
                    .setId(res.getLong("id"))
                    .setStudent(studentDao.get(res.getLong("student_id")).orElseThrow(WrongDataException::new))
                    .setSub1(res.getByte("sub1"))
                    .setSub2(res.getByte("sub2"))
                    .setSub3(res.getByte("sub3"));
            statement.close();
            connection.close();
        }
        return Optional.ofNullable(rating);
    }

    @Override
    public List<Rating> getAll() throws SQLException, WrongDataException, ClassNotFoundException {
        List<Rating> ratings = new LinkedList<>();
        Class.forName("org.postgresql.Driver");
        try (Connection connection = DriverManager.getConnection(
                DBConnection.DATABASE_URL,
                DBConnection.DATABASE_USERNAME,
                DBConnection.DATABASE_PASSWORD);
             Statement statement = connection.createStatement()) {
            ResultSet res = statement.executeQuery(GET_ALL);
            while (res.next()) {
                ratings.add(new Rating()
                        .setId(res.getLong("id"))
                        .setStudent(studentDao.get(res.getLong("student_id")).orElseThrow(WrongDataException::new))
                        .setSub1(res.getByte("sub1"))
                        .setSub2(res.getByte("sub2"))
                        .setSub3(res.getByte("sub3")));
            }
            statement.close();
            connection.close();
        }
        return ratings;
    }

    @Override
    public Rating save(Rating rating) throws SQLException, WrongDataException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        try (Connection connection = DriverManager.getConnection(
                DBConnection.DATABASE_URL,
                DBConnection.DATABASE_USERNAME,
                DBConnection.DATABASE_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, rating.getStudent().getId());
            statement.setByte(2, rating.getSub1());
            statement.setByte(3, rating.getSub2());
            statement.setByte(4, rating.getSub3());
            int id = statement.executeUpdate();

            if (id == 0) {
                throw new SQLException("Creating rating failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    rating.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating rating failed, no ID obtained.");
                }
            }
            statement.close();
            connection.close();
        }

        return rating;
    }

    @Override
    public Rating update(Rating rating) throws SQLException, WrongDataException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        try (Connection connection = DriverManager.getConnection(
                DBConnection.DATABASE_URL,
                DBConnection.DATABASE_USERNAME,
                DBConnection.DATABASE_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setLong(1, studentDao.getByNames(
                    rating.getStudent().getFirstName(), rating.getStudent().getLastName(), rating.getStudent().getFatherName())
                    .orElseThrow(WrongDataException::new).getId());
            statement.setLong(2, rating.getSub1());
            statement.setLong(3, rating.getSub2());
            statement.setLong(4, rating.getSub3());
            statement.setLong(5, rating.getId());
            statement.executeUpdate();
            statement.close();
            connection.close();
        }
        return rating;
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

    public List<StatisticDto> getStatisticForAllSubjects() throws ClassNotFoundException, SQLException {
        List<StatisticDto> statistic = new LinkedList<>();
        Class.forName("org.postgresql.Driver");
        try (Connection connection = DriverManager.getConnection(
                DBConnection.DATABASE_URL,
                DBConnection.DATABASE_USERNAME,
                DBConnection.DATABASE_PASSWORD);

             Statement statement2 = connection.createStatement()) {

            StatisticDto statisticItem;
            for (int i = 1; i < 4; i++) {
                PreparedStatement statement1 = connection.prepareStatement(GET_QUANTITY_BY_SUBJECT_AND_RATING[i-1]);
                statisticItem = new StatisticDto();
                statement1.setInt(1, 2);
                ResultSet res = statement1.executeQuery();
                res.next();
                statisticItem.setRating2(res.getInt(1));
                statement1.setInt(1, 3);
                res = statement1.executeQuery();
                res.next();
                statisticItem.setRating3(res.getInt(1));
                statement1.setInt(1, 4);
                res = statement1.executeQuery();
                res.next();
                statisticItem.setRating4(res.getInt(1));
                statement1.setInt(1, 5);
                res = statement1.executeQuery();
                res.next();
                statisticItem.setRating5(res.getInt(1));

                res = statement2.executeQuery(GET_AVG_VALUE_BY_SUBJECT_PART1 + "sub" + i + GET_AVG_VALUE_BY_SUBJECT_PART2);
                res.next();
                statisticItem.setRatingAvg(res.getDouble(1));
                statistic.add(statisticItem);
                statement1.close();
            }

            statement2.close();
            connection.close();
        }
        return statistic;
    }
}
