package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public abstract class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected String baseURL = "https://vue-demo.daniel-avellaneda.com/";

    @BeforeClass
    public void setup() {

        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }

    @BeforeMethod
    public void beforeMethod() {
        driver.get(baseURL);
    }

    @AfterMethod
    public void afterMethod() {

    }

    @AfterClass
    public void afterClass() throws InterruptedException {

        Thread.sleep(5000);
        driver.quit();

    }

}
