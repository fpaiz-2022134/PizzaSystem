package model.ingredients;

/**
 * Base class for all pizza ingredients.
 * Uses inheritance so Base, Sauce and Topping share common behavior.
 */
public abstract class Ingredient {

    private final String name;

    public Ingredient(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /** Returns the ingredient category (Base, Sauce, Topping). */
    public abstract String getCategory();

    @Override
    public String toString() {
        return getCategory() + ": " + name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Ingredient)) return false;
        Ingredient other = (Ingredient) obj;
        return name.equalsIgnoreCase(other.name);
    }

    @Override
    public int hashCode() {
        return name.toLowerCase().hashCode();
    }
}

