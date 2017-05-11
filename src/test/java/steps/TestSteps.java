package steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.URL;

import static org.junit.Assert.assertTrue;

public class TestSteps {
    private ChromeDriver driver;

    @Before
    public void before() {
        System.setProperty("webdriver.chrome.driver", getDriverExecutable().getPath());
        driver = new ChromeDriver();
    }

    @After
    public void after() {
        if (driver != null) {
            driver.quit();
        }
    }

    @When("I navigate to Stack Overflow question page (\\d+)")
    public void navigateToStackOverflowQuestionPage(Integer page) {
        driver.navigate().to("http://stackoverflow.com/questions?page=" + page);
    }

    @Then("I verify Stack Overflow question page (\\d+) is opened")
    public void verifyCorrectQuestionPageIsOpened(Integer page) {
        assertTrue(driver.getTitle().contains("Page " + page));
    }

    private URL getDriverExecutable() {
        final String osName = System.getProperty("os.name").toLowerCase();

        if (osName.contains("mac")) {
            return ClassLoader.getSystemResource("drivers/mac_chromedriver");
        } else if (osName.contains("windows")) {
            return ClassLoader.getSystemResource("drivers/win_chromedriver");
        } else {
            throw new RuntimeException("Chrome driver executable is not provided for this operating system!");
        }
    }
}
