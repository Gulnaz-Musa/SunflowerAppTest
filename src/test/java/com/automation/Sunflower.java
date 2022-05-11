package com.automation;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Sunflower {

    AppiumDriver<AndroidElement> driver;
    //WebDriverWait wait = new WebDriverWait(driver,10);

    @Before
    public void setup() throws Exception {

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
        //desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "7.0");
        //desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Sunflower");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        desiredCapabilities.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + "/app-debug.apk");
        // app will be automatically installed on your device before tese execution

        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver<>(url, desiredCapabilities);

    }

    @Test
    public void verifyGardenIsEmpty1stLaunch() throws InterruptedException {

        //{ This method is used to verify the text Your garden is empty & add plant button.}
        String yourGardenEmpty = driver.findElement(MobileBy.id("com.google.samples.apps.sunflower:id/empty_garden")).getText();
        Thread.sleep(3000);
        Assert.assertEquals("Your garden is empty", yourGardenEmpty);
        driver.findElement(MobileBy.id("com.google.samples.apps.sunflower:id/add_plant")).isDisplayed();
        System.out.println("test case pass - Your garden is empty is displayed");

    }

    @Test
    public void verifyPlantshareBtn() throws InterruptedException {

        //{ This method is used to click Plant List on the landing page.}
        driver.findElementByXPath("//android.widget.LinearLayout[@content-desc=\"Plant list\"]").click();
        Thread.sleep(2000);

        //{ This dynamic method is used to verify the Share button on any type of plant  page.}
        String plantName = "Apple";
        driver.findElementByXPath("//android.widget.TextView[@text='" + plantName + "']").click();
        Thread.sleep(2000);
        driver.findElementByAccessibilityId("Share").isDisplayed();
        System.out.println("test case pass - Share button of plant is displayed");

    }

    @Test
    public void verifyPlantHeader() throws InterruptedException {

        //{ These methods are used to verify any plant header.}
        driver.findElementByXPath("//android.widget.LinearLayout[@content-desc=\"Plant list\"]").click();
        Thread.sleep(2000);

        String plantName = "Beet";
        driver.findElementByXPath("//android.widget.TextView[@text='" + plantName + "']").click();
        Thread.sleep(2000);

        String actualFruitHeader = driver.findElement(MobileBy.id("com.google.samples.apps.sunflower:id/plant_detail_name")).getText();
        Thread.sleep(2000);
        Assert.assertEquals(plantName, actualFruitHeader);
        System.out.println("test case pass - Plant header " + plantName + " is displayed");
    }

    @Test
    public void verifyAddingPlant() throws InterruptedException {

        driver.findElementByXPath("//android.widget.LinearLayout[@content-desc=\"Plant list\"]").click();
        Thread.sleep(2000);

        String plantName = "Beet";
        driver.findElementByXPath("//android.widget.TextView[@text='" + plantName + "']").click();
        Thread.sleep(2000);

        //{ This method click the + icon to add plant}
        driver.findElement(MobileBy.id("com.google.samples.apps.sunflower:id/fab")).click();
        Thread.sleep(2000);

        //{<-- go back arrow button
        driver.findElement(MobileBy.className("android.widget.ImageButton")).click();
        Thread.sleep(2000);

        driver.findElementByXPath("//android.widget.TextView[@text='MY GARDEN']").click();
        Thread.sleep(2000);

        //{ This method is used to verify the added plant name is displayed  on the MY GARDEN page.}
        driver.findElementByXPath("//android.widget.TextView[@text='" + plantName + "']").isDisplayed();
        Thread.sleep(2000);
        System.out.println("test case pass - plant name " + plantName + " on my garden is displayed ");
    }

    @Test
    public void verifyPlantAddedDate() throws InterruptedException {
        driver.findElementByXPath("//android.widget.LinearLayout[@content-desc=\"Plant list\"]").click();
        Thread.sleep(2000);

        String plantName = "Apple";
        driver.findElementByXPath("//android.widget.TextView[@text='" + plantName + "']").click();
        Thread.sleep(2000);

        //{ This method click the + icon to add plant}
        driver.findElement(MobileBy.id("com.google.samples.apps.sunflower:id/fab")).click();
        Thread.sleep(2000);
        
        //{<-- go back arrow button
        driver.findElement(MobileBy.className("android.widget.ImageButton")).click();
        Thread.sleep(2000);

        driver.findElementByXPath("//android.widget.TextView[@text='MY GARDEN']").click();
        Thread.sleep(2000);


        //{ This method is used to verify  date and time that plant is added}
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat simpleformat = new SimpleDateFormat("MMM dd, yyyy");
        System.out.println("Current Date = " + simpleformat.format(cal.getTime()));
        String currentDate = simpleformat.format(cal.getTime());

        driver.findElementByXPath("(//android.widget.TextView[@text='" + currentDate + "'])[1]").isDisplayed();
        Thread.sleep(2000);
        System.out.println("test case pass - Plant adding date  " + currentDate + " is displayed");

    }

    @After
    public void tearDown() {
        driver.closeApp();
        driver.quit();

    }

}
