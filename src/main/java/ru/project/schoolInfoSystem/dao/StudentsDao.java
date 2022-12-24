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

    public static int add(Student student) {
        String query = "INSERT INTO students(student_name, address, birthdate, parent_name, phone_number, class_id) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            initStatement(preparedStatement, student);

            return preparedStatement.executeUpdate();
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

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void delete(Long id) {
        String query = "DELETE FROM students WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Student> getAll() {
        String query = "SELECT * FROM students";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Student> students = new ArrayList<>();

            while(resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getLong("id"));
                student.setStudentName(resultSet.getString("student_name"));
                student.setAddress(resultSet.getString("address"));
                student.setBirthdate(resultSet.getDate("birthdate"));
                student.setParentName(resultSet.getString("parent_name"));
                student.setPhoneNumber(resultSet.getString("phone_number"));
                student.setClassId(resultSet.getLong("class_id"));

                students.add(student);
            }

            return students;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
