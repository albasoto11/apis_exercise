package org.challenge.tasks;


import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.annotations.Step;
import org.challenge.ui.OrderModalPage;

/**
 * Task: Clicks the OK button to dismiss the purchase confirmation dialog.
 */
public class ConfirmPurchase implements Task {

    public ConfirmPurchase() {}

    public static ConfirmPurchase byClickingOk() {
        return new ConfirmPurchase();
    }

    @Override
    @Step("{0} confirms the purchase by clicking OK")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            Click.on(OrderModalPage.OK_BUTTON)
        );
    }
}
