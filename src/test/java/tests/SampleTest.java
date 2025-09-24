package tests;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.*;
import utility.DriverFactory;

public class SampleTest {

    WebDriver driver;

    @BeforeMethod  //4 browser
    @BeforeTest  // 2 browser
    @Parameters({"browser"})
    public void setup(String browser){
        System.out.println("BeforeTest: Starting browser => " + browser);
        DriverFactory.intializeDriver(browser);
        System.out.println("Started " + browser + " on thread: " + Thread.currentThread().getId());


    }
    @Test
    public void testOne() {
        System.out.println("Running testOne on " + driver);
        WebDriver driver = DriverFactory.getDriver();
        driver.get("https://www.google.com");
        System.out.println("TestOne running on: " + driver + " | Thread: " + Thread.currentThread().getId());
    }

    @Test
    public void testTwo() {
        WebDriver driver = DriverFactory.getDriver();
        driver.get("https://www.bing.com");
        System.out.println("TestTwo running on: " + driver + " | Thread: " + Thread.currentThread().getId());
    }

//    @AfterTest
//    public void tearDown(){
//        DriverFactory.quitDriver();
//        System.out.println("Closed browser on thread: " + Thread.currentThread().getId());
//
//    }

}

