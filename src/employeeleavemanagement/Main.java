package employeeleavemanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);

    static String url = "jdbc:mysql://localhost:3306/training";
    static String username = "root";
    static String password = "sql123";

    public static void main(String[] args) {

        while (true) {

            System.out.println();
            System.out.println("==================================");
            System.out.println(" EMPLOYEE LEAVE MANAGEMENT SYSTEM");
            System.out.println("==================================");

            System.out.println("1. Register Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Apply Leave");
            System.out.println("4. View Leave Requests");
            System.out.println("5. Approve Leave");
            System.out.println("6. Reject Leave");
            System.out.println("7. View Leave Balance");
            System.out.println("8. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {

            case 1:
                registerEmployee();
                break;

            case 2:
                viewEmployees();
                break;

            case 3:
                applyLeave();
                break;

            case 4:
                viewLeaveRequests();
                break;

            case 5:
                updateLeaveStatus("Approved");
                break;

            case 6:
                updateLeaveStatus("Rejected");
                break;

            case 7:
                viewLeaveBalance();
                break;

            case 8:
                System.out.println("Thank you!");
                return;

            default:
                System.out.println("Invalid choice.");
            }
        }
    }

    // ================= REGISTER EMPLOYEE =================

    public static void registerEmployee() {

        sc.nextLine();

        System.out.println("\n========== REGISTER EMPLOYEE ==========");

        System.out.print("Enter employee name: ");
        String name = sc.nextLine();

        System.out.print("Enter email: ");
        String email = sc.nextLine();

        System.out.print("Enter phone: ");
        String phone = sc.nextLine();

        System.out.print("Enter department: ");
        String department = sc.nextLine();

        String query ="INSERT INTO employees (employee_name, email, phone, department) VALUES (?, ?, ?, ?)";

        try {

            Connection con = DriverManager.getConnection(url, username, password);

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.setString(4, department);

            ps.executeUpdate();

            System.out.println("Employee registered successfully.");

            con.close();

        } catch (Exception e) {

            System.out.println("Error: " + e.getMessage());
        }
    }

    // ================= VIEW EMPLOYEES =================

    public static void viewEmployees() {

        String query = "SELECT * FROM employees";

        try {

            Connection con = DriverManager.getConnection(url, username, password);

            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                System.out.println("-------------------------");

                System.out.println("ID   : " + rs.getInt("employee_id"));

                System.out.println("Name :" + rs.getString("employee_name"));

                System.out.println("Email : " + rs.getString("email"));

                System.out.println("Phone : " + rs.getString("phone"));

                System.out.println("Department : "+ rs.getString("department"));
            }

            con.close();

        } catch (Exception e) {

            System.out.println("Error: " + e.getMessage());
        }
    }

    // ================= APPLY LEAVE =================

    public static void applyLeave() {

        sc.nextLine();

        System.out.println("\n========== APPLY LEAVE ==========");

        System.out.print("Enter employee ID: ");
        int employeeId = sc.nextInt();

        sc.nextLine();

        System.out.print("Enter leave type: ");
        String leaveType = sc.nextLine();

        System.out.print("Enter start date (YYYY-MM-DD): ");
        Date startDate = Date.valueOf(sc.nextLine());

        System.out.print("Enter end date (YYYY-MM-DD): ");
        Date endDate = Date.valueOf(sc.nextLine());

        System.out.print("Enter reason: ");
        String reason = sc.nextLine();

        String query ="INSERT INTO leave_requests (employee_id, leave_type, start_date, end_date, reason) VALUES (?, ?, ?, ?, ?)";

        try {

            Connection con = DriverManager.getConnection(url, username, password);

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, employeeId);
            ps.setString(2, leaveType);
            ps.setDate(3, startDate);
            ps.setDate(4, endDate);
            ps.setString(5, reason);

            ps.executeUpdate();

            System.out.println("Leave applied successfully.");

            con.close();

        } catch (Exception e) {

            System.out.println("Error: " + e.getMessage());
        }
    }

    // ================= VIEW LEAVE REQUESTS =================

    public static void viewLeaveRequests() {

        String query =
                "SELECT l.leave_id, e.employee_name, " +
                "l.leave_type, l.start_date, l.end_date, " +
                "l.reason, l.status " +
                "FROM leave_requests l " +
                "JOIN employees e " +
                "ON l.employee_id = e.employee_id";

        try {

            Connection con =  DriverManager.getConnection(url, username, password);

            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                System.out.println("-------------------------");

                System.out.println("Leave ID : "+ rs.getInt("leave_id"));

                System.out.println("Employee : " + rs.getString("employee_name"));

                System.out.println("Leave Type : "+ rs.getString("leave_type"));

                System.out.println("Start Date : "+ rs.getDate("start_date"));

                System.out.println("End Date : " + rs.getDate("end_date"));

                System.out.println("Reason : " + rs.getString("reason"));

                System.out.println("Status : "+ rs.getString("status"));
            }

            con.close();

        } catch (Exception e) {

            System.out.println("Error: " + e.getMessage());
        }
    }

    // ================= APPROVE / REJECT =================

    public static void updateLeaveStatus(String status) {

        System.out.print("Enter leave ID: ");
        int leaveId = sc.nextInt();

        String query ="UPDATE leave_requests SET status = ? WHERE leave_id = ?";

        try {

            Connection con = DriverManager.getConnection(url, username, password);

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, status);
            ps.setInt(2, leaveId);

            int rows = ps.executeUpdate();

            if (rows > 0) {

                System.out.println("Leave " + status.toLowerCase() + " successfully.");

            } else {

                System.out.println("Leave request not found.");
            }

            con.close();

        } catch (Exception e) {

            System.out.println("Error: " + e.getMessage());
        }
    }

    // ================= LEAVE BALANCE =================

    public static void viewLeaveBalance() {

        System.out.print("Enter employee ID: ");
        int employeeId = sc.nextInt();

        String query = "SELECT * FROM leave_balance WHERE employee_id = ?";

        try {

            Connection con = DriverManager.getConnection(url, username, password);

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, employeeId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                System.out.println("\n========== LEAVE BALANCE ==========");

                System.out.println("Employee ID : " + rs.getInt("employee_id"));
                       
                System.out.println("Total Leaves : "+ rs.getInt("total_leaves"));
                        
                System.out.println("Used Leaves : "+ rs.getInt("used_leaves"));
                        
                System.out.println("Remaining Leaves : "  + rs.getInt("remaining_leaves"));
                     
            } else {

                System.out.println("Leave balance not found.");
            }

            con.close();

        } catch (Exception e) {

            System.out.println("Error: " + e.getMessage());
        }
    }
}
