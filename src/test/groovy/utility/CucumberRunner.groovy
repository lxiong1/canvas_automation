package utility

import cucumber.api.CucumberOptions
import cucumber.api.junit.Cucumber
import org.junit.runner.RunWith

@CucumberOptions(
        strict=false,
        plugin=["pretty", "html:build/cucumber-html-report", "json:build/cucumber-report.json"],
        features='src/test/groovy/features',
        glue=['step_definitions', 'utility']
)
@RunWith(Cucumber.class)

class CucumberRunner
{

}