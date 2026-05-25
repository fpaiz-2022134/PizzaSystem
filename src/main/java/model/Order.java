package model;



import model.ingredients.Ingredient;

import java.util.List;

public class Order {

    public enum Status { NEW, PENDING, COMPLETED, FAILED }

    private static int idCounter = 1000;

    private final int id;
    private Status status;
    private final List<Ingredient> requiredIngredients;

    public Order(List<Ingredient> requiredIngredients) {
        this.id = ++idCounter;
        this.status = Status.NEW;
        this.requiredIngredients = requiredIngredients;
    }

    public int getId() { return id; }
    public Status getStatus() { return status; }
    public List<Ingredient> getRequiredIngredients() { return requiredIngredients; }

    public void setStatus(Status status) { this.status = status; }

    @Override
    public String toString() {
        return "Order #" + id + " [" + status + "] - " + requiredIngredients;
    }
}

