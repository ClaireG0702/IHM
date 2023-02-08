package fr.iutfbleau.projetIHM2022FI2.Controller;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.swing.JOptionPane;

import fr.iutfbleau.projetIHM2022FI2.Model.API.AbstractGroupeFactory;
import fr.iutfbleau.projetIHM2022FI2.Model.API.Groupe;
import fr.iutfbleau.projetIHM2022FI2.Model.API.Etudiant;


/**
 * La classe <code>Demande</code> enregistre une demande de changement de groupe
 * 
 * @version 1.1
 * @author Adrien Dos Santos, Claire Gobert, Kayyissa Haissous
 */
public class Demande {

    /**
     * Constructeur qui vérifie que la demande respecte les contraintes
     * 
     * @param groupeFactory l'usine qui gère les groupes
     * @param groupe le groupe dans lequel l'étudiant veut aller
     * @param nom le nom de l'étudiant
     * @param prenom le prénom de l'étudiant
     * @param justif le motif qu'a renseigné l'étudiant
     */
    public Demande(AbstractGroupeFactory groupeFactory, Groupe groupe, String nom, String prenom, String justif) {
        Set<Etudiant> etudiantsNom = groupeFactory.getEtudiants(nom);
        Set<Etudiant> etudiants = new LinkedHashSet<Etudiant>();
        for (Etudiant e : etudiantsNom) {
            if (e.getPrenom().equals(prenom)) {
                etudiants.add(e);
            }
        }

        if (etudiants.size()==0){
            JOptionPane.showMessageDialog(null, "Cet étudiant n'existe pas.", "InfoBox" , JOptionPane.INFORMATION_MESSAGE);
        } else if (etudiants.size()<1){
            JOptionPane.showMessageDialog(null, "Plusieurs étudiants portent ce nom, la demande n'a pas été prise en compte", "InfoBox" , JOptionPane.INFORMATION_MESSAGE);
        } else {
            Etudiant etu = null;
            for (Etudiant etudiant : etudiants) {
                etu = etudiant;
            }
            JOptionPane.showMessageDialog(null, "La demande a été prise en compte", "InfoBox" , JOptionPane.INFORMATION_MESSAGE);
            // Pour aller au bout il faudrait utiliser AbstractChangementFactory
        }
    }
    
}
