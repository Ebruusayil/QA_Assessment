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

    private By menMenu =
            By.xpath("//nav[contains(@class,'navigation')]//li[a[normalize-space()='Men']]");

    private By topsMenu =
            By.xpath("//nav[contains(@class,'navigation')]//li[a[normalize-space()='Men']]//li[a[normalize-space()='Tops']]");

    private By jacketsMenu =
            By.xpath("//nav[contains(@class,'navigation')]//li[a[normalize-space()='Men']]//a[normalize-space()='Jackets']");

    public HomePage open(String url) {
        driver.get(url);
        return this;
    }

    public CategoryPage goToMenTopsJackets() {

        Actions actions = new Actions(driver);

        actions.moveToElement(waits.visible(menMenu))
                .pause(Duration.ofMillis(300))
                .moveToElement(waits.visible(topsMenu))
                .pause(Duration.ofMillis(300))
                .perform();

        waits.jsClick(jacketsMenu);

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
