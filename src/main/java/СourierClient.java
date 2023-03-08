import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class СourierClient extends Client {
    //private static String CREATE_PATH = "/api/v1/courier/";
    public static final String LOGIN = "/login";

    //private static final String DELETE_PATH = "/api/v1/courier/";
        public ValidatableResponse creatingСourier(Сourier courier){
        return given()
                .spec(getBaseSpec())
                .body(courier)
                .when()
                .post(BASE_URL)
                .then();

    }
    public ValidatableResponse courierLogin(CourierCredentials courierCredentials) {
        return given()
                .spec(getBaseSpec())
                .body(courierCredentials)
                .when()
                .post(BASE_URL + LOGIN)
                .then();

    }

    public ValidatableResponse deleteCourier(int id) {
        return given()
                .spec(getBaseSpec())
                .when()
                .delete(BASE_URL + "/" + id)
                .then();

    }
}
