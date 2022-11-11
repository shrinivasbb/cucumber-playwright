package com.cukespro.pom;

import org.testng.Assert;

import com.cukespro.utils.ObjectRepository;
import com.microsoft.playwright.Page;

public class ConfirmationPage {

	Page page;
	private ObjectRepository repo;

	public ConfirmationPage(Page page) throws Exception {
		this.page = page;
		repo = new ObjectRepository("repo.json", this.getClass().getSimpleName());
	}

	public void verifyConfirmation() {
		Assert.assertTrue(page.locator("h2").innerText().equals("Appointment Confirmation"));
	}

	public void verifyFacility(String facility) {
		Assert.assertTrue(page.locator(repo.getSelectorFor("paraFacility")).innerText().equals(facility));
	}

	public void verifyReadmission(String admission) {
		if (Boolean.parseBoolean(admission) == true) {
			Assert.assertTrue(page.locator(repo.getSelectorFor("paraReadmission")).innerText().equals("Yes"));
		}
		else {
			Assert.assertTrue(page.locator(repo.getSelectorFor("paraReadmission")).innerText().equals("No"));
		}
	}

	public void verifyProgram(String program) {
		Assert.assertTrue(page.locator(repo.getSelectorFor("paraProgram")).innerText().equals(program));
	}

	public void verifyVisitDate(String visitDate) {
		Assert.assertTrue(page.locator(repo.getSelectorFor("paraVisitDate")).innerText().equals(visitDate));
	}

	public void verifyComment(String comment) {
		Assert.assertTrue(page.locator(repo.getSelectorFor("paraComment")).innerText().equals(comment));
	}
}
