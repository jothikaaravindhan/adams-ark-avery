package org.adamsArkAndAvery.web;

import org.adamsArkAndAvery.service.PaymentService;
import org.adamsArkAndAvery.service.OrderStore;
import org.adamsArkAndAvery.model.Order;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;

@ManagedBean(name = "paymentBean")
@ViewScoped
public class PaymentBean implements Serializable {

	private PaymentService paymentServices = new PaymentService();

	@ManagedProperty("#{orderStore}")
	private OrderStore orderStore;

	public void setOrderStore(OrderStore orderStore) {
		this.orderStore = orderStore;
	}
	public PaymentBean() {
		// Empty constructor
	}

	public void createPayment() {
		try {
			if (orderStore != null && !orderStore.getAllOrders().isEmpty()) {
				Order latestOrder = orderStore.getOrder();

				if (latestOrder == null) {
					System.out.println("Latest order is null.");
					return;
				}

				double amount = latestOrder.getPrice();
				System.out.println("Processing payment for amount: $" + amount);

				// Log services for confirmation if needed
				System.out.println("Selected services: " + latestOrder.showServices());

				// Call PaymentService with the order and amount
				String approvalLink = paymentServices.authorizePayment(latestOrder, amount);

				// Redirect user to payment approval page (PayPal)
				FacesContext.getCurrentInstance().getExternalContext().redirect(approvalLink);
			} else {
				System.out.println("No orders found to process payment.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
