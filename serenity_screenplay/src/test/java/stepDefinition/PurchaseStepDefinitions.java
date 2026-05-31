package stepDefinition;

import org.challenge.model.PurchaseData;
import org.challenge.questions.CartItemCount;
import org.challenge.questions.PurchaseConfirmationVisible;
import org.challenge.questions.SuccessMessage;
import org.challenge.tasks.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.containsString;

import java.util.Map;

public class PurchaseStepDefinitions {

    private Actor customer;

    @Given("^el usuario navega a la página de inicio de Demoblaze\\.$")
    public void elUsuarioNavegaALaPaginaDeInicioDeDemoblaze() {
        customer = OnStage.theActorCalled("Customer");
        customer.attemptsTo(NavigateToDemoblaze.homePage());
    }

    @When("el usuario agrega el producto {string} al carrito")
    public void elUsuarioAgregaElProductoAlCarrito(String productName)  {
        customer = OnStage.theActorCalled("Customer");
        customer.attemptsTo(AddProductToCart.named(productName));
    }

    @And("^el usuario visualiza el carrito de compras$")
    public void elUsuarioVisualizaElCarritoDeCompras() {
        customer = OnStage.theActorCalled("Customer");
        customer.attemptsTo(ViewCart.now());
    }

    @Then("^el carrito debe contener (\\d+) productos$")
    public void elCarritoDebeContenerProductos(int expectedCount) {
        customer = OnStage.theActorCalled("Customer");
        customer.should(
                seeThat("the number of items in cart",
                        CartItemCount.displayed(),
                        is(greaterThanOrEqualTo(expectedCount)))
        );
    }

    @When("^el usuario realiza un pedido con los siguientes datos:$")
    public void elUsuarioRealizaUnPedidoConLosSiguientesDatos(DataTable dataTable) {
        customer = OnStage.theActorCalled("Customer");
        Map<String, String> data = dataTable.asMap(String.class, String.class);
        PurchaseData purchaseData = PurchaseData
                .withName(data.get("name"))
                .fromCountry(data.get("country"))
                .inCity(data.get("city"))
                .payingWithCard(data.get("card"))
                .expiringMonth(data.get("month"))
                .expiringYear(data.get("year"));

        customer.attemptsTo(PlaceOrder.with(purchaseData));
    }

    @When("el usuario realiza un pedido con los datos: name {string}, country {string}, city {string}, card {string}, month {string} and year {string}")
    public void el_usuario_realiza_un_pedido_con_los_datos_name_country_city_card_month_and_year(String name, String country, String city, String card, String month, String year) {
        PurchaseData purchaseData = new PurchaseData(name, country, city, card, month, year);
        customer.attemptsTo(PlaceOrder.with(purchaseData));
    }


    @Then("^la compra debe ser confirmada exitosamente$")
    public void laCompraDebeSerConfirmadaExitosamente() {
        customer = OnStage.theActorCalled("Customer");
        customer.should(
                seeThat("the purchase confirmation dialog is visible",
                        PurchaseConfirmationVisible.onScreen(),
                        is(true))
        );
        customer.attemptsTo(ConfirmPurchase.byClickingOk());
    }


}

