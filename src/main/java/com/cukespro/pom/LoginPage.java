package com.cukespro.pom;

import com.cukespro.utils.ObjectRepository;
import com.microsoft.playwright.Page;

public class LoginPage {

	Page page;
	private ObjectRepository repo;

	public LoginPage(Page page) throws Exception {
		this.page = page;
		repo = new ObjectRepository("repo.json", this.getClass().getSimpleName().toString());
	}
	
	public LoginPage navigateToLoginPage() {
		page.navigate("https://katalon-demo-cura.herokuapp.com/profile.php#login");
		return this;
	}
	
	public LoginPage Logout() {
		page.click(repo.getSelectorFor("btnMenu"));
		page.click(repo.getSelectorFor("mnuLogout"));
		return this;
	}
	
	public LoginPage clickLogin() {
		page.click(repo.getSelectorFor("btnMenu"));
		page.click(repo.getSelectorFor("mnuLogin"));
		return this;
	}
	
	public LoginPage clickMakeAppointment() {
		page.click(repo.getSelectorFor("btnMakeAppointment"));
		return this;
	}

	public LoginPage enterUsername(String username) {
		page.type(repo.getSelectorFor("txtUsername"), username);
		return this;
	}

	public LoginPage enterPassword(String password) {
		page.type(repo.getSelectorFor("txtPassword"), password);
		return this;
	}

	public LoginPage clickLoginButton() {
		page.click(repo.getSelectorFor("btnLogin"));
		return this;
	}

	public LoginPage verifyErrorMessage() {
		page.locator(repo.getSelectorFor("paraErrorMessage")).innerText()
				.equals("Login failed! Please ensure the username and password are valid");
		return this;
	}
}
