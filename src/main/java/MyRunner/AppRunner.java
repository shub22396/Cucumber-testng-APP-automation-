package MyRunner;

import java.net.URL;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;

@CucumberOptions(
        features = "src/main/java/Features/App1.feature",
        glue = {"stepDefinitions"},
        tags = {"~@Ignore"},
        format = {
                "pretty",
                "html:target/cucumber-reports/cucumber-pretty",
                "json:target/cucumber-reports/CucumberTestReport.json",
                "rerun:target/cucumber-reports/rerun.txt"
        },plugin = "json:target/cucumber-reports/CucumberTestReport.json")

public class AppRunner {

    private TestNGCucumberRunner testNGCucumberRunner;

    public static AndroidDriver<AndroidElement> connection;

    @BeforeClass(alwaysRun = true)
    public void setUpCucumber() {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @BeforeMethod(alwaysRun = true)
    @Parameters({ "platformName", "platformVersion", "deviceName" })
    public void setUpClass(String platformName, String platformVersion, String deviceName) throws Exception {

        String username = System.getenv("LT_USERNAME") == null ? "YOUR LT_USERNAME" : System.getenv("LT_USERNAME");
        String accesskey = System.getenv("LT_ACCESS_KEY") == null ? "YOUR LT_ACCESS_KEY" : System.getenv("LT_ACCESS_KEY");

        DesiredCapabilities capability = new DesiredCapabilities();
        capability.setCapability("platformName", platformName);
        capability.setCapability("platformVersion",platformVersion);
        capability.setCapability("deviceName", deviceName);

        capability.setCapability("build", "Cucumber Sample");
        capability.setCapability("isRealMobile", true);
        capability.setCapability("network", true);
        capability.setCapability("video", true);
        capability.setCapability("console", true);
        capability.setCapability("app", "lt://APP10020921644598251044639");
        capability.setCapability("visual", true);

        capability.setCapability("devicelog", true);



        String gridURL = "https://" + username + ":" + accesskey + "@beta-hub.lambdatest.com/wd/hub";
        System.out.println(gridURL);
        connection = new AndroidDriver<AndroidElement>(new URL(gridURL), capability);
        System.out.println(capability);
        System.out.println(connection.getSessionId());
    }

    @Test(groups = "cucumber", description = "Runs Cucumber Feature", dataProvider = "features")
    public void feature(CucumberFeatureWrapper cucumberFeature) {
        testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());

    }

    @DataProvider
    public Object[][] features() {
        return testNGCucumberRunner.provideFeatures();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() throws Exception {
        testNGCucumberRunner.finish();
    }
}