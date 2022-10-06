package fr.cda.projet;

/**
 * Classe de definition d'un produit du stock
 */

public class Produit
{
    /**
     * Les caracteristiques d'un Produit
     */

    private String  reference;      // reference du produit
    private String  nom;            // nom du produit
    private double  prix;           // prix du produit
    private int quantite;       // quantit� du produit

    /**
     * Constructeur
     */
    public Produit(String reference, String nom, double prix, int quantite) {
        this.reference = reference;
        this.nom = nom;
        this.prix = prix;
        this.quantite = quantite;
    }

    /**
     * @return getters and setters
     */

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getQuantite() {
        return quantite;
    }
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    /**
     * @return Method ToString
     */

    @Override
    public String toString() {
        return
                "Ref =   '" + reference + '\'' +
                        ", Nom =   '" + nom + '\'' +
                        ", Prix =   " + prix +
                        ", Quantite =   " + quantite + '\n';
    }
}