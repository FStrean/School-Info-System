package ru.project.schoolInfoSystem.dao;

import ru.project.schoolInfoSystem.model.Student;
import ru.project.schoolInfoSystem.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentsDao {
    private static final Connection connection;

    static {
        connection = DatabaseConnection.get();
    }

    private static void initStatement(PreparedStatement preparedStatement, Student student) throws SQLException{
        preparedStatement.setString(1, student.getStudentName());
        preparedStatement.setString(2, student.getAddress());
        preparedStatement.setDate(3, student.getBirthdate());
        preparedStatement.setString(4, student.getParentName());
        preparedStatement.setString(5, student.getPhoneNumber());
        preparedStatement.setLong(6, student.getClassId());
    }

    public static void add(Student student) {
        String query = "INSERT INTO students(student_name, address, birthdate, parent_name, phone_number, class_id) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            initStatement(preparedStatement, student);

            preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void update(Student student) {
        String query = "UPDATE students " +
                "set student_name=?, address=?, birthdate=?, parent_name=?, phone_number=?, class_id=? " +
                "where id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            initStatement(preparedStatement, student);
            preparedStatement.setLong(7, student.getId());

            preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void delete(Long id) {
        String query = "DELETE FROM students WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Student> getStudents() {
        String query = "SELECT * FROM students";
        try {
            PreparedStatement preparedStudentStatement = connection.prepareStatement(query);
            ResultSet resultStudentSet = preparedStudentStatement.executeQuery();
            List<Student> students = new ArrayList<>();

            while(resultStudentSet.next()) {
                Student student = new Student();
                student.setId(resultStudentSet.getLong("id"));
                student.setStudentName(resultStudentSet.getString("student_name"));
                student.setAddress(resultStudentSet.getString("address"));
                student.setBirthdate(resultStudentSet.getDate("birthdate"));
                student.setParentName(resultStudentSet.getString("parent_name"));
                student.setPhoneNumber(resultStudentSet.getString("phone_number"));
                student.setClassId(resultStudentSet.getLong("class_id"));
            }

            return students;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
