package fr.iutfbleau.projetIHM2022FI2.View;

import fr.iutfbleau.projetIHM2022FI2.Controller.*;
import fr.iutfbleau.projetIHM2022FI2.Model.API.AbstractGroupeFactory;

import java.awt.*;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import javax.swing.*;

/**
 * La classe <code>NewGroup</code> Pop up qui demande les informations pour créer un groupe
 * 
 *  
 * @version 1.1
 * @author Adrien Dos Santos, Claire Gobert, Kayyissa Haissous
 */
public class NewGroup {
    private JTextField nom = new JTextField("Nom");
    private JTextField nomParent = new JTextField("Nom du groupe parent");
    private JCheckBox partition = new JCheckBox("Partition");
    private JTextField nbGroupes = new JTextField("Nombre de Groupes");

    /**
     * Constructeur qui ouvre la fenêtre de la demande de création de groupe
     * @param groupeFactory l'usine qui gère les groupes
     * @param arbreA l'arbre qui affiche les groupes pour le mettre à jour
     */
    public NewGroup(AbstractGroupeFactory groupeFactory, AfficheArbre arbreA) {
        UIManager.put("OptionPane.background", Color.DARK_GRAY);
        UIManager.put("Panel.background", Color.DARK_GRAY);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,2,10,10));

        nom.setBackground(Color.DARK_GRAY);
        nom.setForeground(Color.WHITE);
        panel.add(nom);
        nomParent.setBackground(Color.DARK_GRAY);
        nomParent.setForeground(Color.WHITE);
        panel.add(nomParent);

        partition.setBackground(Color.DARK_GRAY);
        partition.setForeground(Color.WHITE);
        panel.add(partition);

        nbGroupes.setBackground(Color.DARK_GRAY);
        nbGroupes.setForeground(Color.WHITE);
        nbGroupes.setVisible(false);
        panel.add(nbGroupes);

        partition.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e){
                if (partition.isSelected()){
                    nbGroupes.setVisible(true);
                } else {
                    nbGroupes.setVisible(false);
                }
            }

        });

        int result = JOptionPane.showConfirmDialog(null, panel, "Nouveau Groupe", JOptionPane.OK_CANCEL_OPTION);
        if(result == JOptionPane.OK_OPTION) {
            new ListenerCreateGroup(arbreA, groupeFactory, nom, nomParent, partition, nbGroupes);
        }
    }
}
