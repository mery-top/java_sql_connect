//SQL Connector JAVA
import java.sql.*;

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
        String name = "XYZ";
        String gender = "Male";

        try {
            // Prepare SQL statement to insert data into the table
            String sql = "INSERT INTO users (name, gender) VALUES (?, ?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, name);
            pst.setString(2, gender);

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

