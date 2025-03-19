import java.io.IOException;
import java.util.Scanner;

public class Grille {
    private Case[][] cases;
    
    /**
     * Constructeur par défaut - crée une grille vide
     */
    public Grille() {
        cases = new Case[9][9];
        // Initialiser toutes les cases
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                cases[i][j] = new Case();
            }
        }
    }
    
    /**
     * Charge la grille depuis un fichier
     * @param nomFichier le nom du fichier à charger
     * @throws IOException si le fichier est introuvable ou illisible
     * @throws FormatFichierInvalideException si le format du fichier est invalide
     */
    public void chargerDepuisFichier(String nomFichier) throws IOException, FormatFichierInvalideException {
        int[][] valeurs = FichierUtil.lireFichier(nomFichier);
        
        // Remplir la grille avec les valeurs lues
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                try {
                    cases[i][j] = new Case(valeurs[i][j], valeurs[i][j] != 0);
                } catch (IllegalArgumentException e) {
                    throw new FormatFichierInvalideException("Valeur invalide à la position (" + (i + 1) + "," + (j + 1) + "): " + e.getMessage());
                }
            }
        }
        
        // Vérifier que la grille initiale est valide
        if (!estValide()) {
            throw new FormatFichierInvalideException("La grille initiale contient des valeurs conflictuelles");
        }
    }
    
    /**
     * Charge la grille depuis la console (saisie utilisateur)
     */
    public void chargerDepuisConsole() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Veuillez saisir la grille ligne par ligne (9 lignes de 9 chiffres séparés par des espaces)");
        System.out.println("Utilisez 0 pour les cases vides");
        
        for (int i = 0; i < 9; i++) {
            boolean ligneSaisieCorrectement = false;
            
            while (!ligneSaisieCorrectement) {
                System.out.print("Ligne " + (i + 1) + ": ");
                String ligne = scanner.nextLine().trim();
                
                // Vérifier que la ligne n'est pas vide
                if (ligne.isEmpty()) {
                    System.out.println("La ligne ne peut pas être vide. Veuillez réessayer.");
                    continue;
                }
                
                String[] valeurs = ligne.split("\\s+");
                
                // Vérifier le nombre de valeurs
                if (valeurs.length != 9) {
                    System.out.println("Vous devez saisir exactement 9 valeurs séparées par des espaces. Veuillez réessayer.");
                    continue;
                }
                
                boolean erreurDansLigne = false;
                for (int j = 0; j < 9; j++) {
                    try {
                        int valeur = Integer.parseInt(valeurs[j]);
                        cases[i][j] = new Case(valeur, valeur != 0);
                    } catch (NumberFormatException e) {
                        System.out.println("La valeur à la position " + (j + 1) + " n'est pas un nombre valide: " + valeurs[j]);
                        erreurDansLigne = true;
                        break;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Erreur à la position " + (j + 1) + ": " + e.getMessage());
                        erreurDansLigne = true;
                        break;
                    }
                }
                
                if (!erreurDansLigne) {
                    ligneSaisieCorrectement = true;
                }
            }
        }
        
        // Vérifier que la grille initiale est valide
        if (!estValide()) {
            System.out.println("Attention: La grille saisie contient des valeurs conflictuelles et pourrait ne pas avoir de solution.");
            System.out.print("Voulez-vous continuer quand même? (O/N): ");
            String reponse = scanner.nextLine().trim().toUpperCase();
            if (!reponse.equals("O")) {
                // Recommencer la saisie
                chargerDepuisConsole();
            }
        }
    }
    
    /**
     * Vérifie si la grille est valide (pas de conflits dans les lignes, colonnes et sous-grilles)
     * @return true si la grille est valide, false sinon
     */
    public boolean estValide() {
        // Vérifier les lignes
        for (int i = 0; i < 9; i++) {
            boolean[] vu = new boolean[10]; // ignorer l'index 0
            for (int j = 0; j < 9; j++) {
                int valeur = cases[i][j].getValeur();
                if (valeur != 0) {
                    if (vu[valeur]) {
                        return false; // Valeur déjà vue dans cette ligne
                    }
                    vu[valeur] = true;
                }
            }
        }
        
        // Vérifier les colonnes
        for (int j = 0; j < 9; j++) {
            boolean[] vu = new boolean[10]; // ignorer l'index 0
            for (int i = 0; i < 9; i++) {
                int valeur = cases[i][j].getValeur();
                if (valeur != 0) {
                    if (vu[valeur]) {
                        return false; // Valeur déjà vue dans cette colonne
                    }
                    vu[valeur] = true;
                }
            }
        }
        
        // Vérifier les sous-grilles 3x3
        for (int boxRow = 0; boxRow < 3; boxRow++) {
            for (int boxCol = 0; boxCol < 3; boxCol++) {
                boolean[] vu = new boolean[10]; // ignorer l'index 0
                for (int i = boxRow * 3; i < boxRow * 3 + 3; i++) {
                    for (int j = boxCol * 3; j < boxCol * 3 + 3; j++) {
                        int valeur = cases[i][j].getValeur();
                        if (valeur != 0) {
                            if (vu[valeur]) {
                                return false; // Valeur déjà vue dans cette sous-grille
                            }
                            vu[valeur] = true;
                        }
                    }
                }
            }
        }
        
        return true;
    }
    
    /**
     * Obtient une case à une position donnée
     * @param ligne l'indice de la ligne (0-8)
     * @param colonne l'indice de la colonne (0-8)
     * @return la case à la position spécifiée
     * @throws IndexOutOfBoundsException si les indices ne sont pas valides
     */
    public Case getCase(int ligne, int colonne) {
        if (ligne < 0 || ligne >= 9 || colonne < 0 || colonne >= 9) {
            throw new IndexOutOfBoundsException("Indices invalides: (" + ligne + "," + colonne + ")");
        }
        return cases[ligne][colonne];
    }
    
    // Autres méthodes à implémenter plus tard...
}