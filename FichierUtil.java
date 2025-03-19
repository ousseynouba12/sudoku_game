import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe utilitaire pour la gestion des fichiers
 */
public class FichierUtil {
    
    /**
     * Lit un fichier contenant une grille de sudoku
     * @param nomFichier le nom du fichier à lire
     * @return un tableau 9x9 d'entiers représentant la grille
     * @throws IOException si le fichier est introuvable ou illisible
     * @throws FormatFichierInvalideException si le format du fichier est invalide
     */
    public static int[][] lireFichier(String nomFichier) throws IOException, FormatFichierInvalideException {
        List<String> lignes = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(nomFichier))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                lignes.add(ligne);
            }
        } catch (IOException e) {
            throw new IOException("Fichier introuvable ou illisible: " + nomFichier, e);
        }
        
        // Vérifier le nombre de lignes
        if (lignes.size() != 9) {
            throw new FormatFichierInvalideException("Le fichier doit contenir exactement 9 lignes, mais en contient " + lignes.size());
        }
        
        int[][] grille = new int[9][9];
        
        for (int i = 0; i < 9; i++) {
            String ligne = lignes.get(i);
            // Assurons-nous que les chiffres sont séparés par des espaces
            String[] valeurs = ligne.trim().split("\\s+");
            
            // Vérifier le nombre de valeurs par ligne
            if (valeurs.length != 9) {
                throw new FormatFichierInvalideException("La ligne " + (i + 1) + " doit contenir exactement 9 valeurs séparées par des espaces, mais en contient " + valeurs.length);
            }
            
            for (int j = 0; j < 9; j++) {
                try {
                    int valeur = Integer.parseInt(valeurs[j]);
                    
                    // Vérifier que la valeur est entre 0 et 9
                    if (valeur < 0 || valeur > 9) {
                        throw new FormatFichierInvalideException("La valeur à la position (" + (i + 1) + "," + (j + 1) + ") doit être comprise entre 0 et 9");
                    }
                    
                    grille[i][j] = valeur;
                } catch (NumberFormatException e) {
                    throw new FormatFichierInvalideException("La valeur à la position (" + (i + 1) + "," + (j + 1) + ") n'est pas un nombre valide: " + valeurs[j]);
                }
            }
        }
        
        return grille;
    }
}