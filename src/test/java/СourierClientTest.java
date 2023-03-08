import io.qameta.allure.junit4.*;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.apache.http.HttpStatus.*;
// import static org.apache.http.HttpStatus.SC_CREATED;
import static org.junit.Assert.*;

public class СourierClientTest {
    private Сourier courier;
    private СourierClient сourierClient;
    private int id;
    private static final Random rnd = new Random();

    @Before
    public void setUp() {
        courier = CourierGenerator.getCourier();
        сourierClient = new СourierClient();
    }

    @Test
    @DisplayName("Курьера можно создать")
    public void canCreateCourierTest() {
        ValidatableResponse response = сourierClient.creatingСourier(courier);
        response.log().all();
        ValidatableResponse loginResponse = сourierClient.courierLogin(CourierCredentials.from(courier));
        loginResponse.log().all();
        id = loginResponse.extract().path("id");
        int statusCode = response.extract().statusCode();

        assertEquals(SC_CREATED, statusCode);
        ValidatableResponse deleteResponse = сourierClient.deleteCourier(id);
        deleteResponse.log().all();

        System.out.println(courier.getLogin()); // Для проверки создания курьера в Postman
        System.out.println(courier.getPassword());
        System.out.println(id);
        System.out.println(Client.BASE_URL + СourierClient.LOGIN);
        }
    @Test
    @DisplayName("Нельзя создать двух одинаковых курьеров")
    public void cannotСreateTwoIdenticalCouriersTest() {
        String login = "ninja"+rnd.nextInt(1000);
        courier = new Сourier(login, "1234", "saske");
        ValidatableResponse firstResponse = сourierClient.creatingСourier(courier);
        ValidatableResponse response = сourierClient.creatingСourier(courier);
        response.assertThat().body("message", Matchers.notNullValue()).and().statusCode(409);
        //System.out.println(login); // Для проверки создания курьера в Postman
    }
    @Test
    @DisplayName("Если одного из полей нет при создании курьера, запрос возвращает ошибку *отсутстие поля login")
    public void cannotCreateCourierWithoutLoginTest() {
        //String login = "ninja"+rnd.nextInt(1000);
        courier = new Сourier("", "1234", "saske");
        ValidatableResponse response = сourierClient.creatingСourier(courier);
        int statusCode = response.extract().statusCode();
        assertEquals("Status code is not 201 CREATED", SC_BAD_REQUEST, statusCode);
        boolean isCourierNotCreated = response.extract().path("ok");
        assertTrue("Courier is not created", isCourierNotCreated);
       // response.assertThat().body("message", Matchers.notNullValue()).and().statusCode(409);
       // System.out.println(login); // Для проверки создания курьера в Postman
    }
    @Test
    @DisplayName("Если одного из полей нет при создании курьера, запрос возвращает ошибку *отсутстие поля login")
    public void cannotCreateCourierWithoutLoginTest111() {
        String login = RandomStringUtils.randomAlphanumeric(2,8);
         System.out.println(login); // Для проверки создания курьера в Postman
    }
}
