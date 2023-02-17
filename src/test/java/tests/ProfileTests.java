package tests;

import com.sun.org.glassfish.gmbal.Description;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProfileTests extends BaseTest {

    @Test(priority = 10)
    @Description("Test #1: Visits profile page")
    public void visitsProfilePage() {

        navPage.getLoginLink().click();
        navPage.waitForPageToLoad();
        loginPage.getEmailInput().sendKeys("admin@admin.com"); // we need the super admin logged in for this
        loginPage.getPasswordInput().sendKeys("12345");
        loginPage.getLoginButton().click();
        messagePopUpPage.waitForProgressBarToBecomeInvisible();
        navPage.getMyProfileLink().click();
        navPage.waitForPageToLoad();

        Assert.assertEquals(driver.getCurrentUrl(),
                baseUrl + "/profile",
                "Not on profile page.");

        new Actions(driver).moveToElement(profilePage.getEmailInput()).click().perform();

//      the above action chain is necessary to focus on the email input, because it's fully overlapped by a span element

        Assert.assertEquals(profilePage.getEmailInput().getAttribute("value"),
                "admin@admin.com",
                "E-mail address not 'admin@admin.com'.");

        navPage.getLogoutButton().click(); // logging out is mandatory for the sequential passing of all the tests here!

    }

    @Test (priority = 20)
    @Description("Test #2: Checks input types")
    public void checksInputTypes() {

        navPage.getLoginLink().click();
        navPage.waitForPageToLoad();
        loginPage.getEmailInput().sendKeys("admin@admin.com");
        loginPage.getPasswordInput().sendKeys("12345");
        loginPage.getLoginButton().click();
        messagePopUpPage.waitForProgressBarToBecomeInvisible();
        navPage.getMyProfileLink().click();
        navPage.waitForPageToLoad();

        Assert.assertEquals(profilePage.getEmailInput().getAttribute("type"),
                "email",
                "Input type not 'email'.");

        Assert.assertEquals(profilePage.getEmailInput().getAttribute("disabled"),
                "true",
                "Input not disabled."); // this functions like assertTrue, as 'disabled' is a boolean attribute
                                                // zero ideas whatsoever about why no focusing is necessary here, though

        Assert.assertTrue(profilePage.getEmailInput().getAttribute("outerHTML")
                .contains("disabled=\"disabled\"")); // I just added this to show that the HTML says disabled='disabled'

        Assert.assertEquals(profilePage.getNameInput().getAttribute("type"),
                "text",
                "Input type not 'text'.");

        Assert.assertEquals(profilePage.getCityInput().getAttribute("type"),
                "text",
                "Input type not 'text'.");

        Assert.assertEquals(profilePage.getCountryInput().getAttribute("type"),
                "text",
                "Input type not 'text'.");

        Assert.assertEquals(profilePage.getUrlTwitterInput().getAttribute("type"),
                "url",
                "Input type not 'url'.");

        Assert.assertEquals(profilePage.getUrlGitHubInput().getAttribute("type"),
                "url",
                "Input type not 'url'.");

        Assert.assertEquals(profilePage.getPhoneInput().getAttribute("type"),
                "tel",
                "Input type not 'tel'.");

        navPage.getLogoutButton().click();

    }

    @Test (priority = 30)
    @Description("Test #3: Edits profile")
    public void editsProfile() throws InterruptedException {

        navPage.getLoginLink().click();
        navPage.waitForPageToLoad();
        loginPage.getEmailInput().sendKeys("admin@admin.com");
        loginPage.getPasswordInput().sendKeys("12345");
        loginPage.getLoginButton().click();
        messagePopUpPage.waitForProgressBarToBecomeInvisible();
        navPage.getMyProfileLink().click();
        Thread.sleep(1000); // I deemed an explicit waiter necessary here, because waitForPageToLoad often crashed

        profilePage.getNameInput().sendKeys(Keys.chord(Keys.CONTROL, "a"));
        profilePage.getNameInput().sendKeys(Keys.DELETE);
        profilePage.getNameInput().sendKeys("Dimitrije Mandić");

        profilePage.getPhoneInput().sendKeys(Keys.chord(Keys.CONTROL, "a"));
        profilePage.getPhoneInput().sendKeys(Keys.DELETE);
        profilePage.getPhoneInput().sendKeys("+38161283223");

        profilePage.getCityInput().sendKeys(Keys.chord(Keys.CONTROL, "a"));
        profilePage.getCityInput().sendKeys(Keys.DELETE);
        profilePage.getCityInput().sendKeys("Bucaramanga");

        profilePage.getCountryInput().click();
        profilePage.getCountryInput().sendKeys(Keys.chord(Keys.CONTROL, "a"));
        profilePage.getCountryInput().sendKeys(Keys.DELETE);
        profilePage.getCountryInput().sendKeys("Spain");

        profilePage.getUrlTwitterInput().sendKeys(Keys.chord(Keys.CONTROL, "a"));
        profilePage.getUrlTwitterInput().sendKeys(Keys.DELETE);
        profilePage.getUrlTwitterInput().sendKeys("https://twitter.com/profile/milan1232"); // not me but OK

        profilePage.getUrlGitHubInput().sendKeys(Keys.chord(Keys.CONTROL, "a"));
        profilePage.getUrlGitHubInput().sendKeys(Keys.DELETE);
        profilePage.getUrlGitHubInput().sendKeys("https://github.com/shaikidow");

        profilePage.getSaveButton().click();
        messagePopUpPage.waitForPopupToBecomeVisible();

        Assert.assertTrue(messagePopUpPage.getPopupElementsContainingText().stream().anyMatch(e->e.getText()
                        .contains("Profile saved successfuly")),
                "Incorrect or missing message."); // another misspelling on the site itself, like in LoginTests

        Assert.assertEquals(profilePage.getNameInput().getAttribute("value"),
                "Dimitrije Mandić",
                "Incorrect or missing profile name."); // calls DOM attributes/properties if unwritten in HTML!

        Assert.assertEquals(profilePage.getPhoneInput().getAttribute("value"),
                "+38161283223",
                "Incorrect or missing phone number.");

        Assert.assertEquals(profilePage.getCityInput().getAttribute("value"),
                "Bucaramanga",
                "Incorrect or missing city.");

        Assert.assertEquals(profilePage.getCountryInput().getAttribute("value"),
                "Spain",
                "Incorrect or missing country.");

        Assert.assertEquals(profilePage.getUrlTwitterInput().getAttribute("value"),
                "https://twitter.com/profile/milan1232",
                "Incorrect or missing Twitter URL.");

        Assert.assertEquals(profilePage.getUrlGitHubInput().getAttribute("value"),
                "https://github.com/shaikidow",
                "Incorrect or missing GitHub URL.");

        navPage.getLogoutButton().click();

    }

}
