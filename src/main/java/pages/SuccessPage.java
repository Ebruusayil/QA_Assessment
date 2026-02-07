package pages;


import utils.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class SuccessPage {

    private WebDriver driver;
    private Waits waits;

    public SuccessPage(WebDriver driver) {
        this.driver = driver;
        this.waits =
                new Waits(driver, Duration.ofSeconds(30));
    }

    private final By successContainer = By.cssSelector(".checkout-success");
    private final By orderNumber = By.cssSelector(".checkout-success p:first-of-type span");

    public boolean isOrderSuccess() {
        waits.visible(successContainer);
        return !driver.findElements(successContainer).isEmpty();
    }

    public String getOrderNumber() {
        return waits.visible(orderNumber).getText().trim();
    }
}

