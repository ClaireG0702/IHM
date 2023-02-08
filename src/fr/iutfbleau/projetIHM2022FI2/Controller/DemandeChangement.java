package fr.iutfbleau.projetIHM2022FI2.Controller;

import java.awt.event.*;

import javax.swing.JOptionPane;

import fr.iutfbleau.projetIHM2022FI2.Model.API.AbstractGroupeFactory;
import fr.iutfbleau.projetIHM2022FI2.Model.API.Groupe;
import fr.iutfbleau.projetIHM2022FI2.View.GetDemande;;


/**
 * La classe <code>DemandeChangement</code> implémente ActionListener, 
 * vérifie que le groupe de la demande n'est pas plein et ouvre une fenêtre pour demander plus d'informations
 * 
 * @version 1.1
 * @author Adrien Dos Santos, Claire Gobert, Kayyissa Haissous
 */
public class DemandeChangement implements ActionListener {
    private AbstractGroupeFactory groupeFactory;
    private Groupe groupe;

    /**
     * Constructeur
     * 
     * @param groupeFactory l'usine qui gère les groupes
     * @param groupe le groupe dans lequel l'étudiant veut aller
     */
    public DemandeChangement(AbstractGroupeFactory groupeFactory, Groupe groupe) {
        this.groupeFactory = groupeFactory;
        this.groupe = groupe;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (this.groupe.getMax()==this.groupe.getSize()){
            JOptionPane.showMessageDialog(null, "Ce groupe est déjà plein", "InfoBox" , JOptionPane.INFORMATION_MESSAGE);
        } else {
            new GetDemande(this.groupeFactory, this.groupe);
        }
        
    }
    
}
