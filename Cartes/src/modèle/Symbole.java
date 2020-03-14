package mod�le;

/**
 *<b>Enum�ration Symbole.</b>
 *<p>Enum�ration qui d�crit les symboles des cartes (pique, coeur,...)</p>
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
     *Cette m�thode permet de r�cup�rer la couleur (ROUGE ou NOIR) d'une carte selon son symbole
     * 
     *@return Couleur La couleur du symbole
     */
    
	public abstract Couleur getCouleur();
}
