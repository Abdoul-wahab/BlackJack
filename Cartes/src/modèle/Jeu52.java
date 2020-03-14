package modèle;

/**
 *<b>Classe JeuDeCartes.</b>
 *<p>Cette classe permettant de décrire un jeu de 52 cartes</p>
 *
 *@see JeuDeCartes
 *@see JeuFactory
 *
 *@author 21606478, 21701844 et 21914280
 *
 */
public class Jeu52 extends JeuDeCartes{
	
    /**
     * Constructeur du jeu de 52 cartes
     * 
     * @param nom : Le nom du de cartes
     */
    public Jeu52(String nom) {        
       super(nom);
    }
    
    /**
     * Permet de remplir le jeu de 52 cartes
     * 
     */
    @Override										
    public void remplirUnJeu(){
    	viderTas();
        for(int i=0; i<Symbole.values().length; i++){
            for(int j=0; j<Hauteur.values().length;j++)
                ajouterCarte(new Carte(Hauteur.values()[j],Symbole.values()[i]));
        }
    }
}
