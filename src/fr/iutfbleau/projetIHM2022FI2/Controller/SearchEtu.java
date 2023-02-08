package fr.iutfbleau.projetIHM2022FI2.Controller;


import java.util.Set;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import fr.iutfbleau.projetIHM2022FI2.Model.API.AbstractGroupeFactory;
import fr.iutfbleau.projetIHM2022FI2.Model.API.Etudiant;
import fr.iutfbleau.projetIHM2022FI2.View.ChoixEtu;

/**
 * La classe <code>SearchEtu</code> recherche les étudiants portant un nom donné
 *  ouvre ensuite une fenêtre affichant ces étudiants
 * 
 *  
 * @version 1.1
 * @author Adrien Dos Santos, Claire Gobert, Kayyissa Haissous
 */
public class SearchEtu {

    private AbstractGroupeFactory groupeFactory;

    /**
   * Constructeur qui verifie que le nom est porté par au moins un étudiant
   *  cherche ensuite la liste des étudiants le portant et ouvre une fenêtre pour les afficher
   *
   * @param nomEtuField JTextField qui contient le nom à chercher
   * @param groupeFactory l'usine qui gère les groupes
   */
    public SearchEtu(JTextField nomEtuField, AbstractGroupeFactory groupeFactory){
        this.groupeFactory = groupeFactory;
        String nomEtu = nomEtuField.getText();
        if (nomEtu.equals("")){
            JOptionPane.showMessageDialog(null, "Merci d'indiquer le nom de l'étudiant", "InfoBox" , JOptionPane.INFORMATION_MESSAGE);
        } else {
            Set<Etudiant> etu = getEtuByName(nomEtu);
            if (etu.isEmpty()){
                JOptionPane.showMessageDialog(null, "Aucun étudiant ne porte ce nom", "InfoBox" , JOptionPane.INFORMATION_MESSAGE);
                // TODO: changer en proposition à chercher plus floue
            } else {
                // Fenetre qui affiche les étudiants trouvés
                new ChoixEtu(etu, groupeFactory);
            }
        }
    }

    /**
   * cherche les étudiants portant le même nom
   *
   * @param nom le nom pour lequel il faut chercher des étudiants
   * 
   * @return La liste des étudiants portant le nom
   */
    public Set<Etudiant> getEtuByName(String nom){
        // TODO: regex nom
        Set<Etudiant> etudiants= this.groupeFactory.getEtudiants(nom);
        System.out.println(etudiants.toString());
        return etudiants;
    }
}
