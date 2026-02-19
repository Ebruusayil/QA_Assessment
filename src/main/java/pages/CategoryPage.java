package pages;

import utils.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class CategoryPage {

    private WebDriver driver;
    private Waits waits;
    private String selectedProductName;

    public CategoryPage(WebDriver driver) {
        this.driver = driver;
        this.waits =
                new Waits(driver, Duration.ofSeconds(20));
    }

    private By products =
            By.cssSelector("ol.product-items > li");

    private By productLink =
            By.cssSelector("a.product-item-link");

    private By addToCartButton =
            By.cssSelector("button.tocart");

    private By successMessage =
            By.cssSelector("div.message-success");

    private By miniCart =
            By.cssSelector("a.showcart");

    private By viewCart =
            By.cssSelector("a.viewcart");

    private By noProductsMessage =
            By.xpath("//*[contains(normalize-space(), \"We can't find products matching the selection.\")]");

    private By topsBreadcrumb =
            By.xpath("//div[contains(@class,'breadcrumbs')]//a[normalize-space()='Tops']");

    public String getSelectedProductName() {
        return selectedProductName;
    }

    public CartPage addRandomProductToCartAndOpenCart() {

        goToTopsIfNoProducts();

        goToTopsIfNoProducts();

        waits.visible(products);

        List<WebElement> list =
                driver.findElements(products);

        Random rnd = new Random();
        int index = rnd.nextInt(list.size());

        WebElement productItem = list.get(index);
        selectedProductName = productItem.findElement(productLink).getText();

        new Actions(driver)
                .moveToElement(productItem)
                .pause(Duration.ofMillis(300))
                .perform();

        WebElement addButton = productItem.findElement(addToCartButton);
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", addButton);

        waits.visible(successMessage);
        waits.click(miniCart);
        waits.click(viewCart);

        return new CartPage(driver);
    }

    private void goToTopsIfNoProducts() {

        if (driver.findElements(noProductsMessage).isEmpty()) {
            return;
        }

        WebDriverWait shortWait =
                new WebDriverWait(driver, Duration.ofSeconds(3));

        boolean noProductsVisible =
                shortWait.until(ExpectedConditions.visibilityOfElementLocated(noProductsMessage)).isDisplayed();

        if (noProductsVisible) {
            waits.click(topsBreadcrumb);
            waits.visible(products);
        }
    }

    private void goToTopsIfNoProducts() {

        if (driver.findElements(noProductsMessage).isEmpty()) {
            return;
        }

        WebDriverWait shortWait =
                new WebDriverWait(driver, Duration.ofSeconds(3));

        boolean noProductsVisible =
                shortWait.until(ExpectedConditions.visibilityOfElementLocated(noProductsMessage)).isDisplayed();

        if (noProductsVisible) {
            waits.click(topsBreadcrumb);
            waits.visible(products);
        }
    }
}
