/**
 * Exception personnalisée pour les erreurs de format de fichier
 */
public class FormatFichierInvalideException extends Exception {
    public FormatFichierInvalideException(String message) {
        super(message);
    }
}