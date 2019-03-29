package io.apitest.example.integrationtest;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by prasantabiswas on 29/06/18.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features"},
        strict = true,
        glue = "io.apitest.example.integrationtest.steps",
        plugin = {"pretty",
                "html:target/reports/cucumber/html",
                "json:target/cucumber.json",
                "usage:target/usage.jsonx",
                "junit:target/junit.xml"}
                )
public class CustomerAppIntegrationTest {

}
