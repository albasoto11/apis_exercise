package org.challenge.tasks;


import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import org.challenge.model.PurchaseData;
import org.challenge.ui.CartPage;
import org.challenge.ui.OrderModalPage;

/**
 * Task: Open the Place Order modal and fill in customer/payment data,
 * then submit the purchase.
 */
public class PlaceOrder implements Task {

    private final PurchaseData purchaseData;

    private PlaceOrder(PurchaseData purchaseData) {
        this.purchaseData = purchaseData;
    }

    public static PlaceOrder with(PurchaseData purchaseData) {
        return new PlaceOrder(purchaseData);
    }

    @Override
    @Step("{0} places an order for customer '#purchaseData'")
    public <T extends Actor> void performAs(T actor) {
        // Open the Place Order modal
        actor.attemptsTo(
            Click.on(CartPage.PLACE_ORDER_BUTTON)
        );

        // Wait for modal fields to be visible
        actor.attemptsTo(
            WaitUntil.the(OrderModalPage.FIELD_NAME,
                          WebElementStateMatchers.isVisible())
                     .forNoMoreThan(10).seconds()
        );

        // Fill in the order form
        actor.attemptsTo(
            Enter.theValue(purchaseData.getName()).into(OrderModalPage.FIELD_NAME),
            Enter.theValue(purchaseData.getCountry()).into(OrderModalPage.FIELD_COUNTRY),
            Enter.theValue(purchaseData.getCity()).into(OrderModalPage.FIELD_CITY),
            Enter.theValue(purchaseData.getCard()).into(OrderModalPage.FIELD_CARD),
            Enter.theValue(purchaseData.getMonth()).into(OrderModalPage.FIELD_MONTH),
            Enter.theValue(purchaseData.getYear()).into(OrderModalPage.FIELD_YEAR)
        );

        // Click Purchase
        actor.attemptsTo(
            Click.on(OrderModalPage.PURCHASE_BUTTON)
        );

        // Wait for confirmation dialog
        actor.attemptsTo(
            WaitUntil.the(OrderModalPage.CONFIRMATION_TITLE,
                          WebElementStateMatchers.isVisible())
                     .forNoMoreThan(15).seconds()
        );
    }
}
