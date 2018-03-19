package steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.pramcharan.wd.binary.downloader.WebDriverBinaryDownloader;
import io.github.pramcharan.wd.binary.downloader.enums.BrowserType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertTrue;

public class TestSteps {
    private WebDriver driver;

    @Before
    public void before() {
        WebDriverBinaryDownloader.create().downloadLatestBinaryAndConfigure(BrowserType.CHROME);
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
        driver.navigate().to("https://stackoverflow.com/questions?page=" + page);
    }

    @Then("I verify Stack Overflow question page (\\d+) is opened")
    public void verifyCorrectQuestionPageIsOpened(Integer page) {
        assertTrue(driver.getTitle().contains("Page " + page));
    }
}
