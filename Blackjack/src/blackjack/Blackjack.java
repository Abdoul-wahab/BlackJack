package blackjack;

import modèle.*;
import controleurs.*;
import java.awt.*;
import javax.swing.*;
import vues.*;

/**
 *<b>Classe Blackjack.</b>
 *<p>Cette classe permet de lancer la partie de Blackjack</p>
 *
 *@see Controleur
 *@see VueBlackjack
 *@see Joueur
 *@see JeuDeCartes
 *
 * @author 21606478, 21701844 et 21914280
 *
 */
public class Blackjack {

    /**
     * Méthode main pour lancer le programme Blackjack
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
    	//On initialise le joueur et la banque
        Joueur j1 = new Joueur("Joueur");
        Joueur banque = new Joueur("Banque");
        //On initialise un jeu de 52 cartes et on le remplit
        JeuDeCartes jeu = JeuFactory.creerJeu52();
        
        //On initialise la vue
        VueBlackjack vue = new VueBlackjack(j1, banque, jeu);
      
        //On initialise le controller
        Controleur controler = new Controleur(vue, j1, banque, jeu);  
        //Lancement de la partie de Blackjack
        controler.blackjack();
    	
    	
    }
    
}
