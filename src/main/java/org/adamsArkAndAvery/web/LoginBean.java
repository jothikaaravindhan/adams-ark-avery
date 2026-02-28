package org.adamsArkAndAvery.web;

import org.adamsArkAndAvery.model.Customer;
import org.adamsArkAndAvery.service.RegistrationStore;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serial;
import java.io.Serializable;

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String email; 
    private String password;

    private String message;

    //control which panel is visible after a postback
    private boolean showLogin = false;

    @ManagedProperty("#{registrationStore}")
    private RegistrationStore registrationStore;

    public void setRegistrationStore(RegistrationStore registrationStore) {this.registrationStore = registrationStore;}

    // Getters & Setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public boolean isShowLogin() { return showLogin; }
    public void setShowLogin(boolean showLogin) { this.showLogin = showLogin; }

    private void addMsg(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, (new FacesMessage(severity,summary, detail)));
    }

    public String login() {
      showLogin = true;

      final String user = email == null ? "" : email.trim();
      final String pass = password == null ? "" : password;

      if (user.isEmpty() || pass.isEmpty()) {
          addMsg(FacesMessage.SEVERITY_WARN, "Missing details", "Please enter both email/username and password");

          return null;
      }

      for (Customer c : registrationStore.getUsers()) {
          boolean idMatch =
                  (c.getEmail() != null && c.getEmail().equalsIgnoreCase(user) || (c.getUsername()) != null && c.getUsername().equalsIgnoreCase(user));

          if (idMatch && pass.equals(c.getPassword())) {
              addMsg(FacesMessage.SEVERITY_INFO, "Welcome" + c.getUsername(), "Login Successful");
              this.password = null;
              FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userRole", "CUSTOMER");

              return "homepage?faces-redirect=true";
          }
      }

      addMsg(FacesMessage.SEVERITY_ERROR,"Invalid Credentials","Please try again.");
        return null;
    }

    public void openLoginPanel() { this.showLogin = true; }


}
