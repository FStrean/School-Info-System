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
public class Student {
    private Long id;

    private String studentName;

    private Date birthdate;

    private String address;

    private String parentName;

    private String phoneNumber;

    private Long classId;
}
