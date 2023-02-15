package tests;

import com.sun.org.glassfish.gmbal.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignupTests extends BaseTest {

    @Test(priority = 10)
    @Description("Test #1: Visits signup page")
    public void visitsSignupPage() {

        navPage.getSignUpLink().click();
        navPage.waitForPageToLoad();

        Assert.assertEquals(driver.getCurrentUrl(),
                baseUrl + "/signup",
                "Not on signup page.");

    }

    @Test (priority = 20)
    @Description("Test #2: Checks input types")
    public void checksInputTypes() {

        navPage.getSignUpLink().click();
        navPage.waitForPageToLoad();

        Assert.assertEquals(signupPage.getEmailInput().getAttribute("type"),
                "email",
                "Input type not 'email'.");

        Assert.assertEquals(signupPage.getPasswordInput().getAttribute("type"),
                "password",
                "Input type not 'password'.");

        Assert.assertEquals(signupPage.getConfirmPasswordInput().getAttribute("type"),
                "password",
                "Input type not 'password'.");

    }

    @Test (priority = 30)
    @Description("Test #3: Displays errors when user already exists")
    public void displaysErrorsWhenUserAlreadyExists() {

        navPage.getSignUpLink().click();
        navPage.waitForPageToLoad();

        Assert.assertEquals(driver.getCurrentUrl(),
                baseUrl + "/signup",
                "Not on signup page.");

        signupPage.getNameInput().sendKeys("Another User");
        signupPage.getEmailInput().sendKeys("admin@admin.com");
        signupPage.getPasswordInput().sendKeys("12345");
        signupPage.getConfirmPasswordInput().sendKeys("12345");
        signupPage.getSignMeUpButton().click();
        messagePopUpPage.waitForPopupToBecomeVisible();

        Assert.assertTrue(messagePopUpPage.getPopupElementsContainingText().stream().anyMatch(e->e.getText()
                        .equals("E-mail already exists")),
                "Incorrect or missing message.");

        Assert.assertEquals(driver.getCurrentUrl(),
                baseUrl + "/signup",
                "Not on signup page.");

    }

    @Test (priority = 40)
    @Description("Test #4: Signup")
    public void signup() {

        navPage.getSignUpLink().click();
        navPage.waitForPageToLoad();

        signupPage.getNameInput().sendKeys("Dimitrije Mandić");
        signupPage.getEmailInput().sendKeys("dimitrije.mandic@itbootcamp.rs");
        signupPage.getPasswordInput().sendKeys("12345");
        signupPage.getConfirmPasswordInput().sendKeys("12345");
        signupPage.getSignMeUpButton().click();
        messagePopUpPage.waitForProgressBarToBecomeInvisible();

        Assert.assertTrue(messagePopUpPage.getVerificationDialogHeader().getText()
                .contains("IMPORTANT: Verify your account"),
                "Incorrect or missing message.");

        messagePopUpPage.getCloseVerificationDialogButton().click();
        navPage.getLogoutButton().click();

//      when performing this test multiple times within 30min., change the e-mail address in line 75 so it doesn't crash

    }

}
