package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage extends BasePage {

    public ProfilePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public WebElement inputEmail() {
        return driver.findElement(By.id("email"));
    }

    public WebElement inputName() {
        return driver.findElement(By.id("name"));
    }

    public WebElement inputCity() {
        return driver.findElement(By.id("city"));
    }

    public WebElement inputCountry() {
        return driver.findElement(By.id("country"));
    }

    public WebElement inputUrlTwitter() {
        return driver.findElement(By.id("urlTwitter"));
    }

    public WebElement inputUrlGitHub() {
        return driver.findElement(By.id("urlGitHub"));
    }

    public WebElement inputPhone() {
        return driver.findElement(By.id("phone"));
    }

    public WebElement buttonSave() {
        return driver.findElement(By.className("btnSave"));
    }

}
