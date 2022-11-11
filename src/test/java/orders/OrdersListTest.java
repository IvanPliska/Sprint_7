package orders;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.assertEquals;

public class OrdersListTest {
    private OrdersClient ordersClient;

    @Before
    public void setUp() {
        ordersClient = new OrdersClient();
    }

    @DisplayName("Test to get list orders")
    @Test
    public void getListOrders () {
        ValidatableResponse responseCreate = ordersClient.getCreateOrders();
        int actualStatusCode = responseCreate.extract().statusCode();
        String actualListOrders = responseCreate.extract().path("order").toString();

        assertEquals("Status code is not 200", SC_OK, actualStatusCode);
        System.out.println(actualListOrders);
    }
}
