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
# Table: formation
#------------------------------------------------------------

CREATE TABLE formation(
        id           Int  Auto_increment  NOT NULL ,
        WEEK_OF_YEAR Int ,
        YEAR         Int NOT NULL ,
        recusive     Bool ,
        MONDAY_am    Bool ,
        MONDAY_pm    Bool ,
        TUESDAY_am   Bool ,
        TUESDAY_pm   Bool ,
        WEDNESDAY_am Bool ,
        WEDNESDAY_pm Bool ,
        THURSDAY_am  Bool ,
        THURSDAY_pm  Bool ,
        FRIDAY_am    Bool ,
        FRIDAY_pm    Bool ,
        id_salle     Int ,
        id_Classe    Int NOT NULL
	,CONSTRAINT formation_PK PRIMARY KEY (id)

	,CONSTRAINT formation_salle_FK FOREIGN KEY (id_salle) REFERENCES salle(id)
	,CONSTRAINT formation_Classe0_FK FOREIGN KEY (id_Classe) REFERENCES Classe(id)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: ROLE
#------------------------------------------------------------

CREATE TABLE ROLE(
        id   Int NOT NULL ,
        ROLE Varchar (45) NOT NULL
	,CONSTRAINT ROLE_PK PRIMARY KEY (id,ROLE)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: user
#------------------------------------------------------------

CREATE TABLE user(
        id       Int  Auto_increment  NOT NULL ,
        login    Varchar (45) NOT NULL ,
        password Varchar (120) NOT NULL ,
        id_ROLE  Int NOT NULL ,
        ROLE     Varchar (45) NOT NULL
	,CONSTRAINT user_PK PRIMARY KEY (id)

	,CONSTRAINT user_ROLE_FK FOREIGN KEY (id_ROLE,ROLE) REFERENCES ROLE(id,ROLE)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: profil
#------------------------------------------------------------

CREATE TABLE profil(
        id        Int  Auto_increment  NOT NULL ,
        lastname  Varchar (45) NOT NULL ,
        firstname Varchar (45) NOT NULL ,
        id_user   Int NOT NULL
	,CONSTRAINT profil_PK PRIMARY KEY (id)

	,CONSTRAINT profil_user_FK FOREIGN KEY (id_user) REFERENCES user(id)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: list_issue
#------------------------------------------------------------

CREATE TABLE list_issue(
        id        Int  Auto_increment  NOT NULL ,
        nom       Varchar (250) NOT NULL ,
        status    Enum ("initial","en cours","terminé") NOT NULL ,
        id_profil Int NOT NULL
	,CONSTRAINT list_issue_PK PRIMARY KEY (id)

	,CONSTRAINT list_issue_profil_FK FOREIGN KEY (id_profil) REFERENCES profil(id)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: teach
#------------------------------------------------------------

CREATE TABLE teach(
        id           Int NOT NULL ,
        id_formation Int NOT NULL
	,CONSTRAINT teach_PK PRIMARY KEY (id,id_formation)

	,CONSTRAINT teach_profil_FK FOREIGN KEY (id) REFERENCES profil(id)
	,CONSTRAINT teach_formation0_FK FOREIGN KEY (id_formation) REFERENCES formation(id)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: learn
#------------------------------------------------------------

CREATE TABLE learn(
        id        Int NOT NULL ,
        id_Classe Int NOT NULL
	,CONSTRAINT learn_PK PRIMARY KEY (id,id_Classe)

	,CONSTRAINT learn_profil_FK FOREIGN KEY (id) REFERENCES profil(id)
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

