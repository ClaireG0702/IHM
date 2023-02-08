package fr.iutfbleau.projetIHM2022FI2.View;

import fr.iutfbleau.projetIHM2022FI2.Controller.AfficheArbre;
import fr.iutfbleau.projetIHM2022FI2.Controller.EmulEtu;
import fr.iutfbleau.projetIHM2022FI2.Controller.SearchEtu;
import fr.iutfbleau.projetIHM2022FI2.Model.MNP.AbstractGroupeFactoryNP;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * La classe <code>Enseignant</code> est la fenêtre principale avec laquelle interagit l'enseignant
 * 
 *  
 * @version 1.1
 * @author Adrien Dos Santos, Claire Gobert, Kayyissa Haissous
 */
public class Enseignant extends JFrame{
    private JPanel panel = new JPanel();
    private JPanel panelArbre = new JPanel();
    private JLabel search = new JLabel("Chercher le(s) groupe(s) d'un étudiant : ");
    private JTextField nom = new JTextField("Nom");
    private JButton commit = new JButton("Rechercher");

    /**
     * Constructeur qui ouvre la fenêtre
     */
    public Enseignant() {
        /*
         * Parametres fenetre
         */
        //Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //Dimension frameSize = new Dimension (screenSize.width *2/3,screenSize.height * 6/7);
	    //this.setSize(frameSize);
	    //this.setLocation(screenSize.width * 1/6, screenSize.height * 1/15);
        this.setSize(1200,800);
        this.setTitle("Gestion des Groupes - Administrateur");
    	this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        /*
         * Contraintes, ajout + parametres du panel contenant la representation de la promo
         */
        panelArbre.setLayout(new GridBagLayout());
        panelArbre.setBackground(Color.DARK_GRAY);
        panelArbre.setBorder(BorderFactory.createLineBorder(Color.black));
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.DARK_GRAY);
        this.add(panel); 

        GridBagConstraints cont = new GridBagConstraints();	

        /*
         * Creation usine groupes
         */
        AbstractGroupeFactoryNP groupFactory = new AbstractGroupeFactoryNP("promotion", 1, 100);
        DefaultMutableTreeNode promo = new DefaultMutableTreeNode(groupFactory.getPromotion().getName());

        // Stimule des étudiants
        new EmulEtu(groupFactory);

        AfficheArbre arbreA = new AfficheArbre(groupFactory, promo, 0);

        JLabel arbre = new JLabel("Groupes :");
        cont.gridx = 0;
        cont.gridy = 0;
        cont.gridwidth = 1;
        cont.gridheight = 1;
        cont.fill = GridBagConstraints.BOTH;
        cont.anchor = GridBagConstraints.CENTER;
        cont.insets = new Insets(20, 20, 0, 0);
        cont.weightx = 1.0f;
        cont.weighty = 0.0f;
        arbre.setForeground(Color.WHITE);
        panelArbre.add(arbre, cont);

        cont.gridx = 0;
        cont.gridy = 1;
        cont.gridwidth = 1;
        cont.gridheight = 6;
        cont.fill = GridBagConstraints.BOTH;
        cont.anchor = GridBagConstraints.CENTER;
        cont.insets = new Insets(20, 20, 20, 20);
        cont.weightx = 1.0f;
        cont.weighty = 6.0f;
        panelArbre.add(arbreA.printArbre(), cont);

        cont.gridx = 0;
        cont.gridy = 0;
        cont.gridwidth = 2;
        cont.gridheight = 5;
        cont.fill = GridBagConstraints.BOTH;
        cont.anchor = GridBagConstraints.CENTER;
        cont.insets = new Insets(20, 20, 20, 20);
        cont.weightx = 2.0f;
        cont.weighty = 5.0f;
        panel.add(panelArbre, cont);

        /*
         * Liste des étudiants du groupe sélectionné
         */
        JPanel listGroup = arbreA.getFocusedEtu();
        cont.gridx = 2;
        cont.gridy = 0;
        cont.gridwidth = 2;
        cont.gridheight = 5;
        cont.fill = GridBagConstraints.BOTH;
        cont.anchor = GridBagConstraints.CENTER;
        cont.insets = new Insets(20, 20, 20, 20);
        cont.weightx = 2.0f;
        cont.weighty = 5.0f;
        panel.add(listGroup, cont);
        JScrollPane scrollGroup = new JScrollPane(listGroup);
        scrollGroup.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(scrollGroup, cont);

        /*
         * Liste des étudiants de toute la promo
         */
        JPanel listAll = arbreA.getAllEtu();
        cont.gridx = 4;
        cont.gridy = 0;
        cont.gridwidth = 2;
        cont.gridheight = 5;
        cont.fill = GridBagConstraints.BOTH;
        cont.anchor = GridBagConstraints.CENTER;
        cont.insets = new Insets(20, 20, 20, 20);
        cont.weightx = 2.0f;
        cont.weighty = 5.0f;
        panel.add(listAll, cont);
        JScrollPane scrollAll = new JScrollPane(listAll);
        scrollAll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(scrollAll, cont);
        

        /*
         * Contrainte, ajout + parametres barre de recherche
         */
        cont.gridx = 0;
        cont.gridy = 5;
        cont.gridwidth = 2;
        cont.gridheight = 1;
        cont.fill = GridBagConstraints.BOTH;
        cont.anchor = GridBagConstraints.CENTER;
        cont.insets = new Insets(60, 60, 60, 60);
        cont.weightx = 2.0f;
        cont.weighty = 1.0f;
        search.setBackground(Color.DARK_GRAY);
        search.setForeground(Color.WHITE);
        panel.add(search, cont);

        cont.gridx = 2;
        cont.gridy = 5;
        cont.gridwidth = 2;
        cont.gridheight = 1;
        cont.fill = GridBagConstraints.BOTH;
        cont.anchor = GridBagConstraints.CENTER;
        cont.insets = new Insets(60, 60, 60, 60);
        cont.weightx = 2.0f;
        cont.weighty = 1.0f;
        nom.setBackground(Color.DARK_GRAY);
        nom.setForeground(Color.WHITE);
        panel.add(nom, cont);
        

        cont.gridx = 4;
        cont.gridy = 5;
        cont.gridwidth = 2;
        cont.gridheight = 1;
        cont.fill = GridBagConstraints.BOTH;
        cont.anchor = GridBagConstraints.CENTER;
        cont.insets = new Insets(60, 60, 60, 60);
        cont.weightx = 2.0f;
        cont.weighty = 1.0f;
        commit.setBackground(Color.LIGHT_GRAY);
        commit.setForeground(Color.BLACK);
        commit.setBorder(BorderFactory.createLineBorder(Color.black));
        panel.add(commit, cont);
        commit.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                new SearchEtu(nom, groupFactory);
                
            }
        });

        setVisible(true);
    }
}