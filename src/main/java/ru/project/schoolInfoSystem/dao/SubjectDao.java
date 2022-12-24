package ru.project.schoolInfoSystem.dao;

import ru.project.schoolInfoSystem.model.Class;
import ru.project.schoolInfoSystem.model.Subject;
import ru.project.schoolInfoSystem.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectDao {
    private static final Connection connection = DatabaseConnection.get();

    public static List<Subject> getAll() {
        String query = "SELECT * FROM subjects";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Subject> subjects = new ArrayList<>();

            while(resultSet.next()) {
                Subject subject = new Subject();
                subject.setId(resultSet.getLong("id"));
                subject.setSubjectName(resultSet.getString("subject_name"));

                subjects.add(subject);
            }

            return subjects;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
