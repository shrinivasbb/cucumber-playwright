package com.cukespro.steps;

import io.cucumber.java.en.When;

public class AppointConfirmationPage {

	Config config;

	public AppointConfirmationPage(Config config) {
		this.config = config;
	}

	@When("Save should be successful")
	public void save_successful() {
		config.confirmationPage.verifyConfirmation();
	}

	@When("Verify Facility is {string}")
	public void VerifyFacility(String facility) {
		config.confirmationPage.verifyFacility(facility);
	}

	@When("Verify ReAdmission is {string}")
	public void verifyReadmission(String admit) {
		config.confirmationPage.verifyReadmission(admit);
	}

	@When("Verify Program is {string}")
	public void verifyProgram(String program) {
		config.confirmationPage.verifyProgram(program);
	}

	@When("Verify Visit Date is {string}")
	public void verifyVisitDate(String date) {
		config.confirmationPage.verifyVisitDate(date);
	}

	@When("Verify comment is {string}")
	public void verifyComment(String comment) {
		config.confirmationPage.verifyComment(comment);
	}

}
