package orders;

import io.qameta.allure.Step;

public class OrdersGenerator {

    @Step("Create new order with Scooter black")
    public static Order getOrdersWithScooterBlack(){
        return new Order("Ivan", "Ivanov", "Stepanova st. 8",
                "Cokolova", "+78888888888", 6,
                "2022-06-09", "Ok", new String[]{"BLACK"});
    }

    @Step("Create new order with Scooter gray")
    public static Order getOrdersWithScooterGray(){
        return new Order("Nina", "Ninova", "Ivanova st. 7",
                "Konushinaya", "+78888889999", 7,
                "2022-06-08", "Ok", new String[]{"GRAY"});
    }

    @Step("Create new order with Scooter black and gray")
    public static Order getOrdersWithScooterBlackAndGray(){
        return new Order("Kolia", "Ivanov", "Fokina st. 15",
                "Lomikova", "+78888888888", 10,
                "2022-06-09", "Ok", new String[]{"BLACK","GRAY"});
    }

    @Step("Create new order with Scooter not colour")
    public static Order getOrdersWithScooterNoColour(){
        return new Order("Luidmila", "Pogadaeva", "Smirnova st. 9",
                "Moskovskaia", "+78888880000", 8,
                "2022-09-06", "Ok", new String[]{""});
    }
}
