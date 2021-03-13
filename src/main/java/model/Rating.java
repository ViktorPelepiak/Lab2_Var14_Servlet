package model;

public class Rating {
    private Long id;
    private Student student;
    private Byte sub1;
    private Byte sub2;
    private Byte sub3;

    public Long getId() {
        return id;
    }

    public Rating setId(Long id) {
        this.id = id;
        return this;
    }

    public Student getStudent() {
        return student;
    }

    public Rating setStudent(Student student) {
        this.student = student;
        return this;
    }

    public Byte getSub1() {
        return sub1;
    }

    public Rating setSub1(Byte sub1) {
        this.sub1 = sub1;
        return this;
    }

    public Byte getSub2() {
        return sub2;
    }

    public Rating setSub2(Byte sub2) {
        this.sub2 = sub2;
        return this;
    }

    public Byte getSub3() {
        return sub3;
    }

    public Rating setSub3(Byte sub3) {
        this.sub3 = sub3;
        return this;
    }
}
