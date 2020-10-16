package endpoints;

import config.Config;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.Order;

import static io.restassured.RestAssured.given;

public class PetStoreOrderEndpoint {
    public Response getOrderById(long id) {
        return given()
                .pathParam("orderId", id)
                .when()
                .get(Config.ORDER_BY_ID)
                .then()
                .extract().response();

    }

    public Response getInventoriesByStatus(String status) {
        return given()
                .queryParam("status", status)
                .when()
                .get(Config.FIND_INVENTORIES_BY_STATUS)
                .then().extract().response();

    }

    public Response createOrder(Order order) {
        return given()
                .body(order)
                .when()
                .post(Config.CREATE_ORDER)
                .then().extract().response();

    }


    public Response deleteById(long id) {
        return given()
                .when()
                .delete(Config.ORDER_BY_ID, id)
                .then().extract().response();

    }

    private RequestSpecification given() {
        return RestAssured.given()
                .log().uri()
                .log().body()
                .baseUri(Config.PETSTORE_BASE_URL)
                .contentType(ContentType.JSON);
    }


}
