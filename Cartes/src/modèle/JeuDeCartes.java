package modèle;

import java.util.LinkedList;
import java.util.List;

/**
 *<b>Classe JeuDeCartes.</b>
 *<p>Cette classe permettant de décrire un jeu de cartes</p>
 *
 *@see Tas
 *@see Carte
 *@see Jeu32
 *@see Jeu52
 *
 *@author 21606478, 21701844 et 21914280
 *
 */
public abstract class JeuDeCartes extends Tas {
	
    /**
     * Constructeur du jeu de cartes
     * 
     * @param nom du jeu
     */
	public JeuDeCartes(String nom){        
       super(nom);
    }
    /**
     * Méthode abstraite permettant de remplir un jeu de cartes
     * 
     */
    public abstract void remplirUnJeu(); 
   	
	/**
	 * 
	 * @return un entier: l'emplacement de coupe aléatoire parmi le tas de cartes.
	 */
	public int couper(){
		int val=0;
		for(int i=0; i<getTaille(); i++)
			if(Math.random()<0.5) val++;
	    return val;
	}
	/**
	 * 
	 * @return Tas: on coupe le tas de cartes du jeu appelant. Les c cartes du sommet du jeu appelant seront retournées dans un nouveau tas de cartes,
	 *  tandis que le jeu appelant sera composé des n-c cartes restantes. 
	 */
	private Tas diviser(){
		Tas resultat=new Tas();
        int c=couper();
        for(int i=0; i<c; i++)
            resultat.ajouterCarte(premiereCarte());
        return resultat;
    }
	/**
	 * 
	 * @param paquet mélange des deux tas de cartes (le tas appelant et celui nommé paquet)
	 */
	private void melangerCartes (Tas paquet){
        List<Carte> resultat=new LinkedList<Carte>();
        while(!paquet.estVide() && !estVide()){
            double p= ((double) paquet.getTaille())/((double) paquet.getTaille()+getTaille());
            if(Math.random()<p)
                resultat.add(paquet.premiereCarte());
            else
                resultat.add(premiereCarte());
        }                
        
        resultat.addAll(paquet.getLesCartes());
        resultat.addAll(this.getLesCartes());
        setLesCartes(resultat);
    }

	/**
	 * 
	 * @param n  le nombre de fois pù l'opération (diviser + melangerCartes) sur le jeu de cartes courant est répété
	 */
	public void battre(int n){
		for(int i=0; i<n; i++){
			Tas tmp=diviser();
			melangerCartes(tmp);
        }
                
    }
        
      
	
}
