package pages;

import utils.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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

    public ProductPage openRandomProduct() {

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
}
