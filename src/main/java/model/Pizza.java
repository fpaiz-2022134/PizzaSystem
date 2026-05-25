package model;

import model.ingredients.Ingredient;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Represents a pizza being assembled.
 * Uses a Stack internally so the last added ingredient can be removed (undo).
 */
public class Pizza {

    // Stack supports push/pop — perfect for "undo last ingredient"
    private final Stack<Ingredient> ingredientStack;

    public Pizza() {
        this.ingredientStack = new Stack<>();
    }

    /** Adds an ingredient on top of the stack. */
    public void addIngredient(Ingredient ingredient) {
        ingredientStack.push(ingredient);
        System.out.println("Added: " + ingredient);
    }

    /** Removes the last added ingredient (undo). Returns null if empty. */
    public Ingredient removeLastIngredient() {
        if (ingredientStack.isEmpty()) {
            System.out.println("No ingredients to remove.");
            return null;
        }
        Ingredient removed = ingredientStack.pop();
        System.out.println("Removed: " + removed);
        return removed;
    }

    /** Returns a flat list of current ingredients (bottom to top). */
    public List<Ingredient> getIngredients() {
        return new ArrayList<>(ingredientStack);
    }

    public boolean isEmpty() {
        return ingredientStack.isEmpty();
    }

    /** Prints the current pizza layers. */
    public void printStatus() {
        if (ingredientStack.isEmpty()) {
            System.out.println("  (empty pizza)");
            return;
        }
        List<Ingredient> list = getIngredients();
        for (int i = list.size() - 1; i >= 0; i--) {
            String marker = (i == list.size() - 1) ? " <- top" : "";
            System.out.println("  [" + (i + 1) + "] " + list.get(i) + marker);
        }
    }
}
