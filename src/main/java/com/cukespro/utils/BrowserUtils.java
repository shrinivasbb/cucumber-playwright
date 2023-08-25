package com.cukespro.utils;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class BrowserUtils {

	public Playwright playwright;
	public Browser browser;
	public BrowserContext context;
	public Page page;
	
	public BrowserUtils() { /* TODO document why this constructor is empty */ }
	

	/**
	 * This method is used to create a session file for the opened browser and application.
	 * 
	 * @param browserName should be a String
	 * @return boolean 
	 * @throws Exception
	 */
//	public Boolean createSessionFile(String browserName) throws Exception {
//		page = startHeadlessBrowser(browserName);
//		Properties properties = new PropertiesReader().readPropertyFile();
//		String url = properties.getProperty("appurl");
//		String email = properties.getProperty("appemail");
//		String password = properties.getProperty("apppassword");
//		playwrightUtils = new PlaywrightUtils(page);
//		if (!new File(System.getenv("user.dir") + File.separator + browserName.toLowerCase() + "auth.json").exists()) {
//			playwrightUtils.navigate(url).fill(userNameSelector, email).fill(passwordSelector, password).click(loginBtnSelector)
//					.waitForLoadState(LoadState.NETWORKIDLE)
//					.storageState(new BrowserContext.StorageStateOptions().setPath(Paths.get(
//							System.getProperty("user.dir") + File.separator + browserName.toLowerCase() + "auth.json")))
//					.closeBrowser();
//			return true;
//		} else if (new File(System.getenv("user.dir") + File.separator + "auth.json").exists()) {
//			new File(System.getenv("user.dir") + File.separator + "auth.json").delete();
//			System.out.println("Session file was already persistant, No new file creation required.");
//			playwrightUtils.navigate(url).fill("#username", email).fill("#password", password).click("#login-btn")
//					.waitForLoadState(LoadState.NETWORKIDLE)
//					.storageState(new BrowserContext.StorageStateOptions().setPath(Paths.get(
//							System.getProperty("user.dir") + File.separator + browserName.toLowerCase() + "auth.json")))
//					.closeBrowser();
//			return true;
//		} else {
//			return false;
//		}
//	}
	
	/**
	 * This method is used to clean up all the created session files.
	 * 
	 */
	public void cleanUpSessionFiles() {
		@SuppressWarnings("serial")
		List<File> lstFile = new ArrayList<File>() {{
			add(new File(System.getProperty("user.dir")+File.separator+"chromeauth.json"));
			add(new File(System.getProperty("user.dir")+File.separator+"chromiumauth.json"));
			add(new File(System.getProperty("user.dir")+File.separator+"edgeauth.json"));
			add(new File(System.getProperty("user.dir")+File.separator+"firefoxauth.json"));
			add(new File(System.getProperty("user.dir")+File.separator+"safariauth.json"));
		}};
		lstFile.stream().forEach(file->{if(file.exists()) {file.delete();}});
	}

	/**
	 * This method is used to create a browser session by providing browser name as argument.
	 * e.g. firefox, safari, chromium, chrome, edge
	 * 
	 * @param browserName should be a String
	 * @return Page Object
	 */
	public Page startBrowser(String browserName) {
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) size.getWidth();
		int height = (int) size.getHeight();
		String browseName = browserName.toLowerCase();
		switch (browseName) {
		case "firefox":
			playwright = Playwright.create();
			browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(true));
			context = browser.newContext(
					new Browser.NewContextOptions().setAcceptDownloads(true).setViewportSize(width, height));
			page = context.newPage();
			return page;
		case "chrome":
			playwright = Playwright.create();
			browser = playwright.chromium()
					.launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(true));
			context = browser.newContext(
					new Browser.NewContextOptions().setAcceptDownloads(true).setViewportSize(width, height));
			page = context.newPage();
			return page;
		case "edge":
			playwright = Playwright.create();
			browser = playwright.chromium()
					.launch(new BrowserType.LaunchOptions().setChannel("msedge").setHeadless(true));
			context = browser.newContext(
					new Browser.NewContextOptions().setAcceptDownloads(true).setViewportSize(width, height));
			page = context.newPage();
			return page;
		case "chromium":
			playwright = Playwright.create();
			browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
			context = browser.newContext(
					new Browser.NewContextOptions().setAcceptDownloads(true).setViewportSize(width, height));
			page = context.newPage();
			return page;
		case "safari":
			playwright = Playwright.create();
			browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(true));
			context = browser.newContext(
					new Browser.NewContextOptions().setAcceptDownloads(true).setViewportSize(width, height));
			page = context.newPage();
			return page;
		default:
			return null;
		}
	}

	
	/**
	 * This method is used to create a browser session using saved session file by providing browser name as argument.
	 * e.g. firefox, safari, chromium, chrome, edge
	 * 
	 * @param browserName should be a String
	 * @return Page Object
	 */
	public Page startBrowserUsingSessionFile(String browserName) {
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) size.getWidth();
		int height = (int) size.getHeight();
		String browseName = browserName.toLowerCase();
		switch (browseName) {
		case "firefox":
			playwright = Playwright.create();
			browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(true));
			context = browser.newContext(new Browser.NewContextOptions().setAcceptDownloads(true)
					.setViewportSize(width, height).setStorageStatePath(
							Path.of(System.getProperty("user.dir") + File.separator + "firefoxauth.json")));
			page = context.newPage();
			return page;
		case "chrome":
			playwright = Playwright.create();
			browser = playwright.chromium()
					.launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(true));
			context = browser.newContext(new Browser.NewContextOptions().setAcceptDownloads(true)
					.setViewportSize(width, height)
					.setStorageStatePath(Path.of(System.getProperty("user.dir") + File.separator + "chromeauth.json")));
			page = context.newPage();
			return page;
		case "edge":
			playwright = Playwright.create();
			browser = playwright.chromium()
					.launch(new BrowserType.LaunchOptions().setChannel("msedge").setHeadless(true));
			context = browser.newContext(new Browser.NewContextOptions().setAcceptDownloads(true)
					.setViewportSize(width, height)
					.setStorageStatePath(Path.of(System.getProperty("user.dir") + File.separator + "edgeauth.json")));
			page = context.newPage();
			return page;
		case "chromium":
			playwright = Playwright.create();
			browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
			context = browser.newContext(new Browser.NewContextOptions().setAcceptDownloads(true)
					.setViewportSize(width, height).setStorageStatePath(
							Path.of(System.getProperty("user.dir") + File.separator + "chromiumauth.json")));
			page = context.newPage();
			return page;
		case "safari":
			playwright = Playwright.create();
			browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(true));
			context = browser.newContext(new Browser.NewContextOptions().setAcceptDownloads(true)
					.setViewportSize(width, height)
					.setStorageStatePath(Path.of(System.getProperty("user.dir") + File.separator + "safariauth.json")));
			page = context.newPage();
			return page;
		default:
			return null;
		}
	}

	/**
	 * This method is used to create a headless browser session by providing browser name as argument.
	 * e.g. firefox, safari, chromium, chrome, edge
	 * 
	 * @param browserName should be a String
	 * @return Page Object
	 */ 	
	public Page startHeadlessBrowser(String browserName) {
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) size.getWidth();
		int height = (int) size.getHeight();
		String browseName = browserName.toLowerCase();
		switch (browseName) {
		case "firefox":
			playwright = Playwright.create();
			browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(true));
			context = browser.newContext(
					new Browser.NewContextOptions().setAcceptDownloads(true).setViewportSize(width, height));
			page = context.newPage();
			return page;
		case "chrome":
			playwright = Playwright.create();
			browser = playwright.chromium()
					.launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(true));
			context = browser.newContext(
					new Browser.NewContextOptions().setAcceptDownloads(true).setViewportSize(width, height));
			page = context.newPage();
			return page;
		case "edge":
			playwright = Playwright.create();
			browser = playwright.chromium()
					.launch(new BrowserType.LaunchOptions().setChannel("msedge").setHeadless(true));
			context = browser.newContext(
					new Browser.NewContextOptions().setAcceptDownloads(true).setViewportSize(width, height));
			page = context.newPage();
			return page;
		case "chromium":
			playwright = Playwright.create();
			browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
			context = browser.newContext(
					new Browser.NewContextOptions().setAcceptDownloads(true).setViewportSize(width, height));
			page = context.newPage();
			return page;
		case "safari":
			playwright = Playwright.create();
			browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(true));
			context = browser.newContext(
					new Browser.NewContextOptions().setAcceptDownloads(true).setViewportSize(width, height));
			page = context.newPage();
			return page;
		default:
			return null;
		}
	}

}
