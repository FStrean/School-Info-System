CREATE TABLE subjects (
                        id BIGSERIAL NOT NULL PRIMARY KEY,
                        subject_name VARCHAR(50) NOT NULL CHECK(length(subject_name)>0)
);

CREATE TABLE teachers (
                          id BIGSERIAL NOT NULL PRIMARY KEY,
                          teacher_name VARCHAR(100) NOT NULL CHECK(length(teacher_name)>0),
                          subject_id BIGINT NOT NULL REFERENCES subjects(id),
                          passport VARCHAR(300) NOT NULL CHECK(length(passport)>0),
                          work_experience VARCHAR(1000),
                          education VARCHAR(500) NOT NULL CHECK(length(education)>0),
                          phone_number VARCHAR(25) NOT NULL CHECK(length(phone_number)>0)
);

CREATE TABLE classes (
                         id BIGSERIAL NOT NULL PRIMARY KEY,
                         number VARCHAR(10) NOT NULL CHECK(length(number)>0),
                         cabinet VARCHAR (20) NOT NULL CHECK(length(cabinet)>0),
                         teacher_id BIGINT NOT NULL REFERENCES teachers(id),
                         UNIQUE (teacher_id)
);

CREATE TABLE students (
                        id BIGSERIAL NOT NULL PRIMARY KEY,
                        student_name VARCHAR(100) NOT NULL CHECK(length(student_name)>0),
                        birthdate DATE NOT NULL,
                        address VARCHAR(200) NOT NULL CHECK(length(address)>0),
                        parent_name VARCHAR(100) NOT NULL CHECK(length(parent_name)>0),
                        phone_number VARCHAR(25) NOT NULL CHECK(length(phone_number)>0),
                        class_id BIGINT NOT NULL REFERENCES classes(id)
);