package service.impl;

import dao.impl.GroupDao;
import dao.impl.RatingDao;
import dao.impl.StudentDao;
import dto.RatingAddDto;
import dto.RatingDto;
import dto.StatisticDto;
import exception.WrongDataException;
import model.Group;
import model.Rating;
import model.Student;
import service.RatingService;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class RatingServiceImpl implements RatingService {
    private final RatingDao ratingDao;
    private final GroupDao groupDao;
    private final StudentDao studentDao;

    public RatingServiceImpl() {
        ratingDao = new RatingDao();
        studentDao = new StudentDao();
        groupDao = new GroupDao();
    }

    @Override
    public Rating save(RatingAddDto ratingAddDto) throws SQLException, ClassNotFoundException, WrongDataException {
        Group group = groupDao.get(ratingAddDto.getGroupId()).orElseThrow(WrongDataException::new);
        Student student = studentDao.save(new Student()
                .setFirstName(ratingAddDto.getFirstName())
                .setLastName(ratingAddDto.getLastName())
                .setFatherName(ratingAddDto.getFatherName())
                .setGroup(group));
        return ratingDao.save(new Rating()
                .setStudent(student)
                .setSub1(ratingAddDto.getSub1())
                .setSub2(ratingAddDto.getSub2())
                .setSub3(ratingAddDto.getSub3()));
    }

    @Override
    public List<RatingDto> getRatingsForAllStudents() throws SQLException, WrongDataException, ClassNotFoundException {
        return ratingDao.getAll().stream().map(RatingDto::toRatingDto).collect(Collectors.toList());
    }

    @Override
    public List<StatisticDto> getStatisticForAllSubjects() throws SQLException, ClassNotFoundException {
        return ratingDao.getStatisticForAllSubjects();
    }
}
