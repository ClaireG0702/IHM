package fr.iutfbleau.projetIHM2022FI2.View;

import javax.swing.*;
import java.awt.*;

import fr.iutfbleau.projetIHM2022FI2.Controller.Demande;
import fr.iutfbleau.projetIHM2022FI2.Model.API.AbstractGroupeFactory;
import fr.iutfbleau.projetIHM2022FI2.Model.API.Groupe;

/**
 * La classe <code>GetDemande</code> ouvre une fenêtre pour récupérer plus d'informations sur un changement de groupe
 * 
 *  
 * @version 1.1
 * @author Adrien Dos Santos, Claire Gobert, Kayyissa Haissous
 */
public class GetDemande {
    private JTextField nomField = new JTextField("Nom");
    private JTextField prenomField = new JTextField("Prenom");
    private JTextField justif = new JTextField("Motif de la demande");

    /**
     * Constructeur qui ouvre et gère cette fenêtre 
     * @param groupeFactory l'usine qui gère les groupes
     * @param groupe le groupe souhaité
     */
    public GetDemande(AbstractGroupeFactory groupeFactory, Groupe groupe){
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
        justif.setBackground(Color.DARK_GRAY);
        justif.setForeground(Color.WHITE);
        panel.add(justif);


        int result = JOptionPane.showConfirmDialog(null, panel, "Demande de changement de groupe", JOptionPane.OK_CANCEL_OPTION);
        if(result == JOptionPane.OK_OPTION) {
            new Demande(groupeFactory, groupe, nomField.getText(), prenomField.getText(), justif.getText());
        }
    }
}
