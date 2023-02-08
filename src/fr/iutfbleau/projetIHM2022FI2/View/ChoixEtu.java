package fr.iutfbleau.projetIHM2022FI2.View;

import java.util.Set;
import java.awt.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import fr.iutfbleau.projetIHM2022FI2.Controller.ListenerGroupesEtu;
import fr.iutfbleau.projetIHM2022FI2.Model.API.AbstractGroupeFactory;
import fr.iutfbleau.projetIHM2022FI2.Model.API.Etudiant;

/**
 * La classe <code>ChoixEtu</code> ouvre une fenêtre avec des étudiants portant le même nom suite à une recherche
 * 
 *  
 * @version 1.1
 * @author Adrien Dos Santos, Claire Gobert, Kayyissa Haissous
 */
public class ChoixEtu {
    private int i = 1;

    /**
     * Constructeur ouvrant la fenêtre avec la liste des étudiants
     * 
     * @param etudiants la liste des étudiants à afficher
     * @param groupeFactory l'usine qui gère les groupes
     */
    public ChoixEtu(Set<Etudiant> etudiants, AbstractGroupeFactory groupeFactory) {
        UIManager.put("OptionPane.background", Color.DARK_GRAY);
        //UIManager.put("Panel.background", Color.DARK_GRAY);
        UIManager.put("Label.background", Color.GRAY);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints cont = new GridBagConstraints();	

        // Affichage phrase
        JLabel phrase = new JLabel("Cliquez sur l'étudiant concerné :");

        cont.gridx = 0;
        cont.gridy = 0;
        cont.gridwidth = 1;
        cont.gridheight = 1;
        cont.fill = GridBagConstraints.BOTH;
        cont.anchor = GridBagConstraints.CENTER;
        cont.insets = new Insets(20, 20, 20, 20);
        cont.weightx = 1.0f;
        cont.weighty = 1.0f;
        phrase.setBackground(Color.DARK_GRAY);
        phrase.setForeground(Color.WHITE);
        panel.add(phrase, cont);

        // Affichage Etudiants avec le bon nom
        for(Etudiant etu : etudiants) {
            JLabel nom = new JLabel(etu.getNom()+" "+etu.getPrenom());

            cont.gridx = 0;
            cont.gridy = i;
            cont.gridwidth = 1;
            cont.gridheight = 1;
            cont.fill = GridBagConstraints.BOTH;
            cont.anchor = GridBagConstraints.CENTER;
            cont.insets = new Insets(20, 20, 20, 20);
            cont.weightx = 1.0f;
            cont.weighty = 1.0f;
            nom.setOpaque(true);
            nom.setBackground(Color.GRAY);
            nom.setForeground(Color.BLACK);
            nom.addMouseListener(new ListenerGroupesEtu(etu.getId(),groupeFactory));
            panel.add(nom, cont);

            i++;
        }

        JOptionPane.showMessageDialog(null, panel);
    }
    
}
