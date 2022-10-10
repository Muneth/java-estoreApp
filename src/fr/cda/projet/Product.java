package fr.cda.projet;

/**
 * The type Product.
 */
public record Product(String reference, String name, double price, int quantity) {
    /**
     * Instantiates a new Product.
     *
     * @param reference the reference
     * @param name      the name
     * @param price     the price
     * @param quantity  the quantity
     */
    public Product {
    }

    /**
     * Gets reference.
     *
     * @return the reference
     */
    @Override
    public String reference() {
        return reference;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    @Override
    public String name() {
        return name;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    @Override
    public double price() {
        return price;
    }

    /**
     * Gets quantity.
     *
     * @return the quantity
     */
    @Override
    public int quantity() {
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