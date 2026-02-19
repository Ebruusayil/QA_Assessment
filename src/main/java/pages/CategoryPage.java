package pages;

import utils.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class CategoryPage {

    private WebDriver driver;
    private Waits waits;

    public CategoryPage(WebDriver driver) {
        this.driver = driver;
        this.waits =
                new Waits(driver, Duration.ofSeconds(20));
    }

    private By products =
            By.cssSelector("ol.product-items > li");

    private By productLink =
            By.cssSelector("a.product-item-link");

    private By noProductsMessage =
            By.xpath("//*[contains(normalize-space(), \"We can't find products matching the selection.\")]");

    private By topsBreadcrumb =
            By.xpath("//div[contains(@class,'breadcrumbs')]//a[normalize-space()='Tops']");

    public ProductPage openRandomProduct() {

        goToTopsIfNoProducts();

        waits.visible(products);

        List<WebElement> list =
                driver.findElements(products);

        Random rnd = new Random();
        int index = rnd.nextInt(list.size());

        list.get(index)
                .findElement(productLink)
                .click();

        return new ProductPage(driver);
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
