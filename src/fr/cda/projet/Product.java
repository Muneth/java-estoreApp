package fr.cda.projet;

/**
 * The type Product.
 */
public class Product {
    private final String  reference;
    private final String  name;
    private final double price;
    private final int quantity;

    /**

     * Instantiates a new Product.
     *
     * @param reference the reference
     * @param name      the name
     * @param price     the price
     * @param quantity  the quantity
     */
    public Product(String reference, String name, double price, int quantity) {
        this.reference = reference;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Gets reference.
     *
     * @return the reference
     */
    public String getReference() {
        return reference;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Gets quantity.
     *
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    public String toString() {
        return
                "Ref =   " + reference +
                ", Name =   " + name + '\'' + '\n' +
                " Price =   " + price +
                ", Quantity =   " + quantity + '\n' +
                '\n' +
                "======================================";
    }
}