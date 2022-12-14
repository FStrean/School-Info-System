package ru.project.schoolInfoSystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Grades {
    private Long id;

    private Long studentId;

    private Long subjectId;

    private int mark;
}
