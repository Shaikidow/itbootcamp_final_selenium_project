package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MessagePopUpPage extends BasePage {

    public MessagePopUpPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void waitForErrorPopupToBecomeVisible() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error")));
    }

    public List<WebElement> getErrorPopupMessages() {
        return driver.findElement(By.className("error")).findElements(By.tagName("li"));
//        return driver.findElements(By.xpath("//div[contains(@class, 'error')]//ul/li"));
    }

    public WebElement buttonCloseErrorPopup() {
        return driver.findElement(By.className("error")).findElement(By.tagName("button"));
    }

    public void waitForVerificationDialogToBecomeVisible() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("v-dialog--active")));
    }

    public WebElement getVerificationDialogHeader() {
        return driver.findElement(By.linkText(" IMPORTANT: Verify your account "));
//        return driver.findElement(By.xpath("//div[contains(@class, 'dlgVerifyAccount')]"));
    }

    public WebElement buttonCloseVerificationDialog() {
        return driver.findElement(By.className("btnClose"));
    }

}
