package model.ingredients;

public class Sauce extends Ingredient {

    public Sauce(String name) {
        super(name);
    }

    @Override
    public String getCategory() {
        return "Sauce";
    }
}

