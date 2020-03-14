package mod�le;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

/**
 *<b>Classe Tas.</b>
 *<p>Cette classe d�crit un tas de cartes</p>
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
	 * @param autreTas qui sert � remplir le Tas
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
	 * Cette m�thode permet de r�cup�rer la premi�re carte au sommet du tas
	 * 
	 * @return Carte La premi�re carte au sommet du tas
	 */
	public Carte premiereCarte() {
		Carte premiere =(Carte)((LinkedList<Carte>) lesCartes).removeFirst(); 
		return premiere;
	}
	    
	/**
	 * Cette m�thode de savoir si le Tas ne poss�de plus aucune carte
	 * 
	 * @return bool�en indiquant si le tas est vide (vrai) ou non 
	 */
	public boolean estVide() {
		return lesCartes.isEmpty();
	}
	    
	/**
	 * Cette m�thode permet d'ajouter une carte sous le Tas
	 * 
	 * @param uneCarte a ajout� sous le tas
	 */
	public void ajouterCarte(Carte uneCarte) {
		lesCartes.add(uneCarte);
    }
	
	/**
	 * Cette m�thode permet d'ajouter toutes les cartes d'un tas dans la liste de cartes
	 * 
	 * @param tas dont les cartes sont ajout�es sous celles du tas appelant
	 */
	void ajouterTous(Tas tas){
		lesCartes.addAll(tas.lesCartes);
	}
	
	/**
	 * Cette m�thode permet de vider la liste de cartes du Tas
	 * 
	 */
	public void viderTas() {
		lesCartes = new LinkedList<Carte>();
	}

	
	/**
	 * Accesseur de la taille du Tas
	 * 
	 * @return un entier qui repr�sente le nombre de cartes du tas
	 */
	public int getTaille (){
		return lesCartes.size(); 
	}
    
	/**
	 * Une m�thode qui permet de r�cup�rer la valeur du Tas de carte en terme de jeu BlackJack
	 * 
	 * @return un entier d�crivant la valeur du Tas
	 */
    public int getValeurPaquet() {
        int res=0;
        for(Carte c : this.getLesCartes()) {
            res+=c.getValeur();
        }
        return res;
    }
	
	/**
	 * Permet de r�cup�rer une chaine de caract�re repr�sentant la Tas de carte
	 * 
	 * @return String repr�sentant les cartes du tas
	 */
	@Override
	 public  String toString() {		
	    return ""+lesCartes;
	}	

}
