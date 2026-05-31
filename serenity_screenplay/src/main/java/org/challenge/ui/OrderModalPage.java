package org.challenge.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

/**
 * UI map for the Order / Place-Order modal dialog.
 */
public class OrderModalPage {

    public static final Target MODAL_TITLE =
            Target.the("Order modal title")
                  .located(By.id("orderModal"));

    // Form fields
    public static final Target FIELD_NAME =
            Target.the("Name field")
                  .located(By.id("name"));

    public static final Target FIELD_COUNTRY =
            Target.the("Country field")
                  .located(By.id("country"));

    public static final Target FIELD_CITY =
            Target.the("City field")
                  .located(By.id("city"));

    public static final Target FIELD_CARD =
            Target.the("Credit card field")
                  .located(By.id("card"));

    public static final Target FIELD_MONTH =
            Target.the("Month field")
                  .located(By.id("month"));

    public static final Target FIELD_YEAR =
            Target.the("Year field")
                  .located(By.id("year"));

    public static final Target PURCHASE_BUTTON =
            Target.the("Purchase button")
                  .located(By.xpath("//button[contains(text(),'Purchase')]"));

    // Confirmation
    public static final Target CONFIRMATION_TITLE =
            Target.the("Order confirmation heading")
                  .located(By.xpath("//h2[contains(text(),'Thank you for your purchase!')]"));

    public static final Target CONFIRMATION_DETAILS =
            Target.the("Order confirmation details paragraph")
                  .located(By.xpath("//p[@class='lead text-muted ']"));

    public static final Target OK_BUTTON =
            Target.the("OK button on confirmation")
                  .located(By.xpath("//button[contains(text(),'OK')]"));

    private OrderModalPage() {}
}
