package studentmanagement;

import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);

    static StudentDAO studentDAO =   new StudentDAO();

    static CourseDAO courseDAO =   new CourseDAO();

    static EnrollmentDAO enrollmentDAO = new EnrollmentDAO();

    static AttendanceDAO attendanceDAO = new AttendanceDAO();

    static MarksDAO marksDAO =  new MarksDAO();


    public static void main(String[] args) {


        // Create all tables

        CreateTables.createTables();

        while (true) {

            System.out.println(  "\n==================================" );

            System.out.println(   " STUDENT MANAGEMENT SYSTEM" );

            System.out.println(  "=================================="  );

            System.out.println( "1. Register Student"  );

            System.out.println("2. View Students"  );

            System.out.println("3. Search Student" );

            System.out.println( "4. Update Student" );

            System.out.println( "5. Delete Student");

            System.out.println("6. Add Course" );

            System.out.println("7. View Course" );

            System.out.println("8. Enroll Student" );

            System.out.println( "9. View Enrollments" );

            System.out.println( "10. Add Attendance" );

            System.out.println("11. Add Marks");

            System.out.println("12. Generate Result");

            System.out.println("13. Transaction");

            System.out.println("14. Exit");

            System.out.print("\nEnter your choice: ");


            int choice;

            try {

                choice = Integer.parseInt( sc.nextLine()
                        );

            } catch (Exception e) {

                System.out.println("Enter numbers only" );

                continue;
            }


            switch (choice) {


                // ================= STUDENT =================

                case 1:

                    registerStudent();

                    break;


                case 2:

                    studentDAO.viewStudents();

                    break;


                case 3:

                    System.out.print( "Enter student ID: "  );

                    int searchId = Integer.parseInt( sc.nextLine());

                    studentDAO.searchStudent(  searchId);

                    break;

                case 4:

                    updateStudent();

                    break;


                case 5:

                    System.out.print("Enter student ID: " );

                    int deleteId=  Integer.parseInt( sc.nextLine() );

                    studentDAO.deleteStudent(  deleteId);

                    break;


                // ================= COURSE =================

                case 6:

                    System.out.print( "Enter course name: "  );

                    String course = sc.nextLine();


                    System.out.print( "Enter duration: " );

                    String duration =
                            sc.nextLine();


                    courseDAO.addCourse(
                            course,
                            duration
                    );

                    break;


                case 7:

                    courseDAO.viewCourses();

                    break;


                // ================= ENROLLMENT =================

                case 8:

                    System.out.println(  "Enter student ID: ");
                  
                    int studentId =Integer.parseInt(sc.nextLine());
                 
                    System.out.print( "Enter course ID: ");
                  
                    int courseId = Integer.parseInt(  sc.nextLine());
                         
                    enrollmentDAO.enrollStudent(studentId, courseId);
                  
                    break;


                case 9:

                    enrollmentDAO.viewEnrollments();

                    break;


                // ================= ATTENDANCE =================

                case 10:

                    System.out.print(  "Enter enrollment ID: ");
               

                    int enrollmentId =  Integer.parseInt( sc.nextLine());
                               

                    System.out.print( "Enter status (Present/Absent): ");
                  

                    String status = sc.nextLine();


                    attendanceDAO.addAttendance(enrollmentId, status);
                
                    break;

                // ================= MARKS =================

                case 11:

                    System.out.print("Enter enrollment ID: ");
                   
                    int markEnrollmentId =   Integer.parseInt( sc.nextLine());
                                   

                    System.out.print("Enter subject: ");
                
                    String subject =  sc.nextLine();
                     
                    System.out.print("Enter marks: ");
                   

                    int marks =Integer.parseInt(sc.nextLine());
                  
                    if (marks >= 0 && marks <= 100) {

                        marksDAO.addMarks(
                                markEnrollmentId,
                                subject,
                                marks
                        );

                    } else {

                        System.out.println(  "Marks must be between 0 and 100.");
                  
                    }

                    break;


                // ================= RESULT =================

                case 12:

                    marksDAO.generateResult();

                    break;


                // ================= TRANSACTION =================

                case 13:

                    System.out.print( "Enter student ID: ");

                    int transactionStudentId = Integer.parseInt( sc.nextLine());
                           

                    System.out.print( "Enter course ID: ");
                   
                    int transactionCourseId =  Integer.parseInt( sc.nextLine());
                            

                    enrollmentDAO.enrollmentTransaction(
                            transactionStudentId,
                            transactionCourseId
                    );

                    break;


                // ================= EXIT =================

                case 14:

                    System.out.println("Thank you!" );

                    sc.close();

                    return;


                default:

                    System.out.println( "Invalid choice." );
            }
        }
    }


    // ==================================================
    //             REGISTER STUDENT
    // ==================================================

    public static void registerStudent() {


        System.out.println(   "\n========== REGISTER STUDENT ==========");

        String name;


        // NAME VALIDATION

        while (true) {

            System.out.print(   "Enter student name: ");

            name = sc.nextLine();

            if (name.matches( "[a-zA-Z ]+")) {

                break;

            } else {

                System.out.println(  "Name should contain alphabets only."  );
            }
        }


        // EMAIL VALIDATION

        String email;


        while (true) {

            System.out.print(   "Enter email: "  );

            email =  sc.nextLine();


            if (email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {

                break;

            } else {

                System.out.println( "Invalid email." );
            }
        }


        // PHONE VALIDATION

        String phone;


        while (true) {

            System.out.print( "Enter phone: " );

            phone = sc.nextLine();


            if (phone.matches( "[0-9]{10}")) {

                break;

            } else {

                System.out.println("Phone must contain 10 digits.");
            }
        }


        Student student = new Student( name,email,phone);
                 
        studentDAO.addStudent(student);
    }


    // ==================================================
    //             UPDATE STUDENT
    // ==================================================

    public static void updateStudent() {
        System.out.print( "Enter student ID: ");

        int id =Integer.parseInt(sc.nextLine());


        System.out.print( "Enter new name");

        String name =   sc.nextLine();


        System.out.print( "Enter new email: " );

        String email = sc.nextLine();


        System.out.print(  "Enter new phone: " );

        String phone =  sc.nextLine();


        Student student = new Student(name, email,phone);
               
        student.setStudentId(id);


        studentDAO.updateStudent(student);
    }
}
