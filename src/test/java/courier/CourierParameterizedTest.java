package courier;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class CourierParameterizedTest {

    private CourierClient courierClient;

    private Courier courier;
    private int statusCode;
    private String massage;

    @Before
    public void setUp() {
        courierClient = new CourierClient();
    }

    public CourierParameterizedTest(Courier courier, int statusCode, String massage) {
        this.courier = courier;
        this.statusCode = statusCode;
        this.massage = massage;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {CourierGenerator.getWithLoginOnly(), SC_BAD_REQUEST, "Недостаточно данных для создания учетной записи"},
                {CourierGenerator.getWithPasswordOnly(), SC_BAD_REQUEST, "Недостаточно данных для создания учетной записи"},
        };
    }

    @DisplayName("Parameterized test to not create new courier")
    @Test
    public void courierCanNotBeCreated() {
        ValidatableResponse responseCreate = courierClient.createClient(courier);
        String actualMessage = responseCreate.extract().path("message");
        int actualStatusCode = responseCreate.extract().statusCode();

        assertEquals("Status code is not 40*", statusCode, actualStatusCode);
        assertEquals("Error massage uncorrected", massage, actualMessage);
    }
}
