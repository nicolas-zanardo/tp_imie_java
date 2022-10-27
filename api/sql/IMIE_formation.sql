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
# Table: cours
#------------------------------------------------------------

CREATE TABLE cours(
        id           Int  Auto_increment  NOT NULL ,
        WEEK_OF_YEAR Int NOT NULL ,
        recusive     Bool NOT NULL ,
        MONDAY_am    Bool NOT NULL ,
        MONDAY_pm    Bool NOT NULL ,
        TUESDAY_am   Bool NOT NULL ,
        TUESDAY_pm   Bool NOT NULL ,
        WEDNESDAY_am Bool NOT NULL ,
        WEDNESDAY_pm Bool NOT NULL ,
        THURSDAY_am  Bool NOT NULL ,
        THURSDAY_pm  Bool NOT NULL ,
        FRIDAY_am    Bool NOT NULL ,
        FRIDAY_pm    Bool NOT NULL ,
        id_salle     Int ,
        id_Classe    Int NOT NULL
	,CONSTRAINT cours_PK PRIMARY KEY (id)

	,CONSTRAINT cours_salle_FK FOREIGN KEY (id_salle) REFERENCES salle(id)
	,CONSTRAINT cours_Classe0_FK FOREIGN KEY (id_Classe) REFERENCES Classe(id)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: credential
#------------------------------------------------------------

CREATE TABLE credential(
        id       Int  Auto_increment  NOT NULL ,
        login    Varchar (45) NOT NULL ,
        password Varchar (120) NOT NULL ,
        ROLE     Varchar (250) NOT NULL
	,CONSTRAINT credential_PK PRIMARY KEY (id)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: type_profil
#------------------------------------------------------------

CREATE TABLE type_profil(
        id   Int NOT NULL ,
        type Varchar (45) NOT NULL
	,CONSTRAINT type_profil_PK PRIMARY KEY (id,type)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: user
#------------------------------------------------------------

CREATE TABLE user(
        id               Int  Auto_increment  NOT NULL ,
        lastname         Varchar (45) NOT NULL ,
        firstname        Varchar (45) NOT NULL ,
        id_credential    Int NOT NULL ,
        id_type_profil   Int NOT NULL ,
        type_type_profil Varchar (45) NOT NULL
	,CONSTRAINT user_PK PRIMARY KEY (id)

	,CONSTRAINT user_credential_FK FOREIGN KEY (id_credential) REFERENCES credential(id)
	,CONSTRAINT user_type_profil0_FK FOREIGN KEY (id_type_profil,type_type_profil) REFERENCES type_profil(id,type)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: teach
#------------------------------------------------------------

CREATE TABLE teach(
        id       Int NOT NULL ,
        id_cours Int NOT NULL
	,CONSTRAINT teach_PK PRIMARY KEY (id,id_cours)

	,CONSTRAINT teach_user_FK FOREIGN KEY (id) REFERENCES user(id)
	,CONSTRAINT teach_cours0_FK FOREIGN KEY (id_cours) REFERENCES cours(id)
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

