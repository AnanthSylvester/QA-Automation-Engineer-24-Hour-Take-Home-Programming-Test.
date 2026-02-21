package steps;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.CartPage;
import pages.CheckoutPage;
import pages.ProductsPage;

import java.io.File;
import java.io.IOException;

public class PurchaseSteps {

    ProductsPage productsPage = new ProductsPage();
    CartPage cartPage = new CartPage();
    CheckoutPage checkoutPage = new CheckoutPage();

    @When("I add {string} to the cart")
    public void addItemToCart(String itemName) {

        // Only backpack implemented (robust locator)
        productsPage.addBackpackToCart();
        Assert.assertTrue(productsPage.isCartBadgeUpdated());
        
     // Force failure for testing screenshot
//      Assert.assertTrue(false);
    }

    @When("I checkout with valid customer details")
    public void checkoutWithValidDetails() throws IOException {

        productsPage.openCart();
        cartPage.clickCheckout();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode data = mapper.readTree(new File("src/test/resources/testdata/checkout.json"));

        checkoutPage.enterCustomerDetails(
                data.get("firstName").asText(),
                data.get("lastName").asText(),
                data.get("postalCode").asText()
        );

        checkoutPage.finishOrder();
    }

    @Then("I should see the order confirmation page")
    public void verifyOrderConfirmation() {
    	
       Assert.assertTrue(checkoutPage.isOrderCompleteVisible());
        
     // Force failure for testing screenshot
//        Assert.assertTrue(false);
    }
}