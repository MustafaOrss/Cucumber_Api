package runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {

                "html:target/cucumber-reports.html",
                "json:target/json-reports/cucumber.json",
                "junit:target/xml-report/cucumber.xml",

        },
        features = "C:\\Users\\MustafaOrs\\Cucumber_aPI\\src\\test\\resources\\features",
        glue = {"stepdefinitions","hooks"},
        tags = "@api1",

        dryRun = false
)

public class RunnerApi {

}
