package Kishore_B_JSF_01_258586;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

@ManagedBean(name = "Kishore_B_JSF_01_258586_form")
@RequestScoped
public class Kishore_B_JSF_01_258586_form {
	 FacesContext facesContext = FacesContext.getCurrentInstance();  

    private String name;
    private String username;
    private String password;
    private String retypePassword;
    private String gender;
    private String contactno;
    private String email;
    private String college;
    private List<String> programmingSkills;  // List of selected programming skills

    private boolean passwordsMatch = true;

    // Getters and Setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getRetypePassword() {
        return retypePassword;
    }
    public void setRetypePassword(String retypePassword) {
        this.retypePassword = retypePassword;
    }

    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getContactno() {
        return contactno;
    }
    public void setContactno(String contactno) {
        this.contactno = contactno;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getCollege() {
        return college;
    }
    public void setCollege(String college) {
        this.college = college;
    }

    public List<String> getProgrammingSkills() {
        return programmingSkills;
    }
    public void setProgrammingSkills(List<String> programmingSkills) {
        this.programmingSkills = programmingSkills;
    }

    public boolean isPasswordsMatch() {
        return passwordsMatch;
    }

    // Method to handle form submission
    public String submitForm() {
        if (password.equals(retypePassword)) {
            passwordsMatch = true;
            // Insert data into the database
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kishore_258586", "root", "Kishore01@28");

                String sql = "INSERT INTO form (name, username, password, gender, programmingskills, contactno, email, college) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement stmt = con.prepareStatement(sql);

                String skills = String.join(", ", programmingSkills);  // Convert list to comma-separated string

                stmt.setString(1, name);
                stmt.setString(2, username);
                stmt.setString(3, password);
                stmt.setString(4, gender);
                stmt.setString(5, skills);
                stmt.setString(6, contactno);
                stmt.setString(7, email);
                stmt.setString(8, college);

                int rowsAffected = stmt.executeUpdate();
                stmt.close();
                con.close();

                if (rowsAffected > 0) {
                    facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Insertion successful!"));
                    return "success";  // Navigate to success page or show confirmation
                } else {
                    facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Insertion failed! Please try again."));
                    return "error";  // Stay on the current page or show an error
                }
            } catch (Exception e) {
                e.printStackTrace();
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "An error occurred: " + e.getMessage()));
                return "error";
            }
        } else {
            passwordsMatch = false;
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Password Mismatch", "Passwords do not match!"));
            return null;  // Stay on the same page to show the error message
        }
    }
}




