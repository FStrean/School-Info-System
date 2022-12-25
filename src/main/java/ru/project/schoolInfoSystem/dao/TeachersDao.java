package ru.project.schoolInfoSystem.dao;

import ru.project.schoolInfoSystem.model.Class;
import ru.project.schoolInfoSystem.model.Teacher;
import ru.project.schoolInfoSystem.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeachersDao {
    private static final Connection connection = DatabaseConnection.get();

    private static void initStatement(PreparedStatement preparedStatement, Teacher teacher) throws SQLException {
        preparedStatement.setString(1, teacher.getTeacherName());
        preparedStatement.setLong(2, teacher.getSubjectId());
        preparedStatement.setString(3, teacher.getPassport());
        preparedStatement.setString(4, teacher.getWorkExperience());
        preparedStatement.setString(5, teacher.getEducation());
        preparedStatement.setString(6, teacher.getPhoneNumber());
    }

    public static int add(Teacher teacher) {
        String query = "INSERT INTO teachers(teacher_name, subject_id, passport, work_experience, education, phone_number) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            initStatement(preparedStatement, teacher);

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void update(Teacher teacher) {
        String query = "UPDATE teachers " +
                "set teacher_name=?, subject_id=?, passport=?, work_experience=?, education=?, phone_number=? " +
                "where id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            initStatement(preparedStatement, teacher);
            preparedStatement.setLong(7, teacher.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void delete(Long id) {
        String query = "DELETE FROM teachers WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Teacher> getAll() {
        String query = "SELECT * FROM teachers";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Teacher> teachers = new ArrayList<>();

            while(resultSet.next()) {
                Teacher teacher = getFromResultSet(resultSet);
                teachers.add(teacher);
            }

            return teachers;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static Teacher get(Long id) {
        String query = "SELECT * FROM teachers WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Teacher teacher = null;
            while(resultSet.next()) {
                teacher = getFromResultSet(resultSet);
            }
            return teacher;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Class getTeacherClass(Teacher teacher) {
        String query = "SELECT * FROM classes WHERE teacher_id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, teacher.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            Class schoolClass = new Class();
            while(resultSet.next()) {
                schoolClass.setId(resultSet.getLong("id"));
                schoolClass.setNumber(resultSet.getString("number"));
                schoolClass.setCabinet(resultSet.getString("cabinet"));
                schoolClass.setTeacherId(resultSet.getLong("teacher_id"));
            }

            return schoolClass;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Teacher getFromResultSet(ResultSet resultSet) throws SQLException {
        Teacher teacher = new Teacher();
        teacher.setId(resultSet.getLong("id"));
        teacher.setTeacherName(resultSet.getString("teacher_name"));
        teacher.setPassport(resultSet.getString("passport"));
        teacher.setWorkExperience(resultSet.getString("work_experience"));
        teacher.setEducation(resultSet.getString("education"));
        teacher.setPhoneNumber(resultSet.getString("phone_number"));

        return teacher;
    }
}
