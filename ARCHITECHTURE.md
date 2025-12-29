# ARCHITECTURE.md

## Etudiants:
- Ziad El Hassan
- Ahmed Benarous
- Mathieu

---

## 1. Introduction

Ce document décrit l’architecture logicielle de l’application **Zombie Apocalypse**, réalisée dans le cadre du projet fil rouge.  
L’objectif principal était de transformer le prototype initial en une application **robuste, maintenable et évolutive**, en appliquant les principes **MVC, SOLID** et des **Design Patterns**.

---

## 2. Architecture Générale (MVC)

L’application est organisée selon le pattern **MVC** :

`src` :
- model # Contient les données et la logique métier pure
- view # Gère l’interaction utilisateur
- controller # Orchestration entre modèle et vue


- **Modèle (`model`)** :  
  Contient toutes les classes représentant les entités du projet et les règles de gestion.  
  Aucun code d’affichage ou d’interaction utilisateur n’y est présent.

- **Vue (`view`)** :  
  Gestion de l’affichage et des entrées utilisateur (console ou interface graphique).  
  Se limite à l’interface utilisateur.

- **Contrôleur (`controller`)** :  
  Coordonne les appels entre la vue et le modèle.  
  Contient la logique pour exécuter les actions demandées par l’utilisateur.

> Cette séparation garantit que le modèle reste **indépendant de la vue**, facilitant l’évolution et la maintenance.

---

## 3. Application des Principes SOLID

### 1. SRP (Single Responsibility Principle)

Chaque classe a une responsabilité unique :

- **Survivor** : représente un survivant et gère uniquement sa santé, ses items et ses actions.  
- **Fighter, Healer, Lucky, Searcher** : spécialisations de Survivor avec des comportements particuliers (combat, soin, chance, recherche).  
- **Zombie, Abomination, Bigboy, Runner, Walker** : représentent différents types de zombies avec leurs propres dégâts et comportements.  
- **Actor** : classe de base pour tout acteur du jeu, ne gère que la santé et les coordonnées.  

> Chaque classe ne mélange pas la logique métier avec la gestion des entrées/sorties ou des décisions de jeu externes.

---

### 2. OCP (Open/Closed Principle)

Le code est ouvert à l’extension mais fermé à la modification :

- Pour ajouter un nouveau type de **Survivor** ou de **Zombie**, il suffit de créer une nouvelle sous-classe.  
- Exemple : `Healer` étend `Survivor` et ajoute une méthode `healSurvivor()` sans modifier la classe de base `Survivor`.  
- Si on veut un nouvel objet utilisable comme arme ou outil, on peut créer une nouvelle classe implémentant `Item` et le code existant s’adapte automatiquement.

---

### 3. DIP (Dependency Inversion Principle)

- Les modules de haut niveau n’interagissent pas directement avec les implémentations concrètes mais via des abstractions (`Actor`, `Item`).  
- Exemple : `Survivor` utilise des objets de type `Item` ou `Weapon` sans connaître leur implémentation exacte.  
- Cela permet de changer les objets (ajouter un nouveau type d’arme ou de potion) sans toucher au code des survivants.

---

### 4. LSP (Liskov Substitution Principle)

- Toutes les sous-classes de `Survivor` ou `Zombie` peuvent être utilisées à la place de leur super-classe sans casser le comportement attendu.  
- Exemple : un `Fighter` peut être utilisé partout où un `Survivor` est attendu, et un `Abomination` partout où un `Zombie` est attendu.

---

### 5. ISP (Interface Segregation Principle)

- Bien que le projet n’ait pas encore défini d’interfaces complexes, chaque classe n’est forcée d’implémenter que ce qui est nécessaire.  
- Par exemple, les méthodes de soin (`healSelf`, `healSurvivor`) ne sont présentes que dans `Healer` et `Survivor` et ne polluent pas les autres classes.


---

## 4. Design Patterns Implémentés

1. **Factory Method** (Créationnel)  
   - Permet de créer différents types de zombies ou de survivants sans modifier le code existant.  
   - Exemple : `createZombie(zombieID)` retourne un `Abomination`, `Bigboy`, `Runner` ou `Walker` selon l’ID.  
   - Exemple : `createSurvivor(survivorID)` retourne un `Fighter`, `Healer`, `Lucky` ou `Searcher`.  

2. **Strategy** (Comportemental)  
   - Permet de changer dynamiquement le comportement des zombies ou des survivants (déplacement, attaque, recherche).  
   - Exemple : chaque type de zombie pourrait avoir sa propre stratégie de mouvement et d’attaque.  

3. **Observer** (Comportemental)  
   - Permet aux zombies d’être alertés par le bruit généré dans les cellules (`makeNoise`) et de réagir en conséquence.  
   - Exemple : la cellule notifie les zombies observateurs lorsqu’un bruit est produit.  

4. **Template Method** (Comportemental)  
   - Définit la structure générale d’un tour de survivant ou de zombie, laissant certaines étapes spécifiques aux sous-classes.  
   - Exemple : `survivorTurn()` et `zombieTurn()` définissent les étapes générales du tour, mais les actions spécifiques dépendent du type de l’acteur.  

> Ces patterns ont été choisis pour **favoriser l’extensibilité, la modularité et la maintenabilité** du code.

---

## 5. Méthodologie

### Analyse du prototype
- Identification des entités et règles métier.
- Extraction des fonctionnalités principales.

### Découpage en MVC
- Création des dossiers `model`, `view`, `controller`.
- Déplacement progressif du code existant vers les bonnes couches.

### Application SOLID et Patterns
- Refactorisation des classes pour SRP et OCP.
- Introduction de **Factory Method** et **Strategy** pour améliorer l’évolutivité.

### Tests et validation
- Vérification que chaque action fonctionne correctement via la console ou l’interface.
- Correction des dépendances pour respecter DIP.

---

## 6. Transparence et Critique IA

### Outils IA utilisés
- **ChatGPT** : suggestions de structure et aide avec gradle  

### Usage
- Génération de classes modèles et interfaces.
- Proposition de patterns pour la création d’ennemis.

### Critique
- L’IA proposait parfois des méthodes mélangeant vue et modèle, ce qui violait SRP et MVC.  
- L’IA a été utilisée comme copilote, jamais comme béquille.  
- Toutes les solutions ont été validées pour respecter les standards **SOLID** et **MVC**.


## 7. Compilation, Exécution et Tests

### Compilation
- Le projet utilise **Gradle** pour la gestion du build.
- Compilation via la ligne de commande :
```bash
./gradlew build
```
- Les fichiers `.class` sont générés automatiquement dans `build/classes/java/main`.

## Creation d'un JAR
```bash
./gradlew jar
```
- Le JAR sera disponible dans :
```bash
build/libs/zombicide.jar
```
- Vous pouvez l’exécuter ensuite avec :
```bash
java -jar build/libs/zombicide.jar 10 14 4
```

### Exécution
- Lancement du programme principal :
```bash
./gradlew run
```
- Le `mainClass` doit être défini dans le fichier `build.gradle` :
```bash
application {
    mainClass = 'zombieapocalypse.controller.Livrable'
}
```

### Tests
- Tests unitaires:
```bash
Vérification des méthodes critiques (déplacement, attaque, ouverture de portes).
```
- Verification dans `build/reports/index.html`


- Dans le terminal:

  - "-" bleu: Door
  - "S" jaune: Sewer
  - "S" bleu: Street
  - "R" rouge: Room
  - "C" jaune: Continental
  - "+" vert: Pharmacy
  - nombre supérieur droit: nombre de survivants dans la case
  - nombre supérieur gauche: nombre de zombies dans la case


