package courier;

import io.qameta.allure.Step;

import io.restassured.filter.Filter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.ValidatableResponse;


import static io.restassured.RestAssured.given;

public class CourierClient extends Client {

    private static final String PATH_CREATE = "/api/v1/courier";
    public static final String PATH_LOGIN = "/api/v1/courier/login";
    public static final String PATH_DELETE = "/api/v1/courier/";

    private final Filter requestFilter = new RequestLoggingFilter();
    private final Filter responseFiler = new ResponseLoggingFilter();

    @Step("Create new client")
    public ValidatableResponse createClient(Courier courier) {
        return given()
                .with()
                .filters(requestFilter, responseFiler)
                .spec(getSpec())
                .body(courier)
                .when()
                .post(PATH_CREATE)
                .then();
    }

    @Step("Login new client")
    public ValidatableResponse loginClient(Credentials credential) {
        return given()
                .with()
                .filters(requestFilter, responseFiler)
                .spec(getSpec())
                .body(credential)
                .when()
                .post(PATH_LOGIN)
                .then();
    }

    @Step("Delete new client")
    public ValidatableResponse deleteClient(int id) {
        return given()
                .with()
                .filters(requestFilter, responseFiler)
                .spec(getSpec())
                .when()
                .delete(PATH_DELETE+id)
                .then();
    }
}
