package fr.iutfbleau.projetIHM2022FI2.View;
import java.awt.*;
import javax.swing.*;

import fr.iutfbleau.projetIHM2022FI2.Controller.AfficheArbre;
import fr.iutfbleau.projetIHM2022FI2.Controller.ListenerRenameGroup;
import fr.iutfbleau.projetIHM2022FI2.Model.API.AbstractGroupeFactory;


/**
 * La classe <code>RenameGroupe</code> Pop up qui demande les informations pour renommer
 * 
 *  
 * @version 1.1
 * @author Adrien Dos Santos, Claire Gobert, Kayyissa Haissous
 */
public class RenameGroupe extends JOptionPane{
    private JLabel rename = new JLabel("Renommez le groupe :");
    private JTextField oldName = new JTextField("Ancien nom");
    private JTextField newName = new JTextField("Nouveau nom");

    /**
     * Constructeur qui ouvre la fenêtre
     * @param groupeFactory l'usine qui gère les groupes
     * @param arbreA l'arbre qui affiche les groupes à mettre à jour
     */
    public RenameGroupe(AbstractGroupeFactory groupeFactory, AfficheArbre arbreA){
        UIManager.put("OptionPane.background", Color.DARK_GRAY);
        UIManager.put("Panel.background", Color.DARK_GRAY);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,1,10,10));

        rename.setBackground(Color.DARK_GRAY);
        rename.setForeground(Color.WHITE);
        panel.add(rename);
        oldName.setBackground(Color.DARK_GRAY);
        oldName.setForeground(Color.WHITE);
        panel.add(oldName);
        newName.setBackground(Color.DARK_GRAY);
        newName.setForeground(Color.WHITE);
        panel.add(newName);

        int result = JOptionPane.showConfirmDialog(null, panel, "Renommer un Groupe", JOptionPane.OK_CANCEL_OPTION);
        if(result == JOptionPane.OK_OPTION) {
            new ListenerRenameGroup(arbreA, groupeFactory, oldName, newName);
        }
    }
    
}