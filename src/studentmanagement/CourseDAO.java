package studentmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CourseDAO {


    // ================= ADD COURSE =================

    public void addCourse( String courseName, String duration) {


        String query = "INSERT INTO courses (course_name, duration) VALUES (?, ?)";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps =con.prepareStatement(query);

            ps.setString(1, courseName);
            ps.setString(2, duration);

            ps.executeUpdate();


            System.out.println("Course added successfully.");
           
            con.close();

        } catch (Exception e) {

            System.out.println( "Error: " + e.getMessage());
        
        }
    }


    // ================= VIEW COURSES =================

    public void viewCourses() {

        String query ="SELECT * FROM courses";
        try {

            Connection con =DBConnection.getConnection();

            PreparedStatement ps =con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                System.out.println(  "Course ID : " +rs.getInt("course_id"));
                 
                System.out.println(   "Course    : " +rs.getString("course_name"));
               
                System.out.println( "Duration  : " +rs.getString("duration"));
                   
                System.out.println( "-------------------------");
               
            }

            con.close();

        } catch (Exception e) {

            System.out.println( "Error: " + e.getMessage());
     
        }
    }
}
