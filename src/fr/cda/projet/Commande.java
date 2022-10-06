package fr.cda.projet;
import fr.cda.util.Terminal;

import java.util.*;

/**
 * Classe de definition d'une commande
 */
public class Commande
{
    /**
     * Les caracteristiques d'une commande
     */
    private int     numero;         // numero de la commande
    private String  date;           // date de la commande. Au format JJ/MM/AAAA
    private String  client;         // nom du client
    private ArrayList<String> references = new ArrayList<>(); // les references des produits de la commande
    /**
     * The Reasons.
     */
    ArrayList<String> reasons = new ArrayList<>();

    /**
     * Constructeur
     *
     * @param numero the numero
     * @param date   the date
     * @param client the client
     */
    public Commande(int numero, String date, String client) {
        this.numero = numero;
        this.date = date;
        this.client = client;
    }

    /**
     * Ajout de motifs de non-livraison
     *
     * @param str the str
     */
    public void addReasons(String str){
        reasons.add(str);
    }

    /**
     * Ajout de références dans Reference arraylist..
     *
     * @param reference the reference
     */
    public void addRef(String reference){
        references.add(reference);
    }

    /**
     * Gets numero.
     *
     * @return getter and setters
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Sets numero.
     *
     * @param numero the numero
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Gets client.
     *
     * @return the client
     */
    public String getClient() {
        return client;
    }

    /**
     * Sets client.
     *
     * @param client the client
     */
    public void setClient(String client) {
        this.client = client;
    }

    /**
     * Gets references.
     *
     * @return the references
     */
    public ArrayList<String> getReferences() {
        return references;
    }

    /**
     * Sets references.
     *
     * @param references the references
     */
    public void setReferences(ArrayList<String> references) {
        this.references = references;
    }

    /**
     * afficher les references
     *
     * @return the string
     */
    public String displayRef(){
        String res = "";
        for (int i = 0; i <references.size(); i++) {
            res = res+ references.get(i) + "\n" + "            ";
        }
        return res;
    }

    private int getReferenceIndex(String ref)
    {
        for (int i = 0; i < references.size(); i++)
            if (references.get(i).split("=")[0].equals(ref))
                return (i);
        return (-1);
    }

    public void setQuantity(String ref, int value)
    {
        int index;
        index = getReferenceIndex(ref);
        if (index == -1)
            return ; // Reference non trouvee
        if (value <= 0)
            references.remove(index);
        else
            references.set(index, references.get(index).split("=")[0] + "=" + value);
    }

    public int getQuantity(String ref)
    {
        int     index;
        index = -1;
        while (++index < references.size()) // Cherche la reference
            if (references.get(index).split("=")[0].equals(ref))
                break;
        if (index == references.size()) // Si pas trouve
            return (-1); // Erreur
        return (Integer.parseInt(references.get(index).split("=")[1]));
    }

    /**
     * afficher les reasons pour non livrer
     *
     * @return the string
     */
    public String displayReasons(){
        String res = "";
        for (int i = 0; i <reasons.size(); i++) {
            res = res+ reasons.get(i) + "\n";
        }
        return res;
    }

    /**
     * method toString
     *
     * @param displayresons the displayresons
     * @return Method toString
     */
    public String toString(boolean displayresons) {
        return "Commande         :    " + numero + '\n'+
                "      Date         :    " + date + '\'' + '\n'+
                "      Client       :    " + client + '\'' + '\n'+
                "      RefProduits  :    " + '\n' + '\n'+ "            " +
                displayRef() + '\n'+
                (displayresons ?  displayReasons(): "") + '\n'+
                "__________________________________________________"+ '\n'+ '\n';
    }
}