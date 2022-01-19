package com.gmail.testrunner;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(snippets = SnippetType.CAMELCASE, 
		features = "resources/Features/Gmail/VerifyComposeFunction.feature"
		,glue={"com.gmail.stepdefinitions"},
		plugin = { "pretty" }
		)


public class TestRunnerGmail{
}
