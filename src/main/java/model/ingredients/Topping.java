package model.ingredients;

public class Topping extends Ingredient {

    public Topping(String name) {
        super(name);
    }

    @Override
    public String getCategory() {
        return "Topping";
    }
}

