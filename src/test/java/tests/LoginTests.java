package tests;

import com.sun.org.glassfish.gmbal.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    @Test (priority = 10)
    @Description("Test #1: Visits login page")
    public void visitsLoginPage() {

        navPage.getLocaleActivationButton().click(); // I feel this was proposed so that the test wouldn't be too short?
        navPage.getLanguageButtonEn().click(); // anyway, setting the language to English without thinking about it much
        navPage.getLoginLink().click();
        navPage.waitForPageToLoad();

        Assert.assertEquals(driver.getCurrentUrl(),
                baseUrl + "/login",
                "Not on login page.");

    }

    @Test (priority = 20)
    @Description("Test #2: Checks input types")
    public void checksInputTypes() {

        navPage.getLoginLink().click();
        navPage.waitForPageToLoad();

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
        navPage.waitForPageToLoad();
        loginPage.getEmailInput().sendKeys("non-existing-user@gmail.com"); // really what it says on the tin
        loginPage.getPasswordInput().sendKeys("password123"); // same here - some arbitrary invalid password
        loginPage.getLoginButton().click();
        messagePopUpPage.waitForPopupToBecomeVisible();

        Assert.assertTrue(messagePopUpPage.getPopupElementsContainingText().stream().anyMatch(e->e.getText()
                        .contains("User does not exists")),
                "Incorrect or missing message."); // yes, it should say "exist", it's a misspelling on the site

        Assert.assertEquals(driver.getCurrentUrl(),
                baseUrl + "/login",
                "Not on login page.");

    }

    @Test (priority = 40)
    @Description("Test #4: Displays errors when password is wrong")
    public void displaysErrorsWhenPasswordIsWrong() {

        navPage.getLoginLink().click();
        navPage.waitForPageToLoad();
        loginPage.getEmailInput().sendKeys("admin@admin.com"); // e-mail for the super administrator account
        loginPage.getPasswordInput().sendKeys("password123"); // still invalid
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
        navPage.waitForPageToLoad();
        loginPage.getEmailInput().sendKeys("admin@admin.com");
        loginPage.getPasswordInput().sendKeys("12345");
        loginPage.getLoginButton().click();
        messagePopUpPage.waitForProgressBarToBecomeInvisible();

        Assert.assertEquals(driver.getCurrentUrl(),
                baseUrl + "/home",
                "Not on home page.");

    }

    @Test (priority = 60)
    @Description("Test #6: Logout")
    public void logout() {

        Assert.assertTrue(navPage.getLogoutButton().isDisplayed(),
                "Logout button not visible."); // if you can see it, you can click it

        navPage.getLogoutButton().click();

    }

}
