package courier;

public class LoginGenerator {

    public static Credentials getWithPasswordOnly() {
        return new Credentials(" ", "1234");
    }

    public static Credentials getWithLoginOnly() {
        return new Credentials("Maria", " ");
    }

    public static Credentials getEmptyBoxCourier() {
        return new Credentials("", "");
    }
}
