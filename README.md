# Gestion de groupes d'étudiants / Student Group Management

## 🇫🇷 Version française

### Description

Ce projet est une **application Java avec interface graphique (IHM)** destinée à la **gestion de groupes d’étudiants**.  
L’application distingue deux types d’utilisateurs :  

- **Administration** : accès complet pour **ajouter, modifier, supprimer et consulter** des groupes et des étudiants (CRUD).  
- **Étudiants** : accès en **lecture seule** pour consulter les informations de leur groupe.

Le projet a été développé dans le cadre du **BUT Informatique** afin de pratiquer la **programmation orientée objet**, le **développement d’interfaces graphiques en Java (Swing / JavaFX)** et la **gestion d’une application multi-utilisateurs avec rôles**.

### Fonctionnalités

- Interface graphique conviviale pour la gestion des groupes et étudiants  
- Fonctionnalités CRUD pour les administrateurs  
- Accès en lecture seule pour les étudiants  
- Authentification simplifiée pour différencier les rôles  
- Visualisation et recherche des étudiants par groupe  

### Technologies

- **Java (JDK ≥ 8)** – logique métier et IHM  
- **Swing / JavaFX** – interface graphique  
- **Makefile** – compilation et lancement automatisés  

### Installation & Compilation

#### Pré-requis
Assurez-vous d’avoir installé le **JDK (Java Development Kit) version 8 ou supérieure**.  
Vérifiez avec :
```bash
java -version
```

#### 1. Compilation
Dans un terminal, à la racine du projet :   
```bash
make
```

#### 2. Lancement de l'application
```bash
make run
```

#### 3. Nettoyage des fichiers compilés
```bash
make clean
```

### Contrôles

- **Administrateur** : accès aux boutons "Ajouter", "Modifier" et "Supprimer"
- **Etudiant** : consultation seule des groupes et des informations associées

### Auteurs

Projet réalisé par
- **Kayyissa Haïssous**
- **Adrien Dos Santos**
- **Claire Gobert**

Dans le cadre du BUT Informatique - 2ème année (2022-2023)  

---

## 🇬🇧 English version

### Description

This project is a **Java application with a graphical user interface (GUI)** for **managing student groups**.  
The application supports two types of users:  

- **Administration**: full access to **create, read, update, delete (CRUD)** groups and students.  
- **Students**: **read-only access** to view their group information.  

The project was developed as part of the **Computer Science BUT** to practise **object-oriented programming**, **Java GUI development (Swing / JavaFX)**, and **multi-user application management with role-based access**.  

### Features

- User-friendly GUI for managing groups and students  
- CRUD operations for administrators  
- Read-only access for students  
- Simple authentication to differentiate roles  
- Viewing and searching students by group

### Technologies

- **Java (JDK ≥ 8)** – business logic and GUI
- **Swing / JavaFX** – graphical interface
- **Makefile** – automated compilation and execution

### Installation & Compilation

#### Requirements
Ensure that the **Java Development Kit (JDK) verison 8 or higher** is installed.  
Check with:
```bash
java -version
```

#### 1. Compilation
From the project root directory, run:   
```bash
make
```

#### 2. Run the application
```bash
make run
```

#### 3. Clean compiled files
```bash
make clean
```

### Controls

- **Administrator**: acces to "Add", "Edit" and "Delete"
- **Student**: view-only access to groups and associated information

### Authors

Project developed by  
- **Kayyissa Haïssous**
- **Adrien Dos Santos**
- **Claire Gobert**

As part of the Computer Science BUT - 2nd year (2022-2023)
