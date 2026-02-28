package org.adamsArkAndAvery.web;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "LogoutBean")
@SessionScoped
public class LogoutBean {
	public String logout() {
		// Set default redirect page (For CUSTOMER ROLE)
		String redirectPage = "/register.xhtml?faces-redirect=true";
		// Get userRole from session
		String userRole = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userRole");
		if(userRole != null) {
			// Handle Admin Role
			if(userRole.equals("ADMIN")) {
				redirectPage = "/adminLogin.xhtml?faces-redirect=true";
			}
			// Handle Pet Attendant Role
			else if(userRole.equals("PETATTENDANT")) {
				redirectPage = "/staffLogin.xhtml?faces-redirect=true";
			}
		}
		// Invalidate the session to log the user out
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		System.out.println("Logout Successfull!");
		// Redirect to the registration page after logout
		return redirectPage;
	}
}
