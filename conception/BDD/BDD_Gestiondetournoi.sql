#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------


#------------------------------------------------------------
# Table: Score
#------------------------------------------------------------

CREATE TABLE Score(
        id                 int (11) Auto_increment  NOT NULL ,
        nombre_buts        Int ,
        type_victoire      Varchar (25) ,
        bonus              Int ,
        points             Int ,
        nombre_buts_contre Int ,
        forfait            TinyINT ,
        gagnant_penalty    TinyINT ,
        id_Rencontre       Int ,
        id_Equipe          Int ,
        PRIMARY KEY (id ) ,
        UNIQUE (type_victoire )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Equipe
#------------------------------------------------------------

CREATE TABLE Equipe(
        id     int (11) Auto_increment  NOT NULL ,
        nom    Varchar (25) ,
        numero Int ,
        info   Varchar (25) ,
        PRIMARY KEY (id )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Groupe
#------------------------------------------------------------

CREATE TABLE Groupe(
        id     int (11) Auto_increment  NOT NULL ,
        nom    Varchar (25) ,
        numero Int ,
        PRIMARY KEY (id )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Utilisateur
#------------------------------------------------------------

CREATE TABLE Utilisateur(
        id           int (11) Auto_increment  NOT NULL ,
        identifiant  Varchar (25) ,
        mot_de_passe Varchar (25) ,
        role         Varchar (25) ,
        PRIMARY KEY (id )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Rencontre
#------------------------------------------------------------

CREATE TABLE Rencontre(
        terrain        Varchar (25) ,
        rang_planning  Int ,
        heure          Time ,
        init           TinyINT ,
        id             int (11) Auto_increment  NOT NULL ,
        id_Groupe      Int ,
        id_Score       Int ,
        id_Utilisateur Int ,
        PRIMARY KEY (id )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: contient
#------------------------------------------------------------

CREATE TABLE contient(
        id        Int NOT NULL ,
        id_Equipe Int NOT NULL ,
        PRIMARY KEY (id ,id_Equipe )
)ENGINE=InnoDB;

ALTER TABLE Score ADD CONSTRAINT FK_Score_id_Rencontre FOREIGN KEY (id_Rencontre) REFERENCES Rencontre(id);
ALTER TABLE Score ADD CONSTRAINT FK_Score_id_Equipe FOREIGN KEY (id_Equipe) REFERENCES Equipe(id);
ALTER TABLE Rencontre ADD CONSTRAINT FK_Rencontre_id_Groupe FOREIGN KEY (id_Groupe) REFERENCES Groupe(id);
ALTER TABLE Rencontre ADD CONSTRAINT FK_Rencontre_id_Score FOREIGN KEY (id_Score) REFERENCES Score(id);
ALTER TABLE Rencontre ADD CONSTRAINT FK_Rencontre_id_Utilisateur FOREIGN KEY (id_Utilisateur) REFERENCES Utilisateur(id);
ALTER TABLE contient ADD CONSTRAINT FK_contient_id FOREIGN KEY (id) REFERENCES Groupe(id);
ALTER TABLE contient ADD CONSTRAINT FK_contient_id_Equipe FOREIGN KEY (id_Equipe) REFERENCES Equipe(id);
