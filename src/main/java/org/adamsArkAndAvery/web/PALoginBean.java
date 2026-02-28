package org.adamsArkAndAvery.web;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import java.io.Serializable;

@ManagedBean(name = "paLoginBean")
@SessionScoped
public class PALoginBean implements Serializable {

    private String email;
    private String password;
    private String message;

    private final String[] STAFF_EMAILS    = {"alex@3a.ie", "sam@3a.ie"};
    private final String[] STAFF_PASSWORDS = {"alex123",    "sam123"};

    public String login() {
        if (isValidStaff(email, password)) {
            message = null;
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userRole", "PETATTENDANT");
            return "staff?faces-redirect=true";  // navigate to /staff.xhtml
        }
        message = "Invalid email or password.";
        return null;  // stay on the same page
    }

    private boolean isValidStaff(String email, String password) {
        if (email == null || password == null) return false;
        String e = email.trim().toLowerCase();
        String p = password.trim();
        for (int i = 0; i < STAFF_EMAILS.length; i++) {
            if (e.equals(STAFF_EMAILS[i].toLowerCase()) && p.equals(STAFF_PASSWORDS[i])) {
                return true;
            }
        }
        return false;
    }


// Getters/setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
