package ru.project.schoolInfoSystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
    private Long id;

    private String teacherName;

    private Long subjectId;

    private String passport;

    private String workExperience;

    private String education;

    private String phoneNumber;

    private Long classId;
}
