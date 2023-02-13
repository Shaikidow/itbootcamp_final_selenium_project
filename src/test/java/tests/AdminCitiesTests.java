package tests;

import com.sun.org.glassfish.gmbal.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AdminCitiesTests extends BaseTest {

    @Test(priority = 10)
    @Description("Test #1: Visits admin cities page and lists cities")
    public void visitsAdminCitiesPageAndListsCities() throws InterruptedException {

        navPage.getLinkLogin().click();
        loginPage.inputEmail().sendKeys("admin@admin.com");
        loginPage.inputPassword().sendKeys("12345");
        loginPage.buttonLogin().click();
        Thread.sleep(1000);
        navPage.buttonAdmin().click();
        navPage.getLinkAdminCities().click();

        Assert.assertEquals(driver.getCurrentUrl(),
                baseUrl + "/admin/cities",
                "Not on admin cities page.");

    }

    @Test (priority = 20)
    @Description("Test #2: Checks input types for create/edit new city")
    public void checksInputTypesForCreateEditNewCity() {

        navPage.buttonAdmin().click();
        navPage.getLinkAdminCities().click();
        citiesPage.buttonNewItem().click();
        citiesPage.waitForNewEditItemDialogToBecomeVisible();

        Assert.assertEquals(citiesPage.inputItemName().getAttribute("type"),
                "text",
                "Input type not 'text'.");

    }

    @Test (priority = 30)
    @Description("Test #3: Create new city")
    public void createNewCity() {

        navPage.buttonAdmin().click();
        navPage.getLinkAdminCities().click();
        citiesPage.buttonNewItem().click();
        citiesPage.waitForNewEditItemDialogToBecomeVisible();
        citiesPage.inputItemName().sendKeys("Dimitrije Mandić's city");
        citiesPage.buttonSave().click();
        messagePopUpPage.waitForPopupToBecomeVisible();

        Assert.assertTrue(messagePopUpPage.getPopupElementsContainingText().stream().anyMatch(e->e.getText()
                        .contains("Saved successfully")),
                "Incorrect or missing message.");

    }

    @Test (priority = 40)
    @Description("Test #4: Edit city")
    public void editCity() {

        navPage.buttonAdmin().click();
        navPage.getLinkAdminCities().click();
        citiesPage.inputSearch().sendKeys("Dimitrije Mandić's city");
        citiesPage.waitForNumberOfTableRowsToAppear(1);
        citiesPage.getEditButtonByTableRow(1).click();
        citiesPage.inputItemName().sendKeys(" Edited");
        citiesPage.buttonSave().click();
        messagePopUpPage.waitForPopupToBecomeVisible();

        Assert.assertTrue(messagePopUpPage.getPopupElementsContainingText().stream().anyMatch(e->e.getText()
                        .contains("Saved successfully")),
                "Incorrect or missing message.");

    }

    @Test (priority = 50)
    @Description("Test #5: Search city")
    public void searchCity() {

        navPage.buttonAdmin().click();
        navPage.getLinkAdminCities().click();
        citiesPage.inputSearch().click();
        citiesPage.inputSearch().clear();
        citiesPage.inputSearch().sendKeys("Dimitrije Mandić's city Edited");
        citiesPage.waitForNumberOfTableRowsToAppear(1);

        Assert.assertEquals(citiesPage.getTableCell(1, 1).getText(),
                "Dimitrije Mandić's city Edited",
                "City name does not match search query.");
    }

    @Test (priority = 60)
    @Description("Test #6: Delete city")
    public void deleteCity() {

        navPage.buttonAdmin().click();
        navPage.getLinkAdminCities().click();
        citiesPage.inputSearch().click();
        citiesPage.inputSearch().clear();
        citiesPage.inputSearch().sendKeys("Dimitrije Mandić's city Edited");
        citiesPage.waitForNumberOfTableRowsToAppear(1);

        Assert.assertEquals(citiesPage.getTableCell(1, 1).getText(),
                "Dimitrije Mandić's city Edited",
                "City name does not match search query.");

        citiesPage.getDeleteButtonByTableRow(1).click();
        citiesPage.waitForDeleteWarningToAppear();
        citiesPage.buttonDelete().click();
        messagePopUpPage.waitForPopupToBecomeVisible();

        Assert.assertTrue(messagePopUpPage.getPopupElementsContainingText().stream().anyMatch(e->e.getText()
                        .contains("Deleted successfully")),
                "Incorrect or missing message.");

    }

}
