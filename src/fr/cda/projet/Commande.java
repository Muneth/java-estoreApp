package fr.cda.projet;
import fr.cda.util.Terminal;

import java.util.*;

// Classe de definition d'une commande
//
public class Commande
{
    // Les caracteristiques d'une commande
    //
    private int     numero;         // numero de la commande
    private String  date;           // date de la commande. Au format JJ/MM/AAAA
    private String  client;         // nom du client
    private ArrayList<String> references = new ArrayList<>(); // les references des produits de la commande
    ArrayList<String> reasons = new ArrayList<>();

    // Constructeur
    //
    public Commande(int numero, String date, String client) {
        this.numero = numero;
        this.date = date;
        this.client = client;
    }

    // Adding reasons for non delivery
    public void addReasons(String str){
       reasons.add(str);
    }
    // Adding refrences in Refrence arraylist..
    public void addRef(String reference){
       references.add(reference);
    }
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public ArrayList<String> getReferences() {
        return references;
    }

    public void setReferences(ArrayList<String> references) {
        this.references = references;
    }

    // afficher les refrences
    public String displayRef(){
        String res = "";
        for (int i = 0; i <references.size(); i++) {
            res = res+ references.get(i) + "\n" + "            ";
        }
        return res;
    }

    // afficher les resons pour non livrer
    public String displayReasons(){
        String res = "";
        for (int i = 0; i <reasons.size(); i++) {
            res = res+ reasons.get(i) + "\n";
        }
        return res;
    }

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