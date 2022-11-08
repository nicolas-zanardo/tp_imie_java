#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------


#------------------------------------------------------------
# Table: classe
#------------------------------------------------------------

CREATE TABLE classe(
        id               Int  Auto_increment  NOT NULL ,
        nom              Varchar (45) NOT NULL ,
        nombre_personnes Int NOT NULL
	,CONSTRAINT classe_PK PRIMARY KEY (id)
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
# Table: role
#------------------------------------------------------------

CREATE TABLE role(
        id_role   Int  Auto_increment  NOT NULL ,
        role_name Varchar (50) NOT NULL
	,CONSTRAINT role_PK PRIMARY KEY (id_role)
)ENGINE=InnoDB COMMENT "INSERT INTO role_user (role_name) VALUES ('éléves'), ('formateur'), ('responsable maintenance'), ('responsable global'), ('responsables de la gestion');" ;


#------------------------------------------------------------
# Table: user
#------------------------------------------------------------

CREATE TABLE user(
        id_user   Int  Auto_increment  NOT NULL ,
        lastname  Varchar (250) NOT NULL ,
        firstname Varchar (250) NOT NULL ,
        login     Varchar (250) NOT NULL ,
        password  Varchar (250) NOT NULL ,
        id_role   Int NOT NULL
	,CONSTRAINT user_PK PRIMARY KEY (id_user)

	,CONSTRAINT user_role_FK FOREIGN KEY (id_role) REFERENCES role(id_role)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: formation
#------------------------------------------------------------

CREATE TABLE formation(
        id                 Int  Auto_increment  NOT NULL ,
        nom_formation      Varchar (250) NOT NULL ,
        date_debut         Date ,
        date_fin           Date ,
        recursive_calandar Bool ,
        id_salle           Int NOT NULL ,
        id_classe          Int NOT NULL ,
        id_user            Int NOT NULL
	,CONSTRAINT formation_PK PRIMARY KEY (id)

	,CONSTRAINT formation_salle_FK FOREIGN KEY (id_salle) REFERENCES salle(id)
	,CONSTRAINT formation_classe0_FK FOREIGN KEY (id_classe) REFERENCES classe(id)
	,CONSTRAINT formation_user1_FK FOREIGN KEY (id_user) REFERENCES user(id_user)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: list_issue
#------------------------------------------------------------

CREATE TABLE list_issue(
        id      Int  Auto_increment  NOT NULL ,
        nom     Varchar (250) NOT NULL ,
<<<<<<< HEAD
        status  Varchar (250) NOT NULL ,
=======
        status  Varchar (50) NOT NULL ,
>>>>>>> feature_role
        id_user Int NOT NULL
	,CONSTRAINT list_issue_PK PRIMARY KEY (id)

	,CONSTRAINT list_issue_user_FK FOREIGN KEY (id_user) REFERENCES user(id_user)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: week
#------------------------------------------------------------

CREATE TABLE week(
        id           Int  Auto_increment  NOT NULL ,
        YEAR         Int ,
        WEEK_OF_YEAR Int ,
        MONDAY_AM    Bool NOT NULL ,
        MONDAY_PM    Bool NOT NULL ,
        TUESDAY_AM   Bool NOT NULL ,
        TUESDAY_PM   Bool NOT NULL ,
        WEDNESDAY_AM Bool NOT NULL ,
        WEDNESDAY_PM Bool NOT NULL ,
        THURSDAY_AM  Bool NOT NULL ,
        THURSDAY_PM  Bool NOT NULL ,
        FRIDAY_AM    Bool NOT NULL ,
        FRIDAY_PM    Bool NOT NULL ,
        id_formation Int NOT NULL
	,CONSTRAINT week_PK PRIMARY KEY (id)

	,CONSTRAINT week_formation_FK FOREIGN KEY (id_formation) REFERENCES formation(id)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: learn
#------------------------------------------------------------

CREATE TABLE learn(
        id_user Int NOT NULL ,
        id      Int NOT NULL
	,CONSTRAINT learn_PK PRIMARY KEY (id_user,id)

	,CONSTRAINT learn_user_FK FOREIGN KEY (id_user) REFERENCES user(id_user)
	,CONSTRAINT learn_classe0_FK FOREIGN KEY (id) REFERENCES classe(id)
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

