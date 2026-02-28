package org.adamsArkAndAvery.web;

import org.adamsArkAndAvery.model.PetBooking;
import org.adamsArkAndAvery.service.BookingStore;

import javax.annotation.PostConstruct;                
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.*;

@ManagedBean(name = "paCareBean")
@ViewScoped
public class PACareBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @ManagedProperty("#{bookingStore}")               
    private BookingStore bookingStore;

    private List<PetBooking> active = Collections.emptyList();   // pets currently boarding
    private PetBooking selected;       // pet chosen in the UI

    @PostConstruct
    public void init() {
        if(bookingStore == null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"System error: booking store not available.", null));
        return;
        }
        Date now = new Date();
        List<PetBooking> list = bookingStore.getActiveOrUpcoming(now);
        if(list == null) list = new ArrayList<>();
        list.sort(Comparator.comparing(PetBooking::getCheckInDate));
        this.active = list;
    }

    public void select(PetBooking b) {
        this.selected = b;
        if (!isHasCareInstructions()) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "No care instructions are available for this pet.", null));
        }
    }

    /** Property getter #{paCareBean.hasCareInstructions} */
    private boolean HasCareInstructions() {
        if (selected == null) return false;
        String notes = selected.getSpecialNotes();
        return notes != null && !notes.trim().isEmpty();
    }

    // ----- Getters / setters  -----
    public boolean isHasCareInstructions() {return HasCareInstructions();}
    public boolean getHasCareInstructions() {return HasCareInstructions();}


    public List<PetBooking> getActive() { return active; }
    public PetBooking getSelected() { return selected; }
    public void setSelected(org.adamsArkAndAvery.model.PetBooking selected) {this.selected = selected;}

    public BookingStore getBookingStore() { return bookingStore; }
    public void setBookingStore(BookingStore bookingStore) { this.bookingStore = bookingStore; }


}
