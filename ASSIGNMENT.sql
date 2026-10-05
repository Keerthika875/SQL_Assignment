 -- Banking Scenario
 -- 1. customer table
 use assignment;
CREATE TABLE customers ( 
    customer_id INT PRIMARY KEY AUTO_INCREMENT, 
    name VARCHAR(50), 
    city VARCHAR(50) 
); 
 -- data insertion for customer table
 INSERT INTO customers (name, city)
 VALUES 
('Janani','Chennai'), 
('Arun','Coimbatore'), 
('Priya','Madurai'), 
('Karthik','Salem');

-- Acount table
CREATE TABLE accounts ( 
    account_id INT PRIMARY KEY AUTO_INCREMENT, 
    customer_id INT, 
    account_type VARCHAR(20), 
    balance DECIMAL(10,2), 
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id) 
); 
-- data insertion for accout table
INSERT INTO accounts (customer_id, account_type, balance) VALUES 
(1,'Savings',50000), 
(1,'Current',20000), 
(2,'Savings',30000), 
(3,'Savings',15000), 
(4,'Current',40000); 
select * from customers;
select * from accounts;
-- BANKING SCENARIO – QUESTIONS 
-- Basic + WHERE + Operators (1–15) 
-- 1.Retrieve all accounts with balance greater than 20,000.  
select * from accounts 
where balance >20000;

-- 2.Find customers who live in Chennai.
select * from customers
where city='chennai';

-- 3.Display accounts with balance between 20,000 and 50,000.
select* from accounts
where balance between 20000 AND 50000;

-- 4.Find customers whose names start with 'J'.  
select * from  customers
where name like 'j%';

-- 5.Retrieve accounts of type 'Savings' or 'Current'.  
select* from accounts
where account_type IN('savings' ,'Current');

-- 6. Display accounts that are not 'Savings'.
select * from accounts 
where account_type NOT IN('Savings');

-- 7.Find customers whose names contain the letter 'a'.  
select * from customers 
where name LIKE '%a%';

-- 8.Retrieve accounts with balance less than or equal to 30,000. 
select * from accounts 
where balance <='30000'; 

-- 9. Find customers who are not from Madurai.  
select * from customers
where city NOT IN('madurai');

-- 10. Display accounts where balance is not between 10,000 and 40,000.
select * from accounts 
where balance NOT BETWEEN 10000 AND 40000;

-- 11. Retrieve customers whose names end with 'i'.  
select  * from customers
where name LIKE '%i';

-- 12. Find accounts with balance equal to 50,000.
select * from accounts
where balance = 50000;

-- 13. Display customers whose city is either Chennai or Salem.
select * from customers
where city IN ('chennai' ,'Salem');

-- 14. Find accounts with balance greater than 10,000 and less than 40,000.
select * from accounts
where balance > 10000 AND balance < 40000;

-- 15. Retrieve accounts where account type is not in ('Current').  
select * from accounts
where account_type NOT IN ('Current');

-- ORDER BY (16–18) 
-- 16.Display all accounts sorted by balance in descending order. 
select * from accounts
order by balance DESC; 

-- 17.List customers sorted alphabetically by name.
select * from customers
order by name ASC; 

-- 18. Display accounts sorted by account type and then by balance (descending).  
select * from accounts 
order by account_type ASC , balance DESC;

-- 19. Find the total balance of all accounts.  
select sum(balance) as total_balance from accounts;

-- 20.Calculate the average balance of accounts.  
select avg(balance) as average_balance from accounts;

-- 21. Find the maximum account balance.  
select max(balance) as maximum_balnce from  accounts;

-- 22. Find the minimum account balance.  
select min(balance) as minimum_balance from accounts;

-- 23. Count the total number of customers.
 select count(*) as total_customers from customers;
 
 -- GROUP BY + HAVING (24–28)
  -- 24 . Find total balance grouped by account type.  
   select account_type, SUM(balance) AS total_balance
   FROM accounts
   GROUP BY account_type;
   
   -- 25.Find average balance for each account type.  
   select account_type ,avg(balance) AS avg_balance
   from accounts
   Group by account_type;
   
   -- 26. Display account types having average balance greater than 20,000.
   select account_type,avg(balance) AS avg_balance
   from accounts
   group by account_type having avg(balance)> 20000;
   
   -- 27.Count number of accounts for each customer.  
   select customer_id, count(*)  as countof_customer 
   from customers 
   group by  customer_id;
   
   -- 28.Display customers having more than one account.  
   select costomer_id, COUNT(*) AS account_count
   from accounts
   group by customer_id
   HAVING COUNT(*) > 1;
   
   --  JOINS (29–35) 
   -- 29. Retrieve customer names along with their account balances.  
   select c.name, a.balance
   from customers c
   JOIN accounts a
   ON c.customer_id = a.customer_id;
   
   -- 30. Display all customers and their accounts (including customers without accounts).
   select c.name,a.account_id,a.account_type,a.balance
   from customers c JOIN accounts a
   ON c.customer_id=a.customer_id;
   
   -- 31. Display all accounts and corresponding customer details.
   
   select a.account_id, a.account_type, a.balance,c.name, c.city
   from accounts a
   JOIN customers c
   ON a.customer_id = c.customer_id;
   
   -- 32. Retrieve customer names and account types where balance is greater than 20,000. 
   select c.name, a.account_type, a.balance
   from  customers c
   JOIN accounts a
   ON c.customer_id = a.customer_id
   WHERE a.balance > 20000;
   
   -- 33.List customers with their total balance using JOIN.  
   select c.name, SUM(a.balance) AS total_balance
   FROM customers c JOIN accounts a
   ON c.customer_id = a.customer_id
   GROUP BY c.customer_id, c.name;
   
   -- 34.Display customer names and balances sorted by balance.
   select c.name, a.balance
   from customers c
   JOIN accounts a ON c.customer_id = a.customer_id
   ORDER BY a.balance DESC;
   
   -- 35.Count number of accounts for each city using JOIN. 
    select c.city, COUNT(a.account_id) AS account_count
    from  customers c JOIN accounts a
    ON c.customer_id = a.customer_id
    GROUP BY c.city;
    
    -- SUBQUERIES (36–40)
    -- 36.Find accounts with balance greater than average balance.
      select * FROM accounts
      WHERE balance > ( SELECT AVG(balance) FROM accounts);
      
	-- 37. Retrieve customers who have accounts.
       select * from customers
       WHERE customer_id IN ( SELECT customer_id FROM accounts);
       
   -- 38.Find customers who do not have any accounts.  
   select * from  customers
   WHERE customer_id NOT IN (SELECT customer_id FROM accounts);
   
   -- 39.Display account(s) with the maximum balance.
   select * from accounts
   WHERE balance = ( SELECT MAX(balance) FROM accounts);
   
   -- 40.Find customers whose total balance is greater than 40,000.
   select * from customers
   WHERE customer_id IN(SELECT  customer_id From accounts
   GROUP BY customer_id HAVING SUM(balance)> 40000);
   
   -- RAILWAY RESERVATION SCENARIO 
   -- 3. TRAIN TABLE CREATION
    CREATE TABLE trains ( 
    train_id INT PRIMARY KEY AUTO_INCREMENT, 
    train_name VARCHAR(50), 
    source VARCHAR(50), 
    destination VARCHAR(50) 
   ); 
   
   -- TRAIN TABLE DATA INSERTION
   INSERT INTO trains (train_name, source, destination) VALUES ('Express1','Chennai','Madurai'), 
  ('Express2','Coimbatore','Salem'), 
  ('Express3','Madurai','Chennai');
  
  -- 4. BOOKING TABLE CREATION
     CREATE TABLE bookings ( 
     booking_id INT PRIMARY KEY AUTO_INCREMENT, 
    train_id INT, 
    passenger_name VARCHAR(50), 
    fare DECIMAL(10,2), 
    status VARCHAR(20), 
    FOREIGN KEY (train_id) REFERENCES trains(train_id) 
); 
-- BOOKING TABLE DATA INSERTION
  INSERT INTO bookings (train_id, passenger_name, fare, status) VALUES (1,'Janani',500,'Confirmed'), 
  (1,'Arun',500,'Waiting'), 
  (2,'Priya',300,'Confirmed'), 
  (3,'Karthik',450,'Cancelled'), 
  (2,'Meena',300,'Confirmed');
  
  -- RAILWAY RESERVATION – QUESTIONS 
  -- Basic + WHERE + Operators (41–45)
  -- 41. Retrieve all bookings with fare greater than 400.
   select * from bookings
   WHERE fare > 400;
   
   -- 42.Find bookings where status is not 'Confirmed'. 
   SELECT * FROM bookings
   WHERE status NOT IN('Confirmed');
   
   -- 43.Display trains starting from Chennai.
    select * from trains
    where source = 'Chennai';
    
    -- 44.Retrieve bookings with fare between 300 and 500. 
    select *  from bookings
    WHERE fare BETWEEN 300 AND 500;
    
    -- 45.Find passengers whose names start with 'A'. 
    select * from bookings
    where passenger_name LIKE 'A%';
    
    -- JOINS + GROUP BY + ORDER BY (46–48) 
    -- 46.Retrieve train names along with passenger names. 
    select t.train_name, b.passenger_name 
    from trains t JOIN bookings b
    ON t.train_id = b.train_id;
    
    -- 47.Count number of bookings for each train.
    selecT t.train_name, COUNT(b.booking_id) AS booking_count 
    FROM trains t JOIN bookings b
    ON t.train_id = b.train_id
    GROUP BY t.train_id, t.train_name;
    
    -- 48.Display train names and total fare collected for each train. 
    select  t.train_name, SUM(b.fare) AS total_fare
    FROM trains t JOIN bookings b
    ON t.train_id = b.train_id
    GROUP BY t.train_id, t.train_name;
    
    -- SUBQUERIES (49–50)
    -- 49.Find bookings with fare equal to the highest fare.
     SELECT * FROM bookings
    WHERE fare = (SELECT MAX(fare) FROM bookings);
    
    -- 50.Retrieve trains that have more than one booking.  
    SELECT * FROM trains
    WHERE train_id IN ( SELECT train_id FROM bookings
    GROUP BY train_id
    HAVING COUNT(*) > 1);
    
    -- EMPLOYEE MANAGEMENT   TABLE CREATION
    CREATE TABLE Employee ( 
     emp_id INT PRIMARY KEY, 
     emp_name VARCHAR(50), 
     department VARCHAR(30), 
     salary DECIMAL(10,2), 
     city VARCHAR(30), 
     joining_date DATE 
     ); 
     
     -- EMPLYOYEE MANAGEMENT DATA INSERION
     INSERT INTO Employee VALUES (101,'John','IT',60000,'Chennai','2022-01-15'), 
     (102,'David','HR',45000,'Bangalore','2021-03-10'), 
     (103,'Smith','IT',70000,'Chennai','2020-07-12'), 
     (104,'Mary','Finance',55000,'Mumbai','2023-01-20'), 
     (105,'James','HR',48000,'Delhi','2022-05-05'), 
     (106,'Linda','Finance',65000,'Mumbai','2021-08-18'); 
     
     -- EMPLOYEE TABLE QUESTION AND ANSWER (1-15)
     -- 1.Find the total number of employees in each department.
      SELECT department, COUNT(*) AS employee_count FROM Employee
      GROUP BY department;
      
      -- 2.Find the average salary of employees in each department.  
      select department, AVG(salary) AS average_salary FROM Employee
	   group by department;
       
	-- 3.Display departments having more than one employee.
    select department, COUNT(*) AS employee_count FROM Employee
     GROUP BY department HAVING COUNT(*) > 1;
	
   -- 4.Find the highest salary in each department.  
   SELECT department, MAX(salary) AS highest_salary FROM Employee
   GROUP BY department;
   
   -- 5.Find the lowest salary in each department.
     SELECT department, min(salary) AS lowest_salary FROM Employee
   GROUP BY department;
   
   -- 6.Find departments whose average salary is greater than 50,000.
   SELECT department, AVG(salary) AS average_salary FROM Employee
   GROUP BY department HAVING AVG(salary) > 50000;
   
   -- 7.Calculate the total salary expenditure for each department.  
   SELECT department, SUM(salary) AS total_salary FROM Employee
    group by department;
    
 -- 8.Display all employees sorted by salary in descending order.
   select * from Employee
   ORDER BY salary DESC;
   
   -- 9. Display employees sorted first by department and then by salary in descending order
     select * from Employee
     ORDER BY department ASC, salary DESC;
	
    -- 10. Find cities that have more than one employee.  
    SELECT city, COUNT(*) AS employee_count
    FROM Employee
    GROUP BY city HAVING COUNT(*) > 1;
    
    -- 11.Find the total salary paid in each city.
    SELECT city, SUM(salary) AS total_salary
    FROM Employee group by city;
    
    -- 12.Display departments ordered by total salary expenditure from highest to lowest.  
    SELECT department, SUM(salary) AS total_salary
    FROM Employee
    GROUP BY department ORDER BY total_salary DESC;
    
    -- 13.Find the number of employees in each department whose salary is greater than 50,000. 
    SELECT department, COUNT(*) AS employee_count FROM Employee
    WHERE salary > 50000 GROUP BY department;
    
    -- 14.Find the difference between the highest and lowest salary in each department. 
    SELECT department, MAX(salary) - MIN(salary) AS salary_difference
    FROM Employee GROUP BY department;
    
    -- 15. Display the top 3 highest-paid employees. 
    SELECT * FROM Employee 
    ORDER BY salary DESC LIMIT 3;
    
    -- ORDER TABLE CREATION
    CREATE TABLE Orders ( 
    order_id INT PRIMARY KEY, 
    customer_id INT, 
    amount DECIMAL(10,2), 
    order_date DATE, 
    FOREIGN KEY(customer_id) REFERENCES Customers(customer_id) 
); 
-- data insertion for order table
INSERT INTO Orders
(order_id, customer_id, amount, order_date)
VALUES
(101, 1, 5000, '2026-01-10'),
(102, 1, 3000, '2026-01-15'),
(103, 2, 7000, '2026-02-05'),
(104, 2, 4000, '2026-02-10'),
(105, 3, 2500, '2026-02-20'),
(106, 4, 6000, '2026-03-01');

select * from orders;
-- CUSTOMER & ORDER TABLE QUESTION AND ANSWER(16-30)
-- 16.Find the total order amount for each customer.  
SELECT customer_id, SUM(amount) AS total_amount
FROM Orders GROUP BY customer_id;

-- 17.Find customers who have placed more than 3 orders.  
SELECT customer_id, COUNT(*) AS order_count
FROM Orders 
GROUP BY customer_id HAVING COUNT(*) > 3;

-- 18.Find the average order amount for each customer. 
SELECT customer_id, AVG(amount) AS average_amount
FROM Orders GROUP BY customer_id; 

-- 19.Find the highest order amount placed by each customer.
SELECT customer_id, MAX(amount) AS highest_order
FROM Orders GROUP BY customer_id;  

-- 20.Display customers sorted by their total purchase amount.  
SELECT c.customer_id, c.name, SUM(o.amount) AS total_purchase
FROM Customers c JOIN Orders o
ON c.customer_id = o.customer_id
GROUP BY c.customer_id, c.name
ORDER BY total_purchase DESC;

-- 21.Find customers whose total purchase amount exceeds 10,000.
SELECT c.customer_id, c.name, SUM(o.amount) AS total_purchase
FROM Customers c JOIN Orders o
ON c.customer_id = o.customer_id
GROUP BY c.customer_id, c.name HAVING SUM(o.amount) > 10000;

-- 22.Display customer names along with the total number of orders placed
SELECT c.name, COUNT(o.order_id) AS total_orders
FROM Customers c JOIN Orders o
ON c.customer_id = o.customer_id
GROUP BY c.customer_id, c.name;

-- 23.Find the customer who spent the highest amount. 
SELECT c.customer_id, c.name, SUM(o.amount) AS total_spent
FROM Customers c JOIN Orders o
ON c.customer_id = o.customer_id
GROUP BY c.customer_id, name
ORDER BY total_spent DESC LIMIT 1; 

-- 24.Find the customer who placed the maximum number of orders. 
SELECT c.customer_id, c.name, COUNT(o.order_id) AS total_orders
FROM Customers c JOIN Orders o
ON c.customer_id = o.customer_id
GROUP BY c.customer_id, c.name
ORDER BY total_orders DESC LIMIT 1; 

-- 25.Find customers whose average order amount is greater than 2,000.
SELECT c.customer_id, c.name, AVG(o.amount) AS average_order
FROM Customers c JOIN Orders o
ON c.customer_id = o.customer_id
GROUP BY c.customer_id, c.name HAVING AVG(o.amount) > 2000;  

-- 26. Display the top 5 customers based on total purchase amount.
SELECT c.customer_id, c.name, SUM(o.amount) AS total_purchase
FROM Customers c JOIN Orders o
ON c.customer_id = o.customer_id
GROUP BY c.customer_id, c.name
ORDER BY total_purchase DESC LIMIT 5;  

-- 27.Find the minimum order amount for each customer.  
SELECT customer_id, MIN(amount) AS minimum_order
FROM Orders GROUP BY customer_id;

-- 28.Find customers who have placed orders worth more than 5,000 in total. 
SELECT c.customer_id,c.name, SUM(o.amount) AS total_purchase
FROM Customers c JOIN Orders o
ON c.customer_id = o.customer_id
GROUP BY c.customer_id, c.name HAVING SUM(o.amount) > 5000;

-- 29.Display customer-wise total orders and total purchase amount.  
SELECT c.customer_id, c.name, COUNT(o.order_id) AS total_orders, SUM(o.amount) AS total_purchase
FROM Customers c JOIN Orders o
ON c.customer_id = o.customer_id
GROUP BY c.customer_id, c.name;

-- 30.Find customers who placed more than 2 orders and spent more than 8,000.  
SELECT c.customer_id, c.name, COUNT(o.order_id) AS total_orders, SUM(o.amount) AS total_purchase
FROM Customers c JOIN Orders o
ON c.customer_id = o.customer_id
GROUP BY c.customer_id, c.name HAVING COUNT(o.order_id) > 2 AND SUM(o.amount) > 8000;

select* from orders;

-- Students Table Creation
CREATE TABLE Students ( 
    student_id INT PRIMARY KEY, 
    student_name VARCHAR(50), 
    department VARCHAR(30), 
    marks INT 
); 
-- STUDENT TABLE DATA INSERTION
INSERT INTO Students
(student_id, student_name, department, marks)
VALUES
(1, 'Keerthika', 'IT', 85),
(2, 'Monika', 'ECE', 78),
(3, 'Karthik', 'CSE', 92),
(4, 'Janani', 'CSE', 88),
(5, 'Shahi', 'ECE', 85),
(6, 'Rahul', 'ECE', 82),
(7, 'Divya', 'IT', 90),
(8, 'Suresh', 'CSE', 70),
(9, 'Anitha', 'ECE', 95),
(10, 'Ram', 'IT', 68);
select * from students;
-- STUDENTS TABLED QUESTION AND ANSWER (31-45)
-- 31.Find the average marks scored by students in each department. 
SELECT department, AVG(marks) AS average_marks
FROM Students GROUP BY department; 

-- 32. Find departments whose average marks are above 75. 
SELECT department, AVG(marks) AS average_marks
FROM Students
GROUP BY department HAVING AVG(marks) > 75; 

-- 33.Find the highest mark scored in each department.
SELECT department, MAX(marks) AS highest_marks
FROM Students
GROUP BY department;

-- 34.Find the total number of students in each department
SELECT department, COUNT(*) AS student_count
FROM Students GROUP BY department;

-- 35.Find departments having more than 5 students.
SELECT department, COUNT(*) AS student_count
FROM Students
GROUP BY department HAVING COUNT(*) > 5;  

-- 36.Display departments sorted by average marks in descending order
SELECT department, AVG(marks) AS average_marks
FROM Students
GROUP BY department ORDER BY average_marks DESC;

-- 37.Find the top 3 departments based on average marks
SELECT department, AVG(marks) AS average_marks
FROM Students
GROUP BY department
ORDER BY average_marks DESC LIMIT 3;

-- 38.Find departments whose average marks are between 70 and 90.
SELECT department, AVG(marks) AS average_marks
FROM Students
GROUP BY department HAVING AVG(marks) BETWEEN 70 AND 90;

-- 39.Find the total marks scored by students in each department.
SELECT department, SUM(marks) AS total_marks
FROM Students
GROUP BY department;

-- 40.Display departments sorted by the total number of students. 
SELECT department, COUNT(*) AS student_count
FROM Students
GROUP BY department ORDER BY student_count DESC; 

-- 41.Find the lowest mark scored in each department.  
SELECT department, MIN(marks) AS lowest_marks
FROM Students
GROUP BY department;

-- 42.Find departments where the highest mark is greater than 90.
SELECT department, MAX(marks) AS highest_marks
FROM Students
GROUP BY department HAVING MAX(marks) > 90;  

-- 43.Find the number of students scoring above 80 in each department.
SELECT department, COUNT(*) AS student_count
FROM Students
WHERE marks > 80 GROUP BY department;  

-- 44 .Find departments where more than 3 students scored above 75. 
SELECT department, COUNT(*) AS student_count
FROM Students
WHERE marks > 75
GROUP BY department HAVING COUNT(*) > 3; 

-- 45.Display departments ordered by highest mark in descending order.  
SELECT department, MAX(marks) AS highest_marks
FROM Students
GROUP BY department
ORDER BY highest_marks DESC;






  














 

  
    
    
    
    
    
   
   
    
     
    
    
    
     
    
      
    
    
    
    
   
   
   
     
   
   
   
   
   
   
       
     
      
     
   
   
   
   
   
    
  


 

