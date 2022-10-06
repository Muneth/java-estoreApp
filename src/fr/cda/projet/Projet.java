package fr.cda.projet;
import java.io.*;
import java.util.*;
import fr.cda.util.*;

/**
 * Classe principale d'execution du projet
 */
public class Projet {
    public static void main(String[] a_args) {
        Terminal.ecrireStringln("Execution du projet ");
        Site site = new Site();
        IHMSite ihm = new IHMSite(site);
    }
}
