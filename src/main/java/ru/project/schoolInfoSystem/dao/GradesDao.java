package ru.project.schoolInfoSystem.dao;

import ru.project.schoolInfoSystem.model.Grades;
import ru.project.schoolInfoSystem.dto.GradesDto;
import ru.project.schoolInfoSystem.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GradesDao {
    private static final Connection connection = DatabaseConnection.get();

    private static void initStatement(PreparedStatement preparedStatement, Grades grades) throws SQLException {
        preparedStatement.setLong(1, grades.getStudentId());
        preparedStatement.setLong(2, grades.getSubjectId());
        preparedStatement.setInt(3, grades.getMark());
    }

    public static Long findStudentId(String name) {
        System.out.println(name);
        String query = "SELECT id FROM students WHERE student_name = ?";
        System.out.println(query);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            Long studentId = 0L;

            while(resultSet.next()) {
                studentId = resultSet.getLong("id");
            }
            System.out.println(studentId);
            return studentId;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static int add(Grades grades) {
        String query = "INSERT INTO grades(student_id, subject_id, mark) " +
                "VALUES (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            initStatement(preparedStatement, grades);

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void update(Grades grades) {
        String query = "UPDATE grades " +
                "set student_id=?, subject_id=?, mark=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            initStatement(preparedStatement, grades);
            preparedStatement.setLong(3, grades.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void delete(Long id) {
        String query = "DELETE FROM grades WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<GradesDto> getAll() {
//        String query = "SELECT * FROM grades";
        String query = "SELECT grades.id, students.student_name, subjects.subject_name, grades.mark" +
                " FROM grades JOIN students ON grades.student_id=students.id" +
                " JOIN subjects ON grades.subject_id=subjects.id";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<GradesDto> grades = new ArrayList<>();

            while(resultSet.next()) {
                GradesDto grade = new GradesDto();
                grade.setId(resultSet.getLong("id"));
                grade.setStudentName(resultSet.getString("student_name"));
                grade.setSubjectName(resultSet.getString("subject_name"));
                grade.setMark(resultSet.getInt("mark"));
                grades.add(grade);
            }

            return grades;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
