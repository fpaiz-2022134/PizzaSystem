
import controller.KitchenController;
import model.Order;
import model.ingredients.Ingredient;
import util.IngredientCatalog;
import util.OrderGenerator;

import java.util.List;
import java.util.Scanner;

/**
 * Entry point. Provides a simple console menu for the penguin pizza kitchen.
 */
public class Main {

    private static final KitchenController kitchen = new KitchenController();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        printBanner();
        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Enter your choice: ");
            System.out.println();
            switch (choice) {
                case 1 -> generateOrders();
                case 2 -> nextOrder();
                case 3 -> showCurrentOrder();
                case 4 -> addIngredient();
                case 5 -> undoIngredient();
                case 6 -> showCurrentPizza();
                case 7 -> submitPizza();
                case 8 -> kitchen.printQueue();
                case 9 -> running = false;
                default -> System.out.println("Invalid option, try again.");
            }
        }
        System.out.println("Bye!");
        scanner.close();
    }

    // --- menu actions ---

    private static void generateOrders() {
        List<Order> orders = OrderGenerator.generateSampleOrders();
        orders.forEach(kitchen::enqueueOrder);
        System.out.println("Generated " + orders.size() + " orders.");
        orders.forEach(o -> System.out.println("  " + o));
    }

    private static void nextOrder() {
        kitchen.nextOrder();
    }

    private static void showCurrentOrder() {
        Order o = kitchen.getCurrentOrder();
        if (o == null) {
            System.out.println("No active order.");
        } else {
            System.out.println("Current order: " + o);
        }
    }

    private static void addIngredient() {
        if (!kitchen.hasCurrentOrder()) {
            System.out.println("No active order. Load one first (option 2).");
            return;
        }
        IngredientCatalog.printAll();
        int idx = readInt("Select ingredient number: ") - 1;
        Ingredient ingredient = IngredientCatalog.findByIndex(idx);
        if (ingredient == null) {
            System.out.println("Invalid selection.");
            return;
        }
        kitchen.addIngredient(ingredient);
    }

    private static void undoIngredient() {
        kitchen.undoLastIngredient();
    }

    private static void showCurrentPizza() {
        if (!kitchen.hasCurrentOrder()) {
            System.out.println("No active order.");
            return;
        }
        System.out.println("Current pizza layers (top to bottom):");
        kitchen.getCurrentPizza().printStatus();
    }

    private static void submitPizza() {
        kitchen.submitPizza();
    }

    // --- helpers ---

    private static void printBanner() {
        System.out.println("----------------------------------------------");
        System.out.println("    Penguin Pizza Kitchen - Order System");
        System.out.println("----------------------------------------------");
    }

    private static void printMenu() {
        System.out.println("\nMain menu:");
        System.out.println("  1. Generate sample orders");
        System.out.println("  2. Load next order from queue");
        System.out.println("  3. Show current order");
        System.out.println("  4. Add ingredient to pizza");
        System.out.println("  5. Undo last ingredient");
        System.out.println("  6. Show current pizza");
        System.out.println("  7. Submit pizza");
        System.out.println("  8. View order queue");
        System.out.println("  9. Exit");
    }

    private static int readInt(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.print(prompt);
        }
        int val = scanner.nextInt();
        scanner.nextLine(); // clear buffer
        return val;
    }
}

