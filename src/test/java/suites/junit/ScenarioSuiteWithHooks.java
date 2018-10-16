package suites.junit;

import courgette.api.CourgetteAfterAll;
import courgette.api.CourgetteBeforeAll;
import courgette.api.CourgetteOptions;
import courgette.api.CourgetteRunLevel;
import courgette.api.junit.Courgette;
import cucumber.api.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Courgette.class)
@CourgetteOptions(
        threads = 10,
        runLevel = CourgetteRunLevel.SCENARIO,
        rerunFailedScenarios = true,
        showTestOutput = true,
        reportTargetDir = "build",
        cucumberOptions = @CucumberOptions(
                features = "src/test/resources/features",
                glue = "steps",
                tags = {"@regression", "not @excluded"},
                plugin = {
                        "pretty",
                        "json:build/cucumber-report/cucumber.json",
                        "html:build/cucumber-report/cucumber.html",
                        "junit:build/cucumber-report/cucumber.xml"},
                strict = true
        ))
public class ScenarioSuiteWithHooks {
    @CourgetteBeforeAll
    public static void setUp() {
        System.out.println("I will run before any tests execute");
    }

    @CourgetteAfterAll
    public static void tearDown() {
        System.out.println("I will run after all of the tests execute");
    }
}