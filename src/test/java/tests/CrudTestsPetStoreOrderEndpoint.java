
package tests;

import endpoints.PetStoreOrderEndpoint;
import io.restassured.response.Response;
import models.Order;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Title;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class CrudTestsPetStoreOrderEndpoint {

    public static final PetStoreOrderEndpoint PET_STORE_ORDER_ENDPOINT = new PetStoreOrderEndpoint();

    @Test
    @Title("verification of post request")
    public void createOrder() {
        //Given
        Order order1 = Order.createOrder();

        //When
        Response orderResponse = PET_STORE_ORDER_ENDPOINT.createOrder(order1);

        //Then
        long createdOrderId = orderResponse.body().as(Order.class).getId();
        Order createdOrderFromService = PET_STORE_ORDER_ENDPOINT.getOrderById(createdOrderId).as(Order.class);
        SoftAssertions assertions = new SoftAssertions();
        assertions.assertThat(createdOrderFromService.getPetId()).as("PetId").isEqualTo(order1.getPetId());
        assertions.assertThat(createdOrderFromService.getQuantity()).as("Quantity").isEqualTo(order1.getQuantity());
        assertions.assertAll();

    }

    @Test
    @Title("verification of get request")
    public void readOrder() {
        //Given
        Order order2 = Order.createOrder();
        Response orderResponse = PET_STORE_ORDER_ENDPOINT.createOrder(order2);
        long createdOrderId = orderResponse.body().as(Order.class).getId();

        //When
        Order createdOrderFromService = PET_STORE_ORDER_ENDPOINT.getOrderById(createdOrderId).as(Order.class);

        //Then
        SoftAssertions assertions = new SoftAssertions();
        assertions.assertThat(createdOrderFromService.getPetId()).as("PetId").isEqualTo(order2.getPetId());
        assertions.assertThat(createdOrderFromService.getQuantity()).as("Quantity").isEqualTo(order2.getQuantity());
        assertions.assertAll();
    }

    @Test
    @Title("verification of delete request")
    public void deleteOrder() {
        //Given
        Order order3 = Order.createOrder();
        Response orderResponse = PET_STORE_ORDER_ENDPOINT.createOrder(order3);
        long createdOrderId = orderResponse.body().as(Order.class).getId();
        Order createdOrderFromService = PET_STORE_ORDER_ENDPOINT.getOrderById(createdOrderId).as(Order.class);

        //When
        PET_STORE_ORDER_ENDPOINT.deleteById(createdOrderFromService.getId());

        //Then
        Response orderById = PET_STORE_ORDER_ENDPOINT.getOrderById(createdOrderFromService.getId());
        Assertions.assertThat(orderById.getStatusCode()).isEqualTo(404);

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

    /*
    @Test
    @Title("verification of get request")
    public void readPetInventories(){
        //Given
        Order order4 = Order.createOrder();
        Response orderResponse  = PET_STORE_ORDER_ENDPOINT.createOrder(order4);
        String createdOrderStatus = orderResponse.body().as(Order.class).getStatus();

        //When
        Order createdOrderFromService = PET_STORE_ORDER_ENDPOINT.getInventoriesByStatus(createdOrderStatus).as(Order.class);

        //Then
        SoftAssertions assertions = new SoftAssertions();
        assertions.assertThat(createdOrderFromService.getStatus()).as("Status").isEqualTo(order4.getStatus());
        assertions.assertThat(createdOrderFromService.getQuantity()).as("Quantity").isEqualTo(order4.getQuantity());
        assertions.assertAll();
    }
    */

}
