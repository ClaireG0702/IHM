package fr.iutfbleau.projetIHM2022FI2.Controller;

import javax.swing.JTextField;
import javax.swing.*;

import fr.iutfbleau.projetIHM2022FI2.Model.API.Groupe;
import fr.iutfbleau.projetIHM2022FI2.Model.API.AbstractGroupeFactory;

/**
 * La classe <code>ListenerRenameGroup</code> renomme un groupe donné
 * 
 * @version 1.1
 * @author Adrien Dos Santos, Claire Gobert, Kayyissa Haissous
 */
public class ListenerRenameGroup {

    /**
   * Constructeur qui renomme un groupe s'il existe
   *
   * @param arbreA l'arbre des groupes pour le mettre à jour
   * @param groupeFactory l'usine qui gère les groupes
   * @param oldNameField le TextField où se trouve le nom du groupe
   * @param newNameField le TextField où se trouve le nouveau nom à donner au groupe
   */
    public ListenerRenameGroup(AfficheArbre arbreA, AbstractGroupeFactory groupeFactory, JTextField oldNameField, JTextField newNameField){
        String oldName = oldNameField.getText();
        String newName = newNameField.getText();
        Groupe toRename = groupeFactory.getGroupeByName(oldName);
        if (toRename!=null){
            toRename.SetName(newName);
            arbreA.printArbre();
        } else {
            JOptionPane.showMessageDialog(null, "Ce groupe n'existe pas.", "InfoBox" , JOptionPane.INFORMATION_MESSAGE);
        }
    }
}