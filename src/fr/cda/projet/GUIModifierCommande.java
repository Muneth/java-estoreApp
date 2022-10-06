package fr.cda.projet;
import fr.cda.ihm.Formulaire;
import fr.cda.ihm.FormulaireInt;
import fr.cda.util.Terminal;
import java.util.ArrayList;

/**
 * The type Gui modifier commande.
 */
public class GUIModifierCommande implements FormulaireInt {
    private Site site;
    /**
     * The Form pp.
     */
    private Formulaire parent;
    private Commande commande;
    private ArrayList<String> ref;

    /** Instantiates a new Gui modifier commande.
     *
     * @param parent
     * @param site
     * @param commande
     */
    public GUIModifierCommande(Formulaire parent, Site site, Commande commande) {
        this.site = site;
        this.commande = commande;
        this.parent = parent;
        Formulaire form = new Formulaire("Site de vente", this, 700, 400);
        form.setPosition(20,10);

        ref = commande.getReferences();
        String nom = "";
        String value;
        for (int i = 0; i < ref.size(); i++) {
            nom = ref.get(i).split("=")[0];
            value = ref.get(i).split("=")[1];
            form.addText(nom, nom, true, value);
        }
        form.addLabel("");
        form.addButton("AJOUT_STOCK","Valider");
        form.afficher();
    }

    /**
     * Methode appellee quand on clique dans un bouton
     */
    public void submit(Formulaire form,String nomSubmit) {
        if (nomSubmit.equals("AJOUT_STOCK"))
        {
            String strQty;
            int qty;

            for (int i = 0; i < ref.size(); i++)
            {
                strQty = form.getValeurChamp(ref.get(i).split("=")[0]);
                try {
                    if (strQty.length() < 1)
                        continue;
                    // Si caractere special
                    if (strQty.length() >= 2 && (strQty.charAt(0) == '-' || strQty.charAt(0) == '+') && strQty.charAt(1) >= '0' && strQty.charAt(1) <= '9')
                    {
                        // + : Ajoute
                        // - : Soustrait
                        qty = commande.getQuantity(ref.get(i).split("=")[0]) + (Integer.valueOf(strQty.substring(1)) * (strQty.charAt(0) == '-' ? -1 : 1));
                        Terminal.ecrireStringln("Final qty: " + qty);
                    }
                    else // Sinon
                        qty = Integer.parseInt(strQty); // Ecriture basique
                    commande.setQuantity(ref.get(i).split("=")[0], qty);
                } catch (Exception e) { continue; }
            }
            parent.setValeurChamp("RESULTATS", site.listerToutesCommandes());
            form.fermer();
        }
    }
}
