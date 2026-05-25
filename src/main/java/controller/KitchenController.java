package controller;


import model.Order;
import model.Pizza;
import model.ingredients.Ingredient;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Manages the order queue and the pizza currently being assembled.
 * Orders follow FIFO (queue), ingredients follow LIFO (stack inside Pizza).
 */
public class KitchenController {

    // FIFO queue of pending orders
    private final Queue<Order> orderQueue;

    // The one order currently being worked on
    private Order currentOrder;

    // The pizza being built for the current order
    private Pizza currentPizza;

    public KitchenController() {
        this.orderQueue = new LinkedList<>();
    }

    // --- Order queue management ---

    public void enqueueOrder(Order order) {
        orderQueue.add(order);
    }

    /**
     * Pulls the next order from the queue and starts a new pizza.
     * Returns false if the queue is empty.
     */
    public boolean nextOrder() {
        if (orderQueue.isEmpty()) {
            System.out.println("No orders in queue.");
            return false;
        }
        currentOrder = orderQueue.poll();
        currentOrder.setStatus(Order.Status.PENDING);
        currentPizza = new Pizza();
        System.out.println("Now working on: " + currentOrder);
        return true;
    }

    public boolean hasCurrentOrder() {
        return currentOrder != null;
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public Pizza getCurrentPizza() {
        return currentPizza;
    }

    public int queueSize() {
        return orderQueue.size();
    }

    // --- Pizza assembly ---

    public void addIngredient(Ingredient ingredient) {
        if (!requireCurrentOrder()) return;
        currentPizza.addIngredient(ingredient);
    }

    public void undoLastIngredient() {
        if (!requireCurrentOrder()) return;
        currentPizza.removeLastIngredient();
    }

    /**
     * Compares the assembled pizza with the current order and prints the result.
     * Score = matched ingredients / required ingredients.
     */
    public void submitPizza() {
        if (!requireCurrentOrder()) return;

        List<Ingredient> required = currentOrder.getRequiredIngredients();
        List<Ingredient> made = currentPizza.getIngredients();

        int matched = 0;
        List<String> missing = new java.util.ArrayList<>();

        for (Ingredient req : required) {
            if (made.contains(req)) {
                matched++;
            } else {
                missing.add(req.getName());
            }
        }

        // Extra ingredients that were not requested
        List<String> extra = new java.util.ArrayList<>();
        for (Ingredient m : made) {
            if (!required.contains(m)) {
                extra.add(m.getName());
            }
        }

        System.out.println("\n--- Pizza review ---");
        System.out.println("Order required: " + required);
        System.out.println("Pizza made:     " + made);

        if (missing.isEmpty()) {
            currentOrder.setStatus(Order.Status.COMPLETED);
            System.out.println("Result: correct! Score " + matched + "/" + required.size());
        } else {
            currentOrder.setStatus(Order.Status.FAILED);
            System.out.println("Result: missing ingredient(s): " + missing);
            System.out.println("Score: " + matched + "/" + required.size());
        }

        if (!extra.isEmpty()) {
            System.out.println("Extra (not requested): " + extra);
        }

        System.out.println("--------------------\n");

        // Reset state after submission
        currentOrder = null;
        currentPizza = null;
    }

    private boolean requireCurrentOrder() {
        if (currentOrder == null) {
            System.out.println("No active order. Use 'next order' first.");
            return false;
        }
        return true;
    }

    public void printQueue() {
        if (orderQueue.isEmpty()) {
            System.out.println("Order queue is empty.");
            return;
        }
        System.out.println("Pending orders (" + orderQueue.size() + "):");
        for (Order o : orderQueue) {
            System.out.println("  " + o);
        }
    }
}
