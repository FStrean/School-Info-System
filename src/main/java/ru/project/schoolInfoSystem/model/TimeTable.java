package ru.project.schoolInfoSystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TimeTable {
    private Long id;

    private Class schoolClass;

    private String dayOfWeek;

    private Teacher teacher;

    private String time;

    private String cabinet;

    private String subjectName;
}
