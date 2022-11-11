package courier;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.*;
import static org.junit.Assert.assertEquals;

public class CourierDefaultTest {

    private CourierClient courierClient;
    private Courier courier;
    private Courier courierTwiceLogin;
    private int id;

    @Before
    public void setUp() {
        courierClient = new CourierClient();
        courier = CourierGenerator.getDefaultCourier();
        courierTwiceLogin = CourierGenerator.getDefaultCourierTwice();
    }

    @After
    public void cleanUp() {
        courierClient.deleteClient(id);
    }

    @DisplayName("Created new courier")
    @Test
    public void courierCanBeCreated() {
        ValidatableResponse responseCreate = courierClient.createClient(courier);
        ValidatableResponse responseLogin = courierClient.loginClient(Credentials.from(courier));
        id = responseLogin.extract().path("id");
        boolean isCourierCreated = responseCreate.extract().path("ok");
        int statusCode = responseCreate.extract().statusCode();

        assertEquals("Courier created is not ok", true, isCourierCreated);
        assertEquals("Courier created status code uncorrected", SC_CREATED, statusCode);
    }

    @DisplayName("Can not created new courier with replicate login")
    @Test
    public void courierCanNotBeCreatedTwice() {
        courierClient.createClient(courier);
        ValidatableResponse responseCreateTwice = courierClient.createClient(courierTwiceLogin);
        ValidatableResponse responseLogin = courierClient.loginClient(Credentials.from(courier));
        id = responseLogin.extract().path("id");
        String isCourierNotCreated = responseCreateTwice.extract().path("message");
        int statusCode = responseCreateTwice.extract().statusCode();

        assertEquals("Courier is created ", "Этот логин уже используется. Попробуйте другой.", isCourierNotCreated);
        assertEquals("Courier created status code is corrected", SC_CONFLICT, statusCode);
    }
}
