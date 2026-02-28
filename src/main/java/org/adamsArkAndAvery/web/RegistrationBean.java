package org.adamsArkAndAvery.web;


import org.adamsArkAndAvery.model.Customer;
import org.adamsArkAndAvery.service.RegistrationStore;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


@ManagedBean(name = "registrationBean")
@SessionScoped
public class RegistrationBean {

    private String username;
    private String password;
    private String confirmPassword;
    private String email;

    private String message;

    @ManagedProperty("#{registrationStore}")
    private RegistrationStore registrationStore;
    public void setRegistrationStore(RegistrationStore registrationStore) {
        this.registrationStore = registrationStore;
    }
    

    //Username Getter and Setters
    public String getUsername() {return username; }
    public void setUsername(String username) {this.username = username; }

    //Password Getter and Setters
    public String getPassword() {return password; }
    public void setPassword(String password) {this.password = password; }

    public String getConfirmPassword() {return confirmPassword;}
    public void setConfirmPassword(String confirmPassword) {this.confirmPassword = confirmPassword;}

    //Email getters and setters
    public String getEmail() {return email; }
    public void setEmail(String email) {this.email = email; }

    public String getMessage() {return message;}
    public void setMessage(String message) {this.message = message;}

    private void addMsg(FacesMessage.Severity sev, String summary, String detail){
        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(sev,summary,detail));
    }



    public String register() {

        //Clear Message
        message = null;

        if (password == null || confirmPassword == null || !password.equals(confirmPassword)) {
            addMsg(FacesMessage.SEVERITY_ERROR,"Passwords do not match","Please re-enter the same password twice.");
            confirmPassword = null;
            return null; // To stay on page
        }
        try {
            registrationStore.register(new Customer(username, password, email));
            addMsg(FacesMessage.SEVERITY_INFO,"Register successful","You have successfully registered your account.");
            password = null;
            confirmPassword = null;

            return "homepage?faces-redirect=true"; //Redirects to homepage.xhtml and updates url
        } catch (IllegalArgumentException e) {
            addMsg(FacesMessage.SEVERITY_WARN, "Username is already taken","Please enter another username.");
            return null;
        } catch (Exception e) {
            addMsg(FacesMessage.SEVERITY_ERROR,"Unexpected error","Please try again in a moment.");
            return null;
        }
    }
    public int getUserCount() {
        return registrationStore.count();
    }

    public String getLastInputUsername(){
        return username;
    }
    
    
    
}
