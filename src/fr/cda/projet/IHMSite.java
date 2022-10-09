package fr.cda.projet;
import fr.cda.ihm.*;

/**
 * The type Ihm site.
 */
public class IHMSite implements FormulaireInt {
    private final Site site;
    /**
     * The Form.
     */
    Formulaire form;

    /**
     * Instantiates a new Ihm site.
     *
     * @param site the site
     */
    public IHMSite(Site site) {

        this.site = site;
        form = new Formulaire("Site de vente",this,1100,730);
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
        form.addButton("NONLIVRER","Non Livrer");
        form.addLabel("");
        form.addButton("CALCULER","Calculer ventes");
        form.addLabel("");
        form.setPosition(400,0);
        form.addZoneText("RESULTATS","Resultats",
                            true,
                            "",
                            600,700);

        // Display the form
        form.afficher();
    }

    public void submit(Formulaire form,String nomSubmit) {

        // Display all products
        //
        if (nomSubmit.equals("AFF_STOCK"))
            {
                String res = site.displayAllProducts();
                form.setValeurChamp("RESULTATS",res);
            }

        // Display all orders
        //
        if (nomSubmit.equals("AFF_COMMANDES"))
            {
                String res = site.displayAllOrders();
                form.setValeurChamp("RESULTATS",res);
            }

        // Display order by order number
        //
        if (nomSubmit.equals("AFF_COMMANDE"))
            try{
                {
                    String numStr = form.getValeurChamp("NUM_COMMANDE");
                    int num = Integer.parseInt(numStr);
                    String res = site.displayOrder(num);
                    form.setValeurChamp("RESULTATS",res);
                }

            // Catching and displaying the errors
            //
            } catch (NumberFormatException e){
                String  res = "Entrer le numero de commande";
                form.setValeurChamp("RESULTATS",res);
                System.out.println("Throw exception  :" + e);
            }

        // Display all delivered orders
        //
        if (nomSubmit.equals("LIVRER"))
        {
            String res = site.displayOrdersDelivered();
            form.setValeurChamp("RESULTATS",res);
        }

        // Display all orders not delivered
        //
        if (nomSubmit.equals("NONLIVRER"))
        {
            String res = site.displayOrdersNotDelivered();
            form.setValeurChamp("RESULTATS",res);
        }

        // Modify order quantities
        //
        if (nomSubmit.equals("MOD_COMMANDE"))
        {
            Order order;
            try {
                order = site.getOrderByNumber(Integer.parseInt(form.getValeurChamp("NUM_COMMANDE")));
                if (order == null) // Order not find
                    throw new Exception();
            } catch (Exception e) {
                String  res = "Entrer le numero de order";
                form.setValeurChamp("RESULTATS",res);
                return ;
            }
            GUIModifierCommande guiModifierCommande = new GUIModifierCommande(form, this.site, order);
        }

        // Calculating final sales for the orders delivered
        //
        if (nomSubmit.equals("CALCULER"))
        {
            String res = site.calculateSales();
            form.setValeurChamp("RESULTATS",res);

        }
    }
}