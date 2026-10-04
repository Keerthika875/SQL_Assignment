package studentmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StudentDAO {
    // ================= INSERT =================

    public void addStudent(Student student) {
    	String query = "INSERT INTO students (student_name, email, phone) VALUES (?, ?, ?)";

        try {

            Connection con =DBConnection.getConnection();

            PreparedStatement ps =con.prepareStatement(query);


            ps.setString( 1,  student.getStudentName());
               
            ps.setString(2,  student.getEmail());
           
            ps.setString(3, student.getPhone());
             

            ps.executeUpdate();


            System.out.println(  "Student added successfully.");
          
            con.close();

        } catch (Exception e) {

            System.out.println( "Error: " + e.getMessage());
            
                }
    }


    // ================= SELECT =================

    public void viewStudents() {

        String query ="SELECT * FROM students";


        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps =  con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();


            while (rs.next()) {

                System.out.println(  "ID    : " +rs.getInt("student_id"));
               
                System.out.println(  "Name  : " +   rs.getString("student_name"));
                     
                System.out.println( "Email : " + rs.getString("email"));

                System.out.println("Phone : " +    rs.getString("phone"));
                    
                System.out.println(  "-------------------------"
                );
            }


            con.close();

        } catch (Exception e) {

            System.out.println( "Error: " + e.getMessage());
          
        }
    }


    // ================= SELECT WHERE =================

    public void searchStudent(int id) {

        String query = "SELECT * FROM students  WHERE student_id = ?";


        try {

            Connection con =  DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(query);


            ps.setInt(1, id);


            ResultSet rs = ps.executeQuery();


            if (rs.next()) {

                System.out.println("ID    : " +    rs.getInt("student_id"));
               
                System.out.println( "Name  : " +rs.getString("student_name"));
                 
                System.out.println( "Email : " + rs.getString("email"));
                
                System.out.println( "Phone : " + rs.getString("phone"));
              
            } else {

                System.out.println( "Student not found.");
            
            }


            con.close();

        } catch (Exception e) {

            System.out.println("Error: " + e.getMessage()
            );
        }
    }


    // ================= UPDATE =================

    public void updateStudent(Student student) {

        String query =
                "UPDATE students " +
                "SET student_name = ?, " +
                "email = ?, " +
                "phone = ? " +
                "WHERE student_id = ?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(query);


            ps.setString(1,  student.getStudentName());
                   

            ps.setString(2,  student.getEmail());
           
            ps.setString(3, student.getPhone());
                               
            ps.setInt(4 , student.getStudentId());
            int rows = ps.executeUpdate();
            if (rows > 0) {

                System.out.println( "Student updated successfully.");
             
            } else {

                System.out.println("Student not found.");
           
            }


            con.close();

        } catch (Exception e) {

            System.out.println("Error: " + e.getMessage());
          
        }
    }


    // ================= DELETE =================

    public void deleteStudent(int id) {

        String query ="DELETE FROM students " +
                "WHERE student_id = ?";

        try {

            Connection con =DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(query);


            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {

                System.out.println("Student deleted successfully.");
               
            } else {

                System.out.println("Student not found.");
            
            }

            con.close();

        } catch (Exception e) {

            System.out.println( "Error: " + e.getMessage());
        
        }
    }
}