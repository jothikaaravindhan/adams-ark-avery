package org.adamsArkAndAvery.web;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "adminBean")
@SessionScoped
public class AdminBean implements Serializable {

    private String adminName;
    private String password;
    private String message;
    private boolean loggedIn;  // 👈 added

    public static class Admin {
        private String username;
        private String password;

        public Admin(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() { return username; }
        public String getPassword() { return password; }
    }

    private List<Admin> adminList;

    public AdminBean() {
        adminList = new ArrayList<>();
        adminList.add(new Admin("admin", "123"));
        adminList.add(new Admin("craig", "brennan"));
    }

    public String login() {
        message = null;

        if (adminName == null || adminName.isEmpty() || password == null || password.isEmpty()) {
            message = "Please enter both admin name and password.";
            return null;
        }

        for (Admin admin : adminList) {
            if (admin.getUsername().equalsIgnoreCase(adminName) && admin.getPassword().equals(password)) {
                loggedIn = true;  // 👈 set loggedIn on success
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userRole", "ADMIN");
                return "adminHome?faces-redirect=true";
            }
        }

        loggedIn = false; // 👈 reset if login fails
        message = "Login failed: Invalid admin name or password.";
        return null;
    }

    // Getters & Setters
    public String getAdminName() { return adminName; }
    public void setAdminName(String adminName) { this.adminName = adminName; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public boolean isLoggedIn() { return loggedIn; } // 👈 getter for page access
    public void setLoggedIn(boolean loggedIn) { this.loggedIn = loggedIn; }
}