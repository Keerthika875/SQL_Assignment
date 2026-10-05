package studentmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class AttendanceDAO {


    public void addAttendance(int enrollmentId,String status) {


        String query ="INSERT INTO attendance (enrollment_id, attendance_date, status) VALUES (?, CURDATE(), ?)";

        try {

            Connection con =DBConnection.getConnection();

            PreparedStatement ps =con.prepareStatement(query);


            ps.setInt(1, enrollmentId);
            ps.setString(2, status);


            ps.executeUpdate();


            System.out.println( "Attendance added successfully.");


            con.close();

        } catch (Exception e) {

            System.out.println(   "Error: " + e.getMessage());

        }
    }
}
