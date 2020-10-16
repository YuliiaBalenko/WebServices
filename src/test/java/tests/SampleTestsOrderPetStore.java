package tests;

import io.restassured.response.Response;
import models.Order;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import endpoints.PetStoreOrderEndpoint;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.*;

@RunWith(SerenityRunner.class)
public class SampleTestsOrderPetStore {

    public static final PetStoreOrderEndpoint PET_STORE_ORDER_ENDPOINT = new PetStoreOrderEndpoint();

    @Test
    public void verifyStatusCode() {
        PET_STORE_ORDER_ENDPOINT.getOrderById(5)
                .then()
                .statusCode(200);

    }

    @Test
    public void verifyBody() {
        PET_STORE_ORDER_ENDPOINT.getOrderById(5)
                .then()
                .assertThat()
                .body(is(not(isEmptyOrNullString())));

    }

    @Test
    public void verifyNotExistingOrderReturn404() {
        PET_STORE_ORDER_ENDPOINT
                .getOrderById(1156)
                .then()
                .statusCode(404);

    }

    @Test
    public void verifyOrderCanBeCreated() {
        Order order1 = new Order();
        order1.setId(5);
        order1.setPetId(35);
        order1.setQuantity(1);
        order1.setShipDate("2020-10-15T22:32:22.873Z");
        order1.setStatus("approved");
        order1.setComplete(true);

        PET_STORE_ORDER_ENDPOINT
                .createOrder(order1)
                .then()
                .statusCode(200);
    }


    @Test
    public void verifyOrder1HasIdAfterCreation() {
        Order order1 = Order.createOrder();
        Response orderResponse = PET_STORE_ORDER_ENDPOINT
                .createOrder(order1);
        Order orderFromService = orderResponse.body().as(Order.class);
        Assert.assertNotNull(orderFromService.getId());

    }

 /*
    @BeforeClass
    public static void cleanup() {
        PET_STORE_ORDER_ENDPOINT
                .getInventoriesByStatus("approved")
                .body()
                .jsonPath().getList("findAll {item -> item.petId == 35}", Order.class)
                .stream()
                .forEach(order -> PET_STORE_ORDER_ENDPOINT.deleteById(order.getId()));
    }
 */
}

