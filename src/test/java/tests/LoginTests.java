package tests;

import com.sun.org.glassfish.gmbal.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    @Test (priority = 10)
    @Description("Test #1: Visits login page")
    public void visitsLoginPage() {

        navPage.getLocaleActivationButton().click();
        navPage.getLanguageButtonEn().click();
        navPage.getLoginLink().click();

        Assert.assertEquals(driver.getCurrentUrl(),
                baseUrl + "/login",
                "Not on login page.");

    }

    @Test (priority = 20)
    @Description("Test #2: Checks input types")
    public void checksInputTypes() {

        navPage.getLoginLink().click();

        Assert.assertEquals(loginPage.getEmailInput().getAttribute("type"),
                "email",
                "Input type not 'email'.");

        Assert.assertEquals(loginPage.getPasswordInput().getAttribute("type"),
                "password",
                "Input type not 'password'.");

    }

    @Test (priority = 30)
    @Description("Test #3: Displays errors when user does not exist")
    public void displaysErrorsWhenUserDoesNotExist() {

        navPage.getLoginLink().click();
        loginPage.getEmailInput().sendKeys("non-existing-user@gmail.com");
        loginPage.getPasswordInput().sendKeys("password123");
        loginPage.getLoginButton().click();
        messagePopUpPage.waitForPopupToBecomeVisible();

        Assert.assertTrue(messagePopUpPage.getPopupElementsContainingText().stream().anyMatch(e->e.getText()
                        .contains("User does not exists")),
                "Incorrect or missing message.");

        Assert.assertEquals(driver.getCurrentUrl(),
                baseUrl + "/login",
                "Not on login page.");

    }

    @Test (priority = 40)
    @Description("Test #4: Displays errors when password is wrong")
    public void displaysErrorsWhenPasswordIsWrong() {

        navPage.getLoginLink().click();
        loginPage.getEmailInput().sendKeys("admin@admin.com");
        loginPage.getPasswordInput().sendKeys("password123");
        loginPage.getLoginButton().click();
        messagePopUpPage.waitForPopupToBecomeVisible();

        Assert.assertTrue(messagePopUpPage.getPopupElementsContainingText().stream().anyMatch(e->e.getText()
                        .contains("Wrong password")),
                "Incorrect or missing message.");

        Assert.assertEquals(driver.getCurrentUrl(),
                baseUrl + "/login",
                "Not on login page.");

    }

    @Test (priority = 50)
    @Description("Test #5: Login")
    public void login() {

        navPage.getLoginLink().click();
        loginPage.getEmailInput().sendKeys("admin@admin.com");
        loginPage.getPasswordInput().sendKeys("12345");
        loginPage.getLoginButton().click();
        messagePopUpPage.waitForProgressBarToBecomeInvisible();

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

        Assert.assertTrue(navPage.getLogoutButton().isDisplayed(),
                "Logout button not visible.");

        navPage.getLogoutButton().click();

    }

}
