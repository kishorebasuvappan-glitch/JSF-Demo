package Kishore_B_JSF_01_258586;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@ManagedBean
@ViewScoped
public class Kishore_B_JSF_01_258586_View implements Serializable {

    private String username;
    private String userDetails;

    // Getter and Setter for username and userDetails
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(String userDetails) {
        this.userDetails = userDetails;
    }

    // Method to fetch user details from the database
    public void viewUser() {
        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to the database
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kishore_258586", "root", "Kishore01@28");

            // Prepare the SQL query to fetch user details by username
            String sql = "SELECT * FROM form WHERE username = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, username); // Set the username parameter

            // Execute the query
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Prepare user details string to show in the view
                userDetails = "Name: " + rs.getString("name") + "<br>" +
                              "Username: " + rs.getString("username") + "<br>" +
                              "Gender: " + rs.getString("gender") + "<br>" +
                              "Email: " + rs.getString("email") + "<br>" +
                              "Contact Number: " + rs.getString("contactno") + "<br>" +
                              "College: " + rs.getString("college") + "<br>";
            } else {
                userDetails = "No user found with username: " + username;
            }

            // Close resources
            rs.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
        	 e.printStackTrace();
             userDetails = "Error occurred while fetching data: " + e.getMessage();
         }
     }
 }



