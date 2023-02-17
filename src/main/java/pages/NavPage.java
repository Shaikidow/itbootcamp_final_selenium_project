package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NavPage extends BasePage {

    public NavPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public WebElement getHomeLink() {
        return driver.findElement(By.xpath("//a[span[contains(text(), ' Home ')]]"));
    }

    public WebElement getAboutLink() {
        return driver.findElement(By.xpath("//a[span[contains(text(), ' About ')]]"));
    }

    public WebElement getMyProfileLink() {
        return driver.findElement(By.xpath("//a[span[contains(text(), ' My Profile ')]]"));
    }

    public WebElement getPageHeader() {
        return driver.findElement(By.tagName("h1"));
    }

    public WebElement getAdminButton() {
        return driver.findElement(By.xpath("//button[span[contains(text(), ' Admin ')]]"));
    }

    public WebElement getAdminCitiesLink() {
        return driver.findElement(By.xpath("//a[div[contains(text(), 'Cities')]]"));
    }

    public WebElement getAdminUsersLink() {
        return driver.findElement(By.xpath("//a[div[contains(text(), 'Users')]]"));
    }

    public WebElement getSignUpLink() {
        return driver.findElement(By.xpath("//a[span[contains(text(), ' Sign Up ')]]"));
    }

    public WebElement getLoginLink() {
        return driver.findElement(By.xpath("//a[span[contains(text(), ' Login ')]]"));
    }

    public WebElement getLogoutButton() {
        return driver.findElement(By.xpath("//button[span[contains(text(), ' Logout ')]]"));
    }

    public WebElement getLocaleActivationButton() {
        return driver.findElement(By.className("btnLocaleActivation"));
    }

    public WebElement getLanguageButtonEn() {
        return driver.findElement(By.className("btnEN"));
    }

    public WebElement getLanguageButtonEs() {
        return driver.findElement(By.className("btnES"));
    }

    public WebElement getLanguageButtonFr() {
        return driver.findElement(By.className("btnFR"));
    }

    public WebElement getLanguageButtonCn() {
        return driver.findElement(By.className("btnCN"));
    }

    public WebElement getLanguageButtonUa() {
        return driver.findElement(By.className("btnUA"));
    }

    public void waitForPageToLoad() { // waiting for the first container element to load seems to provide optimal speeds
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("container--fluid")));
    }

}
