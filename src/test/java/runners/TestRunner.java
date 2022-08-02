package runners;

import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;
import io.cucumber.testng.AbstractTestNGCucumberTests;



@CucumberOptions(plugin= {"json:target/jsonReports/cucumber.json"},
        features = {"classpath:features"},
        glue = {"classpath:stepdefinition"})

public class TestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

}
