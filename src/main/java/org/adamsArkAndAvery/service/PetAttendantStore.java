// src/main/java/org/adamsArkAndAvery/service/PetAttendantStore.java
package org.adamsArkAndAvery.service;

import org.adamsArkAndAvery.model.PetAttendant;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PetAttendantStore implements Serializable {
    private final List<PetAttendant> attendants = new ArrayList<>();

    public PetAttendantStore() {
        attendants.add(new PetAttendant("alex123", "alex@3a.ie"));
        attendants.add(new PetAttendant("sam123",  "sam@3a.ie"));
    }

    public PetAttendant authenticate(String email, String password){
        for (PetAttendant pa : attendants){
            if (pa.getEmail().equalsIgnoreCase(email) && pa.getPassword().equals(password)){
                return pa;
            }
        }
        return null;
    }
}
