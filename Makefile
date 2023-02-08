# COMMANDES #
JAVAC = javac
# note $$ to get a single shell $
JAVAC_OPTIONS = -d build -cp build -sourcepath "src:tmp" -implicit:none
JAVA = java
JAR = jar
EXEC_JAR = ${JAVA} -cp /export/documents/mariadb-client.jar -jar 

# CHEMINS RELATIFS
SRC = src/fr/iutfbleau/projetIHM2022FI2
MODEL = src/fr/iutfbleau/projetIHM2022FI2/Model
VIEW = src/fr/iutfbleau/projetIHM2022FI2/View
CONTROLLER = src/fr/iutfbleau/projetIHM2022FI2/Controller
BUILD = build/fr/iutfbleau/projetIHM2022FI2
MODELB = build/fr/iutfbleau/projetIHM2022FI2/Model
VIEWB = build/fr/iutfbleau/projetIHM2022FI2/View
CONTROLLERB = build/fr/iutfbleau/projetIHM2022FI2/Controller
DOC = doc/fr/iutfbleau/projetIHM2022FI2

# CHOIX NOMS
JAR_MNP = GestionGroupes.jar

# BUTS FACTICES #
.PHONY : run clean doc

# BUT PAR DEFAUT #
run : ${JAR_MNP}
	${EXEC_JAR} ${JAR_MNP}

# AUTRE BUTS
doc :
	javadoc -d doc src/fr/iutfbleau/projetIHM2022FI2/Model/API/*.java src/fr/iutfbleau/projetIHM2022FI2/Model/MNP/*.java \
	src/fr/iutfbleau/projetIHM2022FI2/Controller/*.java src/fr/iutfbleau/projetIHM2022FI2/View/*.java

clean :
	rm -rf ${BUILD}/* *.jar


# REGLES DE DEPENDANCE #

## API ##
${MODELB}/API/MonPrint.class : ${MODEL}/API/MonPrint.java 
	${JAVAC} ${JAVAC_OPTIONS} ${MODEL}/API/MonPrint.java

${MODELB}/API/TypeGroupe.class : ${MODEL}/API/TypeGroupe.java 
	${JAVAC} ${JAVAC_OPTIONS} ${MODEL}/API/TypeGroupe.java

${MODELB}/API/Groupe.class : ${MODEL}/API/Groupe.java \
	  		     ${MODELB}/API/TypeGroupe.class\
				 ${MODELB}/API/Etudiant.class\
			     ${MODELB}/API/MonPrint.class
	${JAVAC} ${JAVAC_OPTIONS} ${MODEL}/API/Groupe.java

${MODELB}/API/Etudiant.class : ${MODEL}/API/Etudiant.java \
                            ${MODELB}/API/MonPrint.class
	${JAVAC} ${JAVAC_OPTIONS} ${MODEL}/API/Etudiant.java

${MODELB}/API/Changement.class : ${MODEL}/API/Changement.java \
	  		            ${MODELB}/API/Etudiant.class \
	  		     	    ${MODELB}/API/Groupe.class 
	${JAVAC} ${JAVAC_OPTIONS} ${MODEL}/API/Changement.java

${MODELB}/API/AbstractGroupeFactory.class : ${MODEL}/API/AbstractGroupeFactory.java \
				${MODELB}/API/Groupe.class \
	  		         ${MODELB}/API/Etudiant.class 
	${JAVAC} ${JAVAC_OPTIONS} ${MODEL}/API/AbstractGroupeFactory.java

${MODELB}/API/AbstractChangementFactory.class : ${MODEL}/API/AbstractChangementFactory.java \
	  		            ${MODELB}/API/AbstractGroupeFactory.class \
				    ${MODELB}/API/Changement.class
	${JAVAC} ${JAVAC_OPTIONS} ${MODEL}/API/AbstractChangementFactory.java

## MNP ##

${MODELB}/MNP/EtudiantNP.class : ${MODEL}/MNP/EtudiantNP.java \
                              ${MODELB}/API/Etudiant.class 
	${JAVAC} ${JAVAC_OPTIONS} ${MODEL}/MNP/EtudiantNP.java


${MODELB}/MNP/GroupeNP.class : ${MODEL}/MNP/GroupeNP.java \
                              ${MODELB}/API/Groupe.class \
			      ${MODELB}/API/TypeGroupe.class \
                              ${MODELB}/API/Etudiant.class 
	${JAVAC} ${JAVAC_OPTIONS} ${MODEL}/MNP/GroupeNP.java


${MODELB}/MNP/ChangementNP.class : ${MODELB}/API/Changement.class \
				${MODEL}/MNP/ChangementNP.java \
                              ${MODELB}/API/Groupe.class \
                              ${MODELB}/API/Etudiant.class 
	${JAVAC} ${JAVAC_OPTIONS} ${MODEL}/MNP/ChangementNP.java

${MODELB}/MNP/AbstractGroupeFactoryNP.class : ${MODEL}/MNP/AbstractGroupeFactoryNP.java \
				${MODELB}/API/AbstractGroupeFactory.class \
				${MODELB}/MNP/GroupeNP.class
	${JAVAC} ${JAVAC_OPTIONS} ${MODEL}/MNP/AbstractGroupeFactoryNP.java

${MODELB}/MNP/AbstractChangementFactoryNP.class : ${MODEL}/MNP/AbstractChangementFactoryNP.java \
				${MODELB}/API/AbstractChangementFactory.class \
				${MODELB}/MNP/ChangementNP.class 
	${JAVAC} ${JAVAC_OPTIONS} ${MODEL}/MNP/AbstractChangementFactoryNP.java


## CONTROLLER ##

${CONTROLLERB}/EmulEtu.class : ${CONTROLLER}/EmulEtu.java \
				 ${MODELB}/MNP/EtudiantNP.class \
				 ${MODELB}/API/AbstractGroupeFactory.class \
				 ${MODELB}/API/Groupe.class
	${JAVAC} ${JAVAC_OPTIONS} ${CONTROLLER}/EmulEtu.java

${CONTROLLERB}/Demande.class : ${CONTROLLER}/Demande.java \
				 ${MODELB}/API/AbstractChangementFactory.class \
				 ${MODELB}/API/Groupe.class \
				 ${MODELB}/API/TypeGroupe.class \
				 ${MODELB}/API/Etudiant.class 
	${JAVAC} ${JAVAC_OPTIONS} ${CONTROLLER}/Demande.java

${CONTROLLERB}/DemandeChangement.class : ${CONTROLLER}/DemandeChangement.java \
				 ${VIEWB}/GetDemande.class 
	${JAVAC} ${JAVAC_OPTIONS} ${CONTROLLER}/DemandeChangement.java

${CONTROLLERB}/AddEtu.class : ${CONTROLLER}/AddEtu.java \
				 ${MODELB}/API/AbstractChangementFactory.class \
				 ${MODELB}/API/Groupe.class \
				 ${MODELB}/API/TypeGroupe.class \
				 ${MODELB}/API/Etudiant.class 
	${JAVAC} ${JAVAC_OPTIONS} ${CONTROLLER}/AddEtu.java

${CONTROLLERB}/MvEtu.class : ${CONTROLLER}/MvEtu.java \
				 ${MODELB}/API/AbstractChangementFactory.class \
				 ${MODELB}/API/Groupe.class \
				 ${MODELB}/API/TypeGroupe.class \
				 ${MODELB}/API/Etudiant.class 
	${JAVAC} ${JAVAC_OPTIONS} ${CONTROLLER}/MvEtu.java

${CONTROLLERB}/RmEtu.class : ${CONTROLLER}/RmEtu.java \
				 ${MODELB}/API/AbstractChangementFactory.class \
				 ${MODELB}/API/Groupe.class \
				 ${MODELB}/API/TypeGroupe.class \
				 ${MODELB}/API/Etudiant.class 
	${JAVAC} ${JAVAC_OPTIONS} ${CONTROLLER}/RmEtu.java

${CONTROLLERB}/GestionEtu.class : ${CONTROLLER}/GestionEtu.java \
				 ${VIEWB}/NewEtu.class \
				 ${VIEWB}/MoveEtu.class \
				 ${VIEWB}/SupprEtu.class 
	${JAVAC} ${JAVAC_OPTIONS} ${CONTROLLER}/GestionEtu.java

${CONTROLLERB}/ListenerGroupesEtu.class : ${CONTROLLER}/ListenerGroupesEtu.java \
				 ${MODELB}/API/Etudiant.class \
				 ${MODELB}/API/AbstractGroupeFactory.class \
				 ${VIEWB}/GroupeEtu.class
	${JAVAC} ${JAVAC_OPTIONS} ${CONTROLLER}/ListenerGroupesEtu.java

${CONTROLLERB}/SearchEtu.class : ${CONTROLLER}/SearchEtu.java \
				 ${MODELB}/API/Etudiant.class \
				 ${MODELB}/API/AbstractGroupeFactory.class \
				 ${VIEWB}/ChoixEtu.class
	${JAVAC} ${JAVAC_OPTIONS} ${CONTROLLER}/SearchEtu.java

${CONTROLLERB}/AfficheArbre.class : ${CONTROLLER}/AfficheArbre.java \
				 ${MODELB}/API/Groupe.class \
				 ${CONTROLLERB}/GestionEtu.class \
				 ${CONTROLLERB}/DemandeChangement.class 
	${JAVAC} ${JAVAC_OPTIONS} ${CONTROLLER}/AfficheArbre.java

${CONTROLLERB}/ListenerCreateGroup.class : ${CONTROLLER}/ListenerCreateGroup.java \
				 ${CONTROLLERB}/AfficheArbre.class\
	  		     ${MODELB}/API/AbstractGroupeFactory.class\
			     ${MODELB}/API/Groupe.class
	${JAVAC} ${JAVAC_OPTIONS} ${CONTROLLER}/ListenerCreateGroup.java

${CONTROLLERB}/ListenerRenameGroup.class : ${CONTROLLER}/ListenerRenameGroup.java \
				 ${CONTROLLERB}/AfficheArbre.class\
	  		     ${MODELB}/API/AbstractGroupeFactory.class\
			     ${MODELB}/API/Groupe.class
	${JAVAC} ${JAVAC_OPTIONS} ${CONTROLLER}/ListenerRenameGroup.java

${CONTROLLERB}/ListenerDeleteGroup.class : ${CONTROLLER}/ListenerDeleteGroup.java \
				 ${CONTROLLERB}/AfficheArbre.class\
	  		     ${MODELB}/API/AbstractGroupeFactory.class\
			     ${MODELB}/API/Groupe.class
	${JAVAC} ${JAVAC_OPTIONS} ${CONTROLLER}/ListenerDeleteGroup.java

${CONTROLLERB}/ListenerGroupe.class : ${CONTROLLER}/ListenerGroupe.java \
	  		     ${VIEWB}/NewGroup.class\
				 ${VIEWB}/RenameGroupe.class\
			     ${VIEWB}/SupprGroupe.class
	${JAVAC} ${JAVAC_OPTIONS} ${CONTROLLER}/ListenerGroupe.java

${CONTROLLERB}/Lancement.class : ${CONTROLLER}/Lancement.java \
	  		     ${VIEWB}/Admin.class \
				 ${VIEWB}/Enseignant.class \
				 ${VIEWB}/Etudiant.class
	${JAVAC} ${JAVAC_OPTIONS} ${CONTROLLER}/Lancement.java


## VIEW ##

${VIEWB}/Admin.class : ${VIEW}/Admin.java \
			     ${CONTROLLERB}/EmulEtu.class\
				 ${CONTROLLERB}/ListenerGroupe.class\
				 ${MODELB}/MNP/AbstractGroupeFactoryNP.class
	${JAVAC} ${JAVAC_OPTIONS} ${VIEW}/Admin.java

${VIEWB}/Enseignant.class : ${VIEW}/Enseignant.java \
			     ${CONTROLLERB}/ListenerGroupe.class\
				 ${CONTROLLERB}/SearchEtu.class \
				 ${CONTROLLERB}/EmulEtu.class\
				 ${MODELB}/MNP/AbstractGroupeFactoryNP.class
	${JAVAC} ${JAVAC_OPTIONS} ${VIEW}/Enseignant.java

${VIEWB}/Etudiant.class : ${VIEW}/Etudiant.java \
			     ${CONTROLLERB}/ListenerGroupe.class\
				 ${CONTROLLERB}/EmulEtu.class\
				 ${MODELB}/MNP/AbstractGroupeFactoryNP.class
	${JAVAC} ${JAVAC_OPTIONS} ${VIEW}/Etudiant.java

${VIEWB}/NewGroup.class : ${VIEW}/NewGroup.java \
			     ${CONTROLLERB}/ListenerCreateGroup.class
	${JAVAC} ${JAVAC_OPTIONS} ${VIEW}/NewGroup.java

${VIEWB}/RenameGroupe.class : ${VIEW}/RenameGroupe.java \
			     ${CONTROLLERB}/ListenerRenameGroup.class
	${JAVAC} ${JAVAC_OPTIONS} ${VIEW}/RenameGroupe.java

${VIEWB}/SupprGroupe.class : ${VIEW}/SupprGroupe.java \
			     ${CONTROLLERB}/ListenerDeleteGroup.class
	${JAVAC} ${JAVAC_OPTIONS} ${VIEW}/SupprGroupe.java

${VIEWB}/ChoixEtu.class : ${VIEW}/ChoixEtu.java \
				 ${CONTROLLERB}/ListenerGroupesEtu.class
	${JAVAC} ${JAVAC_OPTIONS} ${VIEW}/ChoixEtu.java

${VIEWB}/GroupeEtu.class : ${VIEW}/GroupeEtu.java \
				 ${MODELB}/API/AbstractGroupeFactory.class \
				 ${MODELB}/API/Etudiant.class
	${JAVAC} ${JAVAC_OPTIONS} ${VIEW}/GroupeEtu.java

${VIEWB}/NewEtu.class : ${VIEW}/NewEtu.java \
				 ${MODELB}/API/AbstractGroupeFactory.class \
				 ${MODELB}/API/Groupe.class \
				 ${CONTROLLERB}/AddEtu.class
	${JAVAC} ${JAVAC_OPTIONS} ${VIEW}/NewEtu.java

${VIEWB}/MoveEtu.class : ${VIEW}/MoveEtu.java \
				 ${MODELB}/API/AbstractGroupeFactory.class \
				 ${MODELB}/API/Groupe.class \
				 ${CONTROLLERB}/MvEtu.class
	${JAVAC} ${JAVAC_OPTIONS} ${VIEW}/MoveEtu.java

${VIEWB}/SupprEtu.class : ${VIEW}/SupprEtu.java \
				 ${MODELB}/API/AbstractGroupeFactory.class \
				 ${MODELB}/API/Groupe.class \
				 ${CONTROLLERB}/RmEtu.class
	${JAVAC} ${JAVAC_OPTIONS} ${VIEW}/SupprEtu.java

${VIEWB}/GetDemande.class : ${VIEW}/GetDemande.java \
				 ${CONTROLLERB}/Demande.class
	${JAVAC} ${JAVAC_OPTIONS} ${VIEW}/GetDemande.java

# ## JARS ##

 ${JAR_MNP} : ${CONTROLLERB}/Lancement.class
	${JAR} cvfe ${JAR_MNP} fr.iutfbleau.projetIHM2022FI2.Controller.Lancement -C build fr

