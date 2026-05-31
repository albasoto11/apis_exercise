package runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

/**
 * Cucumber test runner with Serenity BDD integration using SerenityRunner.
 * This ensures proper initialization of the StepEventBus.
 */
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "stepDefinition",
        plugin = {"pretty"},
        tags = "not @wip"
)
public class runnerTest {
}