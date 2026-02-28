package org.adamsArkAndAvery.model;

public class PetAttendant {
    private String password;
    private String email;

    public PetAttendant(String password, String email) {
        this.email = email;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

}
