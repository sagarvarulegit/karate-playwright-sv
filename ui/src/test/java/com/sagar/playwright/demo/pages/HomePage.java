package com.sagar.playwright.demo.pages;

import com.microsoft.playwright.Page;

public class HomePage {

    private final Page page;

    public HomePage(Page page) {
        this.page = page;
    }

    public void navigate() {
        page.navigate("https://playwright.dev/");
    }

    public String getTitle() {
        return page.title();
    }
}
