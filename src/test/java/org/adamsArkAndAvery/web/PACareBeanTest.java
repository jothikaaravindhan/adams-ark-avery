package org.adamsArkAndAvery.web;

import org.adamsArkAndAvery.model.PetBooking;
import org.adamsArkAndAvery.service.BookingStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class PACareBeanTest {

    private BookingStore store;
    private PACareBean bean;

    private Date day(int offset) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        c.add(Calendar.DATE, offset);
        return c.getTime();
    }

    private PetBooking booking(String pet, int inOffset, int outOffset, String notes) {
        return new PetBooking("Owner", pet, "Dog", day(inOffset), day(outOffset), notes);
    }

    @BeforeEach
    void setup() {
        store = new BookingStore();
        bean  = new PACareBean();
        bean.setBookingStore(store);
    }

    @Test
    void init_loads_currently_boarding() {
        store.saveBooking(booking("Rex", -1, 1, "Walks"));
        store.saveBooking(booking("Out", -2, 0, "gone"));
        bean.init();
        assertEquals(1, bean.getActive().size());
        assertEquals("Rex", bean.getActive().get(0).getPetName());
    }

    @Test
    void select_with_notes_sets_selected_and_is_true() {
        bean.init();
        PetBooking rex = booking("Rex", -1, 1, "Food: grain-free");
        bean.select(rex);
        assertSame(rex, bean.getSelected());
        assertTrue(bean.isHasCareInstructions());
    }

    @Test
    void setSelected_blank_notes_is_false() {
        bean.init();
        PetBooking a = booking("A", -1, 1, "   ");
        bean.setSelected(a);
        assertFalse(bean.isHasCareInstructions());
    }

    @Test
    void setSelected_null_notes_is_false() {
        bean.init();
        PetBooking b = booking("B", -1, 1, null);
        bean.setSelected(b);
        assertFalse(bean.isHasCareInstructions());
    }

    @Test
    void null_selected_is_false() {
        bean.init();
        bean.setSelected(null);
        assertFalse(bean.isHasCareInstructions());
    }
}
