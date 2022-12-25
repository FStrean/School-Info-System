package ru.project.schoolInfoSystem.dao;

import ru.project.schoolInfoSystem.model.Report;

import ru.project.schoolInfoSystem.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReportDao {
    private static final Connection connection = DatabaseConnection.get();

    private static void initStatement(PreparedStatement preparedStatement, Report report) throws SQLException {
        preparedStatement.setDate(1, report.getReportDate());
        preparedStatement.setInt(2, report.getStudentsCount());
        preparedStatement.setInt(3, report.getTeachersCount());
        preparedStatement.setInt(4, report.getClassroomCount());
        preparedStatement.setInt(5, report.getClassesCount());
        preparedStatement.setFloat(6, report.getAverageGrades());
        preparedStatement.setInt(7, report.getExcellentCount());
        preparedStatement.setInt(8, report.getGoodCount());
        preparedStatement.setInt(9, report.getFailureCount());
        preparedStatement.setInt(10, report.getUnderachieverCount());
    }

    public static void generate(Report report) {
        String query = "INSERT INTO reports(report_date, students_count, teachers_count, classes_count, classroom_count, average_grades, " +
                "excellent_count, good_count, failure_count, underachiever_count) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            initStatement(preparedStatement, report);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int generateStudentsCount() {
        String query = "SELECT COUNT(*) FROM students";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            int studentCounts = 0;

            while(resultSet.next()) {
                studentCounts = resultSet.getInt("count");
            }
            System.out.println(studentCounts);
            return studentCounts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int generateTeachersCount() {
        String query = "SELECT COUNT(*) FROM teachers";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            int teachersCounts = 0;

            while(resultSet.next()) {
                teachersCounts = resultSet.getInt("count");
            }
            System.out.println(teachersCounts);
            return teachersCounts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int generateClassesCount() {
        String query = "SELECT COUNT(*) FROM classes";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            int classesCounts = 0;

            while(resultSet.next()) {
                classesCounts = resultSet.getInt("count");
            }
            System.out.println(classesCounts);
            return classesCounts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static float generateAverageGrade() {
        String query = "SELECT ROUND(AVG(mark), 1) FROM grades";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            float averageGrade = 0;

            while(resultSet.next()) {
                averageGrade = resultSet.getFloat("round");
            }
            System.out.println(averageGrade);
            return averageGrade;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int generateExcellentCount() {
        String query = "SELECT COUNT(Distinct students.id) FROM students JOIN grades ON students.id=grades.student_id WHERE grades.mark='5'";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            int excellentCount = 0;

            while(resultSet.next()) {
                excellentCount = resultSet.getInt("count");
            }
            System.out.println(excellentCount);
            return excellentCount;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int generateGoodCount() {
        String query = "SELECT COUNT(Distinct students.id) FROM students JOIN grades ON students.id=grades.student_id WHERE grades.mark='4'";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            int goodCount = 0;

            while(resultSet.next()) {
                goodCount = resultSet.getInt("count");
            }
            System.out.println(goodCount);
            return goodCount;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int generateFailureCount() {
        String query = "SELECT COUNT(Distinct students.id) FROM students JOIN grades ON students.id=grades.student_id WHERE grades.mark='3'";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            int failureCount = 0;

            while(resultSet.next()) {
                failureCount = resultSet.getInt("count");
            }
            System.out.println(failureCount);
            return failureCount;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int generateUnderachieverCount() {
        String query = "SELECT COUNT(Distinct students.id) FROM students JOIN grades ON students.id=grades.student_id WHERE grades.mark='2'";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            int underachieverCount = 0;

            while(resultSet.next()) {
                underachieverCount = resultSet.getInt("count");
            }
            System.out.println(underachieverCount);
            return underachieverCount;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void update(Report report) {
        String query = "UPDATE reports " +
                "set report_date=?, students_count=?, teachers_count=?, classses_count=?, classroom_count=?, average_grades=?, " +
                "excellent_count=?, good_count=?, failure_count=?, underachiever_count=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            initStatement(preparedStatement, report);
            preparedStatement.setLong(7, report.getId());   //why 7

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void delete(Long id) {
        String query = "DELETE FROM reports WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static List<Report> getAll() {
        String query = "SELECT * FROM reports";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Report> reports = new ArrayList<>();

            while(resultSet.next()) {
                Report report = new Report();
                report.setId(resultSet.getLong("id"));
                report.setReportDate(resultSet.getDate("report_date"));
                report.setStudentsCount(resultSet.getInt("students_count"));
                report.setTeachersCount(resultSet.getInt("teachers_count"));
                report.setClassesCount(resultSet.getInt("classes_count"));
                report.setClassroomCount(resultSet.getInt("classroom_count"));
                report.setAverageGrades(resultSet.getFloat("average_grades"));
                report.setExcellentCount(resultSet.getInt("excellent_count"));
                report.setGoodCount(resultSet.getInt("good_count"));
                report.setFailureCount(resultSet.getInt("failure_count"));
                report.setUnderachieverCount(resultSet.getInt("underachiever_count"));

                reports.add(report);
            }

            return reports;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
