package fr.iutfbleau.projetIHM2022FI2.Controller;

import javax.swing.JTextField;
import javax.swing.*;

import fr.iutfbleau.projetIHM2022FI2.Model.API.AbstractGroupeFactory;
import fr.iutfbleau.projetIHM2022FI2.Model.API.Groupe;
import fr.iutfbleau.projetIHM2022FI2.Model.API.TypeGroupe;

import javax.swing.JCheckBox;

/**
 * La classe <code>ListenerCreateGroup</code> crée un nouveau groupe 
 * et récupère les informations nécessaires à cette création
 * 
 *  
 * @version 1.1
 * @author Adrien Dos Santos, Claire Gobert, Kayyissa Haissous
 */
public class ListenerCreateGroup {

    /**
   * Constructeur qui s'assure que les informations renseignées sont acceptables et crée le groupe souhaité
   *
   * @param arbreA l'arbre des groupes pour le mettre à jour
   * @param groupeFactory l'usine qui gère les groupes
   * @param nameField le TextField où se trouve le nom du groupe à créer
   * @param pereField le TextField où se trouve le nom du parent du groupe à créer
   * @param partition La CheckBox qui indique si le groupe à créer est une partition ou non
   * @param nbrField en cas de création de partition : le nombre de sous-groupes à créer (à indiquer mais non traité si le groupe est libre)
   */
    public ListenerCreateGroup(AfficheArbre arbreA, AbstractGroupeFactory groupeFactory, JTextField nameField, JTextField pereField, JCheckBox partition, JTextField nbrField){
        
        String name = nameField.getText();
        Groupe pere = groupeFactory.getGroupeByName(pereField.getText());

        if (name.equals("")){
            JOptionPane.showMessageDialog(null, "Merci de nommer le groupe", "InfoBox" , JOptionPane.INFORMATION_MESSAGE);
        } else if (pere==null){
            JOptionPane.showMessageDialog(null, "Le groupe parent n'existe pas.", "InfoBox" , JOptionPane.INFORMATION_MESSAGE);    
        } else if (pere.getType().equals(TypeGroupe.PARTITION)){
            JOptionPane.showMessageDialog(null, "Vous ne pouvez pas ajouter un groupe à une partition", "InfoBox" , JOptionPane.INFORMATION_MESSAGE);    
        } else if (partition.isSelected()){
            String nbr = nbrField.getText();
            if (nbr.matches("[0-9]+")){
                int nbGroupes = Integer.parseInt(nbr);
                if (nbGroupes>10){
                    JOptionPane.showMessageDialog(null, "Vous ne pouvez pas créer plus de 10 groupes à la fois", "InfoBox" , JOptionPane.INFORMATION_MESSAGE);
                } else {
                    // On crée une partition
                    groupeFactory.createPartition(pere, name, nbGroupes);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Vous devez renseigner un nombre de groupes correct.", "InfoBox" , JOptionPane.INFORMATION_MESSAGE);
            }
                
        } else {
            // On crée un groupe libre
            groupeFactory.createGroupe(pere, name, 1, pere.getMax());
        }
        // mise a jour affichage groupe
        arbreA.printArbre();
    }
        
}

