package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class TestSteps {
    private WebDriver driver;

    @Before
    public void before() throws MalformedURLException {
        configureWebDriver();
    }

    @After
    public void after(Scenario scenario) {
        if (scenario.isFailed()) {
            scenario.log("Scenario failed so capturing a screenshot");

            TakesScreenshot screenshot = (TakesScreenshot) driver;
            scenario.attach(screenshot.getScreenshotAs(OutputType.BYTES), "image/png", scenario.getName());
        }
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("^I navigate to Stack Overflow$")
    public void iNavigateToStackOverflow() {
        driver.navigate().to("https://stackoverflow.com/");
    }

    @When("I navigate to Stack Overflow question page {int}")
    public void i_navigate_to_Stack_Overflow_question_page(Integer page) {
        driver.navigate().to("https://stackoverflow.com/questions?page=" + page);
    }

    @Then("I verify Stack Overflow question page {int} is opened")
    public void i_verify_Stack_Overflow_question_page_is_opened(Integer page) {
        if (!driver.getTitle().contains("Page " + page)) {
            throw new RuntimeException("The Stack Overflow page title does not contain the page number: " + page);
        }
    }

    @When("I use the following data table to navigate to a Stack Overflow question page")
    public void i_use_the_following_data_table_to_navigate_to_a_Stack_Overflow_question_page(io.cucumber.datatable.DataTable dataTable) {
        int pageNumber = Integer.valueOf(dataTable.cell(1, 0));
        i_navigate_to_Stack_Overflow_question_page(pageNumber);
    }

    private void configureWebDriver() throws MalformedURLException {
        String remoteServerUrl = System.getenv("COURGETTE_REMOTE_SERVER_URL");
        if (remoteServerUrl != null) {
            driver = new RemoteWebDriver(new URL(remoteServerUrl), new FirefoxOptions());
            return;
        }

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }
}
