package tests;

import com.sun.org.glassfish.gmbal.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AdminCitiesTests extends BaseTest {

    @Test(priority = 10)
    @Description("Test #1: Visits admin cities page and lists cities")
    public void visitsAdminCitiesPageAndListsCities() {

        navPage.getLoginLink().click();
        navPage.waitForPageToLoad();
        loginPage.getEmailInput().sendKeys("admin@admin.com");
        loginPage.getPasswordInput().sendKeys("12345");
        loginPage.getLoginButton().click();
        messagePopUpPage.waitForProgressBarToBecomeInvisible();
        navPage.getAdminButton().click();
        navPage.getAdminCitiesLink().click();
        navPage.waitForPageToLoad();

        Assert.assertEquals(driver.getCurrentUrl(),
                baseUrl + "/admin/cities",
                "Not on admin cities page.");

    }

    @Test (priority = 20)
    @Description("Test #2: Checks input types for create/edit new city")
    public void checksInputTypesForCreateEditNewCity() {

        navPage.getAdminButton().click();
        navPage.getAdminCitiesLink().click();
        navPage.waitForPageToLoad();
        citiesPage.getNewItemButton().click();
        citiesPage.waitForNewEditItemDialogToBecomeVisible();

        Assert.assertEquals(citiesPage.getNameInput().getAttribute("type"),
                "text",
                "Input type not 'text'.");

    }

    @Test (priority = 30)
    @Description("Test #3: Create new city")
    public void createNewCity() {

        navPage.getAdminButton().click();
        navPage.getAdminCitiesLink().click();
        navPage.waitForPageToLoad();
        citiesPage.getNewItemButton().click();
        citiesPage.waitForNewEditItemDialogToBecomeVisible();
        citiesPage.getNameInput().sendKeys("Dimitrije Mandić's city");
        citiesPage.getSaveButton().click();
        messagePopUpPage.waitForPopupToBecomeVisible();

        Assert.assertTrue(messagePopUpPage.getPopupElementsContainingText().stream().anyMatch(e->e.getText()
                        .contains("Saved successfully")),
                "Incorrect or missing message.");

    }

    @Test (priority = 40)
    @Description("Test #4: Edit city")
    public void editCity() {

        navPage.getAdminButton().click();
        navPage.getAdminCitiesLink().click();
        navPage.waitForPageToLoad();
        citiesPage.getSearchInput().sendKeys("Dimitrije Mandić's city");
        citiesPage.waitForNumberOfTableRowsToAppear(1);
        citiesPage.getEditButtonByTableRow(1).click();
        citiesPage.getNameInput().sendKeys(" Edited");
        citiesPage.getSaveButton().click();
        messagePopUpPage.waitForPopupToBecomeVisible();

        Assert.assertTrue(messagePopUpPage.getPopupElementsContainingText().stream().anyMatch(e->e.getText()
                        .contains("Saved successfully")),
                "Incorrect or missing message.");

    }

    @Test (priority = 50)
    @Description("Test #5: Search city")
    public void searchCity() {

        navPage.getAdminButton().click();
        navPage.getAdminCitiesLink().click();
        navPage.waitForPageToLoad();
        citiesPage.getSearchInput().click();
        citiesPage.getSearchInput().clear();
        citiesPage.getSearchInput().sendKeys("Dimitrije Mandić's city Edited");
        citiesPage.waitForNumberOfTableRowsToAppear(1);

        Assert.assertEquals(citiesPage.getTableCell(1, 1).getText(),
                "Dimitrije Mandić's city Edited",
                "City name does not match search query.");

    }

    @Test (priority = 60)
    @Description("Test #6: Delete city")
    public void deleteCity() {

        navPage.getAdminButton().click();
        navPage.getAdminCitiesLink().click();
        navPage.waitForPageToLoad();
        citiesPage.getSearchInput().click();
        citiesPage.getSearchInput().clear();
        citiesPage.getSearchInput().sendKeys("Dimitrije Mandić's city Edited");
        citiesPage.waitForNumberOfTableRowsToAppear(1);

        Assert.assertEquals(citiesPage.getTableCell(1, 1).getText(),
                "Dimitrije Mandić's city Edited",
                "City name does not match search query.");

        citiesPage.getDeleteButtonByTableRow(1).click();
        citiesPage.waitForDeleteWarningToAppear();
        citiesPage.getDeleteButton().click();
        messagePopUpPage.waitForPopupToBecomeVisible();

        Assert.assertTrue(messagePopUpPage.getPopupElementsContainingText().stream().anyMatch(e->e.getText()
                        .contains("Deleted successfully")),
                "Incorrect or missing message.");

    }

}
