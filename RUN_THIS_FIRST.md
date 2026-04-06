# 🚀 How to Run the Student Results System

## Option 1: Using XAMPP (Easiest for Beginners)

### Step 1: Install XAMPP
1. Download XAMPP from: https://www.apachefriends.org/
2. Install it (choose default settings)
3. Open XAMPP Control Panel

### Step 2: Start Services
1. Click "Start" button for **Apache**
2. Click "Start" button for **MySQL**
3. Both should show green "Running" status

### Step 3: Setup Database
1. Open your web browser
2. Go to: http://localhost/phpmyadmin/
3. Click on "Import" tab
4. Choose file: `database_schema.sql`
5. Click "Go" button at bottom

### Step 4: Place Files
1. Navigate to your XAMPP installation folder (usually `C:\xampp\`)
2. Go to `htdocs` folder
3. Copy these files into `htdocs`:
   - `db_connection.php`
   - `results.php`

### Step 5: Run the Application
1. Open your web browser
2. Go to: http://localhost/results.php
3. Test with these details:
   - Registration: `23mis7320`
   - Semester: `1`
   - CAT: `1`

---

## Option 2: Using WAMP

### Step 1: Install WAMP
1. Download WAMP from: https://www.wampserver.com/
2. Install and start WAMP

### Step 2: Setup Database
1. Open phpMyAdmin from WAMP menu
2. Import `database_schema.sql`

### Step 3: Place Files
1. Go to `C:\wamp64\www\`
2. Copy `db_connection.php` and `results.php` there

### Step 4: Run
1. Open browser
2. Go to: http://localhost/results.php

---

## Option 3: Using MAMP (for Mac)

### Step 1: Install MAMP
1. Download MAMP from: https://www.mamp.info/
2. Install and start MAMP

### Step 2: Setup Database
1. Open phpMyAdmin (usually http://localhost:8888/phpMyAdmin/)
2. Import `database_schema.sql`

### Step 3: Place Files
1. Go to Applications/MAMP/htdocs/
2. Copy the PHP files there

### Step 4: Run
1. Open browser
2. Go to: http://localhost:8888/results.php

---

## 🔧 Troubleshooting

### "Connection failed" Error
1. Make sure MySQL is running (green in XAMPP)
2. Check if `db_connection.php` has correct database settings

### "Page not found" Error
1. Make sure Apache is running (green in XAMPP)
2. Check file names are exactly: `results.php` and `db_connection.php`
3. Make sure files are in the correct folder (htdocs or www)

### Database Import Issues
1. Make sure you select the entire `database_schema.sql` file
2. Click "Go" button after selecting file

### Still Not Working?
1. Restart Apache and MySQL in XAMPP
2. Clear browser cache (Ctrl+F5)
3. Check if port 80 is available (close Skype if needed)

---

## 📱 Test Data to Use

Try these combinations in the form:

| Registration | Semester | CAT |
|-------------|----------|-----|
| 23mis7320   | 1        | 1   |
| 23mis7320   | 1        | 2   |
| 23mic7320   | 1        | 1   |
| 23bce20109  | 1        | 1   |
| 23bce7990   | 1        | 2   |

---

## ✅ Success Signs

If everything works, you should see:
1. A form with 3 input fields
2. After submitting, student information appears
3. A table showing subject-wise marks
4. Total marks and percentage calculation

---

## 🎯 Quick Start Summary

1. **Install XAMPP**
2. **Start Apache + MySQL**
3. **Import database_schema.sql in phpMyAdmin**
4. **Copy PHP files to htdocs**
5. **Open http://localhost/results.php**

That's it! Your student results system should be running.
