package testRunners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/Features", // Path to the feature files
        glue = "stepDefinitions" //Defines the path of step definitions

        // features = {"classpath:features"}, //Defines the path of feature files
     //   glue = {"classpath:stepDefinitions","classpath:APITesting"}//Defines the path of step definitions
)
public class RunTest {

}



