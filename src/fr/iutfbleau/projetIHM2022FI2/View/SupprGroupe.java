package fr.iutfbleau.projetIHM2022FI2.View;

import java.awt.*;

import javax.swing.*;

import fr.iutfbleau.projetIHM2022FI2.Controller.AfficheArbre;
import fr.iutfbleau.projetIHM2022FI2.Controller.ListenerDeleteGroup;
import fr.iutfbleau.projetIHM2022FI2.Model.API.AbstractGroupeFactory;

/**
 * La classe <code>SupprGroupe</code> Pop up qui demande les informations pour supprimer un groupe
 * 
 *  
 * @version 1.1
 * @author Adrien Dos Santos, Claire Gobert, Kayyissa Haissous
 */
public class SupprGroupe extends JOptionPane{
    private JLabel question = new JLabel("Quel groupe voulez-vous supprimer ?");
    private JTextField group = new JTextField();

     /**
     * Constructeur qui ouvre la fenêtre
     * @param groupeFactory l'usine qui gère les groupes
     * @param arbreA l'arbre des groupes pour le mettre à jour
     */
    public SupprGroupe(AbstractGroupeFactory groupeFactory, AfficheArbre arbreA){
        UIManager.put("OptionPane.background", Color.DARK_GRAY);
        UIManager.put("Panel.background", Color.DARK_GRAY);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2,1,10,10));

        question.setBackground(Color.DARK_GRAY);
        question.setForeground(Color.WHITE);
        panel.add(question);
        group.setBackground(Color.DARK_GRAY);
        group.setForeground(Color.WHITE);
        panel.add(group);

        int result = JOptionPane.showConfirmDialog(null, panel, "Suppression de Groupe", JOptionPane.OK_CANCEL_OPTION);
        if(result == JOptionPane.OK_OPTION) {
            new ListenerDeleteGroup(arbreA, groupeFactory, group);
        }
    }
    
}