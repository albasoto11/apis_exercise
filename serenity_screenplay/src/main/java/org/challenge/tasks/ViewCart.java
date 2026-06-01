package org.challenge.tasks;


import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import org.challenge.ui.CartPage;
import org.challenge.ui.HomePage;

/**
 * Task: Navigate to the shopping cart page.
 */
public class ViewCart implements Task {

    public ViewCart() {}

    public static ViewCart now() {
        return new ViewCart();
    }

    @Override
    @Step("{0} views the shopping cart")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            Click.on(HomePage.NAV_CART)
        );
        actor.attemptsTo(
            WaitUntil.the(CartPage.PLACE_ORDER_BUTTON,
                          WebElementStateMatchers.isVisible())
                     .forNoMoreThan(20).seconds()
        );
    }
}
