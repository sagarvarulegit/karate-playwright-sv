package com.sagar.playwright.demo.pages;

import com.microsoft.playwright.Page;

public class LoginPage {

    private Page page;

    private String nameInput = "input[data-qa='signup-name']";
    private String emailInput = "input[data-qa='signup-email']";
    private String signupButton = "button[data-qa='signup-button']";

    public LoginPage(Page page) {
        this.page = page;
    }

    public void enterNameAndEmail(String name, String email) {
        page.fill(nameInput, name);
        page.fill(emailInput, email);
    }

    public void clickSignup() {
        page.click(signupButton);
    }
}
