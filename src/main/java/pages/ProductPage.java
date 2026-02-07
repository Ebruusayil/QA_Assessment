package pages;

import utils.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class ProductPage {

    private WebDriver driver;
    private Waits waits;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.waits =
                new Waits(driver, Duration.ofSeconds(20));
    }

    private By productName =
            By.cssSelector("h1.page-title span");

    private By addToCart =
            By.id("product-addtocart-button");

    private By successMessage =
            By.cssSelector("div.message-success");

    private By miniCart =
            By.cssSelector("a.showcart");

    private By viewCart =
            By.cssSelector("a.viewcart");

    public String getProductName() {
        return waits.visible(productName).getText();
    }

    public ProductPage addToCart() {
        waits.click(addToCart);
        waits.visible(successMessage);
        return this;
    }

    public CartPage openCart() {
        waits.click(miniCart);
        waits.click(viewCart);
        return new CartPage(driver);
    }
}