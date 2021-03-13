package service;

import dto.RatingAddDto;
import dto.RatingDto;
import dto.StatisticDto;
import exception.WrongDataException;
import model.Rating;

import java.sql.SQLException;
import java.util.List;

public interface RatingService {
    Rating save(RatingAddDto ratingAddDto) throws SQLException, ClassNotFoundException, WrongDataException;

    List<RatingDto> getRatingsForAllStudents() throws SQLException, WrongDataException, ClassNotFoundException;

    List<StatisticDto> getStatisticForAllSubjects() throws SQLException, ClassNotFoundException;
}
