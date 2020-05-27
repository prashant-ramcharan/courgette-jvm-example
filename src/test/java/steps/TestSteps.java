package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java8.En;
import io.github.pramcharan.wd.binary.downloader.WebDriverBinaryDownloader;
import io.github.pramcharan.wd.binary.downloader.enums.BrowserType;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestSteps implements En {
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

    public TestSteps() {
        When("^I navigate to Stack Overflow$", () -> {
            driver.navigate().to("https://stackoverflow.com/");
        });

        When("^I navigate to Stack Overflow question page (\\d+)$", (Integer page) -> {
            driver.navigate().to("https://stackoverflow.com/questions?page=" + page);
        });

        Then("^I verify Stack Overflow question page (\\d+) is opened$", (Integer page) -> {
            if (!driver.getTitle().contains("Page " + page)) {
                throw new RuntimeException("The Stack Overflow page title does not contain the page number: " + page);
            }
        });

        When("^I use the following data table to navigate to a Stack Overflow question page$", (io.cucumber.datatable.DataTable dataTable) -> {
            int pageNumber = Integer.parseInt(dataTable.cells().get(1).get(0));
            driver.navigate().to("https://stackoverflow.com/questions?page=" + pageNumber);
        });
    }
}
