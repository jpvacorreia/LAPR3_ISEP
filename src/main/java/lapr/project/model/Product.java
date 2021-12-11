package lapr.project.model;

import lapr.project.controller.ApplicationPOT;


public class Product {

    private int id;
    private String name;
    private double weight;
    private final double price;
    private int id_type;

    public Product(int id, String name, double weight, double price, int id_type){
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.price = price;
        this.id_type = id_type;
    }

    public Product(double price){
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

    public double getWeight() {
        return weight;
    }

    public int getId_type() {
        return id_type;
    }


    /**
     * Retrieves a Product from the DataBase with the id passed as parameter.
     *
     * @param id        Product identification number.
     */
    public static Product getProductByID(int id){
        return ApplicationPOT.getInstance().getPlatform().getProductDB().getProduct(id);
    }
}
