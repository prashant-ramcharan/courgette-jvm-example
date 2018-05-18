package steps;

import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.pramcharan.wd.binary.downloader.WebDriverBinaryDownloader;
import io.github.pramcharan.wd.binary.downloader.enums.BrowserType;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestSteps {
    private WebDriver driver;

    @Before
    public void before() {
        WebDriverBinaryDownloader.create().downloadLatestBinaryAndConfigure(BrowserType.CHROME);
        driver = new ChromeDriver();
    }

    @After
    public void after(Scenario scenario) {
        if (scenario.isFailed()) {
            scenario.write("Scenario failed so capturing a screenshot");

            TakesScreenshot screenshot = (TakesScreenshot) driver;
            scenario.embed(screenshot.getScreenshotAs(OutputType.BYTES), "image/png");
        }
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("^I navigate to Stack Overflow$")
    public void iNavigateToStackOverflow() {
        driver.navigate().to("https://stackoverflow.com/");
    }

    @When("I navigate to Stack Overflow question page (\\d+)")
    public void navigateToStackOverflowQuestionPage(Integer page) {
        driver.navigate().to("https://stackoverflow.com/questions?page=" + page);
    }

    @Then("I verify Stack Overflow question page (\\d+) is opened")
    public void verifyCorrectQuestionPageIsOpened(Integer page) {
        if (!driver.getTitle().contains("Page " + page)) {
            throw new RuntimeException("The Stack Overflow page title does not contain the page number: " + page);
        }
    }

    @When("I use the following data table to navigate to a Stack Overflow question page")
    public void useTheFollowingDataTable(DataTable dataTable) {
        String page = dataTable.getPickleRows().stream().skip(1).findFirst().get().getCells().get(0).getValue();
        navigateToStackOverflowQuestionPage(Integer.valueOf(page));
    }
}
