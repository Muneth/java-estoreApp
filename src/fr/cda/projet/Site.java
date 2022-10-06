package fr.cda.projet;
import java.util.*;
import fr.cda.util.*;

/**
 * Classe de definition du site de vente
 */

public class Site {
    private ArrayList<Produit> stock;
    private ArrayList<Commande> commandes;
    private ArrayList<Commande> commandesNonLivress;
    private ArrayList<Commande> commandesLivress;
    private ArrayList<String> refStockUpdate;
    private ArrayList<String> refStockUpdatelivrer;

    /**
     * Constructeur
     */
    public Site() {
        stock = new ArrayList<Produit>();
        commandes = new ArrayList<Commande>();
        commandesNonLivress = new ArrayList<Commande>();
        commandesLivress = new ArrayList<Commande>();
        refStockUpdate = new ArrayList<>();
        refStockUpdatelivrer = new ArrayList<>();

        // lecture du fichier data/Produits.txt
        //  pour chaque ligne on cree un Produit que l'on ajoute a stock
        initialiserStock("data/Produits.txt");

        //  lecture du fichier data/Commandes.txt
        //  pour chaque ligne on cree une commande ou on ajoute une reference
        //  d'un produit a une commande existante.
        initialiserCommande("data/Commandes.txt");

        // initialiser le method
        stockMissing();
    }

    /**
     * Methode qui retourne sous la forme d'une chaine de caractere
     * tous les produits du stock
     */
    public String listerTousProduits() {
        String res="";
        for (int i = 0; i < stock.size(); i++) {
            Produit prod = stock.get(i);
            res = res + prod.toString() + "\n";
        }
        return res;
    }

    /**
     *  Methode qui retourne sous la forme d'une chaine de caractere\
     *  toutes les commandes
     */

    public String listerToutesCommandes() {
        String res="";
        for (Commande comd : commandes) {
            res = res + comd.toString(false) + "\n";
        }
        return res;
    }

    /**
     * Methode qui retourne sous la forme d'une chaine de caractere
     * une commande
     */
    public String listerCommande(int numero){
        String res="";
        boolean trouve = false;
        for (Commande commande : commandes) {
            int num = commande.getNumero();
            if (num == numero) {
                res = res + commande.toString(false);
                trouve = true;
            }
        }
        if(!trouve) {
            res = "   la commande n'existe pas   ";
        }
        return res;
    }

    /**
     * Afficher les commandes non livrer
     */
    public String listecommandesNonLivre(){
        String res= """
                Les commmandes nom liveres  : 
                =============================
                
                """;
        for (Commande comd : commandesNonLivress) {
            res = res + comd.toString(true) + "\n";
        }
        return res;
    }

    /**
     * mettre à jour les stocks
     */
    public void updateStock(int stockN, String stockref ){
        for (Produit produit : stock) {
            stockref = produit.getReference();
            for (String s : refStockUpdate) {
                if (stockref.equals(s)) {
                    produit.setQuantite(stockN);
                }
            }
        }
    }

    /**
     * Calculer ventes for les commandes livrer
     */
    public String calculerVentes(){
        String res ="";
        String res1 ="";
        String cmdRref = "";
        int cmdQuantity = 0;
        double total = 0;
        for (Produit produit : stock) {
            String stockref = produit.getReference();
            for (int i = 0; i < commandesLivress.size(); i++) {
                res = "Commande  :    " + commandesLivress.get(i).getNumero()+ "  " + '\n';
                String[] refs = commandesLivress.get(i).getReferences().toArray(new String[0]);
                for (String ref : refs) {
                    String[] refsChamps = ref.split("=");
                    cmdRref = refsChamps[0];
                    cmdQuantity = Integer.parseInt(refsChamps[1]);
                    if(stockref.equals(cmdRref)){
                        res1 = res1 + '\n'+ "            "+ produit.getNom() + "    " + cmdQuantity + "    " + (cmdQuantity * produit.getPrix()) + '\n';
                        total = total + (cmdQuantity * produit.getPrix());
                    }
                }
                res = res + res1 +  '\n' + "=====================" + '\n' + " SOMME   :   " + total  + "   euros";
            }
        }
        return res;
    }

    /**
     * Chargement du fichier de stock
     */
    private void initialiserStock(String nomFichier) {
        String[] lignes = Terminal.lireFichierTexte(nomFichier);
        for(String ligne :lignes) {
            //System.out.println(ligne);
            String[] champs = ligne.split(";",4);
            String reference = champs[0];
            String nom = champs[1];
            double prix = Double.parseDouble(champs[2]);
            int quantite =  Integer.parseInt(champs[3]);
            Produit p = new Produit(reference, nom, prix, quantite);
            stock.add(p);
        }
    }

    /**
     * Chargement du fichier de commande
     * Et ajouter la nouvelle commande si la commande existe déjà alors seulement en ajoutant refrence
     */

    private void initialiserCommande(String nomFichier) {
        String[] lignes = Terminal.lireFichierTexte(nomFichier);
        for(String ligne :lignes) {
            //System.out.println(ligne);
            String[] champs = ligne.split(";",4);
            int numeroCmd = Integer.parseInt(champs[0]);
            String date = champs[1];
            String nom = champs[2];
            String reference = champs[3];

            Commande c;
            int index = rechercheParIndex(numeroCmd);
            if(index != -1){
                commandes.get(index).addRef(reference);
            } else {
                c = new Commande(numeroCmd, date, nom);
                c.addRef(reference);
                commandes.add(c);
            }

//            Commande c = rechercheCommande(numeroCmd);
//            if (c == null){
//                c = new Commande(numeroCmd, date, nom);
//                c.addRef(reference);
//
//                commandes.add(c);
//            } else {
//                c.addRef(reference);
//            }
        }
    }

    /**
     * vérifier si la commande est déjà présente dans le Arraylist des commandes return commande
     */
    private Commande rechercheCommande(int num){
        for (Commande c : commandes) {
            if(c.getNumero() == num){
                return c;
            }
        }
        return null;
    }

    /**
     * vérifier si la commande est déjà présente dans le Arraylist des commandes return index de commande
     */
    private int rechercheParIndex(int num){
        for (int i =0; i < commandes.size();i++)
            if(commandes.get(i).getNumero() == num)
                return (i);
        return (-1);
    }

    /**
     *  Rechercher les commandes non livrer
     */
    private void stockMissing(){
        String cmdRref = "";
        int cmdQuantity = 0;
        String res = "";
        for (Commande commande : commandes) {
            String[] refs = commande.getReferences().toArray(new String[0]);
            for (String ref : refs) {
                String[] refsChamps = ref.split("=");
                cmdRref = refsChamps[0];
                cmdQuantity = Integer.parseInt(refsChamps[1]);
                if(notHaveQuantity(cmdRref, cmdQuantity)){
                    res = "   il manque    " + (cmdQuantity - currentQuantityInStock(cmdRref, cmdQuantity)) +"    "+ cmdRref;
                    commande.addReasons(res);  // ajouter les reasons pour non livrer
                    if(!verifyRef(cmdRref)){
                        refStockUpdate.add(cmdRref); // ajouter les refrence de stock pour mettre à jour
                    }
                }
                if(refStockDelivered(cmdRref, cmdQuantity)){
                    refStockUpdatelivrer.add(ref);
                }
            }

            // Ajouter les commandes non livrer dans arraylist
            //
            if(notHaveQuantity(cmdRref, cmdQuantity)){
                commandesNonLivress.add(commande);
            }
            //Ajouter les commandes livrer dans arraylist
            //
            if(refStockDelivered(cmdRref, cmdQuantity)){
                commandesLivress.add(commande);

            }
        }
    }

    /**
     * vérifier si la quantité est inférieure en stock puis retourner la quantité
     * @param ref
     * @param quan
     * @return stock quantity
     */
    private int currentQuantityInStock(String ref, int quan){
        int stkquan = 0;
        for(Produit p : stock){
            String key = p.getReference();
            int val = p.getQuantite();
            if (key.equals(ref) && val < quan) {
                stkquan = val;
            }
        }
        return stkquan;
    }

    /**
     * vérifier si la quantité est plus en stock
     * @param ref
     * @param quan
     * @return boolean
     */

    private boolean refStockDelivered(String ref, int quan){
        boolean stockRef = false;
        for(Produit p : stock){
            String key = p.getReference();
            int val = p.getQuantite();
            if (key.equals(ref) && val > quan) {
                stockRef = true;
                break;
            }
        }
        return stockRef;
    }

    /**
     * vérifier si la quantité est inférieure en stock
     * @param ref
     * @param quan
     * @return boolean
     */
    private boolean notHaveQuantity(String ref, int quan){
        boolean lessQuan = false;
        for(Produit p : stock){
            String key = p.getReference();
            int val = p.getQuantite();
            if (key.equals(ref) && val < quan) {
                lessQuan = true;
                break;
            }
        }
        return lessQuan;
    }

    /**
     * Method to verify if the refrence is already in the arraylist
     * @param ref
     * @return boolean
     */

    private boolean verifyRef(String ref){
        for (int i = 0; i <refStockUpdate.size(); i++) {
            if(refStockUpdate.get(i).equals(ref)){
                return true;
            }
        }
        return false;
    }

}