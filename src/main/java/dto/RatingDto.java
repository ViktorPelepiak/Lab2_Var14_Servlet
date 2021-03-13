package dto;

import model.Rating;

public class RatingDto {
    private String student;
    private String groupName;
    private Byte firstSubjectRating;
    private Byte secondSubjectRating;
    private Byte thirdSubjectRating;

    public String getStudent() {
        return student;
    }

    public RatingDto setStudent(String student) {
        this.student = student;
        return this;
    }

    public String getGroupName() {
        return groupName;
    }

    public RatingDto setGroupName(String groupName) {
        this.groupName = groupName;
        return this;
    }

    public Byte getFirstSubjectRating() {
        return firstSubjectRating;
    }

    public RatingDto setFirstSubjectRating(Byte firstSubjectRating) {
        this.firstSubjectRating = firstSubjectRating;
        return this;
    }

    public Byte getSecondSubjectRating() {
        return secondSubjectRating;
    }

    public RatingDto setSecondSubjectRating(Byte secondSubjectRating) {
        this.secondSubjectRating = secondSubjectRating;
        return this;
    }

    public Byte getThirdSubjectRating() {
        return thirdSubjectRating;
    }

    public RatingDto setThirdSubjectRating(Byte thirdSubjectRating) {
        this.thirdSubjectRating = thirdSubjectRating;
        return this;
    }

    public static RatingDto toRatingDto(Rating rating){
        return new RatingDto()
                .setStudent(rating.getStudent().getLastName()
                        + " " + rating.getStudent().getFirstName()
                        + " " + rating.getStudent().getFatherName())
                .setGroupName(rating.getStudent().getGroup().getGroupNumber())
                .setFirstSubjectRating(rating.getSub1())
                .setSecondSubjectRating(rating.getSub2())
                .setThirdSubjectRating(rating.getSub3());
    }
}
