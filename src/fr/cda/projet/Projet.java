package fr.cda.projet;
import java.io.*;
import java.util.*;
import fr.cda.util.*;

/**
 * The type Projet.
 */
public class Projet {
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
