package Courier;

import io.qameta.allure.Step;

public class CourierGenerator {

    @Step("Create new default courier")
    public static Courier getDefaultCourier() {
        return new Courier("Maria", "1234", "Vasia");
    }

    @Step("Create new courier only password")
    public static Courier getWithPasswordOnly() {
        return new Courier("", "1234", "");
    }

    @Step("Create new courier only login")
    public static Courier getWithLoginOnly() {
        return new Courier("vas", "", "");
    }

    @Step("Create new courier twice to login")
    public static Courier getDefaultCourierTwice() {
        return new Courier("Maria", "123456", "Victor");
    }
}
