package fr.iutfbleau.projetIHM2022FI2.Controller;

import java.awt.Insets;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import fr.iutfbleau.projetIHM2022FI2.Model.API.AbstractGroupeFactory;
import fr.iutfbleau.projetIHM2022FI2.Model.API.Etudiant;
import fr.iutfbleau.projetIHM2022FI2.Model.API.Groupe;

/**
 * La classe <code>AfficheArbre</code> permet d'afficher l'arbre des groupes
 * et les étudiants s'y trouvant
 * 
 * @version 1.1
 * @author Adrien Dos Santos, Claire Gobert, Kayyissa Haissous
 */

public class AfficheArbre {
    private JTree arbre;
    private DefaultMutableTreeNode racine;
    private Groupe promo;
    private AbstractGroupeFactory groupeFactory;
    private JPanel allEtu;
    private JPanel focusEtu;
    private Groupe groupeA;
    private int droits;

    /**
     * Constructeur qui initialise les constantes publiques
     * et place un listener sur l'arbre
     *
     * @param groupeFactory l'usine de groupes qui gère les groupes de l'arbre
     * @param racine        la racine de l'arbre à gerer
     * @param droits        droits sur la modification des etudiants
     */
    public AfficheArbre(AbstractGroupeFactory groupeFactory, DefaultMutableTreeNode racine, int droits) {
        UIManager.put("Panel.background", Color.DARK_GRAY);

        // On initialiser les attributs
        this.droits = droits;
        this.racine = racine;
        this.groupeFactory = groupeFactory;
        this.promo = groupeFactory.getPromotion();

        this.arbre = new JTree(racine);
        this.allEtu = new JPanel();
        this.focusEtu = new JPanel();

        // On place des layout sur les panels pour afficher les listes d'étudiants
        allEtu.setLayout(new GridBagLayout());
        focusEtu.setLayout(new GridBagLayout());
        allEtu.setBorder(BorderFactory.createLineBorder(Color.black));
        focusEtu.setBorder(BorderFactory.createLineBorder(Color.black));
        groupeA = this.promo;

        // on initialise la liste des étudiants dans la promo
        afficherEtu(racine, this.allEtu);

        // On place un listener sur la liste des étudiants du groupe qui a le focus
        this.arbre.addTreeSelectionListener(new TreeSelectionListener() {

            // Le listener permet de recharger le panel avec les étudiants du groupe ayant
            // le focus
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) arbre.getLastSelectedPathComponent();
                afficherEtu(selectedNode, focusEtu);
                groupeA = groupeFactory.getGroupeByName((String) selectedNode.getUserObject());
            }
        });
        // TODO: scrollbar
        // TODO: etre moins moche loul
    }

    /**
     * Renvoie l'arbre des groupes en le rechargeant
     *
     * @return JTree l'arbre des groupes
     */
    public JTree printArbre() {
        this.racine.removeAllChildren();
        remplissageArbre(this.racine, this.promo);
        this.arbre.setOpaque(false);
        this.arbre.updateUI();
        return this.arbre;
    }

    /**
     * Rempli l'arbre en explorant les groupes de manière récursive
     * 
     * @param parent la racine de l'arbre à remplir
     * @param pere   le groupe qui correspond à cette racine
     */
    protected void remplissageArbre(DefaultMutableTreeNode parent, Groupe pere) {
        for (Groupe fils : pere.getSousGroupes()) {
            DefaultMutableTreeNode enfant = new DefaultMutableTreeNode(fils.getName());
            parent.add(enfant);
            if (fils.getSousGroupes().size() > 0) {
                remplissageArbre(enfant, fils);
            }
        }
    }

    /**
     * Renvoie un panel contenant une liste des élèves dans la promo
     *
     * @return JPanel la liste des élèves à la racine de l'arbre
     */
    public JPanel getAllEtu() {
        afficherEtu(this.racine, this.allEtu);
        return this.allEtu;
    }

    /**
     * Renvoie un panel contenant une liste des élèves dans le groupe sélectionné
     *
     * @return JPanel la liste des élèves du groupe sélectionné
     */
    public JPanel getFocusedEtu() {
        afficherEtu(this.racine, this.focusEtu);
        return this.focusEtu;
    }

    /**
     * Renvoie un panel contenant une liste des élèves dans le groupe sélectionné
     *
     * @return le groupe qui a le focus
     */
    public Groupe getGroupeActif() {
        return this.groupeA;
    }

    /**
     * Rempli un panel contenant une liste d'étudiants
     * 
     * @param groupeNode le noeud de l'arbre dont on veut connaîtres les étudiants
     * @param panel      le panel sur lequel afficher la liste d'étudiants
     */
    protected void afficherEtu(DefaultMutableTreeNode groupeNode, JPanel panel) {
        Groupe group = groupeFactory.getGroupeByName((String) groupeNode.getUserObject());
        GridBagConstraints cont = new GridBagConstraints();
        int i = 1;
        panel.removeAll();

        JLabel listg = new JLabel("Liste des étudiants du groupe " + group.getName() + " :");
        cont.gridx = 0;
        cont.gridy = 0;
        cont.gridwidth = 3;
        cont.gridheight = 1;
        cont.fill = GridBagConstraints.BOTH;
        cont.anchor = GridBagConstraints.CENTER;
        cont.insets = new Insets(20, 20, 10, 0);
        cont.weightx = 2.0f;
        cont.weighty = 0.0f;
        listg.setForeground(Color.WHITE);
        panel.add(listg, cont);

        for (Etudiant e : group.getEtudiants()) {

            JLabel etu = new JLabel(e.getNom() + " " + e.getPrenom());

            cont.gridx = 0;
            cont.gridy = i;
            cont.gridwidth = 3;
            cont.gridheight = 1;
            cont.fill = GridBagConstraints.BOTH;
            cont.anchor = GridBagConstraints.CENTER;
            cont.insets = new Insets(0, 20, 0, 0);
            cont.weightx = 1.0f;
            cont.weighty = 1.0f;
            etu.setBackground(Color.DARK_GRAY);
            etu.setForeground(Color.WHITE);
            i++;

            panel.add(etu, cont);
        }
        if (panel.equals(this.focusEtu)) {
            // S'il s'agit de la fenêtre de l'administrateur on ajoute des boutons pour qu'il puisse modifier les étudiants
            if (this.droits == 2) {
                for (int x = 0; x < 3; x++) {
                    String buttonText = new String();
                    switch (x) {
                        case 0:
                            buttonText = "Ajouter";
                            break;
                        case 1:
                            buttonText = "Déplacer";
                            break;
                        case 2:
                            buttonText = "Retirer";
                            break;
                    }
                    JButton action = new JButton(buttonText);
                    cont.gridx = x;
                    cont.gridy = i;
                    cont.gridwidth = 1;
                    cont.gridheight = 1;
                    cont.fill = GridBagConstraints.BOTH;
                    cont.anchor = GridBagConstraints.CENTER;
                    cont.insets = new Insets(20, 10, 20, 10);
                    cont.weightx = 1.0f;
                    cont.weighty = 0.0f;

                    action.addActionListener(new GestionEtu(groupeFactory, group, x));
                    action.setBackground(Color.LIGHT_GRAY);
                    action.setBorder(BorderFactory.createLineBorder(Color.black));
                    panel.add(action, cont);
                }
            } else if (this.droits==1){
                // Si c'est la fneêtre de l'étudiant, lui permettre de demander à rejoindre ce groupe
                JButton demande = new JButton("Rejoindre ce groupe");
                cont.gridx = 0;
                cont.gridy = i;
                cont.gridwidth = 1;
                cont.gridheight = 1;
                cont.fill = GridBagConstraints.BOTH;
                cont.anchor = GridBagConstraints.CENTER;
                cont.insets = new Insets(20, 10, 20, 10);
                cont.weightx = 1.0f;
                cont.weighty = 0.0f;

                demande.addActionListener(new DemandeChangement(groupeFactory, group));
                demande.setBackground(Color.LIGHT_GRAY);
                demande.setBorder(BorderFactory.createLineBorder(Color.black));
                panel.add(demande, cont);
            }

        }
        panel.updateUI();
    }
}
