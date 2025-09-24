package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import utility.ConfigProperties;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepDefinition"},
        plugin = {"pretty", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
        monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {
    @BeforeTest
    @Parameters("browser")
    public void defineBrowser(@Optional String browser) {
         ConfigProperties.initializePropertyFile();
        ConfigProperties.property.get().setProperty("browser",browser);

        if(!browser.equals("param-val-not-found")){
            ConfigProperties.property.get().setProperty("browser",browser);
        }
    }


}
