package org.challenge.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.annotations.Step;
import org.openqa.selenium.NoAlertPresentException;

/**
 * Task: Accept a browser alert dialog if one is present.
 * Demoblaze shows an alert after adding a product to the cart.
 */
public class AcceptAlertIfPresent implements Task {

    public AcceptAlertIfPresent() {}

    public static AcceptAlertIfPresent afterAddingToCart() {
        return new AcceptAlertIfPresent();
    }

    @Override
    @Step("{0} accepts the browser alert if present")
    public <T extends Actor> void performAs(T actor) {
        try {
            Thread.sleep(1000); // brief pause for alert to appear
            BrowseTheWeb.as(actor).getDriver().switchTo().alert().accept();
        } catch (NoAlertPresentException e) {
            // No alert — that is fine, continue
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
