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
     *
     * @param reference the reference
     * @param nom       the nom
     * @param prix      the prix
     * @param quantite  the quantite
     */
    public Produit(String reference, String nom, double prix, int quantite) {
        this.reference = reference;
        this.nom = nom;
        this.prix = prix;
        this.quantite = quantite;
    }

    /**
     * Gets reference.
     *
     * @return getters and setters
     */
    public String getReference() {
        return reference;
    }

    /**
     * Sets reference.
     *
     * @param reference the reference
     */
    public void setReference(String reference) {
        this.reference = reference;
    }

    /**
     * Gets nom.
     *
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Sets nom.
     *
     * @param nom the nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Gets prix.
     *
     * @return the prix
     */
    public double getPrix() {
        return prix;
    }

    /**
     * Sets prix.
     *
     * @param prix the prix
     */
    public void setPrix(double prix) {
        this.prix = prix;
    }

    /**
     * Gets quantite.
     *
     * @return the quantite
     */
    public int getQuantite() {
        return quantite;
    }

    /**
     * Sets quantite.
     *
     * @param quantite the quantite
     */
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