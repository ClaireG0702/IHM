package fr.iutfbleau.projetIHM2022FI2.Model.MNP;
import fr.iutfbleau.projetIHM2022FI2.Model.API.*;
import java.util.*;
import java.util.function.BiConsumer;
/**
 * Usine abstraite gérant l'ensemble des groupes.
 * 
 */

public class AbstractGroupeFactoryNP implements AbstractGroupeFactory {

    // la racine (promotion)
    private Groupe promo;

    // On utilise une table de hachage pour retrouver facilement un groupe (à partir de son id).
    // Si il y a beaucoup de groupes c'est plus rapide que de parcourir toute une liste.
    private HashMap<Integer,Groupe> brain;




    /**
     * Le constructeur fabrique le groupe promotion vide.
     * Il faut ensuite y ajouter les étudiants.
     */
    public AbstractGroupeFactoryNP(String name, int min, int max){
        Objects.requireNonNull(name,"On ne peut pas créer une promotion  dont le nom est null");
        this.promo=new GroupeNP(name,min,max);
        this.brain=new HashMap<Integer,Groupe>();
        this.brain.put(Integer.valueOf(this.promo.getId()),this.promo);
    }
    
    /**
     * Test plutôt optimiste. Si la clé est identique alors on fait comme si c'était le bon groupe.
     */
    public Boolean knows(Groupe g){
        return this.brain.containsKey(Integer.valueOf(g.getId()));
    }

    
    
    /**
     * permet de récupérer le Groupe qui contient les étudiants de toute la promotion
     * @return la promo.
     */
    public Groupe getPromotion(){
        return this.promo;
    }

    /**
     * permet de supprimer un groupe connu de l'usine abstraite qui ne contient pas de groupes.
     * Pour détruire un groupe connu qui en contient d'autres il faut le faire récursivement.
     *
     * @throws java.lang.NullPointerException si un argument est null
     * @throws java.lang.IllegalStateException si le groupe contient des groupes
     * @throws java.lang.IllegalArgumentException si le groupe n'est pas connu de l'usine abstraite ou bien si le groupe est celui de toute la promotion (renvoyé par getPromotion)
     */
    public void deleteGroupe(Groupe g){
        Objects.requireNonNull(g,"On ne peut pas enlever un groupe null car null n'est pas un groupe autorisé");
        if (!this.knows(g)){
            throw new IllegalArgumentException("Impossible d'enlever un groupe inconnu");
        }
        if (this.getPromotion().equals(g)){
            throw new IllegalArgumentException("Impossible de détruire le groupe de toute la promotion");
        }
        if (g.getSousGroupes().size()>0){
            throw new IllegalStateException("Impossible de détruire un groupe contenant un groupe");
        }
        g.getPointPoint().removeSousGroupe(g);
        this.brain.remove(Integer.valueOf(g.getId()));
    }

    /**
     * permet d'ajouter un groupe vide de type FREE comme sous-groupe d'un groupe donné.
     * @param pere le groupe père du groupe à créer 
     * @param name le nom du groupe à créer
     * @param min,max bornes indicatives sur la taille du groupe à créer
     *
     * @throws java.lang.NullPointerException si un argument est null
     * @throws java.lang.IllegalArgumentException si le groupe pere est de type PARTITION
     *                                            ou si il n'y a pas 0 plus petit que min plus petit ou egal max 
     */
    public void createGroupe(Groupe pere, String name, int min, int max){
        Objects.requireNonNull(pere,"Le groupe pere ne peut pas être null");
        Objects.requireNonNull(name,"Le nouveau groupe ne peut pas avoir null comme nom");
        if (!this.knows(pere)){
            throw new IllegalArgumentException("Interdit d'ajouter un fils à un groupe inconnu");
        }
        if (pere.getType().equals(TypeGroupe.PARTITION)){
            throw new IllegalArgumentException("Impossible d'ajouter un groupe à une parition. Il faut utiliser createPartition pour créer une partition");
        }
        if ( min <= 0 || max < min){
            throw new IllegalArgumentException("Il faut que 0 < min <= max");
        }
        Groupe g = new GroupeNP(pere,name,min,max);
        pere.addSousGroupe(g);
        this.brain.put(Integer.valueOf(g.getId()),g);
    }

    /**
     * permet de créer une partition automatiquement sous un groupe donné.
     *
     * @param pere le groupe père du groupe à partitionner 
     * @param name le nom des groupe à créer (on ajoute à la suite un numéro de 1 à n  pour distinguer chaque groupe formant la partition)
     * @param n le nombre de partitions
     * @throws java.lang.NullPointerException si un argument est null
     * @throws java.lang.IllegalArgumentException si le groupe pere est de type PARTITION 
     *                                            ou n négatif ou nul
     *
     * NB. doit créer une "copie" de pere 
     *     sous pere de type Partition et ajouter sous ce groupe, n groupes de type "FREE". 
     *     les valeurs min et max de ces n groupes sont 
     *       min = 0 et 
     *       max = partie entière de N/n plus 1, où N est le nombre max du groupe pere.   
     */
    public void createPartition(Groupe pere, String name, int n){
        Objects.requireNonNull(pere,"Le groupe pere ne peut pas être null");
        Objects.requireNonNull(name,"Le nouveau groupe ne peut pas avoir null comme nom");
        if (!this.knows(pere)){
            throw new IllegalArgumentException("Impossible de partitionner ce groupe inconnu");
        }
        if (pere.getType().equals(TypeGroupe.PARTITION)){
            throw new IllegalArgumentException("Impossible de créer une partition à ce niveau. Il faut soit repartitionner le groupe au dessus, soit partitionner une partition en dessous.");
        }
        if ( n <= 0){
            throw new IllegalArgumentException("Le nombre de partitions doit être strictement positif");
        }
        //Création de la racine de la partition.
        Groupe copiePereRacinePartition = new GroupeNP(pere);
        pere.addSousGroupe(copiePereRacinePartition);
        this.brain.put(Integer.valueOf(copiePereRacinePartition.getId()),copiePereRacinePartition);
        // création des sous-groupes
        int min = 0;
        int max = ((int) Math.floor(pere.getMax()/n))+1; //modif : le max doit être le max du père et non sa taille
        List<Groupe> groupes = new ArrayList<Groupe>(n);
        for(int i = 0; i<n; i++){
            Groupe g = new GroupeNP(copiePereRacinePartition,name+"_"+(i+1),min,max); //modif : i+1 au lieu de i car TD_0 pas naturel
            groupes.add(i,g);// ajout dans le tableau des groupes
            copiePereRacinePartition.addSousGroupe(g);
            this.brain.put(Integer.valueOf(g.getId()),g);
        }
        // Partage des étudiants (on ne prête pas attention aux min et max)
        int i=0;
        for (Etudiant s: pere.getEtudiants()){
            copiePereRacinePartition.addEtudiant(s);
            groupes.get(i).addEtudiant(s);
            i = (i+1) %n;
        }
        //modif : On renomme la copie du père et on lui ajoute les étudiants du père
        copiePereRacinePartition.SetName(name);
        for (Etudiant e : pere.getEtudiants()){
        copiePereRacinePartition.addEtudiant(e);
        }
    }

    /**
     * permet d'ajouter un étudiant à un groupe.
     *
     * @param g le groupe dans lequel il faut ajouter l'étudiant
     * @param e l'étudiant à ajouter
     *
     * @throws java.lang.NullPointerException si un argument est null
     * @throws java.lang.IllegalArgumentException la factory ne connaît pas g
     * @throws java.lang.IllegalStateException le père de g ne contient pas e
     */
    public void addToGroupe(Groupe g, Etudiant e){
        Objects.requireNonNull(g,"Le groupe ne peut pas être null");
        Objects.requireNonNull(e,"L'étudiant ne peut pas être null");
        if (!this.knows(g)){
            throw new IllegalArgumentException("Impossible d'ajouter l'étudiant car le est groupe inconnu");
        }
        g.addEtudiant(e);
    }

    /**
     * permet d'enlever un étudiant d'un groupe.
     *
     * @param g le groupe dans lequel il faut enlever l'étudiant
     * @param e l'étudiant à enlever
     *
     * @throws java.lang.NullPointerException si un argument est null
     * @throws java.lang.IllegalStateException g ne contient pas e
     * @throws java.lang.IllegalArgumentException la factory ne connaît pas g
     */
    public void dropFromGroupe(Groupe g, Etudiant e){
        Objects.requireNonNull(g,"Le groupe ne peut pas être null");
        Objects.requireNonNull(e,"L'étudiant ne peut pas être null");
        if (!this.knows(g)){
            throw new IllegalArgumentException("Impossible d'ajouter l'étudiant car le est groupe inconnu");
        }
        g.removeEtudiant(e);
    }

     /**
     * permet de retrouver un étudiant à partir d'un String.
     *
     * NB. dans une version simple il doit s'agir du nom exact.
     * dans une version un peu plus complexe, il s'agit des premières lettres du nom
     * dans une version avancée, on peut autoriser une expression régulière plus ou moins complexe qui est générée si la première recherche n'a pas renvoyé de candidat.
     *
     * @param nomEtu le nom approximmatif de l'étudiant
     * @return l'ensemble des étudiants connus de la factory ayant un nom "proche" de ce string au sens de la remarque ci-dessus.
     *
     * @throws java.lang.NullPointerException si le String est null.
     */
    public Set<Etudiant> getEtudiants(String nomEtu){
        // on cherche bêtement dans la promo.
        Set<Etudiant> out = new LinkedHashSet<Etudiant>();
        for (Etudiant e : getPromotion().getEtudiants()){
            if (e.getNom().equals(nomEtu)){
                    out.add(e);
                    //break;
                    // modif : avec le break on arrete la boucle et on n'a que le premier resultat...
                }
        }
        return out;
    }

    /**
     * permet de retrouver les groupes d'un étudiant.
     *
     * @param etu un étudiant
     * @return Etudiant l'étudiant connu de la factory ayant cet identifiant
     *
     * @throws java.lang.NullPointerException si le String est null.
     */
    public Set<Groupe> getGroupesOfEtudiant(Etudiant etu){
        // Ajout
        Objects.requireNonNull(etu,"L'étudiant ne peut pas être null");

        //Set contenant tous les groupes
        Set<Groupe> all = new LinkedHashSet<Groupe>();
        this.brain.forEach(new BiConsumer<Integer,Groupe>() {

            @Override
            public void accept(Integer i, Groupe g) {
                all.add(g);
            }
            
        });

        Set<Groupe> match = new LinkedHashSet<Groupe>();

        // Boucle qui explore les groupes
        for (Groupe grp : all){
            Set<Etudiant> etudiants = grp.getEtudiants();
            for (Etudiant etud : etudiants){
                if (etud.equals(etu)){
                    match.add(grp);
                }
            }
        }

        return match;
    }

    /**
     * Méthode ajoutée qui renvoit le groupe correspondant à son nom
     *
     * @param groupeName le nom du groupe recherché
     * @return Groupe le groupe correspondant
     *
     * @throws java.lang.NullPointerException si le String est null.
     */
    public Groupe getGroupeByName(String groupeName){
        // Ajout
        Objects.requireNonNull(groupeName,"Le nom du groupe doit être définit");

        //Set contenant tous les groupes
        Set<Groupe> all = new LinkedHashSet<Groupe>();
        this.brain.forEach(new BiConsumer<Integer,Groupe>() {

            @Override
            public void accept(Integer i, Groupe g) {
                all.add(g);
            }
            
        });

        // Boucle qui explore les groupes
        for (Groupe grp : all){
            if (grp.getName().equals(groupeName)){
                return grp;
            }
        }
        return null;
    }
}
