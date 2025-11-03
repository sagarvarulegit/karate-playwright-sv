package com.sagar.playwright.demo.pages;

import com.microsoft.playwright.Page;

public class LoginPage {

    private Page page;

    // Signup selectors
    private String nameInput = "input[data-qa='signup-name']";
    private String emailInput = "input[data-qa='signup-email']";
    private String signupButton = "button[data-qa='signup-button']";
    
    // Login selectors
    private String loginEmailInput = "input[data-qa='login-email']";
    private String loginPasswordInput = "input[data-qa='login-password']";
    private String loginButton = "button[data-qa='login-button']";

    public LoginPage(Page page) {
        this.page = page;
    }

    // Signup methods
    public void enterNameAndEmail(String name, String email) {
        page.fill(nameInput, name);
        page.fill(emailInput, email);
    }

    public void clickSignup() {
        page.click(signupButton);
    }
    
    // Login methods
    public void enterLoginCredentials(String email, String password) {
        page.fill(loginEmailInput, email);
        page.fill(loginPasswordInput, password);
    }
    
    public void clickLogin() {
        page.click(loginButton);
    }
    
    public boolean isLoggedIn() {
        // Check if user is logged in by looking for logout link or username
        return page.isVisible("text=Logged in as");
    }
    
    public void logout() {
        try {
            if (page.isVisible("a:has-text('Logout')")) {
                page.click("a:has-text('Logout')");
                // Wait for logout to complete
                page.waitForURL("**/login", new com.microsoft.playwright.Page.WaitForURLOptions().setTimeout(5000));
            }
        } catch (Exception e) {
            // Already logged out or on different page, ignore
        }
    }
}
