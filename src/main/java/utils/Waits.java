package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waits {

    private WebDriver driver;
    private WebDriverWait wait;

    public Waits(WebDriver driver, Duration timeout) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, timeout);
    }

    public WebElement visible(By by) {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(by));
    }

    public WebElement clickable(By by) {
        return wait.until(
                ExpectedConditions.elementToBeClickable(by));
    }

    public void click(By by) {
        clickable(by).click();
    }

    public void type(By by, String text) {
        WebElement el = visible(by);
        el.clear();
        el.sendKeys(text);
    }

    public void jsClick(By by) {
        WebElement el = clickable(by);
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", el);
    }
}
