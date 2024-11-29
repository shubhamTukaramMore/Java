package shubham;

import java.sql.*;
import java.util.Scanner;

public class sixteenProg {

    // Method to establish the JDBC connection
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/University";  // Change to your database URL
        String username = "root";  // Replace with your DB username
        String password = "Pass@123";  // Replace with your DB password

        // Establish and return the connection
        return DriverManager.getConnection(url, username, password);
    }

    // Method to invoke the "getStudentById" stored procedure using CallableStatement
    public static void getStudentById(int studentId) {
        String procedureCall = "{CALL getStudentById(?)}";  // Stored procedure call

        try (Connection connection = getConnection();
             CallableStatement callableStatement = connection.prepareCall(procedureCall)) {

            // Set the input parameter for the stored procedure
            callableStatement.setInt(1, studentId);

            // Execute the stored procedure and retrieve the result set
            ResultSet resultSet = callableStatement.executeQuery();

            // Check if the student exists
            if (resultSet.next()) {
                // Retrieve and display student details
                int id = resultSet.getInt("student_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                int age = resultSet.getInt("age");
                String major = resultSet.getString("major");
                int enrollmentYear = resultSet.getInt("enrollment_year");

                // Display the details
                System.out.println("Student ID: " + id);
                System.out.println("Name: " + firstName + " " + lastName);
                System.out.println("Age: " + age);
                System.out.println("Major: " + major);
                System.out.println("Enrollment Year: " + enrollmentYear);
            } else {
                System.out.println("Student not found with ID: " + studentId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Main method to run the program
    public static void main(String[] args) {
        // Create a Scanner object to read input from the console
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter a student ID
        System.out.print("Enter student ID: ");
        int studentId = scanner.nextInt();

        // Fetch student details using the getStudentById method
        getStudentById(studentId);
    }
}
