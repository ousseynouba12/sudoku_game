# sudoku_game
# Projet Solveur de Sudoku

## Description
Ce projet est un solveur de Sudoku en mode console développé en Java. Il permet de charger une grille de Sudoku depuis un fichier texte ou via une saisie manuelle, puis d'afficher cette grille de manière structurée avec des caractères de dessin de boîte.

## Structure du projet

### Classes principales
- **Main** : Point d'entrée du programme, gère le chargement et l'affichage de la grille
- **Grille** : Représente une grille de Sudoku avec ses 81 cases
- **Case** : Représente une case individuelle dans la grille avec sa valeur et son état (fixe ou modifiable)
- **FichierUtil** : Classe utilitaire pour la lecture des fichiers de grille
- **FormatFichierInvalideException** : Exception personnalisée pour les erreurs de format de fichier

### Fonctionnalités implémentées
- Chargement d'une grille depuis un fichier texte
- Saisie manuelle d'une grille via la console
- Validation de la grille (vérification des règles du Sudoku)
- Affichage structuré de la grille avec des caractères spéciaux

## Format du fichier d'entrée
Le fichier de grille doit respecter le format suivant :
- 9 lignes, chacune contenant 9 chiffres entre 0 et 9, séparés par des espaces
- Le chiffre 0 représente une case vide à remplir

Exemple :
```
5 3 0 0 7 0 0 0 0
6 0 0 1 9 5 0 0 0
0 9 8 0 0 0 0 6 0
8 0 0 0 6 0 0 0 3
4 0 0 8 0 3 0 0 1
7 0 0 0 2 0 0 0 6
0 6 0 0 0 0 2 8 0
0 0 0 4 1 9 0 0 5
0 0 0 0 8 0 0 7 9
```

## Utilisation
Pour exécuter le programme :

```bash
java Main [nom_du_fichier]
```

- Si un nom de fichier est fourni, le programme tente de charger la grille depuis ce fichier
- Si aucun fichier n'est spécifié ou si le fichier est invalide, l'utilisateur est invité à saisir la grille manuellement

## Gestion des erreurs
Le programme gère plusieurs types d'erreurs :
- Fichier introuvable
- Format de fichier incorrect (nombre de lignes ou de colonnes invalide)
- Valeurs invalides dans le fichier (valeurs non numériques ou hors de l'intervalle 0-9)
- Grille initiale avec des valeurs conflictuelles

## Développement futur
Pour compléter le solveur, les fonctionnalités suivantes peuvent être ajoutées :
- Implémentation de l'algorithme de résolution (Backtracking)
- Affichage de la grille résolue
- Sauvegarde de la solution dans un fichier
- Interface graphique (version 2.0)
