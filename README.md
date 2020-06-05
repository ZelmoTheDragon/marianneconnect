# Keycloak Marianne

Exemple d'implémentation de **FranceConnect** avec **Keycloak**.
Le code est directement inspiré de l'INSEE [Keycloak-FranceConnect](https://github.com/InseeFr/Keycloak-FranceConnect)
Ce projet est sous licence **CeCILL** (**CE**A **C**NRS **I**NRIA **L**ogiciel **L**ibre),
une licence de logicielle libre compatible avec la **GNU GPL**.

> En savoir plus sur la licence [CeCILL](http://cecill.info/index.fr.html)

## Module

Le projet est découpé en modules:
* **marianne-franceconnect**
    * Module *JAR*
    * Ce module contient l'implémentation de FranceConnect pour Keycloak.
* **marianne-theme**
    * Module *JAR*
    * Ce module contient l'intégration du bouton FranceConnect sur la page de connexion et les pages d'administration.
* **dataprovider**
    * Module *WAR*
    * Ce module se comporte comme un fournisseur de données externe (bouchon) pour agréger des données dans le JWT.

## Exécution

Récupération du projet:
~~~
    git clone https://github.com/ZelmoTheDragon/keycloak-marianne.git
    cd keycloak-marianne
    mvn install
~~~
