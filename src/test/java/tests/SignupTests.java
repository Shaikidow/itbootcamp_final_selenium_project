package tests;

import com.sun.org.glassfish.gmbal.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignupTests extends BaseTest {

    @Test(priority = 10)
    @Description("Test #1: Visits signup page")
    public void visitsSignupPage() {

        navPage.getLinkSignUp().click();

        Assert.assertEquals(driver.getCurrentUrl(),
                baseUrl + "/signup",
                "Not on signup page.");

    }

    @Test (priority = 20)
    @Description("Test #2: Checks input types")
    public void checksInputTypes() {

        navPage.getLinkSignUp().click();

        Assert.assertEquals(signupPage.inputEmail().getAttribute("type"),
                "email",
                "Input type not 'email'.");

        Assert.assertEquals(signupPage.inputPassword().getAttribute("type"),
                "password",
                "Input type not 'password'.");

        Assert.assertEquals(signupPage.inputConfirmPassword().getAttribute("type"),
                "password",
                "Input type not 'password'.");

    }

    @Test (priority = 30)
    @Description("Test #3: Displays errors when user already exists")
    public void displaysErrorsWhenUserAlreadyExists() {

        navPage.getLinkSignUp().click();

        Assert.assertEquals(driver.getCurrentUrl(),
                baseUrl + "/signup",
                "Not on signup page.");

        signupPage.inputName().sendKeys("Another User");
        signupPage.inputEmail().sendKeys("admin@admin.com");
        signupPage.inputPassword().sendKeys("12345");
        signupPage.inputConfirmPassword().sendKeys("12345");
        signupPage.buttonSignMeUp().click();
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
    public void signup() throws InterruptedException {

        navPage.getLinkSignUp().click();

        signupPage.inputName().sendKeys("Dimitrije Mandić");
        signupPage.inputEmail().sendKeys("dimitrije.mandic@itbootcamp.rs");
        signupPage.inputPassword().sendKeys("12345");
        signupPage.inputConfirmPassword().sendKeys("12345");
        signupPage.buttonSignMeUp().click();
        Thread.sleep(1000); // test docs require driver.get(baseUrl + "/home") instead, which doesn't seem to work

        Assert.assertTrue(messagePopUpPage.getVerificationDialogHeader().getText()
                .contains("IMPORTANT: Verify your account"),
                "Incorrect or missing message.");

        messagePopUpPage.buttonCloseVerificationDialog().click();
        navPage.buttonLogout().click();

    }

}
