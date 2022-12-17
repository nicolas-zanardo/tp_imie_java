# GESTION DE RESERVATION DE SALLE


Cette application permet de gérer la reservation des salles pour des formations.

## Environnement de développement 

> Linux system & MacOS



## Start application

### Création de l'utilisateur admin en base de donnée

````bash
sudo mysql -u root -p
CREATE USER 'admin'@'localhost' IDENTIFIED BY 'admin';
GRANT ALL PRIVILEGES on *.* TO 'admin'@'localhost';
````

### Création de la base de données SQL

````bash
create database formation_IMIE
````

### Surcharger la variable de la base de données pour les tests d'intégration et unitaire

````bash
spring.datasource.url=jdbc:mysql://localhost:3306/IMIE_formation_TEST
````


