package com.sagar.playwright.demo.stepdefinitions;

import com.sagar.playwright.demo.pages.HomePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HomePageSteps {

    private final HomePage homePage;

    public HomePageSteps(TestContext testContext) {
        this.homePage = new HomePage(testContext.page);
    }

    @Given("I am on the Playwright home page")
    public void i_am_on_the_playwright_home_page() {
        homePage.navigate();
    }

    @When("I check the title of the page")
    public void i_check_the_title_of_the_page() {
        // This step is intentionally left blank as the title is checked in the Then step
    }

    @Then("the title should be {string}")
    public void the_title_should_be(String expectedTitle) {
        assertEquals(expectedTitle, homePage.getTitle());
    }
}
