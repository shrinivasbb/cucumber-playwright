package com.cukespro.runner;

import org.testng.annotations.DataProvider;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(monochrome = true, features = { "src/test/resources/features" }, glue = {
		"classpath:com.cukespro.steps" }, plugin = { "pretty", "html:target/cucumber-reports.html",
				"json:target/cucumber-reports/cucumber.json" })
public class TestRunner extends AbstractTestNGCucumberTests {

	@Override
	@DataProvider(parallel = true)
	public Object[][] scenarios() {
		return super.scenarios();
	}
}
