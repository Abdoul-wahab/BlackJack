package modèle;

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
     * Le nombre de jeton que possède le joueur (pour la mise)
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
	 * Une méthode qui permet de vérifier si la main d'un joueur est vide
	 * 
	 * @return vrai si le joueur n'a plus de cartes en main
	 */
	public boolean estMainVide(){
		return main.estVide();
    }
	
	/**
	 * Permet de récupérer le nombre de carte dans la main du joueur
	 * 
	 * @return un entier: le nombre de cartes dans la main du joueur
	 */
	public int getNbCartes(){
        return main.getTaille();
    }
	
	/**
	 * Permet d'ajouter une carte dans la main du joueur
	 * 
	 * @param carte à ajouter en dessous de la main du joueur
	 */
	public void prendreCarte(Carte carte){
        main.ajouterCarte(carte);
    }
	
	/**
	 * Permet d'ajouter un ensemble de carte dans la main du joueur
	 * 
	 * @param tas dont les cartes sont à ajouter en dessous de la main du joueur
	 */
	public void prendreTous(Tas tas){
        main.ajouterTous(tas);
    }
	
	/**
	 * Cette méthode permet de vider la main du joueur de toutes ses cartes
	 * 
	 */
	public void viderMain() {
		this.main.viderTas();
	}
	
	/**
	 * Cette méthode permet de récupérer la valeur de la main du joueur dans un Blackjack
	 * 
	 * @return un entier décrivant la valeur de la main du joueur
	 * 
	 */
	public int getValeurMain() {
		return this.main.getValeurPaquet();
	}
	
	/**
	 * Cette méthode permet de vérifier si le joueur a un Blackjack dans sa main
	 * 
	 * @return booléen : vrai si le joueur a un Blackjack
	 * 
	 */
	public boolean haveBlackJack() {
		return this.getValeurMain() == 21 && this.getNbCartes()==2;
	}
	
	/**
	 * Permet de remplir la main du joueur avec un Blackjack (méthode de test)
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
	 * Cette méthode permet de retirer la mise du joueur à son nombre de jetons
	 * 
	 * @param mise du joueur
	 */
	public void retirerMise(int mise) {
		this.nbJetons-=mise;
	}
	
	/**
	 * Cette méthode permet d'ajouter des jetons au nombre de jetons du joueur
	 * 
	 * @param nbJetons : Le nombre de jetons à ajouter
	 */
	public void ajouterJetons(int nbJetons) {
		this.nbJetons+=nbJetons;
	}
	
	/**
	 * Permet de vérifier si le joueur a encore des jetons
	 * 
	 * @return vrai si il lui reste des jetons
	 */
	public boolean resteJetons() {
		return !(nbJetons<=0);
	}

	/**
	 * Permet de récupérer une chaine de caractère représentant la main du joueur
	 * 
	 * @return String représentant la main du joueur
	 */
	@Override
    public String toString(){
       return ""+main;
    } 
}
