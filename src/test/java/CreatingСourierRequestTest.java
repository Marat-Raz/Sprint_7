import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static io.restassured.RestAssured.*;

public class CreatingСourierRequestTest {
    private static String PATH = "/api/v1/courier/";
    CreatingСourier courier;
    private static final Random rnd = new Random();
    /*private static int getRnd(){
        return rnd.nextInt(1000);
    }*/

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }

    @Test
    public void canCreateCourier() {
        String login = "ninja"+rnd.nextInt(1000);
        CreatingСourier courier = new CreatingСourier(login, "1234", "saske");
        CreatingСourierRequest hZ = new CreatingСourierRequest();
        //System.out.println(login);
    }
    @Test
    public void cannotСreateTwoIdenticalCouriers() {
        System.out.println("login");
    }
}
