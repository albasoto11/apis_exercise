package org.challenge.tasks;


import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import org.challenge.ui.HomePage;
import org.challenge.ui.ProductPage;

/**
 * Task: Search for a product by name and add it to the cart.
 * After adding, navigates back to the home page so subsequent products can be added.
 */
public class AddProductToCart implements Task {

    private final String productName;

    private AddProductToCart(String productName) {
        this.productName = productName;
    }

    public static AddProductToCart named(String productName) {
        return new AddProductToCart(productName);
    }

    @Override
    @Step("{0} adds the product '#productName' to the cart")
    public <T extends Actor> void performAs(T actor) {
        // Click the product link on the home page
        actor.attemptsTo(
            WaitUntil.the(HomePage.PRODUCT_LINK.of(productName),
                          WebElementStateMatchers.isVisible())
                     .forNoMoreThan(10).seconds(),
            Click.on(HomePage.PRODUCT_LINK.of(productName))
        );

        // Wait for Add to Cart button and click it
        actor.attemptsTo(
            WaitUntil.the(ProductPage.ADD_TO_CART_BUTTON,
                          WebElementStateMatchers.isVisible())
                     .forNoMoreThan(10).seconds(),
            Click.on(ProductPage.ADD_TO_CART_BUTTON)
        );

        // Accept the browser alert that confirms "Product added"
        try {
            // small pause to allow the alert to appear
            Thread.sleep(1000);
            BrowseTheWeb.as(actor).getDriver().switchTo().alert().accept();
        } catch (Exception e) {
            // No alert or interrupted - continue
        }

        // Navigate back to the home page for the next product
        actor.attemptsTo(
            net.serenitybdd.screenplay.actions.Open.url("https://www.demoblaze.com")
        );
    }
}
