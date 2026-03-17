
PRAGMA foreign_keys = ON;

DROP TABLE IF EXISTS exams;
DROP TABLE IF EXISTS grades;
DROP TABLE IF EXISTS subject_professors;
DROP TABLE IF EXISTS enrollments;
DROP TABLE IF EXISTS professors;
DROP TABLE IF EXISTS students;
DROP TABLE IF EXISTS subjects;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
  user_id        INTEGER PRIMARY KEY AUTOINCREMENT,
  email          TEXT NOT NULL UNIQUE,
  password_hash  TEXT NOT NULL,
  role           TEXT NOT NULL CHECK (role IN ('STUDENT','PROFESSOR','ADMIN')),
  created_at     TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE students (
  student_id       INTEGER PRIMARY KEY,
  student_number   TEXT NOT NULL UNIQUE,
  first_name       TEXT NOT NULL,
  last_name        TEXT NOT NULL,
  study_year       INTEGER,
  program_name     TEXT,
  FOREIGN KEY (student_id) REFERENCES users(user_id)
    ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE professors (
  professor_id    INTEGER PRIMARY KEY,
  title           TEXT,
  first_name      TEXT NOT NULL,
  last_name       TEXT NOT NULL,
  FOREIGN KEY (professor_id) REFERENCES users(user_id)
    ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE subjects (
  subject_id      INTEGER PRIMARY KEY AUTOINCREMENT,
  code            TEXT NOT NULL UNIQUE,
  name            TEXT NOT NULL,
  ects            INTEGER,
  description     TEXT
);

CREATE TABLE enrollments (
  enrollment_id   INTEGER PRIMARY KEY AUTOINCREMENT,
  student_id      INTEGER NOT NULL,
  subject_id      INTEGER NOT NULL,
  academic_year   TEXT,
  semester        TEXT CHECK (semester IN ('W','S')),
  status          TEXT NOT NULL DEFAULT 'ENROLLED'
                  CHECK (status IN ('ENROLLED','DROPPED','COMPLETED')),
  enrolled_at     TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP,
  UNIQUE (student_id, subject_id, academic_year, semester),
  FOREIGN KEY (student_id) REFERENCES students(student_id)
    ON DELETE RESTRICT ON UPDATE CASCADE,
  FOREIGN KEY (subject_id) REFERENCES subjects(subject_id)
    ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE grades (
  grade_id        INTEGER PRIMARY KEY AUTOINCREMENT,
  enrollment_id   INTEGER NOT NULL,
  professor_id    INTEGER NOT NULL,
  category        TEXT NOT NULL,
  grade_value     REAL NOT NULL,
  weight          REAL NOT NULL DEFAULT 1.000,
  comment         TEXT,
  graded_at       TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (enrollment_id) REFERENCES enrollments(enrollment_id)
    ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (professor_id) REFERENCES professors(professor_id)
    ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE exams (
  exam_id         INTEGER PRIMARY KEY AUTOINCREMENT,
  enrollment_id   INTEGER NOT NULL,
  professor_id    INTEGER NOT NULL,
  attempt_number  INTEGER NOT NULL,
  exam_date       TEXT,
  status          TEXT NOT NULL DEFAULT 'FAILED'
                  CHECK (status IN ('PASSED','FAILED','ABSENT','CANCELLED')),
  grade_value     REAL,
  comment         TEXT,
  recorded_at     TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP,
  UNIQUE (enrollment_id, attempt_number),
  FOREIGN KEY (enrollment_id) REFERENCES enrollments(enrollment_id)
    ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (professor_id) REFERENCES professors(professor_id)
    ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE subject_professors (
  subject_id      INTEGER NOT NULL,
  professor_id    INTEGER NOT NULL,
  PRIMARY KEY (subject_id, professor_id),
  FOREIGN KEY (subject_id) REFERENCES subjects(subject_id)
    ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (professor_id) REFERENCES professors(professor_id)
    ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE INDEX idx_students_name
  ON students (last_name, first_name);

CREATE INDEX idx_professors_name
  ON professors (last_name, first_name);

CREATE INDEX idx_enroll_student
  ON enrollments (student_id);

CREATE INDEX idx_enroll_subject
  ON enrollments (subject_id);

CREATE INDEX idx_grades_enrollment
  ON grades (enrollment_id);

CREATE INDEX idx_grades_professor
  ON grades (professor_id);

CREATE INDEX idx_grades_graded_at
  ON grades (graded_at);

CREATE INDEX idx_exams_enrollment
  ON exams (enrollment_id);

CREATE INDEX idx_exams_professor
  ON exams (professor_id);

CREATE INDEX idx_exams_status
  ON exams (status);

CREATE INDEX idx_exams_exam_date
  ON exams (exam_date);

INSERT INTO users (user_id, email, password_hash, role, created_at) VALUES
(1, 'student1@example.com', '$samplehash1', 'STUDENT', '2026-03-12 12:21:39'),
(2, 'student2@example.com', '$samplehash2', 'STUDENT', '2026-03-12 12:21:39'),
(3, 'prof1@example.com', '$samplehash3', 'PROFESSOR', '2026-03-12 12:21:39'),
(4, 'prof2@example.com', '$samplehash4', 'PROFESSOR', '2026-03-12 12:21:39');

INSERT INTO students (student_id, student_number, first_name, last_name, study_year, program_name) VALUES
(1, 'S10001', 'Jan', 'Kowalski', 2, 'Informatyka'),
(2, 'S10002', 'Anna', 'Nowak', 3, 'Informatyka');

INSERT INTO professors (professor_id, title, first_name, last_name) VALUES
(3, 'dr', 'Marek', 'Wiśniewski'),
(4, 'dr hab.', 'Ewa', 'Zielińska');

INSERT INTO subjects (subject_id, code, name, ects, description) VALUES
(1, 'BD101', 'Bazy Danych', 5, 'Przykładowy opis'),
(2, 'PR202', 'Programowanie w Java', 6, 'Przykładowy opis');

INSERT INTO subject_professors (subject_id, professor_id) VALUES
(1, 3),
(2, 4);

INSERT INTO enrollments (enrollment_id, student_id, subject_id, academic_year, semester, status, enrolled_at) VALUES
(1, 1, 1, '2025/2026', 'W', 'ENROLLED', '2026-03-12 12:21:40'),
(2, 2, 2, '2025/2026', 'W', 'ENROLLED', '2026-03-12 12:21:40');

INSERT INTO grades (grade_id, enrollment_id, professor_id, category, grade_value, weight, comment, graded_at) VALUES
(1, 1, 3, 'Kolokwium 1', 4.0, 1.000, 'Dobry wynik', '2026-03-12 12:21:40'),
(2, 2, 4, 'Projekt', 5.0, 2.000, 'Bardzo dobry projekt', '2026-03-12 12:21:40');

INSERT INTO exams (exam_id, enrollment_id, professor_id, attempt_number, exam_date, status, grade_value, comment, recorded_at) VALUES
(1, 1, 3, 1, '2026-01-20', 'PASSED', 4.0, 'Zdane w pierwszym terminie', '2026-03-12 12:21:40'),
(2, 2, 4, 2, '2026-02-10', 'FAILED', 2.0, 'Nieudana poprawka', '2026-03-12 12:21:40');
