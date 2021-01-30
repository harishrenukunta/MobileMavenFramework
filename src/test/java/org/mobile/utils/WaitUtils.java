package org.mobile.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * This will contain all wait related methods
 * @author Harish Renukunta
 */

public class WaitUtils {

    public final int explicitWaitDefault = PropertyUtils.getIntegerProperty("explicit.wait", 10);

    /**
     * This method is for static wait
     * @param millis
     */
    public void staticWait(final long millis){
        try {
            TimeUnit.MILLISECONDS.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private WebDriverWait getWebDriverWait(final WebDriver driver, final int explicitWaitDefault){
        return new WebDriverWait(driver, explicitWaitDefault);
    }

    /**
     * This method will wait for a webelement to be clickable
     * @param driver
     * @param by
     */
    public WebElement toWaitForButtonToClick(final WebDriver driver, final By by){
        final WebDriverWait wait = new WebDriverWait(driver, explicitWaitDefault);
        return wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    /**
     * To wait for element to be invisible
     * @param driver
     * @param by
     */
    public void toWaitForElementToBeInvisible(final WebDriver driver, final By by){
        getWebDriverWait(driver, 20).until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    /**
     * To wait for element to be present in DOM
     * @param driver
     * @param locator
     * @return
     */
    public WebElement waitForElementToBePresent(final WebDriver driver, final By locator){
        return getWebDriverWait(driver, explicitWaitDefault)
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /**
     * Wait for an element with locator to be visible
     * @param driver
     * @param locator
     * @return
     */
    public WebElement waitForElementToBeVisible(final WebDriver driver, final By locator){
        return getWebDriverWait(driver, explicitWaitDefault)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * This method will wait for all elements to disappear
     * @param driver
     * @param locator
     */

    public void waitForElementNotToBePresent(final WebDriver driver, final By locator){
        getWebDriverWait(driver, explicitWaitDefault)
                .until(ExpectedConditions.not(ExpectedConditions.presenceOfAllElementsLocatedBy(locator)));
    }

    public List<WebElement> waitForElementsToBePresent(final WebDriver driver, final By locator){
        return getWebDriverWait(driver, explicitWaitDefault)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

}
