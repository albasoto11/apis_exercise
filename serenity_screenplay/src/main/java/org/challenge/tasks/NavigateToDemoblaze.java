package org.challenge.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.annotations.Step;

/**
 * Task: Navigate to the Demoblaze home page.
 */
public class NavigateToDemoblaze implements Task {

    private static final String BASE_URL = "https://www.demoblaze.com";

    @Override
    @Step("{0} navigates to the Demoblaze home page")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Open.url(BASE_URL));
    }

    public static NavigateToDemoblaze homePage() {
        return new NavigateToDemoblaze();
    }
}
