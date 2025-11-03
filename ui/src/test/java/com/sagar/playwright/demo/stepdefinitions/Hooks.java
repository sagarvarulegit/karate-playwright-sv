package com.sagar.playwright.demo.stepdefinitions;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    private Playwright playwright;
    private Browser browser;
    private final TestContext testContext;

    public Hooks(TestContext testContext) {
        this.testContext = testContext;
    }

    @Before
    public void setUp() {
        playwright = Playwright.create();
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "true"));
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(headless));
        BrowserContext context = browser.newContext();
        testContext.page = context.newPage();
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
