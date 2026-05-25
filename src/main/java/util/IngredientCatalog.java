package util;

import model.ingredients.Base;
import model.ingredients.Ingredient;
import model.ingredients.Sauce;
import model.ingredients.Topping;

import java.util.Arrays;
import java.util.List;

/** Static catalog of all ingredients available in the kitchen. */
public class IngredientCatalog {

    public static final List<Ingredient> ALL = Arrays.asList(
            // Bases
            new Base("Thin Crust"),
            new Base("Regular Crust"),
            new Base("Deep Dish"),
            // Sauces
            new Sauce("Normal Sauce"),
            new Sauce("Spicy Sauce"),
            // Toppings
            new Topping("Fish"),
            new Topping("Shrimp"),
            new Topping("Squid"),
            new Topping("Seaweed"),
            new Topping("Ice Flakes"),
            new Topping("Extra Cheese")
    );

    public static Ingredient findByIndex(int index) {
        if (index < 0 || index >= ALL.size()) return null;
        return ALL.get(index);
    }

    public static void printAll() {
        System.out.println("Available ingredients:");
        for (int i = 0; i < ALL.size(); i++) {
            System.out.printf("  %2d. %s%n", i + 1, ALL.get(i));
        }
    }
}
