package org.challenge.questions;



import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.challenge.ui.OrderModalPage;

/**
 * Question: Is the purchase confirmation dialog currently visible?
 */
public class PurchaseConfirmationVisible implements Question<Boolean> {

    private PurchaseConfirmationVisible() {}

    public static PurchaseConfirmationVisible onScreen() {
        return new PurchaseConfirmationVisible();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        try {
            return BrowseTheWeb.as(actor)
                    .find(OrderModalPage.CONFIRMATION_TITLE)
                    .isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}

