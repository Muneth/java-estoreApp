package fr.cda.projet;
import fr.cda.ihm.Formulaire;
import fr.cda.ihm.FormulaireInt;
import fr.cda.util.Terminal;
import java.util.ArrayList;

/**
 * The type Gui modifier commande.
 */
public class GUIModifierCommande implements FormulaireInt {
    private final Site site;
    private final Formulaire parent;
    private final Order order;
    private final ArrayList<String> ref;

    /**
     * Instantiates a new Gui modifier commande.
     *
     * @param parent the parent
     * @param site   the site
     * @param order  the order
     */
    public GUIModifierCommande(Formulaire parent, Site site, Order order) {
        this.site = site;
        this.order = order;
        this.parent = parent;
        Formulaire form = new Formulaire("Site de vente", this, 700, 400);
        form.setPosition(20,10);

        // Creating forms according to the length of the references arraylist in commandes
        //
        ref = order.getReferences();
        String nom = "";
        String value;
        for (String s : ref) {
            nom = s.split("=")[0];
            value = s.split("=")[1];
            form.addText(nom, nom, true, value);
        }
        form.addLabel("");
        form.addButton("AJOUT_STOCK","Valider");
        form.afficher();
    }

    public void submit(Formulaire form,String nomSubmit) {
        if (nomSubmit.equals("AJOUT_STOCK")) {
            String strQty;
            int qty;
            for (String s : ref) {
                strQty = form.getValeurChamp(s.split("=")[0]);
                if (strQty.length() < 1)
                    continue;
                // if there is a special character
                if (strQty.length() >= 2 && (strQty.charAt(0) == '-' || strQty.charAt(0) == '+') && strQty.charAt(1) >= '0' && strQty.charAt(1) <= '9') {
                    // + : Add
                    // - : Subtract
                    qty = order.getQuantity(s.split("=")[0]) + (Integer.parseInt(strQty.substring(1)) * (strQty.charAt(0) == '-' ? -1 : 1));
                    Terminal.ecrireStringln("Final qty: " + qty);
                } else // otherwise
                    qty = Integer.parseInt(strQty); // write normal
                order.setQuantity(s.split("=")[0], qty);
            }
            parent.setValeurChamp("RESULTATS", site.displayAllOrders());
            form.fermer();
        }
    }
}
