package employeeleavemanagement;

public class Employee {

    private int employeeId;
    private String employeeName;
    private String email;
    private String phone;
    private String department;

    public Employee() {
    }

    public Employee(String employeeName, String email, String phone, String department) {
        this.employeeName = employeeName;
        this.email = email;
        this.phone = phone;
        this.department = department;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
