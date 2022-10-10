package fr.cda.projet;
import java.util.*;

/**
 * The type Order.
 */
public class Order {
    private final int cmdNumber;
    private final String date;
    private final String client;
    private final ArrayList<String> references = new ArrayList<>();
    private final ArrayList<String> reasons = new ArrayList<>();

    /**
     * Instantiates a new Order.
     *
     * @param cmdNumber the cmdNumber
     * @param date   the date
     * @param client the client
     */
    public Order(int cmdNumber, String date, String client) {
        this.cmdNumber = cmdNumber;
        this.date = date;
        this.client = client;
    }

    /**
     * Add reasons.
     *
     * @param str the str
     */
    public void addReasons(String str){
        reasons.add(str);
    }

    /**
     * Add ref.
     *
     * @param reference the reference
     */
    public void addRef(String reference){
        references.add(reference);
    }

    /**
     * Gets cmdNumber.
     *
     * @return the cmdNumber
     */
    public int getCmdNumber() {
        return cmdNumber;
    }

    /**
     * Gets references.
     *
     * @return the references
     */
    public ArrayList<String> getReferences() {
        return references;
    }

    private int getReferenceIndex(String ref) {
        for (int i = 0; i < references.size(); i++)
            if (references.get(i).split("=")[0].equals(ref))
                return (i);
        return (-1);
    }

    /**
     * Sets quantity.
     *
     * @param ref   the ref
     * @param value the value
     */
    public void setQuantity(String ref, int value) {
        int index;
        index = getReferenceIndex(ref);
        if (index == -1)
            return ; // Reference not found
        if (value <= 0)
            references.remove(index); // Remove reference if value is negative
        else
            references.set(index, references.get(index).split("=")[0] + "=" + value); // set new value for the reference
    }

    /**
     * Gets quantity.
     *
     * @param ref the ref
     * @return the quantity
     */
    public int getQuantity(String ref) {
        for (String reference : references)
            if (reference.split("=")[0].equals(ref))
                return (Integer.parseInt(reference.split("=")[1]));
        return (-1);
    }

    /**
     * Display ref string.
     *
     * @return the string
     */
    private String displayRef(){
        StringBuilder res = new StringBuilder();
        for (String reference : references) {
            res.append(reference).append("\n").append("            ");
        }
        return res.toString();
    }

    /**
     * Display reasons string.
     *
     * @return the string
     */
    private String displayReasons(){
        StringBuilder res = new StringBuilder();
        for (String reason : reasons) {
            res.append(reason).append("\n");
        }
        return res.toString();
    }

    /**
     * To string string.
     *
     * @param displayReasons the displayReasons
     * @return the string
     */
    public String toString(boolean displayReasons) {
        return "Order         :    " + cmdNumber + '\n'+
                "      Date         :    " + date + '\'' + '\n'+
                "      Client       :    " + client + '\'' + '\n'+
                "      RefProduits  :    " + '\n' + '\n'+ "            " +
                displayRef() + '\n'+
                (displayReasons ?  displayReasons(): "") + '\n'+
                "__________________________________________________"+ '\n'+ '\n';
    }

}