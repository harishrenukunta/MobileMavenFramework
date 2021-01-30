package org.mobile.pageobjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Optional;

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

    private MobileElement getTabByName(final String tab){
        List<WebElement> categoriesElements = waitUtils.waitForElementsToBePresent(driver, categoriesBy);
        return categoriesElements.stream()
                .filter( cat -> ((MobileElement)cat).getText().equalsIgnoreCase(tab))
                .map(cat -> (MobileElement)cat)
                .findFirst()
                .orElse(null);

    }

    private MobileElement getTabsElement(){
        return (MobileElement)waitUtils.waitForElementToBeVisible(driver, By.id("android:id/tabs"));
    }

    public void goToCategories() {
        MobileElement tabsElement = getTabsElement();
        Optional.of(tabsElement)
                .ifPresent( tab -> {
                    MobileElement categoryElement = tab.findElement(By.xpath("//android.widget.LinearLayout/android.widget.TextView[contains(@text, \"CATEGORIES\")]"));
                    categoryElement.click();
                });
    }
}
