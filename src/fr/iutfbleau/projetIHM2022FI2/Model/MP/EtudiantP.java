package fr.iutfbleau.projetIHM2022FI2.Model.MP;
import fr.iutfbleau.projetIHM2022FI2.Model.API.*;
import java.util.*;
import java.sql.*;
/**
 * Un étudiant
 */

public class EtudiantP implements Etudiant{

    static PreparedStatement requete;
	static ResultSet res;
    private int id;
    private String nom, prenom;

    /**
     * Constructeur.
     * cree un nouvel etu
     */
    public EtudiantP(String nom, String prenom, Connection conn){
        Objects.requireNonNull(nom,"On ne peut pas créer un étudiant avec un nom null");
        Objects.requireNonNull(prenom,"On ne peut pas créer un étudiant avec un nom null");
        Objects.requireNonNull(conn,"Il faut une connexion a la base de donnée");
        //recherche de l'id
        try{
			requete = conn.prepareStatement(
				"SELECT MAX(id) FROM Etu ;");
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

        //insert des val
        try{
			requete = conn.prepareStatement(
				"INSERT INTO Etu VALUES(?,?.?);");
            requete.setString(2,nom);
            requete.setString(3,prenom);
		}catch(SQLException e2){
        	System.err.println("requete insert pas bon");
        }
        
        try{
			res = requete.executeQuery();;
		}catch(SQLException e3){
        	System.err.println("requete 2 pas bon");
        }
		
		//fermeture 
		try{
			res.close();
			requete.close();
		}catch(SQLException e4){
            System.err.println("err ferm");
        }
        this.nom=nom;
        this.prenom=prenom;
    }
    /** constructeur select un etu deja existant dans la bd
     * @param id id de l' etudiant
     * @param conn connexion a la base de données
     * @throws IllegalArgumentException si l'id n'existe pas dans la base de données
     */
    public EtudiantP(int id, Connection conn){
        Objects.requireNonNull(id,"Il faut l'id de l'etudiant pour le trouver dans la bd");
        Objects.requireNonNull(conn,"Il faut une connexion a la base de donnée");

        try{
			requete = conn.prepareStatement(
				"SELECT * FROM Etu WHERE id=?;");
			requete.setInt(1,id);
		}catch(SQLException e2){
        	System.err.println("select pas bon");
        }


		try{
			res = requete.executeQuery();
			res.next();
            if (res.getInt(1)==0){  //si la requete renvoie rien alors l'id ext mauvais
                throw new IllegalArgumentException("eleve"+id +"n'existe pas");
            }else{
                this.id = id;
                this.nom=res.getString(2);
                this.prenom=res.getString(3);
            }
		}catch(SQLException e3){
        	System.err.println("requete pas bon2");
        }
		
		//fermeture 
		try{
			res.close();
			requete.close();
		}catch(SQLException e4){
            System.err.println("err ferm");
        }
        /* select * from etu where ?=id */
    
    }
    
    /**
     * permet de récupérer l'identifiant de l'étudiant.
     * @return l'identifiant.
     */
    public int getId(){
        return this.id;
    }

    /**
     * permet de récupérer 
     * @return le nom de l'étudiant.
     */
    public String getNom(){
        return this.nom;
    }

    /**
     * permet de récupérer
     * @return le prénom de l'étudiant
     */
    public String getPrenom(){
        return this.prenom;
    }


}