package ru.project.schoolInfoSystem.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GradesDto {
    private Long id;
    private String studentName;
    private String subjectName;
    private int mark;
}
