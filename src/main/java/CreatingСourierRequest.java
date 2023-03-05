import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class CreatingСourierRequest extends Client {
    private static String PATH = "/api/v1/courier/";
    //CreatingСourier courier;

    public ValidatableResponse CreatingСourierRequest(CreatingСourier courier){
        return given()
                .spec(getBaseSpec())
                .body(courier)
                .when()
                .post(PATH)
                .then();

    }
}
