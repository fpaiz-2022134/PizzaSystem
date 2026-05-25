package model.ingredients;

public class Base extends Ingredient {

    public Base(String name) {
        super(name);
    }

    @Override
    public String getCategory() {
        return "Base";
    }
}
