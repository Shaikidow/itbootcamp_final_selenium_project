package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NavPage extends BasePage {

    public NavPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public WebElement getLinkHome() {
        return driver.findElement(By.xpath("//a[span[contains(text(), ' Home ')]]"));
    }

    public WebElement getLinkAbout() {
        return driver.findElement(By.xpath("//a[span[contains(text(), ' About ')]]"));
    }

    public WebElement getLinkMyProfile() {
        return driver.findElement(By.xpath("//a[span[contains(text(), ' My Profile ')]]"));
    }

    public WebElement buttonAdmin() {
        return driver.findElement(By.xpath("//button[span[contains(text(), ' Admin ')]]"));
    }

    public WebElement getLinkAdminCities() {
        return driver.findElement(By.xpath("//a[div[contains(text(), 'Cities')]]"));
    }

    public WebElement getLinkAdminUsers() {
        return driver.findElement(By.xpath("//a[div[contains(text(), 'Users')]]"));
    }

    public WebElement getLinkSignUp() {
        return driver.findElement(By.xpath("//a[span[contains(text(), ' Sign Up ')]]"));
    }

    public WebElement getLinkLogin() {
        return driver.findElement(By.xpath("//a[span[contains(text(), ' Login ')]]"));
    } // ask about whether it should be a link or a button, project docs treat it as a button but that's not what it is!

    public WebElement buttonLogout() {
        return driver.findElement(By.xpath("//button[span[contains(text(), ' Logout ')]]"));
    }

    public WebElement buttonLanguageSelect() {
        return driver.findElement(By.className("btnLocaleActivation"));
    }

    public WebElement buttonLanguageEn() {
        return driver.findElement(By.className("btnEN"));
    }

    public WebElement buttonLanguageEs() {
        return driver.findElement(By.className("btnES"));
    }

    public WebElement buttonLanguageFr() {
        return driver.findElement(By.className("btnFR"));
    }

    public WebElement buttonLanguageCn() {
        return driver.findElement(By.className("btnCN"));
    }

    public WebElement buttonLanguageUa() {
        return driver.findElement(By.className("btnUA"));
    } // again, project docs don't mention this, but it should absolutely be included

}
