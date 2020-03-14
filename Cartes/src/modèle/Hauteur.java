package modèle;

/**
 *<b>Enumération Hauteur.</b>
 *<p>Enumération qui décrit les hauteurs des cartes (8, 9, 10, valet,...)</p>
 *
 *@see Carte
 *
 *@author 21606478, 21701844 et 21914280
 *
 */
public enum Hauteur {
	
	DEUX(2), TROIS(3), QUATRE(4), CINQ(5), SIX(6), SEPT(7), HUIT(8), NEUF(9), DIX(10), 
	VALET("Valet", 10), DAME("Dame", 10), ROI("Roi", 10), AS("As", 11);
	 
	/**
	 * Une chaine de caractère qui définit la hauteur (Ex: 9 ou Roi)
	 */
    private final String hauteur;
    /**
     * la valeur que représente la hauteur dans un jeu BlackJack
     */
    private final int val;
	
    private Hauteur (final int val) {
    	this.hauteur = Integer.toString(val);
        this.val=val;
	  }
    private Hauteur (final String hauteur, final int val) {
    	this.hauteur = hauteur;
        this.val=val;
	  }
	 
	/**
	 * @return la valeur de la hauteur
	 */
	public int getValeur() {		
		return this.val;
	}
	  
	    
	  @Override
	  public String toString(){
		  return hauteur;
	  }	  

}
