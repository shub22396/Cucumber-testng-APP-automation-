//package stepDefinitions;
//
//import MyRunner.AppRunner;
//import org.openqa.selenium.remote.RemoteWebDriver;
//
//import cucumber.api.Scenario;
//import cucumber.api.java.After;
//import cucumber.api.java.Before;
//
//public class Hook extends AppRunner {
//    public RemoteWebDriver driver = this.connection;
//
//    @Before
//    public void updateName(Scenario scenario) throws InterruptedException {
//        Thread.sleep(30);
//        driver.executeScript("lambda-name=" + scenario.getName());
//    }
//
//    @After
//    public void close_the_browser(Scenario scenario) {
//        driver.executeScript("lambda-status=" + (scenario.isFailed() ? "failed" : "passed"));
//        System.out.println(driver.getSessionId());
//
//    }
//
//}