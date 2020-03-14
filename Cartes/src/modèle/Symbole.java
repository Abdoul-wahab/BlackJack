package modèle;

/**
 *<b>Enumération Symbole.</b>
 *<p>Enumération qui décrit les symboles des cartes (pique, coeur,...)</p>
 *
 *@see Carte
 *@see Couleur
 *
 *@author 21606478, 21701844 et 21914280
 */
public enum Symbole {

	PIQUE { 
		@Override public Couleur getCouleur() { 
			return Couleur.NOIR;
		} 
	},
    CARREAU { 
		@Override public Couleur getCouleur() { 
			return Couleur.ROUGE;  
		} 
	},
    COEUR   { 
		@Override public Couleur getCouleur() { 
			return Couleur.ROUGE;  
		} 
	},
    TREFLE   { 
		@Override public Couleur getCouleur() { 
			return Couleur.NOIR;
		} 
	};

    /**
     *Cette méthode permet de récupérer la couleur (ROUGE ou NOIR) d'une carte selon son symbole
     * 
     *@return Couleur La couleur du symbole
     */
    
	public abstract Couleur getCouleur();
}
