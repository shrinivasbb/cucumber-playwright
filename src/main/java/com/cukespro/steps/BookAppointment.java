package com.cukespro.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BookAppointment {
	
	Config config;
	
	public BookAppointment(Config config) {
		this.config=config;
	}

	@Given("I am on Appointment Page")
	public void i_am_on_appointment_page() {
	   config.appointmentPage.verifyAppointmentPage();
	}

	@When("I select facility as {string}")
	public void i_select_facility_as(String facility) {
		config.appointmentPage.selectFacility(facility);
	}

	@When("I set Apply for hospital readmission to {string}")
	public void i_set_apply_for_hospital_readmission_to(String readmit) {
		config.appointmentPage.selectReadmission(Boolean.parseBoolean(readmit));
	}

	@When("I select Healthcare Program as {string}")
	public void i_select_healthcare_program_as(String program) {
	    config.appointmentPage.selectProgram(program);
	}

	@When("I select Visit Date as {string}")
	public void i_select_visit_date_as(String newDate) {
	    config.appointmentPage.selectVisitDate(newDate);
	}

	@When("I enter comments as {string}")
	public void i_enter_comments_as(String comments) {
		config.appointmentPage.enterComments(comments);
	}

	@Then("I click on Book Appointment button")
	public void i_click_on_book_appointment_button() {
		config.appointmentPage.clickBookAppointment();
	}

	
}
