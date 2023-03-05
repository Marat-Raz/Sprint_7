import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class Client {
    public RequestSpecification getBaseSpec() {
        final String BASE_URL = "http://qa-scooter.praktikum-services.ru";
        return new RequestSpecBuilder().addHeader("Content-type", "application/json").setBaseUri(BASE_URL).build();
    }
}
