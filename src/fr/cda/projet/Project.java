package fr.cda.projet;
import fr.cda.util.*;

/**
 * The type Project.
 */
public class Project {
    /**
     * The entry point of application.
     *
     * @param a_args the input arguments
     */

    public static void main(String[] a_args) {
        Terminal.ecrireStringln("Execution du projet ");
        Site site = new Site();
        IHMSite ihm = new IHMSite(site);
    }
}
