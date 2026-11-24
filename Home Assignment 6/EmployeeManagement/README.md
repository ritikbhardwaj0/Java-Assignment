# Employee Management (Java Swing + JDBC)

A desktop application for adding employees to MySQL and displaying all employee records in a sortable table.

## Requirements

- JDK 17 or newer
- Maven 3.8 or newer
- MySQL 8 or newer

## 1. Create the database

Run the included SQL script with a MySQL account that can create databases and tables:

```sh
mysql -u root -p < database/schema.sql
```

The table uses the requested `emp_id`, `name`, `department`, and `salary` columns. The name column allows 100 characters as specified by the form requirements; the assignment's sample DDL omits the size for `name`, so the natural `VARCHAR(100)` form limit is used.

## 2. Configure database access

The defaults are `jdbc:mysql://localhost:3306/company_db`, username `root`, and an empty password. Set environment variables to use another local configuration:

```sh
export DB_URL='jdbc:mysql://localhost:3306/company_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC'
export DB_USER='root'
export DB_PASSWORD='your_mysql_password'
```

## 3. Run

From this directory:

```sh
mvn clean compile exec:java
```

Enter the employee data and select **Submit**. **View All Employees** reloads the table from MySQL. Employee IDs are primary keys and must be unique.

## Project layout

```text
EmployeeManagement/
├── database/schema.sql
├── pom.xml
└── src/main/java/com/example/
    ├── EmployeeDAO.java
    └── EmployeeForm.java
```
