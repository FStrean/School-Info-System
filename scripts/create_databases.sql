CREATE TABLE subjects (
                        id BIGSERIAL NOT NULL PRIMARY KEY,
                        subject_name VARCHAR(50) NOT NULL
);

CREATE TABLE teachers (
                          id BIGSERIAL NOT NULL PRIMARY KEY,
                          teacher_name VARCHAR(100) NOT NULL,
                          subject_id BIGINT NOT NULL REFERENCES subjects(id),
                          passport VARCHAR(300) NOT NULL,
                          work_experience VARCHAR(1000),
                          education VARCHAR(500) NOT NULL,
                          phone_number VARCHAR(25) NOT NULL
);

CREATE TABLE classes (
                         id BIGSERIAL NOT NULL PRIMARY KEY,
                         number VARCHAR(10) NOT NULL,
                         cabinet VARCHAR (20) NOT NULL,
                         teacher_id BIGINT NOT NULL REFERENCES teachers(id),
                         UNIQUE (teacher_id)
);

CREATE TABLE students (
                        id BIGSERIAL NOT NULL PRIMARY KEY,
                        student_name VARCHAR(100) NOT NULL,
                        birthdate DATE NOT NULL,
                        address VARCHAR(200) NOT NULL,
                        parent_name VARCHAR(100) NOT NULL,
                        phone_number VARCHAR(25) NOT NULL,
                        class_id BIGINT NOT NULL REFERENCES classes(id)
);