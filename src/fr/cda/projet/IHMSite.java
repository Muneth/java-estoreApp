package fr.cda.projet;

import fr.cda.ihm.*;

/**
 * The type Ihm site.
 */
// Classe de definition de l'IHM principale du compte
//
public class IHMSite implements FormulaireInt
{
    private Site site;  // Le site

    /**
     * The Form.
     */
    Formulaire form;

    /**
     * Instantiates a new Ihm site.
     *
     * @param site the site
     */
// Constructeur
    //
    public IHMSite(Site site)
    {
        this.site = site;

        // Creation du formulaire
        form = new Formulaire("Site de vente",this,1100,730);

        //  Creation des elements de l'IHM
        //
        form.addLabel("Afficher tous les produits du stock");
        form.addButton("AFF_STOCK","Tous le stock");
        form.addLabel("");
        form.addLabel("Afficher tous les bons de commande");
        form.addButton("AFF_COMMANDES","Toutes les commandes");
        form.addLabel("");
        form.addText("NUM_COMMANDE","Numero de commande",true,"1");
        form.addButton("AFF_COMMANDE","Afficher");
        form.addLabel("");

        form.addButton("MOD_COMMANDE","Modifier");
        form.addLabel("");
        form.addButton("LIVRER","Livrer");
        form.addLabel("");
        form.addButton("CALCULER","Calculer ventes");
        form.addLabel("");

        form.setPosition(400,0);
        form.addZoneText("RESULTATS","Resultats",
                            true,
                            "",
                            600,700);

        // Affichage du formulaire
        form.afficher();
    }

    // Methode appellee quand on clique dans un bouton
    //
    public void submit(Formulaire form,String nomSubmit)
    {

        // Affichage de tous les produits du stock
        //
        if (nomSubmit.equals("AFF_STOCK"))
            {
                String res = site.listerTousProduits();
                form.setValeurChamp("RESULTATS",res);
            }

        // Affichage de toutes les commandes
        //
        if (nomSubmit.equals("AFF_COMMANDES"))
            {
                String res = site.listerToutesCommandes();
                form.setValeurChamp("RESULTATS",res);
            }

        // Affichage d'une commande
        //
        if (nomSubmit.equals("AFF_COMMANDE"))
            try{
                {
                    String numStr = form.getValeurChamp("NUM_COMMANDE");
                    int num = Integer.parseInt(numStr);
                    String res = site.listerCommande(num);
                    form.setValeurChamp("RESULTATS",res);
                }

            } catch (NumberFormatException e){
                String  res = "Entrer le numero de commande";
                form.setValeurChamp("RESULTATS",res);
                System.out.println("Throw exception  :" + e);
            }

        if (nomSubmit.equals("LIVRER"))
        {
            String res = site.listecommandesNonLivre();
            form.setValeurChamp("RESULTATS",res);
        }

        if (nomSubmit.equals("MOD_COMMANDE"))
        {
            GUIModifierCommande guiModifierCommande = new GUIModifierCommande(this, this.site);
        }

        if (nomSubmit.equals("CALCULER"))
        {
            String res = site.calculerVentes();
            form.setValeurChamp("RESULTATS",res);

        }
    }

    /**
     * Afficher result.
     *
     * @param res the res
     */
    public void afficherResult(String res){
        form.setValeurChamp("RESULTATS", res);
    }

}