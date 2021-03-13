package dto;

public class RatingAddDto {
    private String firstName;
    private String lastName;
    private String fatherName;
    private Long groupId;
    private Byte sub1;
    private Byte sub2;
    private Byte sub3;

    public String getFirstName() {
        return firstName;
    }

    public RatingAddDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public RatingAddDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getFatherName() {
        return fatherName;
    }

    public RatingAddDto setFatherName(String fatherName) {
        this.fatherName = fatherName;
        return this;
    }

    public Long getGroupId() {
        return groupId;
    }

    public RatingAddDto setGroupId(Long groupId) {
        this.groupId = groupId;
        return this;
    }

    public Byte getSub1() {
        return sub1;
    }

    public RatingAddDto setSub1(Byte sub1) {
        this.sub1 = sub1;
        return this;
    }

    public Byte getSub2() {
        return sub2;
    }

    public RatingAddDto setSub2(Byte sub2) {
        this.sub2 = sub2;
        return this;
    }

    public Byte getSub3() {
        return sub3;
    }

    public RatingAddDto setSub3(Byte sub3) {
        this.sub3 = sub3;
        return this;
    }
}
