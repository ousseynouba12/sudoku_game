import java.io.IOException;

/**
 * Classe principale du programme de résolution de Sudoku
 * Version simplifiée : chargement et affichage de la grille uniquement
 */
public class Main {
    
    /**
     * Point d'entrée du programme
     * @param args arguments de la ligne de commande (éventuellement le nom du fichier de grille)
     */
    public static void main(String[] args) {
        System.out.println("=== SOLVEUR DE SUDOKU ===");
        
        // Créer une nouvelle grille vide
        Grille grille = new Grille();
        
        // Charger la grille depuis un fichier ou par saisie utilisateur
        if (args.length > 0) {
            String nomFichier = args[0];
            try {
                System.out.println("Chargement de la grille depuis le fichier: " + nomFichier);
                grille.chargerDepuisFichier(nomFichier);
                System.out.println("Grille chargée avec succès.");
            } catch (IOException e) {
                System.err.println("Erreur lors de la lecture du fichier: " + e.getMessage());
                System.out.println("Veuillez saisir la grille manuellement.");
                grille.chargerDepuisConsole();
            } catch (FormatFichierInvalideException e) {
                System.err.println("Format de fichier invalide: " + e.getMessage());
                System.out.println("Veuillez saisir la grille manuellement.");
                grille.chargerDepuisConsole();
            }
        } else {
            System.out.println("Aucun fichier spécifié. Veuillez saisir la grille manuellement.");
            grille.chargerDepuisConsole();
        }
        
        // Afficher la grille chargée
        System.out.println("\nGrille chargée:");
        afficherGrille(grille);
    }
    
    /**
     * Affiche la grille en utilisant des caractères spéciaux de dessin de boîte
     * @param grille la grille à afficher
     */
    private static void afficherGrille(Grille grille) {
        // Caractères Unicode pour le dessin de la grille
        final String HORIZONTAL = "─";
        final String VERTICAL = "│";
        final String INTERSECTION = "┼";
        final String TOP_LEFT = "┌";
        final String TOP_RIGHT = "┐";
        final String BOTTOM_LEFT = "└";
        final String BOTTOM_RIGHT = "┘";
        final String T_DOWN = "┬";
        final String T_UP = "┴";
        final String T_RIGHT = "├";
        final String T_LEFT = "┤";
        
        // Construction des lignes horizontales
        String ligneFine = TOP_LEFT + HORIZONTAL.repeat(3) + T_DOWN + HORIZONTAL.repeat(3) + T_DOWN + HORIZONTAL.repeat(3) + TOP_RIGHT;
        String ligneGrosse = T_RIGHT + HORIZONTAL.repeat(3) + INTERSECTION + HORIZONTAL.repeat(3) + INTERSECTION + HORIZONTAL.repeat(3) + T_LEFT;
        String ligneFin = BOTTOM_LEFT + HORIZONTAL.repeat(3) + T_UP + HORIZONTAL.repeat(3) + T_UP + HORIZONTAL.repeat(3) + BOTTOM_RIGHT;
        
        System.out.println(ligneFine);
        
        for (int i = 0; i < 9; i++) {
            System.out.print(VERTICAL + " ");
            
            for (int j = 0; j < 9; j++) {
                // Afficher la valeur de la case
                Case cellule = grille.getCase(i, j);
                System.out.print(cellule);
                
                // Séparateur vertical
                if ((j + 1) % 3 == 0) {
                    System.out.print(" " + VERTICAL);
                    if (j < 8) {
                        System.out.print(" ");
                    }
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
            
            // Ligne horizontale après chaque bloc de 3 lignes
            if ((i + 1) % 3 == 0 && i < 8) {
                System.out.println(ligneGrosse);
            }
        }
        
        System.out.println(ligneFin);
    }
}