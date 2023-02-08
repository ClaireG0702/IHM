package fr.iutfbleau.projetIHM2022FI2.View;

import fr.iutfbleau.projetIHM2022FI2.Controller.AfficheArbre;
import fr.iutfbleau.projetIHM2022FI2.Controller.EmulEtu;
import fr.iutfbleau.projetIHM2022FI2.Model.MNP.AbstractGroupeFactoryNP;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

import java.awt.*;

/**
 * La classe <code>Etudiant</code> est la fenêtre principale avec laquelle interagit l'étudiant
 * 
 *  
 * @version 1.1
 * @author Adrien Dos Santos, Claire Gobert, Kayyissa Haissous
 */
public class Etudiant extends JFrame{
    private JPanel panel = new JPanel();
    private JPanel panelArbre = new JPanel();

    /**
     * Constructeur qui ouvre la fenêtre
     */
    public Etudiant() {
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
        
        AfficheArbre arbreA = new AfficheArbre(groupFactory, promo, 1);

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

        setVisible(true);
    }
}