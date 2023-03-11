import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.ValidatableResponse;
import orderModel.*;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import сlient.OrderClient;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class OrderClientTest {
    private final OrderClient orderClient;
    private final String[] colour;

    public OrderClientTest(String[] colour) {
        orderClient = new OrderClient();
        this.colour = colour;
    }
    @BeforeClass
    public static void globalSetUp() {
        RestAssured.filters(
                new RequestLoggingFilter(), new ResponseLoggingFilter(),
                new AllureRestAssured());
    }

    @Parameterized.Parameters
    public static Object[][] setColors() {
        return new Object[][]{
                {new String[]{"GRAY", "BLACK"}},
                {new String[]{"GRAY"}},
                {new String[]{"BLACK"}},
                {new String[]{}}
        };
    }

    @Test
    @DisplayName("Когда создаёшь заказ:" +
            "можно указать один из цветов — BLACK или GREY;" +
            "можно указать оба цвета;" +
            "можно совсем не указывать цвет")
    public void orderCanBeCreateTest() {
        Order order = OrderGenerator.getOrder(colour);
        ValidatableResponse response = OrderClient.creatingOrder(order);
        int statusCode = response.extract().statusCode();
        int track = response.extract().path("track");

        assertTrue("Track is incorrect",track > 0 );
        assertEquals("Status code is incorrect",SC_CREATED, statusCode);

        OrderClient.cancelOrder(track);

    }
}