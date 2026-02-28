package org.adamsArkAndAvery.web;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.adamsArkAndAvery.model.Order;
import org.adamsArkAndAvery.service.OrderStore;
import org.adamsArkAndAvery.service.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PaymentBeanTest {

	private PaymentBean paymentBean;
	private OrderStore orderStore;
	private TestPaymentService paymentService;

	// Simple PaymentService stub that returns dummy approval URL
	static class TestPaymentService extends PaymentService {
		@Override
		public String authorizePayment(Order order, double amount) {
			return "http://dummy-approval-link";
		}
	}

	@BeforeEach
	public void setUp() {
		orderStore = new OrderStore();
		paymentService = new TestPaymentService();
		paymentBean = new PaymentBean();


		paymentBean.setOrderStore(orderStore);

		try {
			java.lang.reflect.Field field = PaymentBean.class.getDeclaredField("paymentServices");
			field.setAccessible(true);
			field.set(paymentBean, paymentService);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void testCreatePayment_withValidOrder() {
		// Create a simple order with some services
		List<String> services = new ArrayList<>();
		services.add("Service1");
		Order order = new Order(null, services);  // Booking can be null for this test
		orderStore.getAllOrders().add(order);

		try {
			paymentBean.createPayment();
			// No exception means passed, cannot verify redirect in unit test easily
		} catch (Exception e) {
			fail("createPayment threw exception: " + e.getMessage());
		}
	}

	@Test
	public void testCreatePayment_noOrders() {
		// No orders added to orderStore
		try {
			paymentBean.createPayment();
			// Expect no exception, just no processing
		} catch (Exception e) {
			fail("createPayment threw exception: " + e.getMessage());
		}
	}

	@Test
	public void testCreatePayment_orderIsNull() {
		// Add a null order to simulate
		orderStore.getAllOrders().add(null);

		try {
			paymentBean.createPayment();
			// Should handle gracefully without exception
		} catch (Exception e) {
			fail("createPayment threw exception: " + e.getMessage());
		}
	}
}

