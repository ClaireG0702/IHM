package fr.iutfbleau.projetIHM2022FI2.Controller;


import fr.iutfbleau.projetIHM2022FI2.Model.API.AbstractGroupeFactory;
import fr.iutfbleau.projetIHM2022FI2.Model.API.Groupe;
import fr.iutfbleau.projetIHM2022FI2.View.MoveEtu;
import fr.iutfbleau.projetIHM2022FI2.View.NewEtu;
import fr.iutfbleau.projetIHM2022FI2.View.SupprEtu;

import java.awt.event.*;

/**
 * La classe <code>GestionEtu</code> ouvre la pop up pour renseigner les informations pour une suppression, 
 * un déplacement ou un ajout d'étudiant
 * 
 * @version 1.1
 * @author Adrien Dos Santos, Claire Gobert, Kayyissa Haissous
 */
public class GestionEtu implements ActionListener {

    private AbstractGroupeFactory groupeFactory;
    private int action;
    private Groupe gActif;
    public GestionEtu(AbstractGroupeFactory groupeFactory, Groupe gActif,int action){
            this.groupeFactory=groupeFactory;
            this.gActif = gActif;
            this.action = action;
            }
    
        /**
       * Méthode implémentée de ActionListener qui ouvre la popup appropriée
       *
       * @param e les informations relatives à l'action sur le bouton
       */
        @Override
        public void actionPerformed(ActionEvent e) {
            switch (this.action){
                case 0:
                    new NewEtu(this.groupeFactory, this.gActif);
                    break;
                case 1:
                    new MoveEtu(this.groupeFactory,this.gActif);
                    break;
                case 2:
                    new SupprEtu(this.groupeFactory, this.gActif);
                    break;
            }
            
        }
    
        
    }
    