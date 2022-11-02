#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------


#------------------------------------------------------------
# Table: Classe
#------------------------------------------------------------

CREATE TABLE Classe(
        id               Int  Auto_increment  NOT NULL ,
        nom              Varchar (45) NOT NULL ,
        nombre_personnes Int NOT NULL
	,CONSTRAINT Classe_PK PRIMARY KEY (id)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: type_salle
#------------------------------------------------------------

CREATE TABLE type_salle(
        id  Int  Auto_increment  NOT NULL ,
        nom Varchar (45) NOT NULL
	,CONSTRAINT type_salle_PK PRIMARY KEY (id)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: salle
#------------------------------------------------------------

CREATE TABLE salle(
        id            Int  Auto_increment  NOT NULL ,
        nom           Varchar (45) NOT NULL ,
        nombre_places Int NOT NULL ,
        id_type_salle Int NOT NULL
	,CONSTRAINT salle_PK PRIMARY KEY (id)

	,CONSTRAINT salle_type_salle_FK FOREIGN KEY (id_type_salle) REFERENCES type_salle(id)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: user
#------------------------------------------------------------

CREATE TABLE user(
        id        Int  Auto_increment  NOT NULL ,
        lastname  Varchar (45) NOT NULL ,
        firstname Varchar (45) NOT NULL ,
        ROLE      Enum ("etudiant","formateur","responsables pédagogiques","responsable global ","responsable de maintenance ","responsables de la gestion") NOT NULL
	,CONSTRAINT user_PK PRIMARY KEY (id)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: formation
#------------------------------------------------------------

CREATE TABLE formation(
        id            Int  Auto_increment  NOT NULL ,
        nom_formation Varchar (250) NOT NULL ,
        date_debut    Date ,
        date_fin      Date ,
        recursive     Bool ,
        id_salle      Int NOT NULL ,
        id_Classe     Int NOT NULL ,
        id_user       Int NOT NULL
	,CONSTRAINT formation_PK PRIMARY KEY (id)

	,CONSTRAINT formation_salle_FK FOREIGN KEY (id_salle) REFERENCES salle(id)
	,CONSTRAINT formation_Classe0_FK FOREIGN KEY (id_Classe) REFERENCES Classe(id)
	,CONSTRAINT formation_user1_FK FOREIGN KEY (id_user) REFERENCES user(id)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: list_issue
#------------------------------------------------------------

CREATE TABLE list_issue(
        id      Int  Auto_increment  NOT NULL ,
        nom     Varchar (250) NOT NULL ,
        status  Enum ("initial","en cours","terminé") NOT NULL ,
        id_user Int NOT NULL
	,CONSTRAINT list_issue_PK PRIMARY KEY (id)

	,CONSTRAINT list_issue_user_FK FOREIGN KEY (id_user) REFERENCES user(id)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: week
#------------------------------------------------------------

CREATE TABLE week(
        id           Int  Auto_increment  NOT NULL ,
        YEAR         Int ,
        WEEK_OF_YEAR Int ,
        recursive    Bool ,
        MONDAY_AM    Bool ,
        MONDAY_PM    Bool ,
        TUESDAY_AM   Bool ,
        TUESDAY_PM   Bool ,
        WEDNESDAY_AM Bool ,
        WEDNESDAY_PM Bool ,
        THURSDAY_AM  Bool ,
        THURSDAY_PM  Bool ,
        FRIDAY_AM    Bool ,
        FRIDAY_PM    Bool ,
        id_formation Int NOT NULL
	,CONSTRAINT week_PK PRIMARY KEY (id)

	,CONSTRAINT week_formation_FK FOREIGN KEY (id_formation) REFERENCES formation(id)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: learn
#------------------------------------------------------------

CREATE TABLE learn(
        id        Int NOT NULL ,
        id_Classe Int NOT NULL
	,CONSTRAINT learn_PK PRIMARY KEY (id,id_Classe)

	,CONSTRAINT learn_user_FK FOREIGN KEY (id) REFERENCES user(id)
	,CONSTRAINT learn_Classe0_FK FOREIGN KEY (id_Classe) REFERENCES Classe(id)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: suffer
#------------------------------------------------------------

CREATE TABLE suffer(
        id            Int NOT NULL ,
        id_list_issue Int NOT NULL
	,CONSTRAINT suffer_PK PRIMARY KEY (id,id_list_issue)

	,CONSTRAINT suffer_salle_FK FOREIGN KEY (id) REFERENCES salle(id)
	,CONSTRAINT suffer_list_issue0_FK FOREIGN KEY (id_list_issue) REFERENCES list_issue(id)
)ENGINE=InnoDB;

