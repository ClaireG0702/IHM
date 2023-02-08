package fr.iutfbleau.projetIHM2022FI2.Controller;

import javax.swing.JTextField;

import javax.swing.*;

import fr.iutfbleau.projetIHM2022FI2.Model.API.AbstractGroupeFactory;
import fr.iutfbleau.projetIHM2022FI2.Model.API.Groupe;

/**
 * La classe <code>ListenerDeleteGroup</code> Supprime un groupe à partir de son nom
 * 
 * @version 1.1
 * @author Adrien Dos Santos, Claire Gobert, Kayyissa Haissous
 */
public class ListenerDeleteGroup {

    /**
   * Constructeur qui supprime un groupe avec son nom s'il existe
   *
   * @param arbreA l'arbre des groupes pour le mettre à jour
   * @param groupeFactory l'usine qui gère les groupes
   * @param nameField le TextField où se trouve le nom du groupe à supprimer
   */
    public ListenerDeleteGroup(AfficheArbre arbreA, AbstractGroupeFactory groupeFactory, JTextField nameField){
        String name = nameField.getText();
        Groupe groupeToDelete = groupeFactory.getGroupeByName(name);
        if (groupeToDelete==null){
            JOptionPane.showMessageDialog(null, "Ce groupe n'existe pas.", "InfoBox" , JOptionPane.INFORMATION_MESSAGE);
        } else if (groupeToDelete.getSousGroupes().size()==0){
            groupeFactory.deleteGroupe(groupeToDelete);
            arbreA.printArbre();
        } else {
            JOptionPane.showMessageDialog(null, "Ce groupe ne peut pas être supprimé", "InfoBox" , JOptionPane.INFORMATION_MESSAGE);
 
        }
    }
}
