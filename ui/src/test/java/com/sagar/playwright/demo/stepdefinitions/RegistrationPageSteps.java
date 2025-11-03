package com.sagar.playwright.demo.stepdefinitions;

import com.sagar.playwright.demo.pages.LoginPage;
import com.sagar.playwright.demo.pages.RegistrationPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.yaml.snakeyaml.Yaml;
import com.sagar.playwright.demo.testdata.RegistrationData;

import java.io.InputStream;
import java.util.Properties;


public class RegistrationPageSteps {

    private TestContext testContext;
    private LoginPage loginPage;
    private RegistrationPage registrationPage;
    private final Properties testProps = new Properties();
    private RegistrationData testData;

    public RegistrationPageSteps(TestContext context) {
        testContext = context;
        loginPage = new LoginPage(testContext.page);
        registrationPage = new RegistrationPage(testContext.page);
        loadProperties();
        loadTestDataTyped();
    }

    @Given("I am on the registration page")
    public void iAmOnTheRegistrationPage() {
        String baseUrl = System.getProperty("baseUrl", testProps.getProperty("baseUrl"));
        testContext.page.navigate(baseUrl + "/login");
    }

    @When("I enter my name and email address")
    public void iEnterMyNameAndEmailAddress() {
        String name = testData.getName();
        String emailAddress = testData.getEmail_prefix() + System.currentTimeMillis() + testData.getEmail_domain();
        loginPage.enterNameAndEmail(name, emailAddress);
        loginPage.clickSignup();
    }

    @When("I fill in the registration form")
    public void iFillInTheRegistrationForm() {
        registrationPage.selectTitle();
        registrationPage.enterPassword(testData.getPassword());
        registrationPage.selectDateOfBirth(
                testData.getDob().getDay(),
                testData.getDob().getMonth(),
                testData.getDob().getYear()
        );
        registrationPage.enterAddressInformation(
                testData.getAddress().getFirst_name(),
                testData.getAddress().getLast_name(),
                testData.getAddress().getStreet(),
                testData.getAddress().getCountry(),
                testData.getAddress().getState(),
                testData.getAddress().getCity(),
                testData.getAddress().getZip(),
                testData.getAddress().getPhone()
        );
    }

    @When("I submit the registration form")
    public void iSubmitTheRegistrationForm() {
        registrationPage.clickCreateAccount();
    }

    @Then("I should see a confirmation message")
    public void iShouldSeeAConfirmationMessage() {
        assertEquals("ACCOUNT CREATED!".toLowerCase(), registrationPage.getAccountCreatedTitle().toLowerCase());
    }

    private void loadProperties() {
        try (InputStream in = getClass().getClassLoader().getResourceAsStream("test.properties")) {
            if (in != null) {
                testProps.load(in);
            } else {
                throw new RuntimeException("test.properties not found on classpath");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load test.properties", e);
        }
    }

    private void loadTestDataTyped() {
        try (InputStream in = getClass().getClassLoader().getResourceAsStream("testdata/registration.yaml")) {
            if (in != null) {
                Yaml yaml = new Yaml();
                testData = yaml.loadAs(in, RegistrationData.class);
            } else {
                throw new RuntimeException("registration.yaml not found on classpath");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load registration.yaml", e);
        }
    }
}
