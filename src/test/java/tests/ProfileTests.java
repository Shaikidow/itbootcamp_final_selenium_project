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

        navPage.getLinkLogin().click();
        loginPage.inputEmail().sendKeys("admin@admin.com");
        loginPage.inputPassword().sendKeys("12345");
        loginPage.buttonLogin().click();
        messagePopUpPage.waitForProgressBarToBecomeInvisible();
        driver.get(baseUrl + "/profile");

        Assert.assertEquals(driver.getCurrentUrl(),
                baseUrl + "/profile",
                "Not on profile page.");

        new Actions(driver).moveToElement(profilePage.inputEmail()).click().perform();

        Assert.assertEquals(profilePage.inputEmail().getAttribute("value"),
                "admin@admin.com",
                "Unexpected e-mail address.");

        navPage.buttonLogout().click();

    }

    @Test (priority = 20)
    @Description("Test #2: Checks input types")
    public void checksInputTypes() {

        navPage.getLinkLogin().click();
        loginPage.inputEmail().sendKeys("admin@admin.com");
        loginPage.inputPassword().sendKeys("12345");
        loginPage.buttonLogin().click();
        messagePopUpPage.waitForProgressBarToBecomeInvisible();
        navPage.getLinkMyProfile().click();

        Assert.assertEquals(profilePage.inputEmail().getAttribute("type"),
                "email",
                "Input type not 'email'.");

        Assert.assertEquals(profilePage.inputEmail().getAttribute("disabled"),
                "true",
                "Input not disabled.");

        Assert.assertEquals(profilePage.inputName().getAttribute("type"),
                "text",
                "Input type not 'text'.");

        Assert.assertEquals(profilePage.inputCity().getAttribute("type"),
                "text",
                "Input type not 'text'.");

        Assert.assertEquals(profilePage.inputCountry().getAttribute("type"),
                "text",
                "Input type not 'text'.");

        Assert.assertEquals(profilePage.inputUrlTwitter().getAttribute("type"),
                "url",
                "Input type not 'url'.");

        Assert.assertEquals(profilePage.inputUrlGitHub().getAttribute("type"),
                "url",
                "Input type not 'url'.");

        Assert.assertEquals(profilePage.inputPhone().getAttribute("type"),
                "tel",
                "Input type not 'tel'.");

        navPage.buttonLogout().click();

    }

    @Test (priority = 30)
    @Description("Test #3: Edits profile")
    public void editsProfile() throws InterruptedException {

        navPage.getLinkLogin().click();
        loginPage.inputEmail().sendKeys("admin@admin.com");
        loginPage.inputPassword().sendKeys("12345");
        loginPage.buttonLogin().click();
        messagePopUpPage.waitForProgressBarToBecomeInvisible();
        navPage.getLinkMyProfile().click();
        Thread.sleep(1000);

        profilePage.inputName().sendKeys(Keys.chord(Keys.CONTROL, "a"));
        profilePage.inputName().sendKeys(Keys.DELETE);
        profilePage.inputName().sendKeys("Dimitrije Mandić");

        profilePage.inputPhone().sendKeys(Keys.chord(Keys.CONTROL, "a"));
        profilePage.inputPhone().sendKeys(Keys.DELETE);
        profilePage.inputPhone().sendKeys("+38161283223");

        profilePage.inputCity().sendKeys(Keys.chord(Keys.CONTROL, "a"));
        profilePage.inputCity().sendKeys(Keys.DELETE);
        profilePage.inputCity().sendKeys("Bucaramanga");

        profilePage.inputCountry().click();
        profilePage.inputCountry().sendKeys(Keys.chord(Keys.CONTROL, "a"));
        profilePage.inputCountry().sendKeys(Keys.DELETE);
        profilePage.inputCountry().sendKeys("Spain");

        profilePage.inputUrlTwitter().sendKeys(Keys.chord(Keys.CONTROL, "a"));
        profilePage.inputUrlTwitter().sendKeys(Keys.DELETE);
        profilePage.inputUrlTwitter().sendKeys("https://twitter.com/profile/milan1232");

        profilePage.inputUrlGitHub().sendKeys(Keys.chord(Keys.CONTROL, "a"));
        profilePage.inputUrlGitHub().sendKeys(Keys.DELETE);
        profilePage.inputUrlGitHub().sendKeys("https://github.com/shaikidow");

        profilePage.buttonSave().click();
        messagePopUpPage.waitForPopupToBecomeVisible();

        Assert.assertTrue(messagePopUpPage.getPopupElementsContainingText().stream().anyMatch(e->e.getText()
                        .contains("Profile saved successfuly")),
                "Incorrect or missing message.");

        Assert.assertEquals(profilePage.inputName().getAttribute("value"),
                "Dimitrije Mandić",
                "Unexpected profile name.");

        Assert.assertEquals(profilePage.inputPhone().getAttribute("value"),
                "+38161283223",
                "Unexpected phone number.");

        Assert.assertEquals(profilePage.inputCity().getAttribute("value"),
                "Bucaramanga",
                "Unexpected city.");

        Assert.assertEquals(profilePage.inputCountry().getAttribute("value"),
                "Spain",
                "Unexpected country.");

        Assert.assertEquals(profilePage.inputUrlTwitter().getAttribute("value"),
                "https://twitter.com/profile/milan1232",
                "Unexpected Twitter URL.");

        Assert.assertEquals(profilePage.inputUrlGitHub().getAttribute("value"),
                "https://github.com/shaikidow",
                "Unexpected GitHub URL.");

        navPage.buttonLogout().click();

    }

}
