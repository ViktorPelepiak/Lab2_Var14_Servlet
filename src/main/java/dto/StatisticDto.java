package dto;

public class StatisticDto {
    private int rating2;
    private int rating3;
    private int rating4;
    private int rating5;
    private double ratingAvg;

    public int getRating2() {
        return rating2;
    }

    public StatisticDto setRating2(int rating2) {
        this.rating2 = rating2;
        return this;
    }

    public int getRating3() {
        return rating3;
    }

    public StatisticDto setRating3(int rating3) {
        this.rating3 = rating3;
        return this;
    }

    public int getRating4() {
        return rating4;
    }

    public StatisticDto setRating4(int rating4) {
        this.rating4 = rating4;
        return this;
    }

    public int getRating5() {
        return rating5;
    }

    public StatisticDto setRating5(int rating5) {
        this.rating5 = rating5;
        return this;
    }

    public double getRatingAvg() {
        return ratingAvg;
    }

    public StatisticDto setRatingAvg(double ratingAvg) {
        this.ratingAvg = ratingAvg;
        return this;
    }
}
