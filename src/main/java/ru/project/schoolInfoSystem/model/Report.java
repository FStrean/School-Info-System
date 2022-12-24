package ru.project.schoolInfoSystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Report {
    private Long id;
    private Date reportDate;
    private int studentsCount;
    private int teachersCount;
    private int classroomCount;
    private int classesCount;
    private float averageGrades;
    private int excellentCount;
    private int goodCount;
    private int failureCount;
    private int underachieverCount;
}
