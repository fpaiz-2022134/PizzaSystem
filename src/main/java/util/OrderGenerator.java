package util;


import model.Order;
import model.ingredients.Base;
import model.ingredients.Sauce;
import model.ingredients.Topping;

import java.util.Arrays;
import java.util.List;

/** Generates preset sample orders to populate the queue. */
public class OrderGenerator {

    public static List<Order> generateSampleOrders() {
        Order o1 = new Order(Arrays.asList(
                new Base("Thin Crust"),
                new Sauce("Normal Sauce"),
                new Topping("Fish"),
                new Topping("Extra Cheese")
        ));
        o1.setStatus(Order.Status.PENDING);

        Order o2 = new Order(Arrays.asList(
                new Base("Regular Crust"),
                new Sauce("Spicy Sauce"),
                new Topping("Shrimp"),
                new Topping("Squid")
        ));

        Order o3 = new Order(Arrays.asList(
                new Base("Deep Dish"),
                new Sauce("Normal Sauce"),
                new Topping("Seaweed"),
                new Topping("Ice Flakes")
        ));

        return Arrays.asList(o1, o2, o3);
    }
}

