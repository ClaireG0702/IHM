package fr.iutfbleau.projetIHM2022FI2.Model.MP;
import fr.iutfbleau.projetIHM2022FI2.Model.API.*;

import java.sql.*;
import java.util.*;
/**
 * Un groupe
 */

public class GroupeP implements Groupe {
    //sert a la connexion a la bd
    private PreparedStatement requete;
	private ResultSet res;
    private Connection conn;

    //auto-incrément des groupes. (NB. inutile, mais ça fair un exemple d'attribut statique).
    // attributs naturels induits par getter de l'interface Groupe
    private int id;
    private String name;
    private int min,max,NbEtu;
    private TypeGroupe type;
    private Groupe pointPoint;    
    // On utilise une interface set pour les sous-groupes et pour les membres (ce sont bien des ensembles en pratique).
    private Set<Groupe> sousGroupes;
    private Set<Etudiant> membresDuGroupe;

    /**
     * Nouveau groupe vide de type ROOT sans étudiants, sans sous-Groupe
     */
    public GroupeP(String name, int min, int max,Connection conn){
        Objects.requireNonNull(name,"On ne peut pas créer un groupe dont le nom est null");
        Objects.requireNonNull(conn,"Il faut une connexion a la base de donnée");
        this.conn = conn;
        ID();
        this.name=name;
        this.min=min;
        this.max=max;
        this.type=TypeGroupe.ROOT;
        this.pointPoint=this;
        this.sousGroupes=new LinkedHashSet<Groupe>();
        this.membresDuGroupe=new LinkedHashSet<Etudiant>();
    }
    
    /**
     * Nouveau groupe vide de type FREE sans étudiants, sans sous-Groupe
     */
    public GroupeP(Groupe pere, String name, int min, int max ,Connection conn){
        Objects.requireNonNull(pere,"On ne peut pas créer un groupe dont le père est null");
        Objects.requireNonNull(name,"On ne peut pas créer un groupe dont le nom est null");
        Objects.requireNonNull(conn,"Il faut une connexion a la base de donnée");
        this.conn = conn;
        ID();
        this.name=name;
        this.min=min;
        this.max=max;
        this.type=TypeGroupe.FREE;
        this.pointPoint=pere;
        this.sousGroupes=new LinkedHashSet<Groupe>();
        this.membresDuGroupe=new LinkedHashSet<Etudiant>();

    }

    /**
     * Nouveau groupe de type PARTITION dupliquant le groupe passé en paramètre (pour servir de racine à une partition de ce groupe de type FREE passé en paramètre).
     * 
     */
    public GroupeP(Groupe pere,Connection conn){
        Objects.requireNonNull(pere,"On ne peut pas créer un groupe dont le père est null");
        Objects.requireNonNull(conn,"Il faut une connexion a la base de donnée");
        this.conn = conn;
        ID();
        this.name=pere.getName()+"_PARTITION_"+ this.id;
        this.min=pere.getMin();
        this.max=pere.getMax();
        this.type=TypeGroupe.PARTITION;
        this.pointPoint=pere;
        this.sousGroupes= new LinkedHashSet<Groupe>();
        this.membresDuGroupe= pere.getEtudiants();
        try {requete = this.conn.prepareStatement(
            "SELECT COUNT(*) FROM InfoGroupe WHERE Id=?;");
            requete.setInt(1,this.id);
            res = requete.executeQuery();
            res.next();
            this.NbEtu=res.getInt(1);
        } catch (SQLException e) {
            System.err.println("count cassé");
        }
        try {
            this.res.close();
            requete.close();
        } catch (SQLException e) {
            System.err.println("count cassé");
        }
    }

    /**@param id id de la table
     * @param conn connexion a la base de données
     * charge un groupe deja existant
     * @throws java.lang.IllegalArgumentException si l'id du groupe existe pas
     */
    public GroupeP(int id,Connection conn){
        Objects.requireNonNull(id,"On ne trouver un groupe inexistant");
        Objects.requireNonNull(conn,"Il faut une connexion a la base de donnée");
        this.conn=conn;
        this.id=id;
        this.type=TypeGroupe.FREE;
        this.name = new String();
        this.membresDuGroupe = new LinkedHashSet<Etudiant>();
        this.sousGroupes=new LinkedHashSet<Groupe>();
        if(IsIdExist()){
            InitVar();
            //size 
            try {requete = this.conn.prepareStatement(
                "SELECT COUNT(*) FROM InfoGroupe WHERE Id=?;");
                requete.setInt(1,this.id);
                res = requete.executeQuery();
                res.next();
                this.NbEtu=res.getInt(1);
            } catch (SQLException e) {
                System.err.println("count cassé");
            }
            RemplissageEtu();
            try{
			res.close();
			requete.close();

		}catch(SQLException e4){
            System.err.println("err ferm");
        }
        }else{
            throw new IllegalArgumentException("le groupe"+id+"n'existe pas");
        }
        try {
            res.close();
            requete.close();
        } catch (SQLException e) {
            System.err.println("close cassé");
        }
    }


    /**@param une connexion a une bd
     * init les var :
     *          name,min,max,type,sousgroupes(cf table dependence)
     * 
     */
    private boolean IsIdExist(){
        try{
			requete = this.conn.prepareStatement(
				"SELECT id FROM InfoGroupe WHERE id=?;");
                requete.setInt(1,this.id);
                res = requete.executeQuery();
		}catch(SQLException e2){
        	System.err.println("requete select pas bon");
        }
        boolean LeReturn;
        try {        
            res.next();
            if(res.getInt(1)!=0){
                LeReturn= true;
            } else{
                LeReturn= false ;
            }
        } catch (SQLException e) {
            System.err.println("idisexist class groupea un pb");
            LeReturn= false;
        }
        try {
            res.close();
            requete.close();
        }catch(SQLException e){
            System.err.println("close cassé");
        }
        return LeReturn;

        

    }

    /** init name,min,max,type,sousgroupes  */
    private void InitVar(){
        try{
			requete = this.conn.prepareStatement(
				"SELECT * FROM InfoGroupe Where Id=? ;");
                requete.setInt(1,this.id);
		}catch(SQLException e){
        	System.err.println("select1 pas bon");
        }
        
        try{
			res = requete.executeQuery();
			res.next(); //place sur la ligne 1
			this.name = res.getString(2);
            this.min=res.getInt(3);
            this.max=res.getInt(4);
            String type=res.getString(5);
            if (type=="ROOT"){
                this.type=TypeGroupe.ROOT;
            }else if (type=="FREE"){
                this.type=TypeGroupe.FREE;
            }else if (type=="PARTITION"){
                this.type=TypeGroupe.PARTITION;
            }
            
		}catch(SQLException e){
            System.err.println("requete initvar execute pas bon");
        }
 
        //select/construit les fils

        try{
            requete = this.conn.prepareStatement(
				"SELECT ? FROM Dependances Where ?=? ;");
                requete.setString(1,"IDFils");
                requete.setString(2,"IDPere");
                requete.setInt(3,this.id);
                res = requete.executeQuery();
		}catch(SQLException e){
        	System.err.println("select2 pas bon");
        }
        while (true){
            try{
                
			    res.next();
                System.out.println(res.getInt(1));
			    this.sousGroupes.add(new GroupeP(res.getInt(1),conn)); 
		    }catch(SQLException e3){
                System.err.println("maman divorce");
                break;
            }
        }

        // set le papa
        try{
            requete.setString(2,"IDFils");
            requete.setString(1,"IDPere");
            res = requete.executeQuery();
		}catch(SQLException e){
        	System.err.println("select2 pas bon");
        }
        try{
            res.next();
            System.out.println(requete);
            this.pointPoint=new GroupeP(res.getInt(1),conn); 
        }catch(SQLException e3){
            System.err.println("papa est parti chercher du lait");
        }

        try{
			res.close();
			requete.close();

		}catch(SQLException e4){
            System.err.println("err ferm");
        }
    }


    private void RemplissageEtu(){
        try{
			requete = this.conn.prepareStatement(
				"SELECT IdEtu FROM EtuDansGroupe Where IdGroupe = ? ;");
                requete.setInt(1,this.id);
                res = requete.executeQuery();
		}catch(SQLException e){
        	System.err.println("select RemplissageEtu pas bon");
        }
        while (true){
        try{
            if(res.next()){
            this.membresDuGroupe.add(new EtudiantP(res.getInt(1),conn));
            }else{
                break;
            } 
        }catch(SQLException e3){
           System.out.println("truc");  
        }

        }
        try{
			res.close();
			requete.close();

		}catch(SQLException e4){
            System.err.println("err ferm");
        }

    }
    


    /** cherche id libre */
    private void ID(){
        try{
			requete = this.conn.prepareStatement(
				"SELECT MAX(id) FROM InfoGroupe ;");
		}catch(SQLException e2){
        	System.err.println("requete select id pas bon");
        }

        try{
			res = requete.executeQuery();
			res.next();
			this.id = res.getInt(1)+1;
		}catch(SQLException e3){
            System.err.println("requete execute 1 pas bon");
        }
        try{
			res.close();
			requete.close();

		}catch(SQLException e4){
            System.err.println("err ferm");
        }
    }
    
    /**
     * Ajoute un étudiant. Se comporte comme add de l'interface Set.
     *
     * @return true if e est ajouté
     */
    public boolean addEtudiant(Etudiant e){
        try{
			requete = conn.prepareStatement(
				"INSERT INTO EtuDansGroupe VALUES (?,?);");
			requete.setInt(1,this.id);
            requete.setInt(2,e.getId());
		}catch(SQLException e2){
        	System.err.println("drop pas bon");
            return false;
        }


		try{
			res = requete.executeQuery();

		}catch(SQLException e3){
        	System.err.println("requete pas bon2");
            return false;
        }
		
		//fermeture 
		try{
			res.close();
			requete.close();

		}catch(SQLException e4){
            System.err.println("err ferm");
            return true;
        }
        return true;
    }

    /**
     * Enlève un étudiant. Se comporte comme remove de l'interface Set.
     *
     * @return true iff e est enlevé
     */
    public boolean removeEtudiant(Etudiant e){
        try{
			requete = conn.prepareStatement(
				"DELETE FROM EtuDansGroupe WHERE id=?;");
			requete.setString(1,e.getNom());
		}catch(SQLException e2){
        	System.err.println("drop pas bon");
            return false;
        }


		try{
			res = requete.executeQuery();

		}catch(SQLException e3){
        	System.err.println("requete pas bon2");
            return false;
        }
		
		//fermeture 
		try{
			res.close();
			requete.close();

		}catch(SQLException e4){
            System.err.println("err ferm");
            return true;
        }
        return true;
        //delete etu
    }

    /**
     * Ajoute un sous-groupe. Se comporte comme add de l'interface Set.
     * vérifie que le groupe passé en argument a bien renseigné this comme son père.
     *
     * @return true iff g est ajouté
     * 
     */
     public boolean addSousGroupe(Groupe g){
        Objects.requireNonNull(g,"On ne peut pas ajouter un sous-groupe qui est null");
        if (this.equals(g.getPointPoint()))
            return this.sousGroupes.add(g);
        else throw new IllegalArgumentException("on ne peut pas ajouter un sous-groupe ont le père n'est pas this");
    }

    /**
     * Enlève un groupe. Se comporte comme remove de l'interface Set.
     *
     * @return true iff e est enlevé
     */
    public boolean removeSousGroupe(Groupe g){
        Objects.requireNonNull(g,"On ne peut pas enlever un Étudiant qui est null");

        return this.sousGroupes.remove(g);
    }

    public int getId(){
        return this.id;
    }

    /**
     * permet de récupérer le nom d'un groupe (utile irl).
     * @return le nom.
     */
    public String getName(){
        return this.name;
    }

    /**
     * permet de récupérer le nombre minimum d'étudiants souhaités dans le groupe.
     * @return le minimum souhaité
     */
    public int getMin(){
        return this.min;
    }

    /**
     * permet de récupérer le nombre maximum d'étudiants souhaités dans un groupe.
     * @return le maximum souhaité
     */
    public int getMax(){
        return this.max;
    }

    /**
     * permet de récupérer le nombre d'étudiants dans ce groupe.
     * @return le nombre de places prises (pas forcément limité entre Min et Max, mais c'est le but)
     */
    public int getSize(){
        return this.NbEtu;
    }
    
    /**
     * permet de récupérer la nature du groupe
     * @return le type du groupe
     */
    public TypeGroupe getType(){
        return this.type;
    }

    /**
     * permet de récupérer le groupe père
     * un groupe racine devrait retourner lui-même
     *
     * @return le père
     */
    public Groupe getPointPoint(){
        return this.pointPoint;
    }

    /**
     * Potentiellement "vide"
     * Attention nous renvoyons l'ensemble sans le copier
     *
     * @return l'ensemble des sous-groupes.
     */
    public Set<Groupe> getSousGroupes(){
        return this.sousGroupes;
    }

    /**
     * Potentiellement "vide"
     * Attention nous renvoyons l'ensemble sans le copier
     *
     * @return l'ensemble des étudiants.
     */
    public Set<Etudiant> getEtudiants(){
        return this.membresDuGroupe;
    }

    /**
     * Ajoutée
     * Permet de changer le nom du groupe
     *
     * @return void
     */
    public void SetName(String newName){
        this.name = newName;
    }
}