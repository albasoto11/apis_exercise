package org.challenge.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

/**
 * UI map for the Demoblaze Home Page.
 * Only locators — no logic here.
 */
public class HomePage {

    public static final Target LOGO =
            Target.the("Demoblaze logo")
                  .located(By.id("nava"));

    public static final Target NAV_CART =
            Target.the("Cart navigation link")
                  .located(By.id("cartur"));

    public static final Target PRODUCT_LINK =
            Target.the("product link '{0}'")
                  .locatedBy("//a[normalize-space(.)='{0}']");

    public static final Target CATEGORY_PHONES =
            Target.the("Phones category")
                  .located(By.xpath("//a[contains(text(),'Phones')]"));

    public static final Target CATEGORY_LAPTOPS =
            Target.the("Laptops category")
                  .located(By.xpath("//a[contains(text(),'Laptops')]"));

    public static final Target CATEGORY_MONITORS =
            Target.the("Monitors category")
                  .located(By.xpath("//a[contains(text(),'Monitors')]"));

    private HomePage() {}
}
