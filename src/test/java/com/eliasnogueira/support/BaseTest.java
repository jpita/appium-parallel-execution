package com.eliasnogueira.support;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.File;
import java.net.URL;

public class BaseTest {

    public AppiumDriver<?> driver = null;

    @BeforeMethod(alwaysRun = true)
    @Parameters( { "platform", "udid", "platformVersion"})
    public void beforeTest(@Optional("android") String platform, @Optional("emulator-5554") String udid, @Optional("9") String platformVersion) throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability("--session-override",true);
        String url = "http://localhost:4444/wd/hub";
//        String url = "http://0.0.0.0:4723/wd/hub";
        switch (platform.toLowerCase()) {
            case "ios":
                capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "411025BC-3F30-4967-8EE7-5C42B5CF8AEF");
                capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
                capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
                capabilities.setCapability(MobileCapabilityType.APP, "/Users/pita/appium-parallel-execution/app/FasTip.app");
                driver = new IOSDriver<MobileElement>(new URL(url), capabilities);
                break;

            case "android":
                capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, udid);
                capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
                capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
//                capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.APPIUM);

                if (Boolean.parseBoolean(Utils.readProperty("install.app"))) {
                    capabilities.setCapability(MobileCapabilityType.APP, new File(Utils.readProperty("app.android.path")).getAbsolutePath());
                } else {
                    capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, Utils.readProperty("app.android.appPackage"));
                    capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, Utils.readProperty("app.android.appActivity"));
                }

                driver = new AndroidDriver<MobileElement>(new URL(url), capabilities);
                break;

            default:
                throw new Exception("Platform not supported! Check if you set ios or android on the parameter.");
        }
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
