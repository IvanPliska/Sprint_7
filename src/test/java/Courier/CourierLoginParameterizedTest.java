package Courier;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.apache.http.HttpStatus.*;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class CourierLoginParameterizedTest {

    private CourierClient courierClient;
    private Credentials credentials;

    private Courier courier;
    private int statusCode;
    private String massage;
    private int id;

    @Before
    public void setUp() {
        courierClient = new CourierClient();

    }

    @After
    public void cleanUp() {
        courierClient.deleteClient(id);
    }

    public CourierLoginParameterizedTest(Credentials credentials, int statusCode, String massage, Courier courier) {
        this.credentials = credentials;
        this.statusCode = statusCode;
        this.massage = massage;
        this.courier = courier;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {LoginGenerator.getWithLoginOnly(), SC_NOT_FOUND, "Учетная запись не найдена", CourierGenerator.getDefaultCourier()},
                {LoginGenerator.getWithPasswordOnly(), SC_NOT_FOUND, "Учетная запись не найдена", CourierGenerator.getDefaultCourier()},
                {LoginGenerator.getEmptyBoxCourier(),SC_BAD_REQUEST, "Недостаточно данных для входа", CourierGenerator.getDefaultCourier()},
        };
    }

    @DisplayName("Parameterized test to not login new courier")
    @Test
    public void courierCanNotBeLogin() {
        courierClient.createClient(courier);
        ValidatableResponse responseLogin = courierClient.loginClient(Credentials.from(courier));
        id = responseLogin.extract().path("id");
        ValidatableResponse loginCourier = courierClient.loginClient(credentials);
        String actualMessage = loginCourier.extract().path("message");
        int actualStatusCode = loginCourier.extract().statusCode();

        assertEquals("Status code is not 40*", statusCode, actualStatusCode);
        assertEquals("Error massage uncorrected", massage, actualMessage);
    }

}
