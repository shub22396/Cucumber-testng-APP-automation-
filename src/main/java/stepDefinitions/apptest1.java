package stepDefinitions;


import org.openqa.selenium.By;

import org.testng.Assert;

import MyRunner.AppRunner;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.net.URL;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class apptest1 extends AppRunner {

    public AndroidDriver driver = this.connection;
    public String Status="failed";

    @Given("^user is on home Page$")
    public void user_already_on_home_page() {
        try{
        System.out.println(driver.getCapabilities());
        driver.findElement(MobileBy.AccessibilityId("Search Wikipedia")).click();
        driver.findElement(MobileBy.id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Cristiano Ronaldo");
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
            Status = "passed";
            driver.executeScript("lambda-status=" + Status);
            driver.quit();



    } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }








}
