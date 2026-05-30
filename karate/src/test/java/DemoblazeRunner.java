


import com.intuit.karate.junit5.Karate;
import org.junit.jupiter.api.Tag;

/**
 * Main Karate test runner.
 * Executes all feature files under src/test/resources/demoblaze.
 *
 * Run via Gradle:
 *   ./gradlew test
 *   ./gradlew test -Dkarate.env=dev
 *   ./gradlew test --tests "demoblaze.DemoblazeRunner"
 */
public class DemoblazeRunner {

    /**
     * Runs ALL feature files for the project.
     * Reports are generated under build/reports/karate/karate-summary.html
     */
    @Karate.Test
    Karate testAll() {
        return Karate.run("classpath:demoblaze")
                .relativeTo(getClass())
                .outputHtmlReport(true)
                .outputCucumberJson(true)
                .outputJunitXml(true);
    }

    /**
     * Runs only the Signup feature.
     */
    @Karate.Test
    Karate testSignup() {
        return Karate.run("classpath:demoblaze/signup.feature")
                .relativeTo(getClass());
    }

    /**
     * Runs only the Login feature.
     */
    @Karate.Test
    Karate testLogin() {
        return Karate.run("classpath:demoblaze/login.feature")
                .relativeTo(getClass());
    }
}
