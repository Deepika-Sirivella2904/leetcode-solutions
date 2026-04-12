/**
 * LeetCode Problem: Second Highest Salary
 * Problem Link: https://leetcode.com/problems/second-highest-salary/description/
 * Difficulty: Medium
 * Date Solved: 2026-04-12
 * Submission Link: https://leetcode.com/problems/second-highest-salary/submissions/1569317292/
 */

-- Solution 1: Using LIMIT and OFFSET (MySQL)
SELECT IFNULL(
    (SELECT DISTINCT Salary 
     FROM Employee 
     ORDER BY Salary DESC 
     LIMIT 1 OFFSET 1), 
    NULL
) AS SecondHighestSalary;

-- Solution 2: Using MAX() with subquery (MySQL)
SELECT MAX(Salary) AS SecondHighestSalary
FROM Employee
WHERE Salary < (SELECT MAX(Salary) FROM Employee);

-- Solution 3: Using window functions (MySQL 8.0+)
SELECT DISTINCT Salary AS SecondHighestSalary
FROM (
    SELECT Salary,
           DENSE_RANK() OVER (ORDER BY Salary DESC) as rank_num
    FROM Employee
) ranked
WHERE rank_num = 2;

-- Solution 4: Using NOT EXISTS (MySQL)
SELECT MAX(Salary) AS SecondHighestSalary
FROM Employee e1
WHERE NOT EXISTS (
    SELECT 1 
    FROM Employee e2 
    WHERE e2.Salary > e1.Salary
) AND EXISTS (
    SELECT 1 
    FROM Employee e2 
    WHERE e2.Salary > e1.Salary
);

-- Test data setup (for local testing)
/*
CREATE TABLE Employee (
    id INT PRIMARY KEY,
    salary INT
);

INSERT INTO Employee (id, salary) VALUES
(1, 100),
(2, 200),
(3, 300);

-- Expected output: 200

-- Test case 2: All same salaries
TRUNCATE TABLE Employee;
INSERT INTO Employee (id, salary) VALUES
(1, 100),
(2, 100),
(3, 100);

-- Expected output: NULL

-- Test case 3: Only one employee
TRUNCATE TABLE Employee;
INSERT INTO Employee (id, salary) VALUES
(1, 100);

-- Expected output: NULL

-- Test case 4: Two employees
TRUNCATE TABLE Employee;
INSERT INTO Employee (id, salary) VALUES
(1, 100),
(2, 200);

-- Expected output: 100
*/

-- Explanation:
-- 1. Solution 1 uses LIMIT 1 OFFSET 1 to skip the highest salary and get the second highest
-- 2. Solution 2 finds the maximum salary that is less than the overall maximum
-- 3. Solution 3 uses DENSE_RANK() to rank salaries and selects the one with rank 2
-- 4. Solution 4 uses NOT EXISTS to find salaries that don't have any higher salary except the maximum

-- Time Complexity: O(n log n) for sorting-based solutions, O(n) for subquery-based solutions
-- Space Complexity: O(1)
