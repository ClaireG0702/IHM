package fr.iutfbleau.projetIHM2022FI2.View;

import javax.swing.*;
import java.awt.*;

import fr.iutfbleau.projetIHM2022FI2.Controller.RmEtu;
import fr.iutfbleau.projetIHM2022FI2.Model.API.AbstractGroupeFactory;
import fr.iutfbleau.projetIHM2022FI2.Model.API.Groupe;

/**
 * La classe <code>SupprEtu</code> Pop up qui demande les informations pour supprimer un étudiant
 * 
 *  
 * @version 1.1
 * @author Adrien Dos Santos, Claire Gobert, Kayyissa Haissous
 */
public class SupprEtu {
    private JTextField nomField = new JTextField("Nom");
    private JTextField prenomField = new JTextField("Prenom");

    /**
     * Constructeur qui ouvre la fenêtre
     * @param groupeFactory l'usine qui gère les groupes
     * @param gActif le groupe dans lequel supprimer
     */
    public SupprEtu(AbstractGroupeFactory groupeFactory, Groupe gActif){
        // Pop up qui demande le nom et le prenom de l'étudiant
        // le bouton "Déplacer l'étudiant" doit faire : new RmEtu(groupeFactory, gActif, nom, prenom);
        
        UIManager.put("OptionPane.background", Color.DARK_GRAY);
        UIManager.put("Panel.background", Color.DARK_GRAY);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1,2,10,10));

        nomField.setBackground(Color.DARK_GRAY);
        nomField.setForeground(Color.WHITE);
        panel.add(nomField);
        prenomField.setBackground(Color.DARK_GRAY);
        prenomField.setForeground(Color.WHITE);
        panel.add(prenomField);


        int result = JOptionPane.showConfirmDialog(null, panel, "Supprimer l'étudiant", JOptionPane.OK_CANCEL_OPTION);
        if(result == JOptionPane.OK_OPTION) {
            new RmEtu(groupeFactory, gActif, nomField.getText(), prenomField.getName());
        }
    }
   
}

