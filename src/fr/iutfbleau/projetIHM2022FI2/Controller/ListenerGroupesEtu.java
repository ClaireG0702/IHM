package fr.iutfbleau.projetIHM2022FI2.Controller;

import java.awt.event.*;

import fr.iutfbleau.projetIHM2022FI2.Model.API.AbstractGroupeFactory;
import fr.iutfbleau.projetIHM2022FI2.Model.API.Etudiant;
import fr.iutfbleau.projetIHM2022FI2.View.GroupeEtu;

/**
 * La classe <code>ListenerGroupesEtu</code> implémente l'interface MouseListener
 * ouvre une fenêtre en lui donnant les groupes dans lesquels est un étudiant donné
 * 
 * @version 1.1
 * @author Adrien Dos Santos, Claire Gobert, Kayyissa Haissous
 */
public class ListenerGroupesEtu implements MouseListener {

    /**
   * Constructeur qui cherche l'étudiant dont on veut connaitre les groupes
   *
   * @param id l'identifiant de l'étudiant
   * @param groupeFactory l'usine qui gère les groupes 
   */
    private Etudiant etu;
    private AbstractGroupeFactory groupeFactory;

    public ListenerGroupesEtu(int id, AbstractGroupeFactory groupeFactory){
        
        this.groupeFactory =  groupeFactory;    
        for (Etudiant e : groupeFactory.getPromotion().getEtudiants()){
            if (e.getId()==id){
                this.etu = e;
            }
        }
    }

    /**
   * Méthode implémentée de MouseListener qui lance la fenêtre lorqu'on a cliqué sur un étudiant
   *
   * @param e les informations sur le click sur l'élément 
   */
    @Override
    public void mouseClicked(MouseEvent e) {
        new GroupeEtu(this.groupeFactory.getGroupesOfEtudiant(this.etu), this.groupeFactory);
    }
    
    /**
   * Méthode non implémentées car pas utile ici
   *
   * @param e les informations sur le click sur l'élément 
   */
    public void mousePressed(MouseEvent e) {}
    /**
   * Méthode non implémentées car pas utile ici
   *
   * @param e les informations sur le click sur l'élément 
   */
    public void mouseReleased(MouseEvent e) {}
    /**
   * Méthode non implémentées car pas utile ici
   *
   * @param e les informations sur le click sur l'élément 
   */
    public void mouseEntered(MouseEvent e) {}
    /**
   * Méthode non implémentées car pas utile ici
   *
   * @param e les informations sur le click sur l'élément 
   */
    public void mouseExited(MouseEvent e) {}
    
}
