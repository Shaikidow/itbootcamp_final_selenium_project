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

    public void waitForPopupToBecomeVisible() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath
                ("//div[contains(@class, 'v-snack__wrapper') " +
                        "and not(contains(@style, 'display: none;'))]")));
    }

    public List<WebElement> getPopupElementsContainingText() {
        return driver.findElements(By.xpath
                ("//*[contains(@class, 'v-snack__wrapper')]//*[contains(text(), '')]"));
    }

    public WebElement buttonClosePopup() {
        return driver.findElement(By.xpath
                ("//div[contains(@class, 'v-snack__wrapper') " +
                        "and not(contains(@style, 'display: none;'))]"))
                .findElement(By.tagName("button"));
    }

    public void waitForVerificationDialogToBecomeVisible() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("v-dialog--active")));
    }

    public WebElement getVerificationDialogHeader() {
        return driver.findElement(By.xpath("//div[contains(@class, 'dlgVerifyAccount')]"));
    }

    public WebElement buttonCloseVerificationDialog() {
        return driver.findElement(By.className("btnClose"));
    }

}
