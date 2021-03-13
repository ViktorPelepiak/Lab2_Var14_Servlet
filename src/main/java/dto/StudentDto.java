package dto;

import model.Student;

public class StudentDto {
    private String firstName;
    private String lastName;
    private String fatherName;
    private String groupNumber;

    public String getFirstName() {
        return firstName;
    }

    public StudentDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public StudentDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getFatherName() {
        return fatherName;
    }

    public StudentDto setFatherName(String fatherName) {
        this.fatherName = fatherName;
        return this;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public StudentDto setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
        return this;
    }

    public static StudentDto toDto(Student student){
        return new StudentDto()
                .setFirstName(student.getFirstName())
                .setLastName(student.getLastName())
                .setFatherName(student.getFatherName())
                .setGroupNumber(student.getGroup().getGroupNumber());
    }
}
