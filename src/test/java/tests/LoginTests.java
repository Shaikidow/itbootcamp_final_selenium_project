package tests;

import com.sun.org.glassfish.gmbal.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    @Test (priority = 10)
    @Description("Test #1: Visits login page")
    public void visitsLoginPage() {

        navPage.buttonLanguageSelect().click();
        navPage.buttonLanguageEn().click();
        navPage.getLinkLogin().click();

        Assert.assertEquals(driver.getCurrentUrl(),
                baseUrl + "/login",
                "Not on login page.");

    }

    @Test (priority = 20)
    @Description("Test #2: Checks input types")
    public void checksInputTypes() {

        navPage.getLinkLogin().click();

        Assert.assertEquals(loginPage.inputEmail().getAttribute("type"),
                "email",
                "Input type not 'email'.");

        Assert.assertEquals(loginPage.inputPassword().getAttribute("type"),
                "password",
                "Input type not 'password'.");

    }

    @Test (priority = 30)
    @Description("Test #3: Displays errors when user does not exist")
    public void displaysErrorsWhenUserDoesNotExist() {

        navPage.getLinkLogin().click();
        loginPage.inputEmail().sendKeys("non-existing-user@gmail.com");
        loginPage.inputPassword().sendKeys("password123");
        loginPage.buttonLogin().click();
        messagePopUpPage.waitForPopupToBecomeVisible();

        Assert.assertTrue(messagePopUpPage.getPopupElementsContainingText().stream().anyMatch(e->e.getText()
                        .contains("User does not exists")),
                "Incorrect or missing message."); // this works for WebElement lists, I should come back to it!

        Assert.assertEquals(driver.getCurrentUrl(),
                baseUrl + "/login",
                "Not on login page.");

    }

    @Test (priority = 40)
    @Description("Test #4: Displays errors when password is wrong")
    public void displaysErrorsWhenPasswordIsWrong() {

        navPage.getLinkLogin().click();
        loginPage.inputEmail().sendKeys("admin@admin.com");
        loginPage.inputPassword().sendKeys("password123");
        loginPage.buttonLogin().click();
        messagePopUpPage.waitForPopupToBecomeVisible();

        Assert.assertTrue(messagePopUpPage.getPopupElementsContainingText().stream().anyMatch(e->e.getText()
                        .contains("Wrong password")),
                "Incorrect or missing message."); // this works for WebElement lists, I should come back to it!

        Assert.assertEquals(driver.getCurrentUrl(),
                baseUrl + "/login",
                "Not on login page.");

    }

    @Test (priority = 50)
    @Description("Test #5: Login")
    public void login() throws InterruptedException {

        navPage.getLinkLogin().click();
        loginPage.inputEmail().sendKeys("admin@admin.com");
        loginPage.inputPassword().sendKeys("12345");
        loginPage.buttonLogin().click();
        Thread.sleep(1000);

//      here I would usually use
//      wait.until(ExpectedConditions.urlToBe(baseUrl + "/home"));
//      but aside from it looking redundant, implicit wait timeout would crash the test instead of merely making it fail

        Assert.assertEquals(driver.getCurrentUrl(),
                baseUrl + "/home",
                "Not on home page.");

    }

    @Test (priority = 60)
    @Description("Test #6: Logout")
    public void logout() {

//        IMPORTANT: in order for this test to be passed alone, you need to enable the commented-out block of code below

//        navPage.getLinkLogin().click();
//        loginPage.inputEmail().sendKeys("admin@admin.com");
//        loginPage.inputPassword().sendKeys("12345");
//        loginPage.buttonLogin().click();
//        wait.until(ExpectedConditions.urlToBe(baseUrl + "/home"));
//        Assert.assertEquals(driver.getCurrentUrl(),
//                baseUrl + "/home",
//                "Not on home page.");

        Assert.assertTrue(navPage.buttonLogout().isDisplayed(),
                "Logout button not visible.");
        navPage.buttonLogout().click();

    }

}
