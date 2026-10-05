package studentmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MarksDAO {


    // ================= ADD MARKS =================

    public void addMarks(int enrollmentId,String subject, int marks) {


        String query = "INSERT INTO marks (enrollment_id, subject, marks) VALUES (?, ?, ?)";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps =con.prepareStatement(query);


            ps.setInt(1, enrollmentId);
            ps.setString(2, subject);
            ps.setInt(3, marks);


            ps.executeUpdate();


            System.out.println("Marks added successfully.");

            con.close();

        } catch (Exception e) {

            System.out.println("Error: " + e.getMessage());

        }
    }


    // ================= RESULT JOIN =================

    public void generateResult() {


        String query =
                "SELECT " +
                "s.student_name, " +
                "c.course_name, " +
                "m.subject, " +
                "m.marks " +

                "FROM students s " +

                "JOIN enrollments e " +
                "ON s.student_id = e.student_id " +

                "JOIN courses c " +
                "ON e.course_id = c.course_id " +

                "JOIN marks m " +
                "ON e.enrollment_id = m.enrollment_id";


        try {

            Connection con =DBConnection.getConnection();

            PreparedStatement ps =con.prepareStatement(query);

            ResultSet rs =ps.executeQuery();


            while (rs.next()) {

                System.out.println("Student : " +rs.getString("student_name"));
                
                System.out.println( "Course  : " +rs.getString("course_name"));

                System.out.println("Subject : " + rs.getString("subject"));

                System.out.println(  "Marks   : " + rs.getInt("marks"));
                
                System.out.println( "-------------------------");
            }


            con.close();

        } catch (Exception e) {

            System.out.println( "Error: " + e.getMessage());

        }
    }
}
