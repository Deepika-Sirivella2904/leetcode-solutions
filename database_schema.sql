-- MySQL Database Schema for Student Results System
-- Create database if not exists
CREATE DATABASE IF NOT EXISTS student_results;
USE student_results;

-- Create student_info table
CREATE TABLE IF NOT EXISTS student_info (
    reg_no VARCHAR(15) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    department VARCHAR(50),
    email VARCHAR(100),
    phone VARCHAR(15),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create marks table
CREATE TABLE IF NOT EXISTS marks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    reg_no VARCHAR(15) NOT NULL,
    semester_no INT NOT NULL,
    cat_no INT NOT NULL,
    subject VARCHAR(100) NOT NULL,
    marks_obtained INT NOT NULL,
    max_marks INT NOT NULL,
    FOREIGN KEY (reg_no) REFERENCES student_info(reg_no) ON DELETE CASCADE,
    UNIQUE KEY unique_mark (reg_no, semester_no, cat_no, subject)
);

-- Insert sample data for testing
INSERT INTO student_info (reg_no, name, department, email, phone) VALUES
('23mis7320', 'Alice Johnson', 'MIS', 'alice@university.edu', '9876543210'),
('23mic7320', 'Bob Smith', 'MIC', 'bob@university.edu', '8765432109'),
('23bce20109', 'Charlie Brown', 'BCE', 'charlie@university.edu', '7654321098'),
('23bce7990', 'Diana Prince', 'BCE', 'diana@university.edu', '6543210987');

INSERT INTO marks (reg_no, semester_no, cat_no, subject, marks_obtained, max_marks) VALUES
-- Alice Johnson (23mis7320) - Semester 1 - CAT 1
('23mis7320', 1, 1, 'Data Structures', 85, 100),
('23mis7320', 1, 1, 'Algorithms', 78, 100),
('23mis7320', 1, 1, 'Database Systems', 92, 100),
('23mis7320', 1, 1, 'Web Development', 88, 100),
-- Alice Johnson - Semester 1 - CAT 2
('23mis7320', 1, 2, 'Data Structures', 82, 100),
('23mis7320', 1, 2, 'Algorithms', 80, 100),
('23mis7320', 1, 2, 'Database Systems', 90, 100),
('23mis7320', 1, 2, 'Web Development', 85, 100),
-- Bob Smith (23mic7320) - Semester 1 - CAT 1
('23mic7320', 1, 1, 'Digital Electronics', 75, 100),
('23mic7320', 1, 1, 'Microprocessors', 82, 100),
('23mic7320', 1, 1, 'Circuit Theory', 79, 100),
('23mic7320', 1, 1, 'Signal Processing', 87, 100),
-- Bob Smith - Semester 1 - CAT 2
('23mic7320', 1, 2, 'Digital Electronics', 78, 100),
('23mic7320', 1, 2, 'Microprocessors', 85, 100),
('23mic7320', 1, 2, 'Circuit Theory', 81, 100),
('23mic7320', 1, 2, 'Signal Processing', 89, 100),
-- Charlie Brown (23bce20109) - Semester 1 - CAT 1
('23bce20109', 1, 1, 'Engineering Mathematics', 90, 100),
('23bce20109', 1, 1, 'Physics', 85, 100),
('23bce20109', 1, 1, 'Chemistry', 88, 100),
('23bce20109', 1, 1, 'Computer Programming', 93, 100),
-- Charlie Brown - Semester 1 - CAT 2
('23bce20109', 1, 2, 'Engineering Mathematics', 87, 100),
('23bce20109', 1, 2, 'Physics', 83, 100),
('23bce20109', 1, 2, 'Chemistry', 86, 100),
('23bce20109', 1, 2, 'Computer Programming', 91, 100),
-- Diana Prince (23bce7990) - Semester 1 - CAT 1
('23bce7990', 1, 1, 'Engineering Mathematics', 92, 100),
('23bce7990', 1, 1, 'Physics', 89, 100),
('23bce7990', 1, 1, 'Chemistry', 90, 100),
('23bce7990', 1, 1, 'Computer Programming', 95, 100),
-- Diana Prince - Semester 1 - CAT 2
('23bce7990', 1, 2, 'Engineering Mathematics', 90, 100),
('23bce7990', 1, 2, 'Physics', 87, 100),
('23bce7990', 1, 2, 'Chemistry', 88, 100),
('23bce7990', 1, 2, 'Computer Programming', 93, 100);
