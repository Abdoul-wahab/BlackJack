package modèle;

/**
 *<b>Classe JeuFactory.</b>
 *<p>Cette classe permettant de créer et remplir différents jeu de cartes</p>
 *
 *@see JeuDeCartes
 *@see Jeu32
 *@see Jeu52
 *
 *@author 21606478, 21701844 et 21914280
 *
 */
public class JeuFactory {
    
	/**
	 * Méthode permettant de remplir un jeu de 32 cartes
	 * 
	 * @return Jeu32 Le jeu de 32 cartes remplit
	 */
    public static Jeu32 creerJeu32() {
        Jeu32 j32 = new Jeu32("Jeu 32");
        j32.remplirUnJeu();
        return j32;
    }
    
    /**
	 * Méthode permettant de remplir un jeu de 52 cartes
	 * 
	 * @return Jeu52 Le jeu de 52 cartes remplit
	 */
    public static Jeu52 creerJeu52() {
        Jeu52 j52 = new Jeu52("Jeu 52");
        j52.remplirUnJeu();
        return j52;
    }
    
}
