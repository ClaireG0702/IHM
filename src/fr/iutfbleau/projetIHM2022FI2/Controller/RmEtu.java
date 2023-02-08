package fr.iutfbleau.projetIHM2022FI2.Controller;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.swing.JOptionPane;

import fr.iutfbleau.projetIHM2022FI2.Model.API.AbstractGroupeFactory;
import fr.iutfbleau.projetIHM2022FI2.Model.API.Etudiant;
import fr.iutfbleau.projetIHM2022FI2.Model.API.Groupe;
import fr.iutfbleau.projetIHM2022FI2.Model.API.TypeGroupe;

/**
 * La classe <code>RmEtu</code> supprime un étudiant d'un groupe si c'est possible
 * 
 * @version 1.1
 * @author Adrien Dos Santos, Claire Gobert, Kayyissa Haissous
 */
public class RmEtu {

    /**
     * Constructeur ajoute l'étudiant au groupe donné si c'est possible
     * 
     * @param groupeFactory l'usine qui gère les groupes
     * @param gActif le groupe dans lequel est l'étudiant
     * @param nom le nom de l'étudiant
     * @param prenom le prénom de l'étudiant
     */
    public RmEtu(AbstractGroupeFactory groupeFactory, Groupe gActif, String nom, String prenom){
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
            JOptionPane.showMessageDialog(null, "Plusieurs étudiants portent ce nom, aucun n'a été supprimé", "InfoBox" , JOptionPane.INFORMATION_MESSAGE);
        } else {
            Etudiant etu = null;
            for (Etudiant etudiant : etudiants) {
                etu = etudiant; 
            }
            if (gActif.getMin() == gActif.getSize()){
                JOptionPane.showMessageDialog(null, "Ce groupe ne peut pas contenir moins d'étudiants.", "InfoBox" , JOptionPane.INFORMATION_MESSAGE);
            }
            if (gActif.getPointPoint().getType()==TypeGroupe.PARTITION){
                gActif.removeEtudiant(etu);
                gActif.getPointPoint().removeEtudiant(etu);
                JOptionPane.showMessageDialog(null, "L'étudiant a été retiré de ce groupe et du groupe suppérieur.", "InfoBox" , JOptionPane.INFORMATION_MESSAGE);
            } else {
                gActif.removeEtudiant(etu);
                JOptionPane.showMessageDialog(null, "L'étudiant a été retiré de ce groupe.", "InfoBox" , JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
