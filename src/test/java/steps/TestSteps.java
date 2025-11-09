package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestSteps {
    private WebDriver driver;

    private static final String PROJECT_URL = "https://github.com/prashant-ramcharan/courgette-jvm";
    private static final String PROJECT_CHANGELOG_URL = String.format("%s/blob/master/CHANGELOG.md", PROJECT_URL);

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

    @When("I navigate to the Courgette JVM GitHub project page")
    public void i_navigate_to_the_courgette_jvm_git_hub_project_page() {
        driver.navigate().to(PROJECT_URL);
    }

    @When("I navigate to the Courgette JVM changelog page")
    public void i_navigate_to_the_courgette_jvm_changelog_page() {
        driver.navigate().to(PROJECT_CHANGELOG_URL);
    }

    @Then("I verify the Courgette JVM GitHub project page is opened")
    public void i_verify_the_courgette_jvm_git_hub_project_page_is_opened() {
        String heading = driver.findElement(By.className("heading-element")).getText();
        assertThat(heading, equalTo("Courgette-JVM"));
    }

    @Then("I verify the Courgette JVM changelog includes release {}")
    public void i_verify_the_courgette_jvm_changelog_includes_release(String release) {
        List<String> releases = driver.findElements(By.className("heading-element")).stream().map(WebElement::getText).collect(Collectors.toList());
        assertThat("Release not found in changelog", releases, hasItem(String.format("CHANGES IN VERSION %s", release)));
    }

    @When("I use the following data table to verify the Courgette JVM release exists")
    public void i_use_the_following_data_table_to_search_for_a_release(DataTable dataTable) {
        String release = dataTable.cell(1, 0);
        i_verify_the_courgette_jvm_changelog_includes_release(release);
    }

    private void configureWebDriver() throws MalformedURLException {
        String remoteServerUrl = System.getenv("COURGETTE_REMOTE_SERVER_URL");
        if (remoteServerUrl != null) {
            driver = new RemoteWebDriver(new URL(remoteServerUrl), new ChromeOptions());
            return;
        }

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }
}
