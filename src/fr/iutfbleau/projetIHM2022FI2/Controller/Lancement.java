package fr.iutfbleau.projetIHM2022FI2.Controller;
import fr.iutfbleau.projetIHM2022FI2.View.*;

import java.util.Scanner;

/**
 * La classe <code>Lancement</code> contient le main de l'application et permet de choisir pour quel utilisateur la lancer
 *  
 * @version 1.1
 * @author Adrien Dos Santos, Claire Gobert, Kayyissa Haissous
 */

public class Lancement {
    
    /**
   * main qui demande en console pour quelle utilisateur l'application doit être lancée
   *
   * @param args d'éventuels arguments en ligne de commande
   */
    public static void main(String[] args){

		System.out.println("Qui êtes vous ?");
        System.out.println("Administrateur : 0");
        System.out.println("Enseignant : 1");
        System.out.println("Etudiant : 2");
        Scanner sc = new Scanner(System.in);
        int user = sc.nextInt();
        sc.close();

        // lance l'application en fonction de la réponse de l'utilisateur
        switch (user){
            case 0:
                new Admin();
                break;
            case 1:
                new Enseignant();
                break;
            case 2:
                new Etudiant();
                break;
            default:
            
        }
    }
}
