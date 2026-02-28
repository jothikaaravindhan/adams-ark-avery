package org.adamsArkAndAvery.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AdminBeanTest {
	
	 private AdminBean adminBean;

	    @BeforeEach
	    public void setup() {
	        adminBean = new AdminBean();
	    }
	    
	    @Test
	    public void testLoginSuccess_defaultAdmin() {
	        adminBean.setAdminName("admin");
	        adminBean.setPassword("123");
	        String result = adminBean.login();
	        assertEquals("adminHome?faces-redirect=true", result);
	        assertNull(adminBean.getMessage());
	    }
	    
	    @Test
	    public void testLoginFailure_invalidCredentials() {
	        adminBean.setAdminName("wrongUser");
	        adminBean.setPassword("wrongPass");
	        String result = adminBean.login();
	        assertNull(result);
	        assertEquals("Login failed: Invalid admin name or password.", adminBean.getMessage());
	    }
	    
	    @Test
	    public void testLoginFailure_missingFields() {
	        adminBean.setAdminName("");
	        adminBean.setPassword(null);
	        String result = adminBean.login();
	        assertNull(result);
	        assertEquals("Please enter both admin name and password.", adminBean.getMessage());
	    }
	    
	    @Test
	    public void testLoginSuccess_caseInsensitiveUsername() {
	        adminBean.setAdminName("ADMIN"); // case insensitive match
	        adminBean.setPassword("123");
	        String result = adminBean.login();
	        assertEquals("adminHome?faces-redirect=true", result);
	    }
	    
	    

}
