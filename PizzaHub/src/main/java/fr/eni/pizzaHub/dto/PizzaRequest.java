package fr.eni.pizzaHub.dto;

public class PizzaRequest {
    private String name;
    private String size;

    public String getName() {return this.name;}
    public String getSize() {return this.size;}
    public void setName(String name) {this.name = name;}
    public void setSize(String size) {this.size = size;}
}