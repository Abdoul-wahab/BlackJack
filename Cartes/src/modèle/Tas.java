package modèle;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

/**
 *<b>Classe Tas.</b>
 *<p>Cette classe décrit un tas de cartes</p>
 *
 *@see Carte
 *@see Couleur
 *
 *@author 21606478, 21701844 et 21914280
 */
public class Tas {
	/**
	 * Une liste des cartes du tas
	 */
	private List<Carte> lesCartes;
	/**
	 * Le nom du tas
	 */
    private final String label;
    
	/**
	 * Constructeur de Tas
	 */
	public Tas() {
		label = "";
		lesCartes = new LinkedList<Carte>();
	}
	
	/**
	 * Constructeur de Tas avec un nom
	 * 
	 * @param label du tas
	 */
	public Tas(String label) {
		this.label = label;		
		lesCartes = new LinkedList<Carte>();
	}
	
	/**
	 * Constructeur de Tas avec un autre Tas
	 * Permet de d'initialiser un tas avec les cartes d'un tas
	 * 
	 * @param autreTas qui sert à remplir le Tas
	 */
	public Tas(Tas autreTas){
		label = autreTas.label;
        lesCartes=new LinkedList<Carte>();
        for(Carte carte:autreTas.lesCartes)
            lesCartes.add(carte);		
	}

	/**
	 * Accesseur de la liste de cartes du Tas
	 * 
	 * @return la liste de cartes
	 */
	public List<Carte> getLesCartes() {
		return lesCartes;
	}
	
	/**
	 * Accesseur du nom du Tas
	 * 
	 * @return label Le nom du Tas
	 */
	public String getLabel() {
		return label;
	}
	
	/**
	 * Mutateur de la liste de cartes du Tas
	 * 
	 * @param lesCartes permettant de modifier la liste des cartes
	 */
	public void setLesCartes(List<Carte> lesCartes){
        this.lesCartes = lesCartes;
    }
	
	/**
	 * Cette méthode permet de récupérer la première carte au sommet du tas
	 * 
	 * @return Carte La première carte au sommet du tas
	 */
	public Carte premiereCarte() {
		Carte premiere =(Carte)((LinkedList<Carte>) lesCartes).removeFirst(); 
		return premiere;
	}
	    
	/**
	 * Cette méthode de savoir si le Tas ne possède plus aucune carte
	 * 
	 * @return booléen indiquant si le tas est vide (vrai) ou non 
	 */
	public boolean estVide() {
		return lesCartes.isEmpty();
	}
	    
	/**
	 * Cette méthode permet d'ajouter une carte sous le Tas
	 * 
	 * @param uneCarte a ajouté sous le tas
	 */
	public void ajouterCarte(Carte uneCarte) {
		lesCartes.add(uneCarte);
    }
	
	/**
	 * Cette méthode permet d'ajouter toutes les cartes d'un tas dans la liste de cartes
	 * 
	 * @param tas dont les cartes sont ajoutées sous celles du tas appelant
	 */
	void ajouterTous(Tas tas){
		lesCartes.addAll(tas.lesCartes);
	}
	
	/**
	 * Cette méthode permet de vider la liste de cartes du Tas
	 * 
	 */
	public void viderTas() {
		lesCartes = new LinkedList<Carte>();
	}

	
	/**
	 * Accesseur de la taille du Tas
	 * 
	 * @return un entier qui représente le nombre de cartes du tas
	 */
	public int getTaille (){
		return lesCartes.size(); 
	}
    
	/**
	 * Une méthode qui permet de récupérer la valeur du Tas de carte en terme de jeu BlackJack
	 * 
	 * @return un entier décrivant la valeur du Tas
	 */
    public int getValeurPaquet() {
        int res=0;
        for(Carte c : this.getLesCartes()) {
            res+=c.getValeur();
        }
        return res;
    }
	
	/**
	 * Permet de récupérer une chaine de caractère représentant la Tas de carte
	 * 
	 * @return String représentant les cartes du tas
	 */
	@Override
	 public  String toString() {		
	    return ""+lesCartes;
	}	

}
