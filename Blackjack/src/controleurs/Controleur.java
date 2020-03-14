package controleurs;

import modèle.*;
import vues.*;
import javax.swing.*;

import java.awt.event.*;
import java.util.ArrayList;

/**
 *<b>Classe Controleur.</b>
 *<p>Cette classe implémente le jeu, et permet de gérer les différentes situations pouvant être rencontrées, ainsi que les interactions avec la vue</p>
 *
 *Elle hérite de la classe Ecoutable, pour pouvoir être écoutée par la vue et la mettre à jour
 *
 *@see VueBlackjack
 *@see Joueur
 *@see JeuDeCartes
 *@see Ecoutable
 *
 *@author 21606478, 21701844 et 21914280
 *
 */
public class Controleur extends Ecoutable{
    
	/**
	 * La vue swing avec laquelle intéragit l'utilisateur
	 */
    public VueBlackjack view;
    /**
     * Le joueur humain
     */
    public Joueur joueur;
    /**
     * La banque
     */
    public Joueur banque;
    /**
     * Le jeu de 52 cartes
     */ 
    public JeuDeCartes jeu;
    /**
     * La mise du joueur
     */
    public int miseJoueur;
    /**
     * La liste des observateurs du controleur
     */
    public ArrayList<Ecouteur> ecouteurs;
    
    /**
	 * Constructeur du controleur.
	 * 
	 * @param view Pour que la classe est connaissance de la vue et puisse la notifier
	 * @param joueur Le joueur humain
	 * @param banque Le croupier
	 * @param jeu Le jeu de 52 cartes utilisé pendant la partie
	 * 
	 */
    public Controleur(VueBlackjack view, Joueur joueur, Joueur banque, JeuDeCartes jeu) {
        this.view=view;
        this.joueur=joueur;
        this.banque=banque;
        this.jeu=jeu;
        ecouteurs = new ArrayList<Ecouteur>();
        //La vue est ajoutée au concouteur du controleur pour que le controleur puisse notifier la vue
        ajouterEcouteur(view);
    }
    
    public void blackjack() {
    	view.vueBJ();
    	initialiserActionBoutonPiocher();
    	initialiserActionBoutonStand();
        initialiserPartie();
    }
    
    /**
	 * Cette méthode permet d'initialiser la partie
	 * 
	 */
    public void initialiserPartie() {
    	//On mélange les cartes
        this.jeu.battre(10);
        
        Object choix;
        //On affiche une pop-up permettant au joueur de choisir sa mise
        choix = (new VueMisePopUp("Combien de jetons souhaitez-vous miser ?", this.view, joueur.getNbJetons())).showPopUp();
        //Tant que le résultat est égale à null (si on appuie sur le bouton cancel ou on ferme la pop-up), on ré-affiche la pop (le joueur doit choisir une mise !!) 
        while(choix==null) choix = (new VueMisePopUp("Combien de jetons souhaitez-vous miser ?", this.view, joueur.getNbJetons())).showPopUp();
        
        int mise = Integer.parseInt((String)choix);
        //On récupère la mise dans le controleur
        setMise(mise);
        //On retire la mise du compte de jetons du joueur
        joueur.retirerMise(mise);
        
        this.view.afficherMise(mise);
        
        //On distribue 2 cartes au joueur et une à la banque
        this.joueur.prendreCarte(jeu.premiereCarte());
        this.banque.prendreCarte(jeu.premiereCarte());
        this.joueur.prendreCarte(jeu.premiereCarte());
        notifier();
        //On vérifie si le joueur n'a pas un Blackjack
        isJoueurHaveBlackjack();
        
    }
    
    /**
	 * Cette méthode permet de réinitialiser la partie, lorsque le joueur choisi de rejouer à la fin d'une partie
	 * 
	 */
    public void reinitialiserPartie() {
    	
    	//Si le joueur n'a plus de jetons
    	if(!joueur.resteJetons()) {
    		//On affiche une pop-up pour avertir l'utilisateur
    		JOptionPane.showMessageDialog(this.view, "Vous n'avez plus de jetons", "Fin du jeu", JOptionPane.WARNING_MESSAGE);
    		//On ferme l'application
    		System.exit(0);
    	}
    	
    	Object choix;
    	
    	//On affiche une pop-up permettant au joueur de choisir sa mise
        choix = (new VueMisePopUp("Combien de jetons souhaitez-vous miser ?", this.view, joueur.getNbJetons())).showPopUp();
        //Tant que le résultat est égale à null (si on appuie sur le bouton cancel ou on ferme la pop-up), on ré-affiche la pop (le joueur doit choisir une mise !!) 
        while(choix==null) choix = (new VueMisePopUp("Combien de jetons souhaitez-vous miser ?", this.view, joueur.getNbJetons())).showPopUp();
        
        int mise = Integer.parseInt((String)choix);
        //On récupère la mise dans le controleur
        setMise(mise);
        //On retire la mise du compte de jetons du joueur
        joueur.retirerMise(mise);
        
        //On affiche la mise dans la vue
        this.view.afficherMise(mise);
        
        //On retourne les cartes de la banque
        view.cacherBanque();
        
    	//On re-remplit le jeu de 52 cartes
    	jeu.remplirUnJeu();
    	//On mélange les cartes
    	this.jeu.battre(10);
    	//On vide la main du joueur et de la banque
    	this.joueur.viderMain();
    	this.banque.viderMain();
    	//On distribue 2 cartes au joueur et une à la banque
    	this.joueur.prendreCarte(jeu.premiereCarte());
        this.banque.prendreCarte(jeu.premiereCarte());
        this.joueur.prendreCarte(jeu.premiereCarte());
        
        notifier();
        //On vérifie si le joueur n'a pas un Blackjack
        isJoueurHaveBlackjack();
    }
    
    /**
	 * Cette méthode permet d'initialiser une partie avec un cas de Blackjack pour le joueur, pour tester
	 * 
	 */
    public void testCasBlackJack() {
    	//On remplit la main du joueur avec un BlackJack
        this.joueur.remplirMainAvecBlackJack();
        this.banque.prendreCarte(jeu.premiereCarte());
      //On vérifie si le joueur n'a pas un Blackjack
        isJoueurHaveBlackjack();
    }
    
    /**
	 * Cette méthode permet d'initialiser une partie avec un cas de Blackjack pour le joueur et la banque, pour tester
	 * 
	 */
    public void testDoubleCasBlackJack() {
    	//On remplit la main du joueur et de la banque avec un BlackJack
        this.joueur.remplirMainAvecBlackJack();
        this.banque.remplirMainAvecBlackJack();
      //On vérifie si le joueur n'a pas un Blackjack
        isJoueurHaveBlackjack();
    }
    
    
    
    /**
	 * Cette méthode permet d'initialiser le bouton de pioche et de l'afficher
	 * 
	 */
    public void initialiserActionBoutonPiocher() {
    	//On créer le bouton de pioche et on lui ajoute un listener
    	this.view.getPiocher().addActionListener((event)-> piocherCarte());
    }
    
    public void piocherCarte() {
        //Le joueur pioche une carte
        joueur.prendreCarte(jeu.premiereCarte());
        //On notifie les observateurs pour afficher la nouvelle main du joueur
        notifier();
        //On vérifie si la main du joueur a dépassé 21
        testValeurMainJoueur();
    }
    
    /**
	 * Cette méthode permet d'initialiser le bouton "stand" et de l'afficher
	 * 
	 */
    public void initialiserActionBoutonStand() {
    	//On créer le bouton "stand" et on lui ajoute un listener
    	this.view.getStand().addActionListener((event)-> actionStand());
    }   
    
    public void actionStand() {
    	//On fait piocher la banque
    	piocheBanque();
    	//On retourne les cartes de la banque
    	view.revelerBanque();
    	 //On notifie les observateurs pour afficher la nouvelle main de la banque
    	notifier();
    	//On compare la valeur des mains du joueur et de la banque pour voir qui a gagné
    	comparaisonValeurPaquet();
    }
   

    /**
	 * Cette méthode permet de gérer la pioche de la banque
	 * 
	 * 
	 */
    public void piocheBanque() {
        //Tant que la valeur de sa main est inférieur à 17, la banque pioche
        while(this.banque.getValeurMain() < 17) banque.prendreCarte(jeu.premiereCarte());
    }
    
    /**
   	 * Cette méthode permet de traiter le choix de l'utilisateur sur la pop-up de fin de partie (relancer une partie ou quitter)
   	 * 
   	 * @param choix Un entier décrivant le choix de l'utilisateur
   	 * 
   	 * 
   	 */
    public void traiterChoix(int choix) {
    	//Si le choix est égal à 0, l'utilisateur veut continuer à jouer
    	if(choix==0) {
    		//Une réinitialise une nouvelle partie
    		reinitialiserPartie();
    		//On notifie la vue
    		notifier();
    	}
    	//Sinon, le joueur a choisi d'arrêter, ou à fermer la pop-up. Alors on arrête l'exécution de l'application
    	else System.exit(0);
    }
    
    /**
   	 * Cette méthode permet vérifier si le joueur à un BlackJack et d'agir en conséquence
   	 * 
   	 * 
   	 */
    public void isJoueurHaveBlackjack() {
    	int choix;
    	//Si le joueur a un BlackJack
        if(joueur.haveBlackJack())
        {
        	//On fait piocher la banque, au cas oÃ¹ elle obtient elle aussi un BlackJack
        	this.piocheBanque();
        	
        	//Si la banque a un BlackJack
        	if(banque.haveBlackJack()) {
        		//Alors la banque et le joueur ont un BlackJack. Il y a égalité
        		joueur.ajouterJetons(miseJoueur);
        		//Une pop-up apparait pour laisser au joueur le choix de rejouer ou non
            	choix = (new VueOptionPopUp("Vous et la banque avez un BlackJack !\nÃ‰galité :| Vous récupérez votre mise", this.view)).showPopUp();
            	//On traite le choix du joueur
            	traiterChoix(choix);
        	}
        	//Sinon
        	else {
        		//Alors seul le joueur a un BlackJack, le joueur a gagné
        		joueur.ajouterJetons(miseJoueur+(int)Math.floor(miseJoueur*1.5));
        		//Une pop-up apparait pour laisser au joueur le choix de rejouer ou non
            	choix = (new VueOptionPopUp("Vous avez fait une BlackJack !\nVous avez gagné 1.5 fois votre mise !! :)", this.view)).showPopUp();
            	//On traite le choix du joueur
            	traiterChoix(choix);
        	}
        	
        }
    }
    
    /**
   	 * Cette méthode permet de vérifier la valeur de la main du joueur, pour détecter la présence d'un Blackjack ou si la valeur de sa main a dépassé 21
   	 * 
   	 * 
   	 */
    public void testValeurMainJoueur() {
        int choix;
        //Si la valeur de la main du joueur est supérieur à 21
        if(this.joueur.getValeurMain() > 21)
        {
        	//Alors le joueur a perdu
        	//Une pop-up apparait pour laisser au joueur le choix de rejouer ou non
        	choix = (new VueOptionPopUp("Vous avez dépassé 21.\nVous avez perdu votre mise... :(", this.view)).showPopUp();
        	//On traite le choix du joueur
        	traiterChoix(choix);
        }
    }
    
    /**
   	 * Cette méthode permet de comparer la valeur de la main du joueur et de la main de banque, en fin de partie
   	 * 
   	 * 
   	 */
    public void comparaisonValeurPaquet() {
    	int choix;
    	
    	//Si la banque a un BlackJack
    	if(this.banque.haveBlackJack()) {
    		//Alors la banque a gagné, le joueur a perdu
    		//Une pop-up apparait pour laisser au joueur le choix de rejouer ou non
        	choix = (new VueOptionPopUp("La banque a un BlackJack !\nVous avez perdu votre mise... :(", this.view)).showPopUp();
        	//On traite le choix du joueur
        	traiterChoix(choix);
    	}
    	//Si la valeur de la main de la banque est supérieur à 21
    	else if(this.banque.getValeurMain()>21)
        {
    		//Alors la banque a perdu, le joueur a gagné
    		joueur.ajouterJetons(miseJoueur*2);
    		//Une pop-up apparait pour laisser au joueur le choix de rejouer ou non
        	choix = (new VueOptionPopUp("La banque a dépassé 21.\nVous avez gagné 1 fois votre mise! :)", this.view)).showPopUp();
        	//On traite le choix du joueur
        	traiterChoix(choix);
        }
    	//Si la valeur de la main de la banque et du joueur sont égales
        else if(this.joueur.getValeurMain()==this.banque.getValeurMain())
        {
        	//Alors il y a égalité entre la banque est le joueur
        	joueur.ajouterJetons(miseJoueur);
        	//Une pop-up apparait pour laisser au joueur le choix de rejouer ou non
        	choix = (new VueOptionPopUp("Vous avez fait le même score que la banque\négalité :| Vous récupérez votre mise", this.view)).showPopUp();
        	//On traite le choix du joueur
        	traiterChoix(choix);
        }
    	//Si la valeur de la main du joueur est supérieur à celle de la banque
        else if(this.joueur.getValeurMain()>this.banque.getValeurMain())
        {
        	//Alors le joueur a gagné, la banque a perdu
        	joueur.ajouterJetons(miseJoueur*2);
        	//Une pop-up apparait pour laisser au joueur le choix de rejouer ou non
        	choix = (new VueOptionPopUp("Vous avez fait un meilleur score que la banque.\nVous avez gagné 1 fois votre mise! :)", this.view)).showPopUp();
        	//On traite le choix du joueur
        	traiterChoix(choix);
        }
    	//Si la valeur de la main de la banque est supérieur à celle du joueur
        else if(this.joueur.getValeurMain()<this.banque.getValeurMain())
        {
        	//Alors la banque a gagné, le joueur a perdu
        	//Une pop-up apparait pour laisser au joueur le choix de rejouer ou non
        	choix = (new VueOptionPopUp("Vous avez fait un moins bon score que la banque\nVous avez perdu votre mise... :(", this.view)).showPopUp();
        	//On traite le choix du joueur
        	traiterChoix(choix);
        }
    }
    
    public void setMise(int mise) {
    	miseJoueur=mise;
    }
    
    /**
     * Cette méthode permet d'ajouter un observateur à la liste des observateurs qui écoute le controleur
     * 
     * @param ecouteur L'observateur à ajouter
     * 
     */
    public void ajouterEcouteur(Ecouteur ecouteur) {
    	ecouteurs.add(ecouteur);
    }
    
    /**
     * Cette méthode permet de retirer un observateur à la liste des observateurs qui écoute le controleur
     * 
     * @param ecouteur L'observateur à retirer
     * 
     */
    public void retirerEcouteur(Ecouteur ecouteur) {
    	ecouteurs.remove(ecouteur);
    }
    
    
    /**
     * Cette méthode permet de notifier l'ensemble des observateurs qui écoute le controleur d'un changement 
     * 
     */
    public void notifier() {
    	//On lance la méthode update sur tous les écouteurs pour les mettre à jour
    	for(Ecouteur e : ecouteurs) e.update();
    }
    
}
