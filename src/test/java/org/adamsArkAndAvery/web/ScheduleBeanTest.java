package org.adamsArkAndAvery.web;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.primefaces.model.LazyScheduleModel;
import org.primefaces.model.ScheduleEvent;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.List;

public class ScheduleBeanTest {

	private ScheduleBean scheduleBean;

	@BeforeEach
	public void setUp() {
		scheduleBean = new ScheduleBean();
		scheduleBean.init();
	}


	@Test
	public void testEventModelInitialized() {
		assertNotNull(scheduleBean.getEventModel(), "Event model should be initialized");
		assertFalse(scheduleBean.getEventModel().getEvents().isEmpty(), "Event model should have events after init");
	}

	@Test
	public void testLazyEventModelInitialized() {
		assertNotNull(scheduleBean.getLazyEventModel(), "Lazy event model should be initialized");
	}

	@Test
	public void testAddEventWithExistingEvent() {
		// Use whatever is returned by getEvent(), no setters
		ScheduleEvent<?> event = scheduleBean.getEvent();
		assertNotNull(event, "Event object should not be null");

		// Add current event to model
		scheduleBean.addEvent();

		// Check that model contains at least one event
		assertFalse(scheduleBean.getEventModel().getEvents().isEmpty(), "Event model should have events after addEvent()");
	}

	@Test
	public void testLazyEventModelLoadsEvents() throws Exception {
		ScheduleBean scheduleBean = new ScheduleBean();
		scheduleBean.init();

		LazyScheduleModel lazyModel = (LazyScheduleModel) scheduleBean.getLazyEventModel();

		LocalDateTime start = LocalDateTime.now();
		LocalDateTime end = start.plusDays(7);

		// Use reflection to call the protected loadEvents method
		Method loadEventsMethod = LazyScheduleModel.class.getDeclaredMethod("loadEvents", LocalDateTime.class, LocalDateTime.class);
		loadEventsMethod.setAccessible(true);
		loadEventsMethod.invoke(lazyModel, start, end);

		// Now getEvents() will contain the events
		List<ScheduleEvent<?>> events = lazyModel.getEvents();
		assertEquals(5, events.size(), "LazyScheduleModel should create 5 events");

		int count = 1;
		for (ScheduleEvent<?> event : events) {
			assertTrue(event.getTitle().startsWith("Lazy Event "), "Event title should start with 'Lazy Event '");
			assertEquals(count++, Integer.parseInt(event.getTitle().replace("Lazy Event ", "")));
		}
	}
	@Test
	public void testSetAndGetColumnHeaderFormat() {
		ScheduleBean scheduleBean = new ScheduleBean();

		String format = "HH:mm"; // example format
		scheduleBean.setColumnHeaderFormat(format);

		assertEquals(format, scheduleBean.getColumnHeaderFormat(), 
				"Getter should return the value set by setter");
	}

	@Test
	public void testSetAndGetClientTimeZone() {
		ScheduleBean scheduleBean = new ScheduleBean();

		String timeZone = "UTC";
		scheduleBean.setClientTimeZone(timeZone);

		assertEquals(timeZone, scheduleBean.getClientTimeZone(),
				"Getter should return the value set by the setter");
	}

	@Test
	public void testSetAndGetTimeZone() {
		ScheduleBean scheduleBean = new ScheduleBean();

		String tz = "America/New_York";
		scheduleBean.setTimeZone(tz);

		assertEquals(tz, scheduleBean.getTimeZone(),
				"Getter should return the value set by the setter");
	}

	@Test
	public void testSetAndGetLocale() {
		ScheduleBean scheduleBean = new ScheduleBean();

		String locale = "fr-FR";
		scheduleBean.setLocale(locale);

		assertEquals(locale, scheduleBean.getLocale(),
				"Getter should return the value set by the setter");
	}

	@Test
	public void testSetAndGetMaxTime() {
		ScheduleBean scheduleBean = new ScheduleBean();

		String maxTime = "22:00:00";
		scheduleBean.setMaxTime(maxTime);

		assertEquals(maxTime, scheduleBean.getMaxTime(),
				"Getter should return the value set by the setter");
	}

	@Test
	public void testSetAndGetMinTime() {
		ScheduleBean scheduleBean = new ScheduleBean();

		String minTime = "05:00:00";
		scheduleBean.setMinTime(minTime);

		assertEquals(minTime, scheduleBean.getMinTime(),
				"Getter should return the value set by the setter");
	}

	@Test
	public void testSetAndGetScrollTime() {
		ScheduleBean scheduleBean = new ScheduleBean();

		String scrollTime = "08:00:00";
		scheduleBean.setScrollTime(scrollTime);

		assertEquals(scrollTime, scheduleBean.getScrollTime(),
				"Getter should return the value set by the setter");
	}

	@Test
	public void testSetAndGetSlotLabelInterval() {
		ScheduleBean scheduleBean = new ScheduleBean();

		String interval = "01:00:00";
		scheduleBean.setSlotLabelInterval(interval);

		assertEquals(interval, scheduleBean.getSlotLabelInterval(),
				"Getter should return the value set by the setter");
	}

	@Test
	public void testSetAndGetSlotDuration() {
		ScheduleBean scheduleBean = new ScheduleBean();

		String duration = "00:45:00";
		scheduleBean.setSlotDuration(duration);

		assertEquals(duration, scheduleBean.getSlotDuration(),
				"Getter should return the value set by the setter");
	}

	@Test
	public void testSetAndGetTimeFormat() {
		ScheduleBean scheduleBean = new ScheduleBean();

		String format = "HH:mm";
		scheduleBean.setTimeFormat(format);

		assertEquals(format, scheduleBean.getTimeFormat(),
				"Getter should return the value set by the setter");
	}

	@Test
	public void testSetAndGetAllDaySlot() {
		ScheduleBean scheduleBean = new ScheduleBean();

		// Set to false and verify
		scheduleBean.setAllDaySlot(false);
		assertFalse(scheduleBean.isAllDaySlot(), "Getter should return the value set by the setter");

		// Set to true and verify
		scheduleBean.setAllDaySlot(true);
		assertTrue(scheduleBean.isAllDaySlot(), "Getter should return the value set by the setter");
	}

	@Test
	public void testSetAndGetTooltip() {
		ScheduleBean scheduleBean = new ScheduleBean();

		// Set to false and verify
		scheduleBean.setTooltip(false);
		assertFalse(scheduleBean.isTooltip(), "Getter should return the value set by the setter");

		// Set to true and verify
		scheduleBean.setTooltip(true);
		assertTrue(scheduleBean.isTooltip(), "Getter should return the value set by the setter");
	}

	@Test
	public void testSetAndGetShowWeekends() {
		ScheduleBean scheduleBean = new ScheduleBean();

		// Set to false and verify
		scheduleBean.setShowWeekends(false);
		assertFalse(scheduleBean.isShowWeekends(), "Getter should return the value set by the setter");

		// Set to true and verify
		scheduleBean.setShowWeekends(true);
		assertTrue(scheduleBean.isShowWeekends(), "Getter should return the value set by the setter");
	}

}
