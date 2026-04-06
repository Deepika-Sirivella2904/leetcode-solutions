# Student Results System - Setup Instructions

## Overview
This project implements a web-based student results system that allows viewing student marks subject-wise based on registration number, semester, and CAT (Continuous Assessment Test).

## Files Created
1. **database_schema.sql** - MySQL database schema with sample data
2. **db_connection.php** - Database connection and helper functions
3. **results.php** - Main web page with form and results display
4. **setup_instructions.md** - This file

## Setup Instructions

### 1. Database Setup
1. Start your MySQL server (XAMPP, WAMP, MAMP, or standalone MySQL)
2. Open phpMyAdmin or MySQL command line
3. Import the `database_schema.sql` file:
   - In phpMyAdmin: Click "Import" → Choose file → Go
   - Or run: `mysql -u root -p < database_schema.sql`

### 2. Configure Database Connection
Edit `db_connection.php` and update these values:
```php
$host = 'localhost';
$username = 'root';  // Your MySQL username
$password = '';      // Your MySQL password
$database = 'student_results';
```

### 3. Web Server Setup
1. Place all files in your web server's document root (htdocs, www, or public_html)
2. Start your web server (Apache in XAMPP/WAMP)
3. Access the application: `http://localhost/results.php`

## Features
- **Student Information Display**: Shows student details when results are fetched
- **Subject-wise Marks**: Displays marks for each subject with percentages
- **Total Calculation**: Shows total marks and overall percentage
- **Input Validation**: Validates registration number, semester (1-8), and CAT (1-3)
- **Error Handling**: User-friendly error messages for invalid inputs
- **Responsive Design**: Works on desktop and mobile devices

## Sample Data for Testing
The database includes sample students matching your university format:
- **23mis7320** (Alice Johnson) - MIS Department
- **23mic7320** (Bob Smith) - MIC Department  
- **23bce20109** (Charlie Brown) - BCE Department
- **23bce7990** (Diana Prince) - BCE Department

### Test Combinations:
- Registration: 23mis7320, Semester: 1, CAT: 1
- Registration: 23mis7320, Semester: 1, CAT: 2
- Registration: 23mic7320, Semester: 1, CAT: 1
- Registration: 23bce20109, Semester: 1, CAT: 1
- Registration: 23bce7990, Semester: 1, CAT: 2

## Database Schema

### student_info table
- `reg_no` (VARCHAR, Primary Key) - Registration number
- `name` (VARCHAR) - Student name
- `department` (VARCHAR) - Department name
- `email` (VARCHAR) - Email address
- `phone` (VARCHAR) - Phone number

### marks table
- `id` (INT, Primary Key, Auto-increment)
- `reg_no` (VARCHAR, Foreign Key) - Links to student_info
- `semester_no` (INT) - Semester number (1-8)
- `cat_no` (INT) - CAT number (1-3)
- `subject` (VARCHAR) - Subject name
- `marks_obtained` (INT) - Marks obtained by student
- `max_marks` (INT) - Maximum possible marks

## Technical Requirements
- PHP 7.0 or higher
- MySQL 5.6 or higher
- Web server (Apache, Nginx, or IIS)
- Modern web browser

## Security Features
- Prepared statements to prevent SQL injection
- Input validation and sanitization
- XSS protection with htmlspecialchars()
- Secure database connection handling

## Troubleshooting

### Common Issues:
1. **"Connection failed" error**: Check MySQL server is running and credentials are correct
2. **"Student not found"**: Verify registration number exists in database
3. **"No marks found"**: Check if marks exist for the specified semester and CAT
4. **Blank page**: Ensure PHP error reporting is enabled for debugging

### Enable PHP Error Reporting (for development):
```php
error_reporting(E_ALL);
ini_set('display_errors', 1);
```
Add this at the top of `results.php` for debugging.

## Customization
- Add more students and marks in the database
- Modify the CSS styling in `results.php`
- Extend the schema to include additional fields
- Add export functionality (PDF, Excel)
- Implement user authentication system
