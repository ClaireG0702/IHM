package fr.iutfbleau.projetIHM2022FI2.Controller;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.swing.JOptionPane;

import fr.iutfbleau.projetIHM2022FI2.Model.API.AbstractGroupeFactory;
import fr.iutfbleau.projetIHM2022FI2.Model.API.Etudiant;
import fr.iutfbleau.projetIHM2022FI2.Model.API.Groupe;
import fr.iutfbleau.projetIHM2022FI2.Model.API.TypeGroupe;

/**
 * La classe <code>MvEtu</code> déplace un étudiant si c'est possible
 * 
 * @version 1.1
 * @author Adrien Dos Santos, Claire Gobert, Kayyissa Haissous
 */
public class MvEtu {

    /**
     * Constructeur ajoute l'étudiant au groupe donné si c'est possible
     * 
     * @param groupeFactory l'usine qui gère les groupes
     * @param gActif le groupe dans lequel est l'étudiant
     * @param nom le nom de l'étudiant
     * @param prenom le prénom de l'étudiant
     * @param gDestination le groupe où il faut déplacer l'étudiant
     */
    public MvEtu(AbstractGroupeFactory groupeFactory, Groupe gActif, String nom, String prenom, Groupe gDestination){
        Set<Etudiant> etudiantsNom= groupeFactory.getEtudiants(nom);
        Set<Etudiant> etudiantsNP = new LinkedHashSet<Etudiant>();
        for (Etudiant e : etudiantsNom){
            if (e.getPrenom().equals(prenom)){
            etudiantsNP.add(e);
            }
        }
        Set<Etudiant> etudiants = new LinkedHashSet<Etudiant>();
        for (Etudiant e : gActif.getEtudiants()){
            if (etudiantsNP.contains(e)){
            etudiants.add(e);
            }
        }

        if (etudiants.size()==0){
            JOptionPane.showMessageDialog(null, "Aucun étudiant ne s'appelle comme ça dans ce groupe.", "InfoBox" , JOptionPane.INFORMATION_MESSAGE);
        } else if (etudiants.size()<1){
            JOptionPane.showMessageDialog(null, "Plusieurs étudiants portent ce nom, aucun n'a été déplacé", "InfoBox" , JOptionPane.INFORMATION_MESSAGE);
        } else {
            Etudiant etu = null;
            for (Etudiant etudiant : etudiants) {
                etu = etudiant; 
            }
            if (gActif.equals(gDestination)){
                JOptionPane.showMessageDialog(null, "L'étudiant est déjà dans ce groupe.", "InfoBox" , JOptionPane.INFORMATION_MESSAGE);
            } else if (gActif.getMin()==gActif.getSize()){
                JOptionPane.showMessageDialog(null, "Impossible de retirer un étudiant de ce groupe.", "InfoBox" , JOptionPane.INFORMATION_MESSAGE);
            } else if (gDestination.getMax()==gActif.getSize()){
                JOptionPane.showMessageDialog(null, "Le groupe d'arrivé est déjà plein", "InfoBox" , JOptionPane.INFORMATION_MESSAGE);
            } else if (gActif.getPointPoint().equals(gDestination.getPointPoint()) && gDestination.getPointPoint().getType()==TypeGroupe.PARTITION){
                gDestination.addEtudiant(etu);
                gActif.removeEtudiant(etu);
                JOptionPane.showMessageDialog(null, "L'étudiant a été déplacé avec succès.", "InfoBox" , JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Ces deux groupes ne sont pas dans la même partition.", "InfoBox" , JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
