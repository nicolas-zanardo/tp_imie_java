#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------


#------------------------------------------------------------
# Table: classe
#------------------------------------------------------------

CREATE TABLE learning_class(
        id         Int  Auto_increment  NOT NULL ,
        name       Varchar (250) NOT NULL ,
        nbr_people Int NOT NULL
	,CONSTRAINT learning_class_PK PRIMARY KEY (id)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: room_type
#------------------------------------------------------------

CREATE TABLE room_type(
        id   Int  Auto_increment  NOT NULL ,
        name Varchar (250) NOT NULL
	,CONSTRAINT room_type_PK PRIMARY KEY (id)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: room
#------------------------------------------------------------

CREATE TABLE room(
        id           Int  Auto_increment  NOT NULL ,
        name         Varchar (250) NOT NULL ,
        nbr_place    Int NOT NULL ,
        id_room_type Int NOT NULL
	,CONSTRAINT room_PK PRIMARY KEY (id)

	,CONSTRAINT room_room_type_FK FOREIGN KEY (id_room_type) REFERENCES room_type(id)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: role
#------------------------------------------------------------

CREATE TABLE role(
        id   Int  Auto_increment  NOT NULL ,
        name Varchar (250) NOT NULL
	,CONSTRAINT role_PK PRIMARY KEY (id)
)ENGINE=InnoDB COMMENT "INSERT INTO role_user (role_name) VALUES ('éléves'), ('formateur'), ('responsable maintenance'), ('responsable global'), ('responsables de la gestion');" ;


#------------------------------------------------------------
# Table: user
#------------------------------------------------------------

CREATE TABLE user(
        id                Int  Auto_increment  NOT NULL ,
        lastname          Varchar (250) NOT NULL ,
        firstname         Varchar (250) NOT NULL ,
        login             Varchar (250) NOT NULL ,
        password          Varchar (250) NOT NULL ,
        id_learning_class Int ,
        id_role           Int NOT NULL
	,CONSTRAINT user_PK PRIMARY KEY (id)

	,CONSTRAINT user_learning_class_FK FOREIGN KEY (id_learning_class) REFERENCES learning_class(id)
	,CONSTRAINT user_role0_FK FOREIGN KEY (id_role) REFERENCES role(id)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: training_course
#------------------------------------------------------------

CREATE TABLE training_course(
        id                 Int  Auto_increment  NOT NULL ,
        formation_name     Varchar (250) NOT NULL ,
        date_debut         Date ,
        date_fin           Date ,
        recursive_calandar Bool ,
        id_room            Int NOT NULL ,
        id_learning_class  Int NOT NULL ,
        id_user            Int NOT NULL
	,CONSTRAINT training_course_PK PRIMARY KEY (id)

	,CONSTRAINT training_course_room_FK FOREIGN KEY (id_room) REFERENCES room(id)
	,CONSTRAINT training_course_learning_class0_FK FOREIGN KEY (id_learning_class) REFERENCES learning_class(id)
	,CONSTRAINT training_course_user1_FK FOREIGN KEY (id_user) REFERENCES user(id)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: week
#------------------------------------------------------------

CREATE TABLE week(
        id                 Int  Auto_increment  NOT NULL ,
        YEAR               Int ,
        WEEK_OF_YEAR       Int ,
        MONDAY_AM          Bool NOT NULL ,
        MONDAY_PM          Bool NOT NULL ,
        TUESDAY_AM         Bool NOT NULL ,
        TUESDAY_PM         Bool NOT NULL ,
        WEDNESDAY_AM       Bool NOT NULL ,
        WEDNESDAY_PM       Bool NOT NULL ,
        THURSDAY_AM        Bool NOT NULL ,
        THURSDAY_PM        Bool NOT NULL ,
        FRIDAY_AM          Bool NOT NULL ,
        FRIDAY_PM          Bool NOT NULL ,
        id_training_course Int NOT NULL
	,CONSTRAINT week_PK PRIMARY KEY (id)

	,CONSTRAINT week_training_course_FK FOREIGN KEY (id_training_course) REFERENCES training_course(id)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: room_status
#------------------------------------------------------------

CREATE TABLE room_status(
        id   Int  Auto_increment  NOT NULL ,
        name Varchar (50) NOT NULL
	,CONSTRAINT room_status_PK PRIMARY KEY (id)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: room_issue
#------------------------------------------------------------

CREATE TABLE room_issue(
        id             Int  Auto_increment  NOT NULL ,
        name           Varchar (250) NOT NULL ,
        id_room        Int NOT NULL ,
        id_user        Int NOT NULL ,
        id_room_status Int NOT NULL
	,CONSTRAINT room_issue_PK PRIMARY KEY (id)

	,CONSTRAINT room_issue_room_FK FOREIGN KEY (id_room) REFERENCES room(id)
	,CONSTRAINT room_issue_user0_FK FOREIGN KEY (id_user) REFERENCES user(id)
	,CONSTRAINT room_issue_room_status1_FK FOREIGN KEY (id_room_status) REFERENCES room_status(id)
)ENGINE=InnoDB;

