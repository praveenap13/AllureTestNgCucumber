package stepDefinition;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import utility.ConfigProperties;
import utility.DriverFactory;

import java.io.ByteArrayInputStream;
import java.time.Duration;

public class Hook {

        @Before(order=0)
        public void setUp( ) {
//            // Get browser from system property (passed via Maven/TestNG XML)
//           String  browser = System.getProperty("browser");
//            DriverFactory.intializeDriver(browser);

            // Load config.properties
           // ConfigProperties.initializePropertyFile();


            // Get browser and url from config file
            String browser = ConfigProperties.property.get().getProperty("browser");
            String url = ConfigProperties.property.get().getProperty("url");
            long implicitWait = Integer.parseInt(ConfigProperties.property.get().getProperty("implicitWait"));

            // Initialize driver
            DriverFactory.intializeDriver(browser);

            WebDriver driver = DriverFactory.getDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
            driver.navigate().to(url);  // default URL (can also come from config file)
        }
       @Before(order=1)
        public void applicationLog(Scenario scenario){
        Allure.step("Starting Scenario: " + scenario.getName());
       }
        @After
        public void tearDown(io.cucumber.java.Scenario scenario) {
            WebDriver driver = DriverFactory.getDriver();

            if (scenario.isFailed()) {
                // Attach screenshot to Allure
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                Allure.addAttachment("Failed Screenshot", new ByteArrayInputStream(screenshot));
                Allure.step("Scenario FAILED: " + scenario.getName());
            } else {
                Allure.step("Scenario PASSED: " + scenario.getName());
            }

            DriverFactory.quitDriver();
        }
    }


