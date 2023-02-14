package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CitiesPage extends BasePage {

    public CitiesPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public WebElement getNewItemButton() {
        return driver.findElement(By.className("btnNewItem"));
    }

    public WebElement getSearchInput() {
        return driver.findElement(By.id("search"));
    }

    public WebElement getNameInput() {
        return driver.findElement(By.id("name"));
    }

    public void waitForNewEditItemDialogToBecomeVisible() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("dlgNewEditItem")));
    }

    public void waitForDeleteWarningToAppear() {
        wait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//div[div[header[contains(@class, 'warning')]]]")));
    } // it's important to stress that, unlike the new/edit dialogue, this one doesn't just exist in an invisible manner

    public WebElement getSaveButton() {
        return driver.findElement(By.className("btnSave"));
    }

    public WebElement getDeleteButton() {
        return driver.findElement(By.xpath("//button[span[contains(text(), ' Delete ')]]"));
    }

    public void waitForNumberOfTableRowsToAppear(int rows) {
        wait.until(ExpectedConditions
                .numberOfElementsToBe(By.xpath("//div[contains(@class, 'container')]//tbody/tr"), rows));
    }

    public WebElement getTableCell(int row, int cell) {
        return driver.findElement(By.xpath
                ("//div[contains(@class, 'container')]//tbody/tr[" + row + "]/td[" + (cell + 1) + "]"));
    }

    public WebElement getEditButtonByTableRow(int row) {
        return driver.findElement(By.xpath
                ("//div[contains(@class, 'container')]//tbody/tr[" + row + "]//button[@id='edit']"));
//        return driver.findElement(By.xpath
//                ("//tr[" + row + "][td[contains(@class, 'text')]]//button[@id='edit']"));
    }

    public WebElement getDeleteButtonByTableRow(int row) {
        return driver.findElement(By.xpath
                ("//div[contains(@class, 'container')]//tbody/tr[" + row + "]//button[@id='delete']"));
//        return driver.findElement(By.xpath
//                ("//tr[" + row + "][td[contains(@class, 'text')]]//button[@id='delete']"));
    }

}
