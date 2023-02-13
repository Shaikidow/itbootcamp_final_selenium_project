package tests;

import com.sun.org.glassfish.gmbal.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LocaleTests extends BaseTest {

    @Test(priority = 10)
    @Description("Test #1: Set locale to ES")
    public void setLocaleToEs() {

        navPage.buttonLanguageSelect().click();
        navPage.buttonLanguageEs().click();

        Assert.assertEquals(navPage.getPageHeader().getText(),
                "Página de aterrizaje",
                "Incorrect or missing header.");

    }

    @Test (priority = 20)
    @Description("Test #2: Set locale to EN")
    public void setLocaleToEn() {

        navPage.buttonLanguageSelect().click();
        navPage.buttonLanguageEn().click();

        Assert.assertEquals(navPage.getPageHeader().getText(),
                "Landing",
                "Incorrect or missing header.");

    }

    @Test (priority = 30)
    @Description("Test #3: Set locale to CN")
    public void setLocaleToCn() {

        navPage.buttonLanguageSelect().click();
        navPage.buttonLanguageCn().click();

        Assert.assertEquals(navPage.getPageHeader().getText(),
                "首页",
                "Incorrect or missing header.");

    }

    @Test (priority = 40)
    @Description("Test #4: Set locale to FR")
    public void setLocaleToFr() {

        navPage.buttonLanguageSelect().click();
        navPage.buttonLanguageFr().click();

        Assert.assertEquals(navPage.getPageHeader().getText(),
                "Page d'atterrissage",
                "Incorrect or missing header.");

    }

    @Test (priority = 50)
    @Description("Test #5: Set locale to UA")
    public void setLocaleToUa() {

        navPage.buttonLanguageSelect().click();
        navPage.buttonLanguageUa().click();

        Assert.assertEquals(navPage.getPageHeader().getText(),
                "Лендінг",
                "Incorrect or missing header.");

    }

}
