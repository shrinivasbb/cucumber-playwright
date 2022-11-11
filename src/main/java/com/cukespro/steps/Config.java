package com.cukespro.steps;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import com.cukespro.pom.AppointmentPage;
import com.cukespro.pom.ConfirmationPage;
import com.cukespro.pom.LoginPage;
import com.cukespro.utils.BrowserUtils;
import com.cukespro.utils.PropertiesReader;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Tracing;
import com.microsoft.playwright.options.LoadState;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;

/**
 * @author Shrinivas
 *
 */
@SuppressWarnings({"serial","unused"})
public class Config {

	/**
	 * Private variables defined to generate session files for preserving browser session
	 */
	private static String loginuri = "/profile.php#login";
	private static String homeuri = "/#appointment";
	private static String txtUserName = "#txt-username";
	private static String txtPassword = "#txt-password";
	private static String btnLogin = "#btn-login";
	
	
	/**
	 * Page Object reference variables 
	 */
	public Page page;
	public LoginPage loginPage;
	public AppointmentPage appointmentPage;
	public ConfirmationPage confirmationPage;

	/**
	 * Before is executed before every scenario.
	 * 
	 * @throws Exception
	 */
	@Before
	public void i_open_the_browser() throws Exception {
		Page newpage;
		newpage = System.getProperty("browser") == null ? new BrowserUtils().startBrowserUsingSessionFile("chrome")
				: new BrowserUtils().startBrowserUsingSessionFile(System.getProperty("browser"));
		newpage.navigate(new PropertiesReader().readProperties().getProperty("baseurl") + homeuri);
		newpage.waitForLoadState(LoadState.NETWORKIDLE);
		this.page = newpage;
		page.context().tracing().start(new Tracing.StartOptions().setScreenshots(true).setSnapshots(true));
		appointmentPage = new AppointmentPage(page);
		confirmationPage = new ConfirmationPage(page);
		loginPage = new LoginPage(page);
	}

	/**
	 * Before All is executed only once before test suite execution.
	 * 
	 * @throws Exception
	 */
	@BeforeAll
	public static void beforeAll() throws Exception {
		Page page;
		String dirPath = System.getProperty("user.dir") + File.separator + "traces" + File.separator;
		Path path = Paths.get(dirPath);
		Path createdPath = Files.exists(path)==false?Files.createDirectory(path):null;
		page = System.getProperty("browser") == null ? new BrowserUtils().startHeadlessBrowser("chrome")
				: new BrowserUtils().startHeadlessBrowser(System.getProperty("browser"));
		page.navigate(new PropertiesReader().readProperties().getProperty("baseurl") + loginuri);
		page.waitForLoadState(LoadState.DOMCONTENTLOADED);
		page.type(txtUserName, "John Doe");
		page.type(txtPassword, "ThisIsNotAPassword");
		page.click(btnLogin);
		if (System.getProperty("browser") == null) {
			page.context().storageState(new BrowserContext.StorageStateOptions()
					.setPath(Paths.get(System.getProperty("user.dir") + File.separator + "chromeauth.json")));
		} else {
			page.context().storageState(new BrowserContext.StorageStateOptions().setPath(Paths.get(
					System.getProperty("user.dir") + File.separator + System.getProperty("browser") + "auth.json")));
		}
		page.context().browser().close();
	}

	/**
	 * After All is executed only once after test suite execution.
	 * 
	 * @throws Exception
	 */
	@AfterAll
	public static void AfterAll() {
		List<File> lstFile = new ArrayList<File>() {
			{
				add(new File(System.getProperty("user.dir") + File.separator + "chromeauth.json"));
				add(new File(System.getProperty("user.dir") + File.separator + "chromiumauth.json"));
				add(new File(System.getProperty("user.dir") + File.separator + "edgeauth.json"));
				add(new File(System.getProperty("user.dir") + File.separator + "firefoxauth.json"));
				add(new File(System.getProperty("user.dir") + File.separator + "safariauth.json"));
			}
		};
		lstFile.stream().forEach(file -> {
			if (file.exists()) {
				file.delete();
			}
		});
	}

	/**
	 * Before is executed After every scenario.
	 * 
	 * @throws Exception
	 */
	@After
	public void close_browser() throws Exception {
		String filePath = System.getProperty("user.dir") + File.separator + "traces" + File.separator + "trace"
				+ System.currentTimeMillis() + ".zip";
		new File(filePath);
		page.context().tracing().stop(new Tracing.StopOptions().setPath(Paths.get(filePath)));
		page.context().browser().close();
	}

}
