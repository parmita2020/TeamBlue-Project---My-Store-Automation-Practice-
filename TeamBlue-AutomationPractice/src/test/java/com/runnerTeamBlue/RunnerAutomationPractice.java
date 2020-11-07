package com.runnerTeamBlue;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith (Cucumber.class)
@CucumberOptions(
		features = "src/test/resources",
		glue = {"com.StepDefsTeamBlue"},
		tags = {"@TeamBlue"},
		plugin = {"com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html"},
		monochrome = true
		)


public class RunnerAutomationPractice {

}
