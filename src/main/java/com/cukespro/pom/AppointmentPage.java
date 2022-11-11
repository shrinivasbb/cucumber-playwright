package com.cukespro.pom;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.testng.Assert;
import com.cukespro.utils.ObjectRepository;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

@SuppressWarnings("serial")
public class AppointmentPage {

	/**
	 * Reference variable for accessing object repository
	 */
	private ObjectRepository repo;

	/**
	 * Reference variable for Page
	 */
	public Page page;

	/**
	 * Map for accessing calendar using numbers vs months
	 */
	public Map<Integer, String> monthMap = new HashMap<Integer, String>() {
		{
			put(1, "Jan");
			put(2, "Feb");
			put(3, "Mar");
			put(4, "Apr");
			put(5, "May");
			put(6, "Jun");
			put(7, "Jul");
			put(8, "Aug");
			put(9, "Sep");
			put(10, "Oct");
			put(11, "Nov");
			put(12, "Dec");
		}
	};

	public AppointmentPage(Page page) throws Exception {
		this.page = page;
		repo = new ObjectRepository("repo.json", this.getClass().getSimpleName().toString());
	}

	public void verifyAppointmentPage() {
		Assert.assertTrue(page.locator(repo.getSelectorFor("header")).isVisible());
	}

	public AppointmentPage selectFacility(String option) {
		page.selectOption(repo.getSelectorFor("ddlFacility"), option);
		return this;
	}

	public AppointmentPage selectReadmission(Boolean boolVal) {
		if (boolVal == true) {
			page.check(repo.getSelectorFor("chkReadmission"));
		}
		return this;
	}

	public AppointmentPage selectProgram(String strVal) {
		if (strVal.equalsIgnoreCase("Medicaid")) {
			page.click(repo.getSelectorFor("rdbMedicaid"));
		} else if (strVal.equalsIgnoreCase("Medicare")) {
			page.click(repo.getSelectorFor("rdbMedicare"));
		} else if (strVal.equalsIgnoreCase("none")) {
			page.click(repo.getSelectorFor("rdbNone"));
		}
		return this;
	}

	public AppointmentPage selectVisitDate(String Dateddmmyyyy) {
		if (Dateddmmyyyy != null) {
			String[] dateSplit = Dateddmmyyyy.split("/");
			page.click(repo.getSelectorFor("datOpenCal"));
			Locator elems = page.locator(repo.getSelectorFor("datMonthYear"));
			List<ElementHandle> locElems = elems.elementHandles();
			locElems.get(0).click();
			List<ElementHandle> prevs = page.locator(repo.getSelectorFor("btnPrev")).elementHandles();
			List<ElementHandle> nexts = page.locator(repo.getSelectorFor("btnNext")).elementHandles();
			while (!locElems.get(1).innerText().toString().equalsIgnoreCase(dateSplit[2].toString())) {
				if (Integer.parseInt(locElems.get(1).innerText().toString()) <= Integer.parseInt(dateSplit[2])) {
					nexts.get(1).click();
				} else if (Integer.parseInt(locElems.get(1).innerText().toString()) >= Integer.parseInt(dateSplit[2])) {
					prevs.get(1).click();
				}
			}
			String month = monthMap.get(Integer.parseInt(dateSplit[1]));
			page.click(repo.getSelectorFor("selMonth").replace("MONTH", month));
			Integer dateInt = Integer.parseInt(dateSplit[0]);
			page.click(repo.getSelectorFor("selDay").replace("DAY", dateInt.toString()));
		}
		return this;
	}

	public AppointmentPage enterComments(String txtComments) {
		page.type(repo.getSelectorFor("txtComment"), txtComments);
		return this;
	}

	public AppointmentPage clickBookAppointment() {
		page.click(repo.getSelectorFor("btnBookAppointment"));
		return this;
	}

}
