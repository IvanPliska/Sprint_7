package orders;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class OrdersParameterizedTest {
    private OrdersClient ordersClient;

    private Order order;
    private int statusCode;
    private int track;

    @Before
    public void setUp() {
        ordersClient = new OrdersClient();
    }

    public OrdersParameterizedTest(Order order, int statusCode) {
        this.order = order;
        this.statusCode = statusCode;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {OrdersGenerator.getOrdersWithScooterBlack(), SC_CREATED},
                {OrdersGenerator.getOrdersWithScooterGray(), SC_CREATED},
                {OrdersGenerator.getOrdersWithScooterBlackAndGray(), SC_CREATED},
                {OrdersGenerator.getOrdersWithScooterNoColour(), SC_CREATED}
        };
    }

    @DisplayName("Parameterized test to create new order")
    @Test
    public void ordersCanBeCreated() {
        ValidatableResponse responseCreate =ordersClient.createOrders(order);
        int actualTrack = responseCreate.extract().path("track");
        int actualStatusCode = responseCreate.extract().statusCode();

        assertEquals("Status code is not 201", statusCode, actualStatusCode);
        System.out.println("Успешное создание заказа под треком:"+ actualTrack);
    }
}