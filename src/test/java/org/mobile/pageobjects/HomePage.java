package org.mobile.pageobjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.mobile.utils.PageObjectFactory;
import org.openqa.selenium.By;

import java.util.List;

public class HomePage extends BasePO {

    private static final By productCategoryBy = By.id("de.apptiv.business.android.aldi_uk:id/navParent");

    public HomePage(final AppiumDriver driver){
        super(driver);
    }

    private List<MobileElement> getProductCategories(){
        return driver.findElements(productCategoryBy);
    }

    public SpecialBuys gotoSpecialBuys(){
        getProductCategories().get(1).click();
        //return new SpecialBuys(driver);
        return PageObjectFactory.getPage(driver, SpecialBuys.class);
    }
}
