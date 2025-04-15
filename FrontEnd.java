//SQL Connector JAVA
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
            // Register the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Open a connection
            conn = DriverManager.getConnection("jdbc:mysql://localhost/23ucs083", "root", "student");
            
            System.out.println("Connected to the database.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insert() {
        Scanner scanner =  new Scanner(System.in);
        System.out.println("Enter the Patient Details Below");
        System.out.println("-----------------------------------------");
        System.out.println("Enter your name:");
        String name = scanner.nextLine();

        System.out.println("Enter your gender:");
        String gender = scanner.nextLine();

        System.out.println("Enter your date of birth (dob):");
        String dob = scanner.nextLine();

        System.out.println("Enter your age:");
        int age = scanner.nextInt();
        scanner.nextLine(); // consume the leftover newline

        System.out.println("Enter your phone number:");
        int phone = scanner.nextInt();
        scanner.nextLine(); // consume the leftover newline

        System.out.println("Enter your address:");
        String address = scanner.nextLine();

        System.out.println("Enter your disease:");
        String disease = scanner.nextLine();

        try {
            // Prepare SQL statement to insert data into the table
            String sql = "INSERT INTO patients (name, gender,dob, age, phone, address, disease) VALUES (?, ?, ?, ?, ?, ?, ?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, name);       // name
            pst.setString(2, gender);     // gender
            pst.setString(3, dob);        // date of birth
            pst.setInt(4, age);           // age
            pst.setInt(5, phone);         // phone
            pst.setString(6, address);    // address
            pst.setString(7, disease);

            // Execute the statement
            pst.executeUpdate();
            System.out.println("Data saved successfully!");
            
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");
	         System.out.println("Name\tGender");
         
         while (rs.next()) {
            gender = rs.getString("gender");
            name = rs.getString("name");
            System.out.println(name+"    "+gender);
            }
            

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    // Main method
    public static void main(String[] args) {
    	FrontEnd f=new FrontEnd();
    	f.insert();
    }
}

