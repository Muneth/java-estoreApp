package fr.cda.projet;
import fr.cda.ihm.Formulaire;
import fr.cda.ihm.FormulaireInt;
public class GUIModifierCommande implements FormulaireInt {
    private Site site;
    IHMSite formPP;
    public GUIModifierCommande(IHMSite formPP, Site site) {
        this.formPP = formPP;
        this.site = site;
        Formulaire form = new Formulaire("Site de vente", this, 700, 400);

        form.setPosition(20,10);
        form.addLabel("");
        form.addText("LIVRE1", "LIVRE-1", true, "");
        form.addText("LIVRE2", "LIVRE-3", true, "");
        form.addText("VETEMENT1", "VETEMENT-1", true, "");
        form.addLabel("");
        form.addButton("AJOUT_STOCK","Valider");

        form.afficher();
    }
    public void submit(Formulaire form,String nomSubmit) {
        if (nomSubmit.equals("AJOUT_STOCK")){
            try {
            int res1 = Integer.parseInt(form.getValeurChamp("LIVRE1"));
            int res2 = Integer.parseInt(form.getValeurChamp("LIVRE2"));
            int res3 = Integer.parseInt(form.getValeurChamp("VETEMENT1"));
            site.updateStock(res1, "LIVRE1");
            site.updateStock(res2, "LIVRE2");
            site.updateStock(res3, "VETEMENT1");
            String res = this.site.listerTousProduits();
            this.formPP.afficherResult(res);
            form.fermer();

            }  catch (NumberFormatException e){
                String  res = "Entrer un numéro pour mettre à jour le stock";
                this.formPP.afficherResult(res);
                form.fermer();
                System.out.println("Throw exception  :" + e);
            }
        }
    }
}
