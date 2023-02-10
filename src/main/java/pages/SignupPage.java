package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignupPage extends BasePage {

    public SignupPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public WebElement inputName() {
        return driver.findElement(By.id("name"));
    }

    public WebElement inputEmail() {
        return driver.findElement(By.id("email"));
    }

    public WebElement inputPassword() {
        return driver.findElement(By.id("password"));
    }

    public WebElement inputConfirmPassword() {
        return driver.findElement(By.id("confirmPassword"));
    }

    public WebElement buttonSignMeUp() {
        return driver.findElement(By.xpath("//*[@type='submit']"));
//        return driver.findElement(By.className("column")).findElement(By.tagName("button"));
    }

}
