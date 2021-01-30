package org.mobile.testcases;

import org.mobile.pageobjects.HomePage;
import org.mobile.pageobjects.SpecialBuys;
import org.mobile.utils.PageObjectFactory;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LaunchTests extends BaseTestCase {

    @Test
    public void launchAldi(){
        System.out.println("Successfully launch aldi app");
        HomePage homePage = PageObjectFactory.getPage(driver, HomePage.class);
        final SpecialBuys specialBuys = homePage.gotoSpecialBuys();
        List<String> specialBuysCategories = specialBuys.getCategories();
        final String expectedCategory = "CATEGORIES";
        assertThat(specialBuysCategories)
                .as(String.format("Special Buys page contains '%s'", expectedCategory ))
                .contains(expectedCategory);
        System.out.println("");
    }
}
