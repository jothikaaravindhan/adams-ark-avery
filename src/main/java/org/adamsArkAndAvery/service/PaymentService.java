package org.adamsArkAndAvery.service;

import java.util.ArrayList;
import java.util.List;

import org.adamsArkAndAvery.model.Order;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

public class PaymentService {

	private static final String CLIENT_ID = "AWeHaIGkHtPDljIQKeKI8udQ_znhUUK1bGmTvpdkGKqW-c4pkeh-4lu-OWJKFw0uiwLCdwys6_aTD89d";
	private static final String CLIENT_SECRET = "EEIxsPCms-kOHkcndm6tLV5aGPE2Ge71W1cEjNXBg-wixUqDxX2SqKpqifVa12awxzBR5u1cpyoGqcyV";
	private static final String MODE = "sandbox";

	public String authorizePayment(Order order,double amount) throws PayPalRESTException {
		Payer payer = getPayerInformation();
		RedirectUrls redirectUrls = getRedirectURLs();
		List<Transaction> listTransaction = getTransactionInformation(order);

		Payment requestPayment = new Payment();
		requestPayment.setIntent("authorize");
		requestPayment.setPayer(payer);
		requestPayment.setRedirectUrls(redirectUrls);
		requestPayment.setTransactions(listTransaction);

		APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);

		Payment approvedPayment = requestPayment.create(apiContext);

		return getApprovalLink(approvedPayment);
	}

	private Payer getPayerInformation() {//Added description for getPayerInformation() to specify how payer details are set
		Payer payer = new Payer();
		payer.setPaymentMethod("paypal");
		return payer;
	}

	private RedirectUrls getRedirectURLs() {//Added comments for getRedirectURLs() describing return and cancel URL setup
		RedirectUrls redirectUrls = new RedirectUrls();
		redirectUrls.setCancelUrl("http://localhost:8080/tomcat_app/cancel.xhtml");
		redirectUrls.setReturnUrl("http://localhost:8080/tomcat_app/PaymentConfirmation.xhtml");
		return redirectUrls;
	}

	// Create transaction info based on Order
	public List<Transaction> getTransactionInformation(Order order) {
		double totalAmount = order.getPrice();
		String servicesDescription = order.showServices();

		Details details = new Details();

		details.setSubtotal(String.format("%.2f", totalAmount));
		details.setTax("0");  

		Amount amount = new Amount();
		amount.setCurrency("EUR");
		amount.setTotal(String.format("%.2f", totalAmount));
		amount.setDetails(details);

		Transaction transaction = new Transaction();
		transaction.setAmount(amount);
		transaction.setDescription("Payment for services: " + servicesDescription);

		ItemList itemList = new ItemList();
		List<Item> items = new ArrayList<>();


		Item item = new Item();
		item.setCurrency("EUR");
		item.setName("Pet Services");
		item.setPrice(String.format("%.2f", totalAmount));
		item.setTax("0");
		item.setQuantity("1");

		items.add(item);
		itemList.setItems(items);
		transaction.setItemList(itemList);

		List<Transaction> transactions = new ArrayList<>();
		transactions.add(transaction);

		return transactions;
	}

	private String getApprovalLink(Payment approvedPayment) {//Added explanation for getApprovalLink() to clarify approval URL extraction
		for (Links link : approvedPayment.getLinks()) {
			if ("approval_url".equalsIgnoreCase(link.getRel())) {
				return link.getHref();
			}
		}
		return null;
	}

	public Payment getPaymentDetails(String paymentId) throws PayPalRESTException {
		APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
		return Payment.get(apiContext, paymentId);
	}

	public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
		PaymentExecution paymentExecution = new PaymentExecution();
		paymentExecution.setPayerId(payerId);

		Payment payment = new Payment().setId(paymentId);

		APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);

		return payment.execute(apiContext, paymentExecution);
	}
}
