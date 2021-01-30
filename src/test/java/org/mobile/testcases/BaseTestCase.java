package org.mobile.testcases;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.mobile.utils.PropertyUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public abstract class BaseTestCase {
    protected AppiumDriver<MobileElement> driver;

    @BeforeMethod
    public void beforeMethod(){
        DesiredCapabilities cap = new DesiredCapabilities();
        setAndroidDesiredCapabilities(cap);
        URL appiumUrl = null;
        try {
            appiumUrl = new URL("http://127.0.0.1:4723/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver = new AppiumDriver<>(appiumUrl, cap);
    }

    /**
     * This method is used to set desired capabilities for An Android phone
     * @param cap
     */
    protected void setAndroidDesiredCapabilities(final DesiredCapabilities cap){
        final String PLATFORM_NAME = PropertyUtils.getProperty("android.platform.name");
        final String PLATFORM_VERSION = PropertyUtils.getProperty("android.platform.version");
        final String DEVICE_NAME = PropertyUtils.getProperty("android.device.name");
        final String UDID = PropertyUtils.getProperty("android.udid");
        final String APP_ACTIVITY = PropertyUtils.getProperty("android.app.activity");
        final String APP_PACKAGE = PropertyUtils.getProperty("android.app.package");
        final String APP_APK_FILE = PropertyUtils.getProperty("android.apk.file");
        final String NO_RESET = PropertyUtils.getProperty("android.no.reset");
        final String FULL_RESET = PropertyUtils.getProperty("android.full.reset");

        cap.setCapability(MobileCapabilityType.PLATFORM_NAME, PLATFORM_NAME);
        cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, PLATFORM_VERSION);
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, DEVICE_NAME);
        cap.setCapability(MobileCapabilityType.UDID, UDID);
        if(null == APP_APK_FILE){
            cap.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, APP_ACTIVITY);
            cap.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, APP_PACKAGE);
        }else{
            cap.setCapability(MobileCapabilityType.APP, getAPKAbsolutePath(APP_APK_FILE));
        }
        cap.setCapability(MobileCapabilityType.NO_RESET, NO_RESET);
        cap.setCapability(MobileCapabilityType.FULL_RESET, FULL_RESET);
    }

    /**
     * this method is used to return an absolute path of an apk file
     * @param filename - relative path of file
     * @return
     */
    private String getAPKAbsolutePath(final String filename){
        final File apkFile = new File(filename);
        return apkFile.getAbsolutePath();
    }

    @AfterMethod
    public void afterMethod(){
        if(this.driver != null){
            this.driver.quit();
        }
    }
}
