package ru.project.schoolInfoSystem.dao;

import ru.project.schoolInfoSystem.model.Class;
import ru.project.schoolInfoSystem.model.Teacher;
import ru.project.schoolInfoSystem.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassesDao {
    private static final Connection connection = DatabaseConnection.get();

    private static void initStatement(PreparedStatement preparedStatement, Class schoolClass) throws SQLException {
        preparedStatement.setString(1, schoolClass.getNumber());
        preparedStatement.setString(2, schoolClass.getCabinet());
        preparedStatement.setLong(3, schoolClass.getTeacherId());
    }

    public static int add(Class schoolClass) {
        String query = "INSERT INTO classes(number, cabinet, teacher_id) " +
                "VALUES (?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            initStatement(preparedStatement, schoolClass);

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void update(Class schoolClass) {
        String query = "UPDATE classes " +
                "set number=?, cabinet=?, teacher_id=? " +
                "where id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            initStatement(preparedStatement, schoolClass);
            preparedStatement.setLong(4, schoolClass.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void delete(Long id) {
        String query = "DELETE FROM classes WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Class> getAll() {
        String query = "SELECT * FROM classes";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Class> classes = new ArrayList<>();

            while(resultSet.next()) {
                Class schoolClass = new Class();
                schoolClass.setId(resultSet.getLong("id"));
                schoolClass.setNumber(resultSet.getString("number"));
                schoolClass.setCabinet(resultSet.getString("cabinet"));
                schoolClass.setTeacherId(resultSet.getLong("teacher_id"));

                classes.add(schoolClass);
            }

            return classes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Class get(Long id) {
        String query = "SELECT * FROM classes WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
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
}
