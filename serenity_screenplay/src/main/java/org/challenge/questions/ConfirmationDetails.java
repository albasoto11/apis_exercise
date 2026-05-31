package org.challenge.questions;




import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.challenge.ui.OrderModalPage;

/**
 * Question: What is the text content of the confirmation details paragraph?
 */
public class ConfirmationDetails implements Question<String> {

    private ConfirmationDetails() {}

    public static ConfirmationDetails text() {
        return new ConfirmationDetails();
    }

    @Override
    public String answeredBy(Actor actor) {
        try {
            return BrowseTheWeb.as(actor)
                    .find(OrderModalPage.CONFIRMATION_DETAILS)
                    .getText();
        } catch (Exception e) {
            return "";
        }
    }
}
