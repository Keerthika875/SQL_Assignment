package studentmanagement;

import java.sql.Connection;
import java.sql.Statement;

public class CreateTables {

    public static void createTables() {

        try {

            Connection con =
                    DBConnection.getConnection();

            Statement stmt =
                    con.createStatement();


            // STUDENTS TABLE

            String students =
                    "CREATE TABLE IF NOT EXISTS students (" +
                    "student_id INT PRIMARY KEY AUTO_INCREMENT," +
                    "student_name VARCHAR(100) NOT NULL," +
                    "email VARCHAR(100) UNIQUE NOT NULL," +
                    "phone VARCHAR(15) NOT NULL" +
                    ")";

            stmt.executeUpdate(students);


            // COURSES TABLE

            String courses =
                    "CREATE TABLE IF NOT EXISTS courses (" +
                    "course_id INT PRIMARY KEY AUTO_INCREMENT," +
                    "course_name VARCHAR(100) NOT NULL," +
                    "duration VARCHAR(50) NOT NULL" +
                    ")";

            stmt.executeUpdate(courses);


            // ENROLLMENTS TABLE

            String enrollments =
                    "CREATE TABLE IF NOT EXISTS enrollments (" +
                    "enrollment_id INT PRIMARY KEY AUTO_INCREMENT," +
                    "student_id INT NOT NULL," +
                    "course_id INT NOT NULL," +
                    "enrollment_date DATE NOT NULL," +

                    "FOREIGN KEY(student_id) " +
                    "REFERENCES students(student_id) " +

                    "ON DELETE CASCADE," +

                    "FOREIGN KEY(course_id) " +
                    "REFERENCES courses(course_id)" +
                    ")";

            stmt.executeUpdate(enrollments);


            // ATTENDANCE TABLE

            String attendance =
                    "CREATE TABLE IF NOT EXISTS attendance (" +
                    "attendance_id INT PRIMARY KEY AUTO_INCREMENT," +
                    "enrollment_id INT NOT NULL," +
                    "attendance_date DATE NOT NULL," +
                    "status VARCHAR(20) NOT NULL," +

                    "CHECK(status IN ('Present','Absent'))," +

                    "FOREIGN KEY(enrollment_id) " +
                    "REFERENCES enrollments(enrollment_id)" +
                    ")";

            stmt.executeUpdate(attendance);


            // MARKS TABLE

            String marks =
                    "CREATE TABLE IF NOT EXISTS marks (" +
                    "mark_id INT PRIMARY KEY AUTO_INCREMENT," +
                    "enrollment_id INT NOT NULL," +
                    "subject VARCHAR(100) NOT NULL," +
                    "marks INT NOT NULL," +

                    "CHECK(marks BETWEEN 0 AND 100)," +

                    "FOREIGN KEY(enrollment_id) " +
                    "REFERENCES enrollments(enrollment_id)" +
                    ")";

            stmt.executeUpdate(marks);


            System.out.println(
                    "All tables created successfully."
            );

            con.close();

        } catch (Exception e) {

            System.out.println(
                    "Error: " + e.getMessage()
            );
        }
    }
}
