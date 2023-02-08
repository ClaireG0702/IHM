package fr.iutfbleau.projetIHM2022FI2.Controller;
import fr.iutfbleau.projetIHM2022FI2.Model.API.AbstractGroupeFactory;
import fr.iutfbleau.projetIHM2022FI2.View.*;
import java.awt.event.*;

/**
 * La classe <code>ListenerGroupe</code> implémente l'interface ActionListener
 * et appelle la classe qui va gérer l'action demandée
 * en lui offrant les informations dont elle a besoin
 * 
 * @version 1.1
 * @author Adrien Dos Santos, Claire Gobert, Kayyissa Haissous
 */
public class ListenerGroupe implements ActionListener{

    private AbstractGroupeFactory groupeFactory;
    private AfficheArbre arbreA;
    private int action;
    
    /**
   * Constructeur qui donne leur valeurs aux attributs
   *
   * @param groupeFactory l'usine qui gère les groupes
   * @param arbreA l'arbre des groupes 
   * @param actionType l'action voulue
   */
    public ListenerGroupe(AbstractGroupeFactory groupeFactory, AfficheArbre arbreA, int actionType){
        this.groupeFactory=groupeFactory;
        this.arbreA = arbreA;
        this.action = actionType;

        
    }

    /**
   * Méthode implémentée de ActionListener qui ouvre la popup appropriée
   *
   * @param e les informations relatives à l'action sur le bouton
   */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (this.action){
            case 1:
                new NewGroup(this.groupeFactory, this.arbreA);
                break;
            case 2:
                new RenameGroupe(this.groupeFactory,this.arbreA);
                break;
            case 3:
                new SupprGroupe(this.groupeFactory, this.arbreA);
                break;
        }
        
    }

    
}
