package modèle;

/**
 *<b>Classe JeuDeCartes.</b>
 *<p>Cette classe permettant de décrire un jeu de 32 cartes</p>
 *
 *@see JeuDeCartes
 *@see JeuFactory
 *
 *@author 21606478, 21701844 et 21914280
 *
 */
public class Jeu32 extends JeuDeCartes{
    
	/**
     * Constructeur du jeu de 32 cartes
     * 
     * @param nom : Le nom du de cartes
     */
    public Jeu32(String nom) {        
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
            for(int j=5; j<Hauteur.values().length;j++)
                ajouterCarte(new Carte(Hauteur.values()[j],Symbole.values()[i]));
        }
    }
}
