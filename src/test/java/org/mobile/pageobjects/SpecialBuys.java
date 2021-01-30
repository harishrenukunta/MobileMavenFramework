package org.mobile.pageobjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class SpecialBuys extends BasePO {

    private static final By categoriesBy = By.id("de.apptiv.business.android.aldi_uk:id/tab_title");

    public SpecialBuys(final AppiumDriver driver){
        super(driver);
    }

    public List<String> getCategories(){
        List<WebElement> categoriesElements = waitUtils.waitForElementsToBePresent(driver, categoriesBy);
        return categoriesElements.stream()
                .map( cat -> ((MobileElement)cat).getText())
                .collect(toList());
    }
}
