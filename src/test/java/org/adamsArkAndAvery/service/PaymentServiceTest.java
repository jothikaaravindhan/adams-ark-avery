package org.adamsArkAndAvery.service;

import org.adamsArkAndAvery.model.Order;
import org.adamsArkAndAvery.model.PetBooking;
import org.junit.jupiter.api.Test;

import com.paypal.api.payments.*;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PaymentServiceTest {

    @Test
    void testGetTransactionInformationBuildsCorrectTransaction() {
        // Prepare a PetBooking and an Order with services
        PetBooking petBooking = new PetBooking("Owner", "Buddy", "Dog", new Date(), new Date(), "");
        Order order = new Order(petBooking, List.of("Grooming", "Walking")); // example services

        PaymentService service = new PaymentService();

        // Call method under test
        List<Transaction> transactions = service.getTransactionInformation(order);

        // Validate one transaction
        assertEquals(1, transactions.size(), "Should only have one transaction");

        Transaction transaction = transactions.get(0);

        // Description includes all selected services as per PaymentService code
        String expectedDescription = "Payment for services: " + order.showServices();
        assertEquals(expectedDescription, transaction.getDescription());

        // Validate amount details
        Amount amount = transaction.getAmount();
        assertEquals("EUR", amount.getCurrency());

        // Total should match order price formatted to 2 decimals
        String expectedTotal = String.format("%.2f", order.getPrice());
        assertEquals(expectedTotal, amount.getTotal());

        Details details = amount.getDetails();

        // Subtotal equals total (no tax in your code, tax is "0")
        assertEquals(expectedTotal, details.getSubtotal());
        assertEquals("0", details.getTax());

        // Item list validation
        ItemList itemList = transaction.getItemList();
        assertNotNull(itemList);
        assertEquals(1, itemList.getItems().size());

        Item item = itemList.getItems().get(0);
        assertEquals("Pet Services", item.getName());

        // Item price matches subtotal formatted
        assertEquals(expectedTotal, item.getPrice());
        assertEquals("0", item.getTax());
        assertEquals("1", item.getQuantity());
        assertEquals("EUR", item.getCurrency());
    }
}
