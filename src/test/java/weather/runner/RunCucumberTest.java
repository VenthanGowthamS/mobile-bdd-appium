package weather.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * Entry point for "mvn test". Runs every scenario under src/test/resources/features.
 * Pick a subset with: mvn test -Dcucumber.filter.tags="@smoke"
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features",
        glue = {"weather.steps"},
        plugin = {"pretty", "html:target/cucumber-report.html"}
)
public class RunCucumberTest {
}
