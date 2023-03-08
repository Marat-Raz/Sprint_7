import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class Client {
    public static final String BASE_URL = "http://qa-scooter.praktikum-services.ru/api/v1/courier";
    public RequestSpecification getBaseSpec() {
                return new RequestSpecBuilder()
                .addHeader("Content-type", "application/json")
                .setBaseUri(BASE_URL)
                .build();
    }
}
