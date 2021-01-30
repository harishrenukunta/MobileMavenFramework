package org.mobile.utils;

import org.mobile.pageobjects.BasePO;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class PageObjectFactory{

    public static <T extends BasePO> T getPage(WebDriver driver, Class<T> poClazz){
        try {
            Constructor<?> constructor = poClazz.getDeclaredConstructor(driver.getClass());
            constructor.setAccessible(true);
            return (T)constructor.newInstance(new Object[]{driver});
        } catch (InvocationTargetException|NoSuchMethodException|InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
