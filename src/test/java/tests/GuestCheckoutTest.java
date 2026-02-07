package tests;
import base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.*;
import utils.Log;
import utils.Money;
import utils.TestData;

import java.math.BigDecimal;

public class GuestCheckoutTest extends BaseTest {

    @Test
    void guestCheckout_shouldPlaceOrder() {

        Log.info("Started");

        TestData.Guest guest = new TestData.Guest();

        HomePage home = new HomePage(driver).open(TestData.BASE_URL);

        home.waitForCloudflareToClear();

        CategoryPage category = home.goToGearBags();
        Log.info("Navigated to Gear > Bags");

        ProductPage product = category.openRandomProduct();
        Log.info("Random product opened");

        String productName = product.getProductName();

        Assertions.assertFalse(
                productName.isBlank(),
                "Product name should not be blank");

        Log.info("Product Name: " + productName);

        product.addToCart();
        Log.info("Product added");

        CartPage cart = product.openCart();
        Log.info("Cart page opened");

        Assertions.assertEquals(
                productName,
                cart.getCartProductName(),
                "Cart product name mismatch");

        Log.info("Cart product verified");

        BigDecimal unitPrice =
                cart.getUnitPrice();

        Log.info("Unit price: " + unitPrice);

        cart.setQuantity(2).updateCart();

        Log.info("Quantity updated to 2");

        BigDecimal expected = Money.multiply(unitPrice, 2);

        cart.waitSubtotalToBe(expected);

        BigDecimal actual = cart.getSubtotal();

        Assertions.assertEquals(
                0,
                expected.compareTo(actual),
                "Subtotal calculation incorrect");

        Log.info("Subtotal verified Expected: "
                + expected + " Actual: " + actual);

        CheckoutPage checkout = cart.proceedToCheckout();

        Log.info("Proceeded to checkout");

        checkout.fillGuestForm(guest).selectShipping();

        Log.info("Guest form filled shipping selected");

        SuccessPage success = checkout.placeOrder();

        Log.info("Order placed");

        Assertions.assertTrue(
                success.isOrderSuccess(),
                "Order success message not visible");

        String orderNo = success.getOrderNumber();

        Assertions.assertFalse(
                orderNo.isBlank(),
                "Order number is empty");

        Log.info("Order No: " + orderNo);
    }
}

