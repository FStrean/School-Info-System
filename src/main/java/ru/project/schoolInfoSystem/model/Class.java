package ru.project.schoolInfoSystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Class {
    private Long id;

    private String number;

    private String cabinet;

    private Long teacherId;
}
