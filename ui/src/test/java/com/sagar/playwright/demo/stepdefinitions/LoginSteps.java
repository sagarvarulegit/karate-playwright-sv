package com.sagar.playwright.demo.stepdefinitions;

import com.sagar.playwright.demo.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Properties;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginSteps {

    private TestContext testContext;
    private LoginPage loginPage;
    private final Properties testProps = new Properties();

    public LoginSteps(TestContext context) {
        testContext = context;
        loginPage = new LoginPage(testContext.page);
        loadProperties();
    }

    @Given("I am on the login page")
    public void iAmOnTheLoginPage() {
        String baseUrl = System.getProperty("baseUrl", testProps.getProperty("baseUrl"));
        testContext.page.navigate(baseUrl + "/login");
    }

    @When("I enter the registered email and password")
    public void iEnterTheRegisteredEmailAndPassword() {
        loginPage.enterLoginCredentials(testContext.registeredEmail, testContext.registeredPassword);
    }

    @When("I click the login button")
    public void iClickTheLoginButton() {
        loginPage.clickLogin();
    }

    @Then("I should be logged in successfully")
    public void iShouldBeLoggedInSuccessfully() {
        assertTrue(loginPage.isLoggedIn(), "User should be logged in");
    }

    @When("I logout from the application")
    public void iLogoutFromTheApplication() {
        loginPage.logout();
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
}
