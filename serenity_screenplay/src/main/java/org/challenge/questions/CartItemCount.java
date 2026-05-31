package org.challenge.questions;



import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.challenge.ui.CartPage;

/**
 * Question: How many items are currently in the cart table?
 */
public class CartItemCount implements Question<Integer> {

    private CartItemCount() {}

    public static CartItemCount displayed() {
        return new CartItemCount();
    }

    @Override
    public Integer answeredBy(Actor actor) {
        return BrowseTheWeb.as(actor)
                .findAll(CartPage.CART_ITEMS)
                .size();
    }
}
