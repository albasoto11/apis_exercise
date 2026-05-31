package org.challenge.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

/**
 * UI map for the Demoblaze Cart Page.
 */
public class CartPage {

    public static final Target CART_ITEMS =
            Target.the("cart item rows")
                  .located(By.xpath("//tbody/tr"));

    public static final Target CART_ITEM_NAMES =
            Target.the("cart item name cells")
                  .located(By.xpath("//tbody/tr/td[2]"));

    public static final Target PLACE_ORDER_BUTTON =
            Target.the("Place Order button")
                  .located(By.xpath("//button[contains(text(),'Place Order')]"));

    public static final Target TOTAL_PRICE =
            Target.the("cart total price")
                  .located(By.id("totalp"));

    private CartPage() {}
}
