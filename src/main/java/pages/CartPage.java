package pages;

import utils.Money;
import utils.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.math.BigDecimal;
import java.time.Duration;

public class CartPage {

    private WebDriver driver;
    private Waits waits;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.waits =
                new Waits(driver, Duration.ofSeconds(20));
    }

    private By productName =
            By.cssSelector("strong.product-item-name a");

    private By qty =
            By.cssSelector("input.qty");

    private By updateBtn =
            By.cssSelector("button.update");

    private By price =
            By.cssSelector("span.cart-price span.price");

    private By subtotal =
            By.cssSelector("td.subtotal span.price");

    private By checkoutBtn =
            By.cssSelector("button[data-role='proceed-to-checkout']");

    public String getCartProductName() {
        return waits.visible(productName).getText();
    }

    public BigDecimal getUnitPrice() {
        return Money.parse(waits.visible(price).getText());
    }

    public BigDecimal getSubtotal() {
        return Money.parse(waits.visible(subtotal).getText());
    }

    public CartPage setQuantity(int q) {
        WebElement el = waits.visible(qty);
        el.clear();
        el.sendKeys(String.valueOf(q));
        return this;
    }

    public CartPage updateCart() {
        waits.click(updateBtn);
        waits.visible(subtotal);
        return this;
    }

    public CheckoutPage proceedToCheckout() {
        waits.click(checkoutBtn);
        return new CheckoutPage(driver);
    }
    public CartPage waitSubtotalToBe(BigDecimal expected) {
        new org.openqa.selenium.support.ui.WebDriverWait(driver, Duration.ofSeconds(20))
                .until(d -> {
                    BigDecimal current = getSubtotal();
                    return current.compareTo(expected) == 0;
                });
        return this;
    }

}
