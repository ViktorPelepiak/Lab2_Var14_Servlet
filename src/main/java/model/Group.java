package model;

public class Group {
    private Long id;
    private String groupNumber;

    public Long getId() {
        return id;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public Group setId(Long id) {
        this.id = id;
        return this;
    }

    public Group setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
        return this;
    }
}
