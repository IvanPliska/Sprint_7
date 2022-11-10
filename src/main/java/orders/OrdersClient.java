package orders;

import client.Client;
import io.qameta.allure.Step;
import io.restassured.filter.Filter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class OrdersClient extends Client {

    private static final String PATH_CREATE_ORDERS = "/api/v1/orders";
    public static final String PATH_LIST_ORDERS = "/v1/orders?limit=5&page=0";

    private final Filter requestFilter = new RequestLoggingFilter();
    private final Filter responseFiler = new ResponseLoggingFilter();

    @Step("Create new order")
    public ValidatableResponse createOrders(Order order) {
        return given()
                .with()
                .filters(requestFilter, responseFiler)
                .spec(getSpec())
                .body(order)
                .when()
                .post(PATH_CREATE_ORDERS)
                .then();
    }

    @Step("Show orders")
    public ValidatableResponse getCreateOrders() {
        return given()
                .with()
                .filters(requestFilter, responseFiler)
                .spec(getSpec())
                .when()
                .get(PATH_LIST_ORDERS)
                .then();
    }
}
