package fr.iutfbleau.projetIHM2022FI2.Controller;

import fr.iutfbleau.projetIHM2022FI2.Model.API.AbstractGroupeFactory;
import fr.iutfbleau.projetIHM2022FI2.Model.API.Etudiant;
import fr.iutfbleau.projetIHM2022FI2.Model.API.Groupe;
import fr.iutfbleau.projetIHM2022FI2.Model.MNP.EtudiantNP;

/**
 * La classe <code>EmulEtu</code> créer des étudiants pour tester l'appli
 * 
 *  
 * @version 1.1
 * @author Adrien Dos Santos, Claire Gobert, Kayyissa Haissous
 */
public class EmulEtu {
    public EmulEtu(AbstractGroupeFactory agf){
        Groupe promo = agf.getPromotion();
        promo.addEtudiant(new EtudiantNP("Jean", "Michel"));
        promo.addEtudiant(new EtudiantNP("Jean", "Luc"));
        promo.addEtudiant(new EtudiantNP("Picard", "Jean-Luc"));
        promo.addEtudiant(new EtudiantNP("Riker", "William"));
        promo.addEtudiant(new EtudiantNP("Troy", "Baboune"));
        promo.addEtudiant(new EtudiantNP("Dupond", "Tristan"));
        promo.addEtudiant(new EtudiantNP("Bourgey", "Cédric"));
        promo.addEtudiant(new EtudiantNP("Le Grand", "Camille"));
        promo.addEtudiant(new EtudiantNP("Lefevre", "Nina"));
        promo.addEtudiant(new EtudiantNP("Conquerent", "Guillaume"));
        promo.addEtudiant(new EtudiantNP("Marchant", "Julien"));
        promo.addEtudiant(new EtudiantNP("Caffe", "Victoire"));
        promo.addEtudiant(new EtudiantNP("De Bourg", "Maria"));
        promo.addEtudiant(new EtudiantNP("Maria", "Avé"));
        promo.addEtudiant(new EtudiantNP("Beethoven", "Jean-Luc"));
        promo.addEtudiant(new EtudiantNP("Traum", "Annika"));
        promo.addEtudiant(new EtudiantNP("Marie", "Fabrice"));
        promo.addEtudiant(new EtudiantNP("Jean", "Geraldine"));
        promo.addEtudiant(new EtudiantNP("Picard", "Amandine"));
        promo.addEtudiant(new EtudiantNP("Riker", "Francis"));
        promo.addEtudiant(new EtudiantNP("Troy", "Thomas"));
        promo.addEtudiant(new EtudiantNP("Dupond", "Meriem"));
        promo.addEtudiant(new EtudiantNP("Bourgey", "Robert"));
        promo.addEtudiant(new EtudiantNP("Daniel", "Antoine"));
        promo.addEtudiant(new EtudiantNP("Chiu", "Licorne"));
        promo.addEtudiant(new EtudiantNP("Cuverin", "Guy"));
        promo.addEtudiant(new EtudiantNP("Le Marechal", "Julie"));
        promo.addEtudiant(new EtudiantNP("Le Grand", "Victoire"));
        promo.addEtudiant(new EtudiantNP("De Bourg", "Maria"));
        promo.addEtudiant(new EtudiantNP("Gobert", "Claire"));
        promo.addEtudiant(new EtudiantNP("Dos Santos", "Adrien"));
        promo.addEtudiant(new EtudiantNP("Haissous", "Kayyissa"));

        Etudiant e1=new EtudiantNP("césar","lycurgus");
        Etudiant e2=new EtudiantNP("denis","uranus");
        Etudiant e3=new EtudiantNP("marcel","castor");
        Etudiant e4=new EtudiantNP("marin","eurydice");
        Etudiant e5=new EtudiantNP("constantin","akoni");
        Etudiant e6=new EtudiantNP("donat","anakoni");
        Etudiant e7=new EtudiantNP("alexandre","apikalia");
        Etudiant e8=new EtudiantNP("andré","ekewaka");
        Etudiant e9=new EtudiantNP("renard","elikapeka");
        Etudiant e10=new EtudiantNP("achille","haukea");

        System.out.print(".");
        
        Etudiant e11=new EtudiantNP("agathe","iakopa");
        Etudiant e12=new EtudiantNP("sabine","spartacus");
        Etudiant e13=new EtudiantNP("michel","caligula");
        Etudiant e14=new EtudiantNP("marthe","alaric");
        Etudiant e15=new EtudiantNP("juliane","hannibal");
        Etudiant e16=new EtudiantNP("anne","juvenal");
        Etudiant e17=new EtudiantNP("sophie","bede");
        Etudiant e18=new EtudiantNP("louis","hamilcar");
        Etudiant e19=new EtudiantNP("diane","ladislas");
        Etudiant e20=new EtudiantNP("christine","mahatma");

        System.out.print(".");
        
        Etudiant e21=new EtudiantNP("francine","napoleon");
        Etudiant e22=new EtudiantNP("louise","lalita");
        Etudiant e23=new EtudiantNP("chantal","laxman");
        Etudiant e24=new EtudiantNP("giselle","laxmi");
        Etudiant e25=new EtudiantNP("caroline","leela");
        Etudiant e26=new EtudiantNP("claude","lila");
        Etudiant e27=new EtudiantNP("pauline","lilavati");
        Etudiant e28=new EtudiantNP("avril","lochan");
        Etudiant e29=new EtudiantNP("jacqueline","madhav");
        Etudiant e30=new EtudiantNP("denise","turlough");

        System.out.print(".");
        
        Etudiant e31=new EtudiantNP("gabrielle","uaithne");
        Etudiant e32=new EtudiantNP("julie","uilleag");
        Etudiant e33=new EtudiantNP("madeleine","uilliam");
        Etudiant e34=new EtudiantNP("charlotte","uinseann");
        Etudiant e35=new EtudiantNP("bertrand","ulick");
        Etudiant e36=new EtudiantNP("lucile","ultan");
        Etudiant e37=new EtudiantNP("nicole","firdaus");
        Etudiant e38=new EtudiantNP("blanche","yasmin");
        Etudiant e39=new EtudiantNP("jeanine","javed");
        Etudiant e40=new EtudiantNP("roxane","naveed");

        System.out.print(".");
        
        Etudiant e41=new EtudiantNP("adeline","shahnaz");
        Etudiant e42=new EtudiantNP("dion","ardashir");
        Etudiant e43=new EtudiantNP("liane","atefeh");
        Etudiant e44=new EtudiantNP("myriam","luigina");
        Etudiant e45=new EtudiantNP("danielle","luigino");
        Etudiant e46=new EtudiantNP("arlette","maddalena");
        Etudiant e47=new EtudiantNP("michelle","manfredo");
        Etudiant e48=new EtudiantNP("justine","manlio");
        Etudiant e49=new EtudiantNP("natalie","marcellino");
        Etudiant e50=new EtudiantNP("aline","mariangela");

        System.out.print(".");
        
        Etudiant e51=new EtudiantNP("prosper","marzio");
        Etudiant e52=new EtudiantNP("mirabelle","massimiliano");
        Etudiant e53=new EtudiantNP("carine","matteo");
        Etudiant e54=new EtudiantNP("jeannine","melchiorre");
        Etudiant e55=new EtudiantNP("dianne","micaela");
        Etudiant e56=new EtudiantNP("evette","michela");
        Etudiant e57=new EtudiantNP("gisselle","michelangela");

        System.out.println("terminé.");


        agf.addToGroupe(agf.getPromotion(),e1);
        agf.addToGroupe(agf.getPromotion(),e2);
        agf.addToGroupe(agf.getPromotion(),e3);
        agf.addToGroupe(agf.getPromotion(),e4);
        agf.addToGroupe(agf.getPromotion(),e5);
        agf.addToGroupe(agf.getPromotion(),e6);
        agf.addToGroupe(agf.getPromotion(),e7);
        agf.addToGroupe(agf.getPromotion(),e8);
        agf.addToGroupe(agf.getPromotion(),e9);
        agf.addToGroupe(agf.getPromotion(),e10);

        agf.addToGroupe(agf.getPromotion(),e11);
        agf.addToGroupe(agf.getPromotion(),e12);
        agf.addToGroupe(agf.getPromotion(),e13);
        agf.addToGroupe(agf.getPromotion(),e14);
        agf.addToGroupe(agf.getPromotion(),e15);
        agf.addToGroupe(agf.getPromotion(),e16);
        agf.addToGroupe(agf.getPromotion(),e17);
        agf.addToGroupe(agf.getPromotion(),e18);
        agf.addToGroupe(agf.getPromotion(),e19);
        agf.addToGroupe(agf.getPromotion(),e20);
        
        agf.addToGroupe(agf.getPromotion(),e21);
        agf.addToGroupe(agf.getPromotion(),e22);
        agf.addToGroupe(agf.getPromotion(),e23);
        agf.addToGroupe(agf.getPromotion(),e24);
        agf.addToGroupe(agf.getPromotion(),e25);
        agf.addToGroupe(agf.getPromotion(),e26);
        agf.addToGroupe(agf.getPromotion(),e27);
        agf.addToGroupe(agf.getPromotion(),e28);
        agf.addToGroupe(agf.getPromotion(),e29);
        agf.addToGroupe(agf.getPromotion(),e30);
        agf.addToGroupe(agf.getPromotion(),e31);
        agf.addToGroupe(agf.getPromotion(),e32);
        agf.addToGroupe(agf.getPromotion(),e33);
        agf.addToGroupe(agf.getPromotion(),e34);
        agf.addToGroupe(agf.getPromotion(),e35);
        agf.addToGroupe(agf.getPromotion(),e36);
        agf.addToGroupe(agf.getPromotion(),e37);
        agf.addToGroupe(agf.getPromotion(),e38);
        agf.addToGroupe(agf.getPromotion(),e39);
        
        agf.addToGroupe(agf.getPromotion(),e40);
        agf.addToGroupe(agf.getPromotion(),e41);
        agf.addToGroupe(agf.getPromotion(),e42);
        agf.addToGroupe(agf.getPromotion(),e43);
        agf.addToGroupe(agf.getPromotion(),e44);
        agf.addToGroupe(agf.getPromotion(),e45);
        agf.addToGroupe(agf.getPromotion(),e46);
        agf.addToGroupe(agf.getPromotion(),e47);
        agf.addToGroupe(agf.getPromotion(),e48);
        agf.addToGroupe(agf.getPromotion(),e49);
        agf.addToGroupe(agf.getPromotion(),e50);
        agf.addToGroupe(agf.getPromotion(),e51);
        agf.addToGroupe(agf.getPromotion(),e52);
        agf.addToGroupe(agf.getPromotion(),e53);
        agf.addToGroupe(agf.getPromotion(),e54);
        agf.addToGroupe(agf.getPromotion(),e55);
        agf.addToGroupe(agf.getPromotion(),e56);
        agf.addToGroupe(agf.getPromotion(),e57);
        
        agf.createPartition(promo, "TD", 3);

    }
}
