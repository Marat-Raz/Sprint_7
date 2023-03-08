import org.apache.commons.lang3.RandomStringUtils;

public class CourierGenerator {

    public static String login = RandomStringUtils.randomAlphanumeric(2,8);
    public static String password = RandomStringUtils.randomAlphanumeric(2,8);
    public static String firstName = RandomStringUtils.randomAlphanumeric(2,8);

    public static Сourier getCourier() {
        return new Сourier(login, password, firstName);
    }

    public static Сourier getExistingCourier() {
        return new Сourier("existingCourier", "12345", "Courier");
    }

    public static Сourier getCourierWithoutLogin() {
        return new Сourier("", password, firstName);
    }

    public static Сourier getCourierWithoutPassword() {
        return new Сourier(login, "", firstName);
    }

    public static Сourier getCourierWithoutFirstName() {
        return new Сourier(login, password, "");
    }
}