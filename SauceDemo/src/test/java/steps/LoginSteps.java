package steps;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import pages.LoginPage;
import pages.ProductsPage;

import java.io.File;
import java.io.IOException;

public class LoginSteps {

    LoginPage loginPage = new LoginPage();
    ProductsPage productsPage = new ProductsPage();

    @Given("I login as a standard user")
    public void loginAsStandardUser() throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        JsonNode users = mapper.readTree(new File("src/test/resources/testdata/users.json"));

        String username = users.get("standardUser").get("username").asText();
        String password = users.get("standardUser").get("password").asText();

        loginPage.login(username, password);
        Assert.assertTrue(productsPage.isProductsPageVisible());
    }

    @Given("I attempt login with invalid credentials")
    public void invalidLogin() throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        JsonNode users = mapper.readTree(new File("src/test/resources/testdata/users.json"));

        String username = users.get("invalidUser").get("username").asText();
        String password = users.get("invalidUser").get("password").asText();

        loginPage.login(username, password);
    }

    @Then("I should see an authentication error message")
    public void verifyError() {
        Assert.assertTrue(loginPage.isErrorDisplayed());
    }

    @Then("I should remain on login page")
    public void remainOnLoginPage() {
        Assert.assertTrue(loginPage.isErrorDisplayed());
    }
}