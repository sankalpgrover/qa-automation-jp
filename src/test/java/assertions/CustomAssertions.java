package assertions;

import io.cucumber.java.Scenario;
import org.testng.Assert;

/**
 * The type Assertions.
 */
public class CustomAssertions {

    /**
     * Assert equals.
     *
     * @param expectedString the expected string
     * @param actualString   the actual string
     * @param scenario       the scenario
     */
    public void assertEquals( String expectedString, String actualString, Scenario scenario) {
        try {
            if (expectedString == null || actualString == null || expectedString.equals("") || actualString.equals("")) {
                scenario.log("Either expected/actual values are Null OR Empty");
            }
            scenario.log("Expected: "+expectedString);
            scenario.log("Actual: "+actualString);
            Assert.assertTrue(expectedString.equals(actualString));
        } catch (AssertionError e) {
        }
    }

    /**
     * Assert true.
     *
     * @param expectedVal the expected val
     * @param actualVal   the actual val
     * @param scenario    the scenario
     */
    public void assertTrue(Integer expectedVal, Integer actualVal, Scenario scenario) {
            if (expectedVal == null || actualVal == null) {
                scenario.log("Either expected/actual values are Null OR Empty");
            }
            scenario.log("Expected: "+expectedVal);
            scenario.log("Actual: "+actualVal);
            Assert.assertTrue(expectedVal.equals(actualVal));

    }
}
