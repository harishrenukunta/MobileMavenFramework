package org.mobile.pageobjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.mobile.utils.PropertyUtils;
import org.mobile.utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public abstract class BasePO {

    protected AppiumDriver<MobileElement> driver;
    private int IMPLICIT_WAIT;
    protected WaitUtils waitUtils;

    public BasePO(){
       initBasePO();
    }

    private void initBasePO(){
        IMPLICIT_WAIT = PropertyUtils.getIntegerProperty("implicit.wait", 3);
        waitUtils = new WaitUtils();
    }

    public BasePO(final AppiumDriver driver){
        this.driver = driver;
        initBasePO();
    }

    public void setDriver(final WebDriver driver){
        this.driver = (AppiumDriver)driver;
    }

    /**
     * This method is used to initialize mobile elements present on mobile screen
     */
    public void initElements(){
        PageFactory.initElements(new AppiumFieldDecorator(driver , Duration.ofSeconds(IMPLICIT_WAIT )), this);
    }
}
