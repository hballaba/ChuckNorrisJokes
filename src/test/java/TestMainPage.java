import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.Assert.assertEquals;


public class TestMainPage {

    WebDriver driver = null;
    private Logger logger = LogManager.getLogger(TestMainPage.class);

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Иван\\Downloads\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        if (driver != null)
            logger.info("Driver started");
        else
            logger.warn("Error when starting the driver");
        driver.get("http://localhost:8080/");
    }

    @After
    public void cleanUp() {
        if(driver != null)
            driver.quit();
        logger.info("Driver close");
    }

    @Test
    public void testTitleMainPage() {
        String expected = "Welcome to Jokes Chuck Norris";
        String actual = driver.getTitle();
        logger.info("Test title. Expected: " + expected + ". Actual: " + actual);
        assertEquals(expected, actual);
    }

    @Test
    public void testUrlMainPage() {
        String expected = "http://localhost:8080/";
        String actual = driver.getCurrentUrl();
        logger.info("Test URL. Expected: " + expected + ". Actual: " + actual);
        assertEquals(expected, actual);
    }

    @Test
    public void testClickJokes() {

        driver.findElement(By.id("buttonForJokes")).click();

        String expected = "http://localhost:8080/jokes";
        String actual = driver.getCurrentUrl();
        logger.info("Test URL. Expected: " + expected + ". Actual: " + actual);
        assertEquals(expected, actual);
    }
}
