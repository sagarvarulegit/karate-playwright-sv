package com.sagar.playwright.demo.pages;

import com.microsoft.playwright.Page;

public class RegistrationPage {

    private Page page;

    private String titleRadioButton = "input[value='Mr']";
    private String passwordInput = "input[data-qa='password']";
    private String daysDropdown = "select[data-qa='days']";
    private String monthsDropdown = "select[data-qa='months']";
    private String yearsDropdown = "select[data-qa='years']";
    private String firstNameInput = "input[data-qa='first_name']";
    private String lastNameInput = "input[data-qa='last_name']";
    private String addressInput = "input[data-qa='address']";
    private String countryDropdown = "select[data-qa='country']";
    private String stateInput = "input[data-qa='state']";
    private String cityInput = "input[data-qa='city']";
    private String zipcodeInput = "input[data-qa='zipcode']";
    private String mobileNumberInput = "input[data-qa='mobile_number']";
    private String createAccountButton = "button[data-qa='create-account']";
    private String accountCreatedTitle = "h2[data-qa='account-created']";

    public RegistrationPage(Page page) {
        this.page = page;
    }

    public void selectTitle() {
        page.click(titleRadioButton);
    }

    public void enterPassword(String password) {
        page.fill(passwordInput, password);
    }

    public void selectDateOfBirth(String day, String month, String year) {
        page.selectOption(daysDropdown, day);
        page.selectOption(monthsDropdown, month);
        page.selectOption(yearsDropdown, year);
    }

    public void enterAddressInformation(String firstName, String lastName, String address, String country, String state, String city, String zipcode, String mobileNumber) {
        page.fill(firstNameInput, firstName);
        page.fill(lastNameInput, lastName);
        page.fill(addressInput, address);
        page.selectOption(countryDropdown, country);
        page.fill(stateInput, state);
        page.fill(cityInput, city);
        page.fill(zipcodeInput, zipcode);
        page.fill(mobileNumberInput, mobileNumber);
    }

    public void clickCreateAccount() {
        page.click(createAccountButton);
    }

    public String getAccountCreatedTitle() {
        return page.textContent(accountCreatedTitle);
    }
}
