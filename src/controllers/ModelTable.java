package controllers;

public class ModelTable {

    String id, name, ingrediencies, price;

    public ModelTable(String id, String name, String ingrediencies, String price) {
        this.id = id;
        this.name = name;
        this.ingrediencies = ingrediencies;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngrediencies() {
        return ingrediencies;
    }

    public void setIngrediencies(String ingrediencies) {
        this.ingrediencies = ingrediencies;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
