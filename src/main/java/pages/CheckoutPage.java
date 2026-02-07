package pages;

import utils.TestData;
import utils.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

public class CheckoutPage {

    private WebDriver driver;
    private Waits waits;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.waits = new Waits(driver, Duration.ofSeconds(30));
    }

    private By email = By.id("customer-email");
    private By firstName = By.name("firstname");
    private By lastName = By.name("lastname");
    private By company = By.name("company");
    private By street = By.name("street[0]");
    private By city = By.name("city");
    private By zip = By.name("postcode");
    private By phone = By.name("telephone");

    private By country = By.name("country_id");
    private By region = By.name("region_id");

    private By shippingRadio =
            By.cssSelector("input[type='radio']");

    private By nextBtn =
            By.cssSelector("button.continue");
    private By loader =
            By.cssSelector(".loading-mask");

    private By paymentSection =
            By.id("checkout-payment-method-load");

    private By firstPaymentRadio =
            By.cssSelector("#checkout-payment-method-load input[type='radio']");

    private By placeOrderBtn =
            By.cssSelector("button.action.primary.checkout");


    public CheckoutPage fillGuestForm(TestData.Guest g) {

        waits.type(email, g.email);
        waits.type(firstName, g.firstName);
        waits.type(lastName, g.lastName);
        waits.type(company, g.company);
        waits.type(street, g.street);
        waits.type(city, g.city);
        waits.type(zip, g.zip);
        waits.type(phone, g.phone);

        new Select(waits.visible(country))
                .selectByVisibleText(g.countryVisibleText);

        new Select(waits.visible(region))
                .selectByVisibleText(g.regionVisibleText);

        return this;
    }

    public CheckoutPage selectShipping() {
        waits.jsClick(shippingRadio);
        waits.click(nextBtn);
        return this;
    }

    public SuccessPage placeOrder() {
        waits.visible(paymentSection);
        waits.jsClick(firstPaymentRadio);
        org.openqa.selenium.WebElement btn = waits.clickable(placeOrderBtn);
        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", btn);

        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", btn);

        return new SuccessPage(driver);
    }

}
