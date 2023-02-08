package fr.iutfbleau.projetIHM2022FI2.View;

import javax.swing.*;
import java.awt.*;

import fr.iutfbleau.projetIHM2022FI2.Controller.AddEtu;
import fr.iutfbleau.projetIHM2022FI2.Model.API.AbstractGroupeFactory;
import fr.iutfbleau.projetIHM2022FI2.Model.API.Groupe;

/**
 * La classe <code>NewEtu</code> Pop up qui demande le nom et le prenom de l'étudiant à ajouter
 * 
 *  
 * @version 1.1
 * @author Adrien Dos Santos, Claire Gobert, Kayyissa Haissous
 */
public class NewEtu {
    private JTextField nomField = new JTextField("Nom");
    private JTextField prenomField = new JTextField("Prenom");

    /**
     * Constructeur qui ouvre la fenêtre
     * @param groupeFactory l'usine qui gère les groupes
     * @param gActif le groupe dans lequel ajouter l'étudiant
     */
    public NewEtu(AbstractGroupeFactory groupeFactory, Groupe gActif){
        
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


        int result = JOptionPane.showConfirmDialog(null, panel, "Ajouter l'étudiant", JOptionPane.OK_CANCEL_OPTION);
        if(result == JOptionPane.OK_OPTION) {
            new AddEtu(groupeFactory, gActif, nomField.getText(), prenomField.getText());
        }
    }
   
}
