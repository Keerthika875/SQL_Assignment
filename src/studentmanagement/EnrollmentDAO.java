package studentmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;

public class EnrollmentDAO {


    // ================= ENROLL =================

    public void enrollStudent( int studentId, int courseId) {


        String query = "INSERT INTO enrollments (student_id, course_id, enrollment_date) VALUES (?, ?, CURDATE())";

        try {

            Connection con =DBConnection.getConnection();

            PreparedStatement ps =con.prepareStatement(query);

            ps.setInt(1, studentId);
            ps.setInt(2, courseId);

            ps.executeUpdate();

            System.out.println(  "Student enrolled successfully.");

            con.close();

        } catch (Exception e) {

            System.out.println( "Error: " + e.getMessage() );
        }
    }


    // ================= JOIN =================

    public void viewEnrollments() {

        String query =
                "SELECT " +
                "e.enrollment_id, " +
                "s.student_name, " +
                "c.course_name, " +
                "e.enrollment_date " +

                "FROM enrollments e " +

                "JOIN students s " +
                "ON e.student_id = s.student_id " +

                "JOIN courses c " +
                "ON e.course_id = c.course_id";


        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps =con.prepareStatement(query);

            ResultSet rs =ps.executeQuery();


            while (rs.next()) {

                System.out.println(  "Enrollment ID : " +rs.getInt("enrollment_id"));

                System.out.println("Student : " +rs.getString("student_name"));
                
                System.out.println("Course  : " +  rs.getString("course_name"));
               
                System.out.println( "Date : " +rs.getDate("enrollment_date"));
                     

                System.out.println(  "-------------------------");
             
            }


            con.close();

        } catch (Exception e) {

            System.out.println("Error: " + e.getMessage());
         
        }
    }

    // ================= TRANSACTION =================

    public void enrollmentTransaction( int studentId, int courseId) {


    	String query = "INSERT INTO enrollments (student_id, course_id, enrollment_date) VALUES (?, ?, ?)";
    	

        Connection con = null;

        try {

            con = DBConnection.getConnection();


            // Start transaction

            con.setAutoCommit(false);

            PreparedStatement ps = con.prepareStatement(query);


            ps.setInt(1, studentId);

            ps.setInt(2, courseId);

            ps.setDate(3, new Date(System.currentTimeMillis()));


            ps.executeUpdate();


            // Save transaction

            con.commit();


            System.out.println( "Transaction completed.");
    

        } catch (Exception e) {

            try {

                if (con != null) {

                    con.rollback();
                }

            } catch (Exception ex) {

                System.out.println( "Rollback failed.");
                       
            }


            System.out.println( "Transaction failed: " +  e.getMessage());
                

        } finally {

            try {

                if (con != null) {

                    con.close();
                }

            } catch (Exception e) {

                e.printStackTrace();
            }
        }
    }
}
