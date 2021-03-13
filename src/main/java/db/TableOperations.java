package db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TableOperations {
    public final String CREATE_GROUPS = "create table  groups (id serial not null, group_number character varying(10) not null, primary key (id))";
    public final String CREATE_STUDENTS = "create table  students (id serial not null, first_name character varying(20) not null, last_name character varying(20) not null, father_name character varying(20) not null, group_id integer not null, " +
            "primary key (id), foreign key (group_id) references public.groups(id), constraint kNames unique (first_name, last_name, father_name))";
    public final String CREATE_RATINGS = "create table  ratings (id serial not null, student_id integer not null, sub1 integer not null, sub2 integer not null, sub3 integer not null," +
            " primary key (id), foreign key (student_id) references public.students(id))";
    public final String DROP_GROUPS = "drop table public.groups;";
    public final String DROP_STUDENTS = "drop table public.students;";
    public final String DROP_RATINGS = "drop table public.ratings;";

    public void createStudentsTable() throws SQLException, ClassNotFoundException {
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(CREATE_STUDENTS);
            System.out.println("students was created");
        }
    }

    public void dropStudentsTable() throws SQLException, ClassNotFoundException {
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(DROP_STUDENTS);
        }
    }

    public void createGroupsTable() throws SQLException, ClassNotFoundException {
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(CREATE_GROUPS);
            System.out.println("groups was created");
        }
    }

    public void dropGroupsTable() throws SQLException, ClassNotFoundException {
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(DROP_GROUPS);
        }
    }

    public void createRatingsTable() throws SQLException, ClassNotFoundException {
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(CREATE_RATINGS);
            System.out.println("ratings was created");
        }
    }

    public void dropRatingsTable() throws SQLException, ClassNotFoundException {
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(DROP_RATINGS);
        }
    }

    public void createAllTable() throws SQLException, ClassNotFoundException {
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(CREATE_GROUPS);
            statement.executeUpdate(CREATE_STUDENTS);
            statement.executeUpdate(CREATE_RATINGS);
        }
    }

    public void dropAllTables() throws SQLException, ClassNotFoundException {
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(DROP_RATINGS);
            statement.executeUpdate(DROP_STUDENTS);
            statement.executeUpdate(DROP_GROUPS);
        }
    }
}
