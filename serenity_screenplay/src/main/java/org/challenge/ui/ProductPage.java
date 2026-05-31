package org.challenge.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

/**
 * UI map for a Demoblaze Product Detail Page.
 */
public class ProductPage {

    public static final Target ADD_TO_CART_BUTTON =
            Target.the("Add to cart button")
                  .located(By.xpath("//a[contains(text(),'Add to cart')]"));

    public static final Target PRODUCT_NAME =
            Target.the("Product name heading")
                  .located(By.className("name"));

    public static final Target PRODUCT_PRICE =
            Target.the("Product price")
                  .located(By.xpath("//h3[@class='price-container']"));

    private ProductPage() {}
}
