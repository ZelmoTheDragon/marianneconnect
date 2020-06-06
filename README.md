# Keycloak Marianne

Ce projet est sous licence **CeCILL** (**CE**A **C**NRS **I**NRIA **L**ogiciel **L**ibre),
une licence de logiciel libre compatible avec la **GNU GPL**.

> En savoir plus sur la licence [CeCILL](http://cecill.info/index.fr.html)

## Présentation

Implémentation de **FranceConnect** avec **Keycloak**.
Le code est directement inspiré de l'INSEE [Keycloak-FranceConnect](https://github.com/InseeFr/Keycloak-FranceConnect)

## Module

Le projet est découpé en modules:
* **marianne-keycloak**
    * Module *JAR*
    * Ce module contient l'implémentation de FranceConnect pour Keycloak.
* **dataprovider**
    * Module *WAR*
    * Ce module se comporte comme un fournisseur de données externe (bouchon) pour agréger des données dans le *JWT*.

## Environnement

Ce projet est réalisé en **Java 11** *(OpenJDK)*. et **JavaEE 8**.
Le module a été testé avec **Keycloak 10**.
Il utilise l'outil **Maven** en version 3.6.2.

## Exécution

Récupération du projet:
~~~
    git clone https://github.com/ZelmoTheDragon/marianne.git
    cd marianne
    mvn install
~~~

Déploiement du module sur Keycloak:

~~~
    cd marianne-keycloak
    mvn wildly:deploy
~~~

Déploiement du bouchon sur Wildfly ou Keycloak:

~~~
    cd dataprovider
    mvn wildly:deploy
~~~
