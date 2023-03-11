package сlient;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import orderModel.*;
import сlient.base.Client;

import static io.restassured.RestAssured.given;

public class OrderClient extends Client {

    private static final String ORDER_URI = BASE_URL + "orders/";

    @Step("Create order {order}")
    public static ValidatableResponse creatingOrder(Order order) {
        return given()
                .spec(getBaseSpec())
                .body(order)
                .when()
                .post(ORDER_URI)
                .then();
    }
    @Step("Return orderlist")
    public ValidatableResponse getOrderList() { // доработать нужно
        return given()
                .spec(getBaseSpec())
                .when()
                .get(ORDER_URI)
                .then();
    }
    @Step("Delete order")
    public static ValidatableResponse cancelOrder(int track) {
        return given()
                .spec(getBaseSpec())
                .when()
                .put(ORDER_URI + "cancel?track=" + track)
                .then();
    }
}
