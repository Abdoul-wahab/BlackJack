package mod�le;

/**
 *<b>Classe Carte.</b>
 *<p>Cette classe d�finit une carte par rapport � sa hauteur et son symbole)</p>
 *
 *@see Hauteur
 *@see Symbole
 *
 *@author 21606478, 21701844 et 21914280
 *
 */
public class Carte {	
	
	/**
	 * La hauteur de la carte (8, 9, 10, valet,...)
	 */
	private final Hauteur hauteur;
	/**
	 * Le symbole de la carte (pique, coeur,...)
	 */
    private final Symbole symbole;
    
	/**
	 * Constructeur de carte
	 * 
	 * @param hauteur : Hauteur de la carte
	 * @param symbole : Symbole de la carte
	 */
	public Carte(Hauteur hauteur, Symbole symbole) {
		this.hauteur = hauteur;
		this.symbole = symbole;
	}
	
	/**
	 * Accesseur de la hauteur de la carte
	 * 
	 * @return la hauteur de la carte
	 */
	public Hauteur getHauteur() {
		return hauteur;
	}
	/**
	 * Accesseur du symbole de la carte
	 * 
	 * @return le symbole de la carte
	 */
	public Symbole getSymbole() {
		return symbole;
	}
    
	/**
	 * Permet de r�cup�rer la valeur que repr�sente la carte dans une partie de BlackJack
	 * 
	 * @return le symbole de la carte
	 */
    public int getValeur() {
       return hauteur.getValeur();
    }
		
	/**
	 * Permet de r�cup�rer une chaine de caract�re repr�sentant une carte 
	 * 
	 * @return String sous la forme <i>hauteur</i> de <i>symbole</i>. Par exemple: Dame de Pique
	 */
	@Override
	public String toString() {
		return hauteur+" de "+symbole;
	}
	
}
