package Orders;

import io.qameta.allure.Step;

public class OrdersGenerator {

    @Step("Create new order with Scooter black")
    public static Orders getOrdersWithScooterBlack(){
        return new Orders("Ivan", "Ivanov", "Stepanova st. 8",
                "Cokolova", "+78888888888", 6,
                "2022-06-09", "Ok", new String[]{"BLACK"});
    }

    @Step("Create new order with Scooter gray")
    public static Orders getOrdersWithScooterGray(){
        return new Orders("Nina", "Ninova", "Ivanova st. 7",
                "Konushinaya", "+78888889999", 7,
                "2022-06-08", "Ok", new String[]{"GRAY"});
    }

    @Step("Create new order with Scooter black and gray")
    public static Orders getOrdersWithScooterBlackAndGray(){
        return new Orders("Kolia", "Ivanov", "Fokina st. 15",
                "Lomikova", "+78888888888", 10,
                "2022-06-09", "Ok", new String[]{"BLACK","GRAY"});
    }

    @Step("Create new order with Scooter not colour")
    public static Orders getOrdersWithScooterNoColour(){
        return new Orders("Luidmila", "Pogadaeva", "Smirnova st. 9",
                "Moskovskaia", "+78888880000", 8,
                "2022-09-06", "Ok", new String[]{""});
    }
}
