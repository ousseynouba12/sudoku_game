public class Case {
    private int valeur;
    private boolean fixe; //indique si une case est modifiable ou pas
    
    /**
     * Constructeur par défaut d'une case vide et modifiable
     */
    public Case() {
        this.valeur = 0;
        this.fixe = false;
    }
    
    /**
     * Constructeur avec valeur initiale
     * @param valeur la valeur de la case (0-9)
     * @param fixe true si la case est fixe (valeur initiale), false sinon
     * @throws IllegalArgumentException si la valeur n'est pas entre 0 et 9
     */
    public Case(int valeur, boolean fixe) {
        setValeur(valeur); // Utilise le setter qui inclut la validation
        this.fixe = valeur != 0 && fixe; // Une case ne peut être fixe que si elle a une valeur
    }
    
    public int getValeur() {
        return valeur;
    }
    
    /**
     * Définit la valeur de la case si elle est modifiable
     * @param valeur la nouvelle valeur (0-9)
     * @return true si la modification a réussi, false si la case est fixe
     * @throws IllegalArgumentException si la valeur n'est pas entre 0 et 9
     */
    public boolean setValeur(int valeur) {
        // Vérification que la valeur est dans l'intervalle valide
        if (valeur < 0 || valeur > 9) {
            throw new IllegalArgumentException("La valeur doit être comprise entre 0 et 9");
        }
        
        // Si la case est fixe, on ne peut pas la modifier
        if (fixe) {
            return false;
        }
        
        this.valeur = valeur;
        return true;
    }
    
    /**
     * Marque la case comme fixe (ne peut plus être modifiée)
     * Ne fonctionne que si la case n'est pas vide
     * @return true si la case a été marquée comme fixe, false sinon
     */
    public boolean setFixe() {
        if (valeur == 0) {
            return false; // On ne peut pas fixer une case vide
        }
        this.fixe = true;
        return true;
    }
    
    /**
     * @return true si la case est fixe (valeur initiale), false sinon
     */
    public boolean estFixe() {
        return fixe;
    }
    
    /**
     * @return true si la case est modifiable, false sinon
     */
    public boolean estModifiable() {
        return !fixe;
    }
    
    /**
     * @return true si la case est vide (valeur = 0), false sinon
     */
    public boolean estVide() {
        return valeur == 0;
    }
    
    @Override
    public String toString() {
        return estVide() ? " " : String.valueOf(valeur);
    }
}