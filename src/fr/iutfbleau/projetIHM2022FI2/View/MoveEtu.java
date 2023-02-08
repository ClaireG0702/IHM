package fr.iutfbleau.projetIHM2022FI2.View;

import javax.swing.*;
import java.awt.*;

import fr.iutfbleau.projetIHM2022FI2.Controller.MvEtu;
import fr.iutfbleau.projetIHM2022FI2.Model.API.AbstractGroupeFactory;
import fr.iutfbleau.projetIHM2022FI2.Model.API.Groupe;


/**
 * La classe <code>MoveEtu</code> Pop up qui demande le nom et le prenom de l'étudiant, et le groupe dans lequel le deplacer
 * 
 *  
 * @version 1.1
 * @author Adrien Dos Santos, Claire Gobert, Kayyissa Haissous
 */
public class MoveEtu {
    private JTextField nomField = new JTextField("Nom");
    private JTextField prenomField = new JTextField("Prenom");
    private JTextField groupeField = new JTextField("Groupe destination");

    /**
     * Constructeur qui ouvre la fenêtre
     * @param groupeFactory l'usine qui gère les groupes
     * @param gActif le groupe départ
     */
    public MoveEtu(AbstractGroupeFactory groupeFactory, Groupe gActif){
        UIManager.put("OptionPane.background", Color.DARK_GRAY);
        UIManager.put("Panel.background", Color.DARK_GRAY);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2,2,10,10));

        nomField.setBackground(Color.DARK_GRAY);
        nomField.setForeground(Color.WHITE);
        panel.add(nomField);
        prenomField.setBackground(Color.DARK_GRAY);
        prenomField.setForeground(Color.WHITE);
        panel.add(prenomField);
        groupeField.setBackground(Color.DARK_GRAY);
        groupeField.setForeground(Color.WHITE);
        panel.add(groupeField);


        int result = JOptionPane.showConfirmDialog(null, panel, "Déplacer l'étudiant", JOptionPane.OK_CANCEL_OPTION);
        if(result == JOptionPane.OK_OPTION) {
            new MvEtu(groupeFactory, gActif, nomField.getText(), prenomField.getText(), groupeFactory.getGroupeByName(groupeField.getText()));
        }
    }
   
}
