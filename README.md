# Zombie_Apocalypse

# Equipe

- Owen Wanaverbecque
- Farzadde Soleimanian
- Ziad El Hassan
- Abderazak Kassa-Beghdouche

# Sujet

[Le sujet 2024](https://www.fil.univ-lille.fr/~varre/portail/l2s4-projet/sujet2024.pdf)

# UML

https://lucid.app/lucidchart/456a610a-a383-48a7-834a-eb0eaf49c5d9/edit?invitationId=inv_6f89afeb-2641-4162-bb06-7419f331ac1c&page=0_0#

# Livrable

## Livrable 1

Pour compiler et executer le jeu:  
`make`  
Pour choisir la taille de la map:  
`cd bin/classes`  
`java zombieapocalypse.TestMain 5 5`

### Atteinte des objectifs

### Difficultés restant à résoudre

## Livrable 2

Pour compiler et executer le jeu:
`make`

Pour executer les tests:
`java -jar junit-console.jar -classpath bin/classes:test -select-package test`

Pour générer la documentation:
`javadoc -sourcepath src -subpackages zombieapocalypse -d docs`

Pour voir l'ajout des acteurs et de leurs équipements comme demandé dans l'énoncé:

- Dans le code: src>zombieapocalypse>Livrable2.java

- Dans le terminal:

  - "-" bleu: Door
  - "S" jaune: Sewer
  - "S" bleu: Street
  - "R" rouge: Room
  - "C" jaune: Continental
  - "+" vert: Pharmacy
  - nombre supérieur droit: nombre de survivants dans la case
  - nombre supérieur gauche: nombre de zombies dans la case

### Atteinte des objectifs

L'UML a été mis à jour, vous pouvez constater l'ajout des classes Item et Actor ainsi que les classes héritières de celles-ci qui modélisent respectivement tous les outils/armes et tous les survivants/zombies du jeu.

De plus, nous avons terminé les modélisations des égoûts et des portes.

Pour finir, nous avons déplacé tous les survivants de chaque rôle vers le nord d'une case et nous les avons dotés d'une fiole dans la main et d'une carte dans le sac à dos. Les zombies de chaque type ont eux aussi été placés.

### Difficultés restant à résoudre

## Livrable 3

Pour compiler et executer le jeu:
`make`

Pour executer les tests:
`java -jar junit-console.jar -classpath bin/classes:test -select-package test`

Pour générer la documentation:
`javadoc -sourcepath src -subpackages zombieapocalypse -d docs`

Pour générer Livrable3.jar:
`jar cfm Livrable3.jar manifest.txt -C bin/classes .`

Pour exécuter Livrable3.jar:
`java -jar Livrable3.jar`

### Atteinte des objectifs

L'UML a été mis à jour, vous pouvez constater l'ajout des méthodes d'action, les survivants sont une case au nord le second a une hache en main et le troisième une fiole.

### Difficultés restant à résoudre

## Livrable 4

Pour compiler le jeu et les tests:  
`make cls`

Pour executer le jeu depuis les fichiers .class (ne pas oublier de revenir à la racine après pour les autres commandes):  
`cd bin/classes; java zombieapocalypse.Livrable4 10 14 4`

Pour executer les tests:  
`java -jar junit-console.jar -classpath bin/classes:test -select-package test`

Pour générer la documentation:  
`make doc`

Pour générer zombicide.jar:  
`make zombicide.jar`

Pour exécuter zombicide.jar produit sur une machines du M5:  
`java -jar jar/zombicide.jar 10 14 4`

Pour exécuter zombicide.jar produit localement:  
`java -jar zombicide.jar 10 14 4`

Pour supprimer les fichiers générés:  
`make clean`

- Dans le terminal:

  - "-" bleu: Door
  - "S" jaune: Sewer
  - "S" bleu: Street
  - "R" rouge: Room
  - "C" jaune: Continental
  - "+" vert: Pharmacy
  - nombre supérieur droit: nombre de survivants dans la case
  - nombre supérieur gauche: nombre de zombies dans la case

### Atteinte des objectifs

    - le jeu se déroule du début jusqu'à la fin sans interactions
    - l'affichage de la carte est clair et compréhensible
    - une description détaillée des tours de chaque acteur est affiché
    - les survivants peuvent ramasser/utiliser des objets et ouvrir des portes
    - certaines actions génèrent du bruit

### Difficultés restant à résoudre

    - résoudre les edge cases d'une façon plus structurée
    - l'algorithme des tours des survivants n'est pas efficace et les survivants perdent dans la majorité des parties
    - avoir plus de tests pour l'ensemble du projet
    - la classe Game n'est pas vraiment extensible
    - certaines fonctionnalités sont absentes (zombies qui se déplacent vers le bruit, apparition des zombies entre chaque manche)

# Journal de bord

## Semaine 1

- Absent (neige)

## Semaine 2

- Familiarisation avec le sujet
- Mise en commun des idées
- Début de l'UML

## Semaine 3

- 1er version de l'UML
- Discussion de l'UML avec l'enseignant
- Création des premières class java

## Semaine 4

- 1er version de createStreets
- Ajout des RoomCells dans la Map

## Semaine 5

- Ajout de canBeSplit et addStreet
- Ajout du makefile
- Ajout de PimpStyle
- Rendu du Livrable 1

## Semaine 6

- Réorganisation des fichiers
- Ajout AddDoors()

## Semaine 7

- Mise à jour UML pour second livrable
- Mise à jour UML pour les classes anciennes
- Ajout classes Actors, Items
- Ajout des classes de tous les zombies et survivants

## Semaine 8

- Mise à jour des commandes de test
- Mise à jour du makefile
- Brainstorming sur les actions pour le livrable 3

## Semaine 9

- Ajout des méthodes et des paramètres dans les classes map, cell, game, actors et survivors
- Mise à jour des commandes de test
- Modification de quelques méthodes increase/decrease et ajout des setters
- Mise à jour de l'UML

## Semaine 10

- Ajout de la méthode search

## Semaine 11/12

- Supression des fichiers inutiles
- Ajout des méthodes d'actions dans Game
- Ajout des UML des 4 livrables différents au format .png
- Ajout des méthodes de jeu gameloop, survivorTurn et zombieTurn
- Ajout des dernières javadoc et mise à jour du makefile
- Finalisation des derniers tests
- Ajout Livrable4.java
