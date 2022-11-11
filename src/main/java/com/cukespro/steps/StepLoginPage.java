package com.cukespro.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepLoginPage {

	Config config;
	
	public StepLoginPage(Config config) {
		this.config=config;
	}
	
	@Given("I navigate to Login Page")
	public void i_navigate_to_login_page() {
		config.loginPage.Logout();
		config.loginPage.clickLogin();
		config.loginPage.navigateToLoginPage();
	}

	@When("I enter valid {string} and {string}")
	public void i_enter_valid_username_and_password(String username, String password) {
		config.loginPage.enterUsername(username);
		config.loginPage.enterPassword(password);
	}
	
	@When("I Click on Login button")
	public void i_click_on_login_button() {
		config.loginPage.clickLoginButton();
	}
	
	@Then("Login should be unsuccessful")
	public void login_should_be_successful() {
		config.loginPage.verifyErrorMessage();
	}
	
}
