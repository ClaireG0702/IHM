package fr.iutfbleau.projetIHM2022FI2.Controller;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.swing.JOptionPane;

import fr.iutfbleau.projetIHM2022FI2.Model.API.AbstractGroupeFactory;
import fr.iutfbleau.projetIHM2022FI2.Model.API.Etudiant;
import fr.iutfbleau.projetIHM2022FI2.Model.API.Groupe;
import fr.iutfbleau.projetIHM2022FI2.Model.API.TypeGroupe;

/**
 * La classe <code>AddEtu</code> ajoute un étudiant à un groupe donné et à son parent s'il est dans une partition
 * 
 * @version 1.1
 * @author Adrien Dos Santos, Claire Gobert, Kayyissa Haissous
 */
public class AddEtu {

    /**
     * Constructeur ajoute l'étudiant au groupe donné si c'est possible
     * 
     * @param groupeFactory l'usine qui gère les groupes
     * @param gActif le groupe auquel ajouter l'étudiant
     * @param nom le nom de l'étudiant
     * @param prenom le prénom de l'étudiant
     */
    public AddEtu(AbstractGroupeFactory groupeFactory, Groupe gActif, String nom, String prenom) {
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
            JOptionPane.showMessageDialog(null, "Plusieurs étudiants portent ce nom, aucun n'a été ajouté.", "InfoBox" , JOptionPane.INFORMATION_MESSAGE);
        } else {
            Etudiant etu = null;
            for (Etudiant etudiant : etudiants) {
                etu = etudiant;
            }
            if (gActif.getMax()==gActif.getSize()){
                JOptionPane.showMessageDialog(null, "Ce groupe est déjà plein", "InfoBox" , JOptionPane.INFORMATION_MESSAGE);
            } else if (gActif.getPointPoint().getType().equals(TypeGroupe.PARTITION)){
                gActif.getPointPoint().addEtudiant(etu);
                gActif.addEtudiant(etu);
                JOptionPane.showMessageDialog(null, "L'étudiant a été ajouté dans ce groupe et son père", "InfoBox" , JOptionPane.INFORMATION_MESSAGE);
            } else if (gActif.getType().equals(TypeGroupe.PARTITION)){
                JOptionPane.showMessageDialog(null, "Ce groupe est une partition. Ajoutez cet étudiant à un de ses sous-groupes", "InfoBox" , JOptionPane.INFORMATION_MESSAGE);
            }

        }
    }
    
}
