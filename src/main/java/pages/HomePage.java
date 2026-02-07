package pages;


import utils.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class HomePage {

    private WebDriver driver;
    private Waits waits;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.waits =
                new Waits(driver, Duration.ofSeconds(20));
    }

    private By gearMenu =
            By.xpath("//*[@id=\"ui-id-21\"]/span[1]");

    private By bagsMenu =
            By.xpath("//*[@id=\"ui-id-22\"]/span");

    public HomePage open(String url) {
        driver.get(url);
        return this;
    }

    public CategoryPage goToGearBags() {

        new Actions(driver)
                .moveToElement(waits.visible(gearMenu))
                .perform();

        waits.click(bagsMenu);

        return new CategoryPage(driver);
    }
    public void waitForCloudflareToClear() {
        var wait = new org.openqa.selenium.support.ui.WebDriverWait(
                driver, java.time.Duration.ofSeconds(300)
        );

        boolean isChallenge = driver.getCurrentUrl().toLowerCase().contains("cdn-cgi")
                || driver.getTitle().toLowerCase().contains("just a moment")
                || driver.getPageSource().toLowerCase().contains("verify you are human")
                || driver.getPageSource().toLowerCase().contains("insan olduğunuzu doğrulayın");

        if (isChallenge) {
            System.out.println("Cloudflare doğrulaması çıktı. Lütfen tarayıcıda doğrulayın, test bekliyor...");
        }

        wait.until(d -> {
            String url = d.getCurrentUrl().toLowerCase();
            String title = d.getTitle().toLowerCase();
            boolean stillChallenge = url.contains("cdn-cgi") || title.contains("just a moment");
            return !stillChallenge;
        });
    }

}
