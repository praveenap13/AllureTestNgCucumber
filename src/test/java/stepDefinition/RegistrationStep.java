package stepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.RegistrationPage;
import utility.DriverFactory;


public class RegistrationStep {
  private  WebDriver driver=DriverFactory.getDriver();
    private RegistrationPage registrationPage;

    @Given("I on login page")
    public void i_on_login_page() {
        registrationPage=new RegistrationPage(DriverFactory.getDriver());

        String title = DriverFactory.getDriver().getTitle();

        Assert.assertEquals(title, "Automation Exercise");
        registrationPage.clickOnLoginLink();
    }
    @When("I enter {string} and {string} and click on login button")
    public void i_enter_and_and_click_on_login_button(String email, String password) {
        registrationPage.enterLoginEmail(email);
        //registrationPage.enterPassword(password);
        registrationPage.clickOnLoginButton();
    }
    @Then("I am able to logged in successfully")
    public void i_am_able_to_logged_in_successfully() {

    }

    @When("I enter {string} and {string} and click on SignUp button")
    public void i_enter_and_and_click_on_sign_up_button(String name, String email) {

      registrationPage.enterName(name);
      registrationPage.enterEmail(email);
      registrationPage.clickSignUpButton();

    }

    @Then("I redirected to registartion page")
    public void i_redirected_to_registartion_page() {
    registrationPage.verifySignupPageTitle();
  //  ExtentCucumberAdapter.log(ExtentCucumberAdapter.STATUS.INFO,"Entered valid username & password");

    }


}
