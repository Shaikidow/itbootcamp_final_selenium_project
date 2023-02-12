package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public WebElement inputEmail() {
        return driver.findElement(By.id("email"));
    }

    public WebElement inputPassword() {
        return driver.findElement(By.id("password"));
    }

    public WebElement buttonLogin() {
        return driver.findElement(By.xpath("//button[@type='submit']"));
//        return driver.findElement(By.className("column")).findElement(By.tagName("button"));
    }

}
