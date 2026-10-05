package payrollManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    static String url = "jdbc:mysql://localhost:3306/training";
    static String username = "root";
    static String password = "sql123";

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n=================================");
            System.out.println("     PAYROLL MANAGEMENT SYSTEM");
            System.out.println("=================================");

            System.out.println("1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Add Salary");
            System.out.println("6. View Salary");
            System.out.println("7. Generate Payroll");
            System.out.println("8. View Payslip");
            System.out.println("9. Total Salary");
            System.out.println("10. Average Salary");
            System.out.println("11. Batch Payroll");
            System.out.println("12. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    addEmployee();
                    break;

                case 2:
                    viewEmployees();
                    break;

                case 3:
                    updateEmployee();
                    break;

                case 4:
                    deleteEmployee();
                    break;

                case 5:
                    addSalary();
                    break;

                case 6:
                    viewSalary();
                    break;

                case 7:
                    generatePayroll();
                    break;

                case 8:
                    viewPayslip();
                    break;

                case 9:
                    totalSalary();
                    break;

                case 10:
                    averageSalary();
                    break;

                case 11:
                    batchPayroll();
                    break;

                case 12:
                    System.out.println("Thank you!");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // =========================================
    // 1. ADD EMPLOYEE
    // =========================================

    static void addEmployee() {

        System.out.println("\n========== ADD EMPLOYEE ==========");

        sc.nextLine();

        System.out.print("Enter employee name: ");
        String name = sc.nextLine();

        System.out.print("Enter email: ");
        String email = sc.nextLine();

        System.out.print("Enter phone: ");
        String phone = sc.nextLine();

        System.out.print("Enter department: ");
        String department = sc.nextLine();

        String query = "INSERT INTO employees "
                + "(employee_name, email, phone, department) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection con =
                     DriverManager.getConnection(url, username, password);

             PreparedStatement ps =
                     con.prepareStatement(query)) {

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.setString(4, department);

            ps.executeUpdate();

            System.out.println("Employee added successfully.");

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    // =========================================
    // 2. VIEW EMPLOYEES
    // =========================================

    static void viewEmployees() {

        System.out.println("\n========== EMPLOYEE LIST ==========");

        String query = "SELECT * FROM employees";

        try (Connection con =
                     DriverManager.getConnection(url, username, password);

             PreparedStatement ps =
                     con.prepareStatement(query);

             ResultSet rs =
                     ps.executeQuery()) {

            while (rs.next()) {

                System.out.println("--------------------------------");

                System.out.println("Employee ID: "
                        + rs.getInt("employee_id"));

                System.out.println("Name: "
                        + rs.getString("employee_name"));

                System.out.println("Email: "
                        + rs.getString("email"));

                System.out.println("Phone: "
                        + rs.getString("phone"));

                System.out.println("Department: "
                        + rs.getString("department"));
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    // =========================================
    // 3. UPDATE EMPLOYEE
    // =========================================

    static void updateEmployee() {

        System.out.println("\n========== UPDATE EMPLOYEE ==========");

        System.out.print("Enter employee ID: ");
        int id = sc.nextInt();

        sc.nextLine();

        System.out.print("Enter new name: ");
        String name = sc.nextLine();

        System.out.print("Enter new email: ");
        String email = sc.nextLine();

        System.out.print("Enter new phone: ");
        String phone = sc.nextLine();

        System.out.print("Enter new department: ");
        String department = sc.nextLine();

        String query = "UPDATE employees SET "
                + "employee_name=?, email=?, phone=?, department=? "
                + "WHERE employee_id=?";

        try (Connection con =
                     DriverManager.getConnection(url, username, password);

             PreparedStatement ps =
                     con.prepareStatement(query)) {

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.setString(4, department);
            ps.setInt(5, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {

                System.out.println("Employee updated successfully.");

            } else {

                System.out.println("Employee not found.");
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    // =========================================
    // 4. DELETE EMPLOYEE
    // =========================================

    static void deleteEmployee() {

        System.out.println("\n========== DELETE EMPLOYEE ==========");

        System.out.print("Enter employee ID: ");
        int id = sc.nextInt();

        String query =
                "DELETE FROM employees WHERE employee_id=?";

        try (Connection con =
                     DriverManager.getConnection(url, username, password);

             PreparedStatement ps =
                     con.prepareStatement(query)) {

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {

                System.out.println("Employee deleted successfully.");

            } else {

                System.out.println("Employee not found.");
            }

        } catch (SQLException e) {

            System.out.println(
                    "Cannot delete employee because salary or payroll records exist."
            );
        }
    }

    // =========================================
    // 5. ADD SALARY
    // =========================================

    static void addSalary() {

        System.out.println("\n========== ADD SALARY ==========");

        System.out.print("Enter employee ID: ");
        int employeeId = sc.nextInt();

        System.out.print("Enter basic salary: ");
        double basicSalary = sc.nextDouble();

        System.out.print("Enter allowance: ");
        double allowance = sc.nextDouble();

        System.out.print("Enter deduction: ");
        double deduction = sc.nextDouble();

        String query = "INSERT INTO salaries "
                + "(employee_id, basic_salary, allowance, deduction) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection con =
                     DriverManager.getConnection(url, username, password);

             PreparedStatement ps =
                     con.prepareStatement(query)) {

            ps.setInt(1, employeeId);
            ps.setDouble(2, basicSalary);
            ps.setDouble(3, allowance);
            ps.setDouble(4, deduction);

            ps.executeUpdate();

            System.out.println("Salary added successfully.");

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    // =========================================
    // 6. VIEW SALARY
    // =========================================

    static void viewSalary() {

        System.out.println("\n========== SALARY DETAILS ==========");

        String query = "SELECT * FROM salaries";

        try (Connection con =
                     DriverManager.getConnection(url, username, password);

             PreparedStatement ps =
                     con.prepareStatement(query);

             ResultSet rs =
                     ps.executeQuery()) {

            while (rs.next()) {

                System.out.println("--------------------------------");

                System.out.println("Salary ID: "
                        + rs.getInt("salary_id"));

                System.out.println("Employee ID: "
                        + rs.getInt("employee_id"));

                System.out.println("Basic Salary: "
                        + rs.getDouble("basic_salary"));

                System.out.println("Allowance: "
                        + rs.getDouble("allowance"));

                System.out.println("Deduction: "
                        + rs.getDouble("deduction"));
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    // =========================================
    // 7. GENERATE PAYROLL
    // =========================================

    static void generatePayroll() {

        System.out.println("\n========== GENERATE PAYROLL ==========");

        System.out.print("Enter employee ID: ");
        int employeeId = sc.nextInt();

        sc.nextLine();

        System.out.print("Enter payroll month: ");
        String month = sc.nextLine();

        String selectQuery =
                "SELECT basic_salary, allowance, deduction "
                + "FROM salaries WHERE employee_id=?";

        String insertQuery =
                "INSERT INTO payroll "
                + "(employee_id, gross_salary, tax, net_salary, payroll_month) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection con =
                     DriverManager.getConnection(url, username, password);

             PreparedStatement selectPs =
                     con.prepareStatement(selectQuery);

             PreparedStatement insertPs =
                     con.prepareStatement(insertQuery)) {

            selectPs.setInt(1, employeeId);

            ResultSet rs = selectPs.executeQuery();

            if (rs.next()) {

                double basicSalary =
                        rs.getDouble("basic_salary");

                double allowance =
                        rs.getDouble("allowance");

                double deduction =
                        rs.getDouble("deduction");

                // Salary calculation

                double grossSalary =
                        basicSalary + allowance - deduction;

                // Simple 10% tax

                double tax =
                        grossSalary * 0.10;

                double netSalary =
                        grossSalary - tax;

                insertPs.setInt(1, employeeId);
                insertPs.setDouble(2, grossSalary);
                insertPs.setDouble(3, tax);
                insertPs.setDouble(4, netSalary);
                insertPs.setString(5, month);

                insertPs.executeUpdate();

                System.out.println("\nPayroll generated successfully.");

                System.out.println("Gross Salary: "
                        + grossSalary);

                System.out.println("Tax: "
                        + tax);

                System.out.println("Net Salary: "
                        + netSalary);

            } else {

                System.out.println(
                        "Salary details not found."
                );
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    // =========================================
    // 8. VIEW PAYSLIP
    // =========================================

    static void viewPayslip() {

        System.out.println("\n========== PAYSLIP ==========");

        System.out.print("Enter employee ID: ");
        int employeeId = sc.nextInt();

        String query =
                "SELECT e.employee_name, e.email, e.phone, "
                + "e.department, p.gross_salary, p.tax, "
                + "p.net_salary, p.payroll_month "
                + "FROM employees e "
                + "JOIN payroll p "
                + "ON e.employee_id = p.employee_id "
                + "WHERE e.employee_id=?";

        try (Connection con =
                     DriverManager.getConnection(url, username, password);

             PreparedStatement ps =
                     con.prepareStatement(query)) {

            ps.setInt(1, employeeId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                System.out.println("\n================================");
                System.out.println("             PAYSLIP");
                System.out.println("================================");

                System.out.println("Employee Name: "
                        + rs.getString("employee_name"));

                System.out.println("Email: "
                        + rs.getString("email"));

                System.out.println("Phone: "
                        + rs.getString("phone"));

                System.out.println("Department: "
                        + rs.getString("department"));

                System.out.println("Month: "
                        + rs.getString("payroll_month"));

                System.out.println("--------------------------------");

                System.out.println("Gross Salary: "
                        + rs.getDouble("gross_salary"));

                System.out.println("Tax: "
                        + rs.getDouble("tax"));

                System.out.println("Net Salary: "
                        + rs.getDouble("net_salary"));

                System.out.println("================================");

            } else {

                System.out.println("Payslip not found.");
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    // =========================================
    // 9. SUM - TOTAL SALARY
    // =========================================

    static void totalSalary() {

        System.out.println("\n========== TOTAL SALARY ==========");

        String query =
                "SELECT SUM(net_salary) AS total_salary "
                + "FROM payroll";

        try (Connection con =
                     DriverManager.getConnection(url, username, password);

             PreparedStatement ps =
                     con.prepareStatement(query);

             ResultSet rs =
                     ps.executeQuery()) {

            if (rs.next()) {

                System.out.println(
                        "Total Salary Paid: "
                        + rs.getDouble("total_salary")
                );
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    // =========================================
    // 10. AVG - AVERAGE SALARY
    // =========================================

    static void averageSalary() {

        System.out.println("\n========== AVERAGE SALARY ==========");

        String query =
                "SELECT AVG(net_salary) AS average_salary "
                + "FROM payroll";

        try (Connection con =
                     DriverManager.getConnection(url, username, password);

             PreparedStatement ps =
                     con.prepareStatement(query);

             ResultSet rs =
                     ps.executeQuery()) {

            if (rs.next()) {

                System.out.println(
                        "Average Salary: "
                        + rs.getDouble("average_salary")
                );
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    // =========================================
    // 11. BATCH PROCESSING
    // =========================================

    static void batchPayroll() {

        System.out.println("\n========== BATCH PAYROLL ==========");

        sc.nextLine();

        System.out.print("Enter payroll month: ");
        String month = sc.nextLine();

        String selectQuery =
                "SELECT employee_id, basic_salary, "
                + "allowance, deduction FROM salaries";

        String insertQuery =
                "INSERT INTO payroll "
                + "(employee_id, gross_salary, tax, "
                + "net_salary, payroll_month) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection con =
                     DriverManager.getConnection(url, username, password);

             PreparedStatement selectPs =
                     con.prepareStatement(selectQuery);

             PreparedStatement insertPs =
                     con.prepareStatement(insertQuery)) {

            ResultSet rs = selectPs.executeQuery();

            while (rs.next()) {

                int employeeId =
                        rs.getInt("employee_id");

                double basicSalary =
                        rs.getDouble("basic_salary");

                double allowance =
                        rs.getDouble("allowance");

                double deduction =
                        rs.getDouble("deduction");

                double grossSalary =
                        basicSalary + allowance - deduction;

                double tax =
                        grossSalary * 0.10;

                double netSalary =
                        grossSalary - tax;

                insertPs.setInt(1, employeeId);
                insertPs.setDouble(2, grossSalary);
                insertPs.setDouble(3, tax);
                insertPs.setDouble(4, netSalary);
                insertPs.setString(5, month);

                // Add record to batch

                insertPs.addBatch();
            }

            // Execute all records together

            insertPs.executeBatch();

            System.out.println(
                    "Batch payroll processed successfully."
            );

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }
}