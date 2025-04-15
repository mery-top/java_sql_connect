// SQL Connector JAVA
import java.sql.*;
import java.util.*;

public class FrontEnd {

    // JDBC variables
    public static Connection conn;
    public static PreparedStatement pst;

    public FrontEnd() {
        // Connect to the database
        connectToDatabase();
    }

    // Method to connect to the MySQL database
    private void connectToDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/23ucs083", "root", "student");
            System.out.println("Connected to the database.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Insert data into patients table
    public static void insert() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the Patient Details Below");
        System.out.println("-----------------------------------------");

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter your gender: ");
        String gender = scanner.nextLine();

        System.out.print("Enter your date of birth (dob): ");
        String dob = scanner.nextLine();

        System.out.print("Enter your age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // consume newline

        System.out.print("Enter your phone number: ");
        int phone = scanner.nextInt();
        scanner.nextLine(); // consume newline

        System.out.print("Enter your address: ");
        String address = scanner.nextLine();

        System.out.print("Enter your disease: ");
        String disease = scanner.nextLine();

        try {
            String sql = "INSERT INTO patients (name, gender, dob, age, phone, address, disease) VALUES (?, ?, ?, ?, ?, ?, ?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, name);
            pst.setString(2, gender);
            pst.setString(3, dob);
            pst.setInt(4, age);
            pst.setInt(5, phone);
            pst.setString(6, address);
            pst.setString(7, disease);
            pst.executeUpdate();
            System.out.println("Data saved successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // View all patients
    public static void view() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM patients");
            System.out.println("Name\tGender\tDOB\t\tAge\tPhone\t\tAddress\t\t\tDisease");
            while (rs.next()) {
                String name = rs.getString("name");
                String gender = rs.getString("gender");
                String dob = rs.getString("dob");
                int age = rs.getInt("age");
                int phone = rs.getInt("phone");
                String address = rs.getString("address");
                String disease = rs.getString("disease");

                System.out.println(name + "\t" + gender + "\t" + dob + "\t" + age + "\t" + phone + "\t" + address + "\t" + disease);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update a patient by name
    public static void update() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the patient to update: ");
        String name = scanner.nextLine();

        System.out.print("Enter new disease: ");
        String newDisease = scanner.nextLine();

        try {
            String sql = "UPDATE patients SET disease = ? WHERE name = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, newDisease);
            pst.setString(2, name);
            int rows = pst.executeUpdate();
            if (rows > 0) {
                System.out.println("Updated successfully.");
            } else {
                System.out.println("No patient found with that name.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete a patient by name
    public static void delete() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the patient to delete: ");
        String name = scanner.nextLine();

        try {
            String sql = "DELETE FROM patients WHERE name = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, name);
            int rows = pst.executeUpdate();
            if (rows > 0) {
                System.out.println("Deleted successfully.");
            } else {
                System.out.println("No patient found with that name.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Main method
    public static void main(String[] args) {
        FrontEnd f = new FrontEnd();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Insert Patient");
            System.out.println("2. View All Patients");
            System.out.println("3. Update Patient Disease");
            System.out.println("4. Delete Patient");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    f.insert();
                    break;
                case 2:
                    f.view();
                    break;
                case 3:
                    f.update();
                    break;
                case 4:
                    f.delete();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
