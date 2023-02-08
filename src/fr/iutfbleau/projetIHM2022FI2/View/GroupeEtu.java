package fr.iutfbleau.projetIHM2022FI2.View;

import java.util.Set;
import java.awt.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import fr.iutfbleau.projetIHM2022FI2.Model.API.AbstractGroupeFactory;
import fr.iutfbleau.projetIHM2022FI2.Model.API.Groupe;

/**
 * La classe <code>GroupeEtu</code> affiche les groupes dans lesquels un étudiant est présent
 * 
 *  
 * @version 1.1
 * @author Adrien Dos Santos, Claire Gobert, Kayyissa Haissous
 */
public class GroupeEtu {
    private int i = 1;

    /**
     * Constructeur qui ouvre la fenêtre
     * 
     * @param groupes groupes dans lesquels est l'étudiant
     * @param groupeFactory usine qui gère les groupes
     */
    public GroupeEtu(Set<Groupe> groupes, AbstractGroupeFactory groupeFactory) {
        UIManager.put("OptionPane.background", Color.DARK_GRAY);
        UIManager.put("Panel.background", Color.DARK_GRAY);

        JPanel panel = new JPanel();

        if (groupes.size() == 1) {
            JLabel phrase = new JLabel(" Cet étudiant est dans le groupe suivant :");
            panel.add(phrase);
            panel.add(new JLabel("promotion"));
        } else {
            panel.setLayout(new GridBagLayout());

            GridBagConstraints cont = new GridBagConstraints();

            // Affichage phrase
            JLabel phrase = new JLabel(" Cet étudiant est dans les groupes suivants :");

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

            for (Groupe g : groupes) {
                JLabel nom = new JLabel(g.getName());

                cont.gridx = 0;
                cont.gridy = i;
                cont.gridwidth = 1;
                cont.gridheight = 1;
                cont.fill = GridBagConstraints.BOTH;
                cont.anchor = GridBagConstraints.CENTER;
                cont.insets = new Insets(20, 20, 20, 20);
                cont.weightx = 1.0f;
                cont.weighty = 1.0f;
                nom.setBackground(Color.DARK_GRAY);
                nom.setForeground(Color.WHITE);
                panel.add(nom, cont);

                i++;
            }
        }

        JOptionPane.showMessageDialog(null, panel);
        // TODO: fenetre qui affiche la liste des etudiants qui matchent
    }

}
