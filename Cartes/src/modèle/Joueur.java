package mod�le;

/**
 *<b>Classe Joueur.</b>
 *<p>Cette classe un joueur dans un jeu de cartes</p>
 *
 *@see Tas
 *@see Carte
 *
 *@author 21606478, 21701844 et 21914280
 *
 */
public class Joueur {
	
	/**
	 * Le nom du joueur
	 */
	private final String nom;
	/**
	 * La main ou tas de cartes du joueur
	 */
    private final Tas main;
    /**
     * Le nombre de jeton que poss�de le joueur (pour la mise)
     */
    private int nbJetons;
    
	/**
	 * Constructeur du Joueur
	 * 
	 * @param nom du joueur
	 */
	public Joueur(String nom) {
		this.nom = nom;
		this.main = new Tas("La main de "+nom);
		this.nbJetons=10;
	}
	
	/**
	 * Accesseur du nom du joueur
	 * 
	 * @return String : le nom du joueur
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * Accesseur de la main du joueur
	 * 
	 * @return tas : la main du joueur
	 */
	public Tas getMain() {
		return main;
	}
	
	/**
	 * Une m�thode qui permet de v�rifier si la main d'un joueur est vide
	 * 
	 * @return vrai si le joueur n'a plus de cartes en main
	 */
	public boolean estMainVide(){
		return main.estVide();
    }
	
	/**
	 * Permet de r�cup�rer le nombre de carte dans la main du joueur
	 * 
	 * @return un entier: le nombre de cartes dans la main du joueur
	 */
	public int getNbCartes(){
        return main.getTaille();
    }
	
	/**
	 * Permet d'ajouter une carte dans la main du joueur
	 * 
	 * @param carte � ajouter en dessous de la main du joueur
	 */
	public void prendreCarte(Carte carte){
        main.ajouterCarte(carte);
    }
	
	/**
	 * Permet d'ajouter un ensemble de carte dans la main du joueur
	 * 
	 * @param tas dont les cartes sont � ajouter en dessous de la main du joueur
	 */
	public void prendreTous(Tas tas){
        main.ajouterTous(tas);
    }
	
	/**
	 * Cette m�thode permet de vider la main du joueur de toutes ses cartes
	 * 
	 */
	public void viderMain() {
		this.main.viderTas();
	}
	
	/**
	 * Cette m�thode permet de r�cup�rer la valeur de la main du joueur dans un Blackjack
	 * 
	 * @return un entier d�crivant la valeur de la main du joueur
	 * 
	 */
	public int getValeurMain() {
		return this.main.getValeurPaquet();
	}
	
	/**
	 * Cette m�thode permet de v�rifier si le joueur a un Blackjack dans sa main
	 * 
	 * @return bool�en : vrai si le joueur a un Blackjack
	 * 
	 */
	public boolean haveBlackJack() {
		return this.getValeurMain() == 21 && this.getNbCartes()==2;
	}
	
	/**
	 * Permet de remplir la main du joueur avec un Blackjack (m�thode de test)
	 * 
	 */
	public void remplirMainAvecBlackJack() {
		this.main.ajouterCarte(new Carte(Hauteur.AS, Symbole.PIQUE));
		this.main.ajouterCarte(new Carte(Hauteur.ROI, Symbole.PIQUE));
	}
	
	/**
	 * Accesseur du nombre de jetons
	 * 
	 * @return entier : Le nombre de jeton du joueur
	 * 
	 */
	public int getNbJetons() {
		return nbJetons;
	}
	
	/**
	 * Cette m�thode permet de retirer la mise du joueur � son nombre de jetons
	 * 
	 * @param mise du joueur
	 */
	public void retirerMise(int mise) {
		this.nbJetons-=mise;
	}
	
	/**
	 * Cette m�thode permet d'ajouter des jetons au nombre de jetons du joueur
	 * 
	 * @param nbJetons : Le nombre de jetons � ajouter
	 */
	public void ajouterJetons(int nbJetons) {
		this.nbJetons+=nbJetons;
	}
	
	/**
	 * Permet de v�rifier si le joueur a encore des jetons
	 * 
	 * @return vrai si il lui reste des jetons
	 */
	public boolean resteJetons() {
		return !(nbJetons<=0);
	}

	/**
	 * Permet de r�cup�rer une chaine de caract�re repr�sentant la main du joueur
	 * 
	 * @return String repr�sentant la main du joueur
	 */
	@Override
    public String toString(){
       return ""+main;
    } 
}
