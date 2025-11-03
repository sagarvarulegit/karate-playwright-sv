package com.sagar.playwright.demo.stepdefinitions;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Playwright;
import com.sagar.playwright.demo.pages.LoginPage;
import com.sagar.playwright.demo.pages.RegistrationPage;
import com.sagar.playwright.demo.testdata.RegistrationData;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Properties;

public class Hooks {

    private Playwright playwright;
    private Browser browser;
    private final TestContext testContext;

    public Hooks(TestContext testContext) {
        this.testContext = testContext;
    }

    @Before(order = 1)
    public void setUp() {
        playwright = Playwright.create();
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "true"));
        String browserType = System.getProperty("browserType", "chromium");
        if(browserType.equals("chromium")){
            browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(headless));
        }
        else if(browserType.equals("firefox")){
            browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(headless));
        }
        else if(browserType.equals("webkit")){
            browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(headless));
        }
        
        BrowserContext context = browser.newContext();
        testContext.page = context.newPage();
    }

    @Before(value = "@CreateUserFirst", order = 2)
    public void createUserBeforeLogin() {
        // Load test data and properties
        Properties testProps = new Properties();
        RegistrationData testData;
        
        try (InputStream propsIn = getClass().getClassLoader().getResourceAsStream("test.properties")) {
            testProps.load(propsIn);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load test.properties", e);
        }
        
        try (InputStream yamlIn = getClass().getClassLoader().getResourceAsStream("testdata/registration.yaml")) {
            Yaml yaml = new Yaml();
            testData = yaml.loadAs(yamlIn, RegistrationData.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load registration.yaml", e);
        }
        
        // Register user
        String baseUrl = System.getProperty("baseUrl", testProps.getProperty("baseUrl"));
        String email = testData.getEmail_prefix() + System.currentTimeMillis() + testData.getEmail_domain();
        String password = testData.getPassword();
        
        // Store in context for login test
        testContext.registeredEmail = email;
        testContext.registeredPassword = password;
        
        // Navigate and register
        testContext.page.navigate(baseUrl + "/login");
        LoginPage loginPage = new LoginPage(testContext.page);
        loginPage.enterNameAndEmail(testData.getName(), email);
        loginPage.clickSignup();
        
        RegistrationPage registrationPage = new RegistrationPage(testContext.page);
        registrationPage.selectTitle();
        registrationPage.enterPassword(password);
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
        registrationPage.clickCreateAccount();
        
        testContext.page.locator("text=Account Created!").waitFor(new Locator.WaitForOptions().setTimeout(5000));
        
        registrationPage.clickContinue();
        
        loginPage.logout();
    }

    @After
    public void tearDown() {
        if (testContext.page != null) {
            testContext.page.close();
        }
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }
}
