package controleurs;

import mod�le.*;
import vues.*;
import javax.swing.*;

import java.awt.event.*;
import java.util.ArrayList;

/**
 *<b>Classe Controleur.</b>
 *<p>Cette classe impl�mente le jeu, et permet de g�rer les diff�rentes situations pouvant �tre rencontr�es, ainsi que les interactions avec la vue</p>
 *
 *Elle h�rite de la classe Ecoutable, pour pouvoir �tre �cout�e par la vue et la mettre � jour
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
	 * La vue swing avec laquelle int�ragit l'utilisateur
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
	 * @param jeu Le jeu de 52 cartes utilis� pendant la partie
	 * 
	 */
    public Controleur(VueBlackjack view, Joueur joueur, Joueur banque, JeuDeCartes jeu) {
        this.view=view;
        this.joueur=joueur;
        this.banque=banque;
        this.jeu=jeu;
        ecouteurs = new ArrayList<Ecouteur>();
        //La vue est ajout�e au concouteur du controleur pour que le controleur puisse notifier la vue
        ajouterEcouteur(view);
    }
    
    public void blackjack() {
    	view.vueBJ();
    	initialiserActionBoutonPiocher();
    	initialiserActionBoutonStand();
        initialiserPartie();
    }
    
    /**
	 * Cette m�thode permet d'initialiser la partie
	 * 
	 */
    public void initialiserPartie() {
    	//On m�lange les cartes
        this.jeu.battre(10);
        
        Object choix;
        //On affiche une pop-up permettant au joueur de choisir sa mise
        choix = (new VueMisePopUp("Combien de jetons souhaitez-vous miser ?", this.view, joueur.getNbJetons())).showPopUp();
        //Tant que le r�sultat est �gale � null (si on appuie sur le bouton cancel ou on ferme la pop-up), on r�-affiche la pop (le joueur doit choisir une mise !!) 
        while(choix==null) choix = (new VueMisePopUp("Combien de jetons souhaitez-vous miser ?", this.view, joueur.getNbJetons())).showPopUp();
        
        int mise = Integer.parseInt((String)choix);
        //On r�cup�re la mise dans le controleur
        setMise(mise);
        //On retire la mise du compte de jetons du joueur
        joueur.retirerMise(mise);
        
        this.view.afficherMise(mise);
        
        //On distribue 2 cartes au joueur et une � la banque
        this.joueur.prendreCarte(jeu.premiereCarte());
        this.banque.prendreCarte(jeu.premiereCarte());
        this.joueur.prendreCarte(jeu.premiereCarte());
        notifier();
        //On v�rifie si le joueur n'a pas un Blackjack
        isJoueurHaveBlackjack();
        
    }
    
    /**
	 * Cette m�thode permet de r�initialiser la partie, lorsque le joueur choisi de rejouer � la fin d'une partie
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
        //Tant que le r�sultat est �gale � null (si on appuie sur le bouton cancel ou on ferme la pop-up), on r�-affiche la pop (le joueur doit choisir une mise !!) 
        while(choix==null) choix = (new VueMisePopUp("Combien de jetons souhaitez-vous miser ?", this.view, joueur.getNbJetons())).showPopUp();
        
        int mise = Integer.parseInt((String)choix);
        //On r�cup�re la mise dans le controleur
        setMise(mise);
        //On retire la mise du compte de jetons du joueur
        joueur.retirerMise(mise);
        
        //On affiche la mise dans la vue
        this.view.afficherMise(mise);
        
        //On retourne les cartes de la banque
        view.cacherBanque();
        
    	//On re-remplit le jeu de 52 cartes
    	jeu.remplirUnJeu();
    	//On m�lange les cartes
    	this.jeu.battre(10);
    	//On vide la main du joueur et de la banque
    	this.joueur.viderMain();
    	this.banque.viderMain();
    	//On distribue 2 cartes au joueur et une � la banque
    	this.joueur.prendreCarte(jeu.premiereCarte());
        this.banque.prendreCarte(jeu.premiereCarte());
        this.joueur.prendreCarte(jeu.premiereCarte());
        
        notifier();
        //On v�rifie si le joueur n'a pas un Blackjack
        isJoueurHaveBlackjack();
    }
    
    /**
	 * Cette m�thode permet d'initialiser une partie avec un cas de Blackjack pour le joueur, pour tester
	 * 
	 */
    public void testCasBlackJack() {
    	//On remplit la main du joueur avec un BlackJack
        this.joueur.remplirMainAvecBlackJack();
        this.banque.prendreCarte(jeu.premiereCarte());
      //On v�rifie si le joueur n'a pas un Blackjack
        isJoueurHaveBlackjack();
    }
    
    /**
	 * Cette m�thode permet d'initialiser une partie avec un cas de Blackjack pour le joueur et la banque, pour tester
	 * 
	 */
    public void testDoubleCasBlackJack() {
    	//On remplit la main du joueur et de la banque avec un BlackJack
        this.joueur.remplirMainAvecBlackJack();
        this.banque.remplirMainAvecBlackJack();
      //On v�rifie si le joueur n'a pas un Blackjack
        isJoueurHaveBlackjack();
    }
    
    
    
    /**
	 * Cette m�thode permet d'initialiser le bouton de pioche et de l'afficher
	 * 
	 */
    public void initialiserActionBoutonPiocher() {
    	//On cr�er le bouton de pioche et on lui ajoute un listener
    	this.view.getPiocher().addActionListener((event)-> piocherCarte());
    }
    
    public void piocherCarte() {
        //Le joueur pioche une carte
        joueur.prendreCarte(jeu.premiereCarte());
        //On notifie les observateurs pour afficher la nouvelle main du joueur
        notifier();
        //On v�rifie si la main du joueur a d�pass� 21
        testValeurMainJoueur();
    }
    
    /**
	 * Cette m�thode permet d'initialiser le bouton "stand" et de l'afficher
	 * 
	 */
    public void initialiserActionBoutonStand() {
    	//On cr�er le bouton "stand" et on lui ajoute un listener
    	this.view.getStand().addActionListener((event)-> actionStand());
    }   
    
    public void actionStand() {
    	//On fait piocher la banque
    	piocheBanque();
    	//On retourne les cartes de la banque
    	view.revelerBanque();
    	 //On notifie les observateurs pour afficher la nouvelle main de la banque
    	notifier();
    	//On compare la valeur des mains du joueur et de la banque pour voir qui a gagn�
    	comparaisonValeurPaquet();
    }
   

    /**
	 * Cette m�thode permet de g�rer la pioche de la banque
	 * 
	 * 
	 */
    public void piocheBanque() {
        //Tant que la valeur de sa main est inf�rieur � 17, la banque pioche
        while(this.banque.getValeurMain() < 17) banque.prendreCarte(jeu.premiereCarte());
    }
    
    /**
   	 * Cette m�thode permet de traiter le choix de l'utilisateur sur la pop-up de fin de partie (relancer une partie ou quitter)
   	 * 
   	 * @param choix Un entier d�crivant le choix de l'utilisateur
   	 * 
   	 * 
   	 */
    public void traiterChoix(int choix) {
    	//Si le choix est �gal � 0, l'utilisateur veut continuer � jouer
    	if(choix==0) {
    		//Une r�initialise une nouvelle partie
    		reinitialiserPartie();
    		//On notifie la vue
    		notifier();
    	}
    	//Sinon, le joueur a choisi d'arr�ter, ou � fermer la pop-up. Alors on arr�te l'ex�cution de l'application
    	else System.exit(0);
    }
    
    /**
   	 * Cette m�thode permet v�rifier si le joueur � un BlackJack et d'agir en cons�quence
   	 * 
   	 * 
   	 */
    public void isJoueurHaveBlackjack() {
    	int choix;
    	//Si le joueur a un BlackJack
        if(joueur.haveBlackJack())
        {
        	//On fait piocher la banque, au cas où elle obtient elle aussi un BlackJack
        	this.piocheBanque();
        	
        	//Si la banque a un BlackJack
        	if(banque.haveBlackJack()) {
        		//Alors la banque et le joueur ont un BlackJack. Il y a �galit�
        		joueur.ajouterJetons(miseJoueur);
        		//Une pop-up apparait pour laisser au joueur le choix de rejouer ou non
            	choix = (new VueOptionPopUp("Vous et la banque avez un BlackJack !\nÉgalit� :| Vous r�cup�rez votre mise", this.view)).showPopUp();
            	//On traite le choix du joueur
            	traiterChoix(choix);
        	}
        	//Sinon
        	else {
        		//Alors seul le joueur a un BlackJack, le joueur a gagn�
        		joueur.ajouterJetons(miseJoueur+(int)Math.floor(miseJoueur*1.5));
        		//Une pop-up apparait pour laisser au joueur le choix de rejouer ou non
            	choix = (new VueOptionPopUp("Vous avez fait une BlackJack !\nVous avez gagn� 1.5 fois votre mise !! :)", this.view)).showPopUp();
            	//On traite le choix du joueur
            	traiterChoix(choix);
        	}
        	
        }
    }
    
    /**
   	 * Cette m�thode permet de v�rifier la valeur de la main du joueur, pour d�tecter la pr�sence d'un Blackjack ou si la valeur de sa main a d�pass� 21
   	 * 
   	 * 
   	 */
    public void testValeurMainJoueur() {
        int choix;
        //Si la valeur de la main du joueur est sup�rieur � 21
        if(this.joueur.getValeurMain() > 21)
        {
        	//Alors le joueur a perdu
        	//Une pop-up apparait pour laisser au joueur le choix de rejouer ou non
        	choix = (new VueOptionPopUp("Vous avez d�pass� 21.\nVous avez perdu votre mise... :(", this.view)).showPopUp();
        	//On traite le choix du joueur
        	traiterChoix(choix);
        }
    }
    
    /**
   	 * Cette m�thode permet de comparer la valeur de la main du joueur et de la main de banque, en fin de partie
   	 * 
   	 * 
   	 */
    public void comparaisonValeurPaquet() {
    	int choix;
    	
    	//Si la banque a un BlackJack
    	if(this.banque.haveBlackJack()) {
    		//Alors la banque a gagn�, le joueur a perdu
    		//Une pop-up apparait pour laisser au joueur le choix de rejouer ou non
        	choix = (new VueOptionPopUp("La banque a un BlackJack !\nVous avez perdu votre mise... :(", this.view)).showPopUp();
        	//On traite le choix du joueur
        	traiterChoix(choix);
    	}
    	//Si la valeur de la main de la banque est sup�rieur � 21
    	else if(this.banque.getValeurMain()>21)
        {
    		//Alors la banque a perdu, le joueur a gagn�
    		joueur.ajouterJetons(miseJoueur*2);
    		//Une pop-up apparait pour laisser au joueur le choix de rejouer ou non
        	choix = (new VueOptionPopUp("La banque a d�pass� 21.\nVous avez gagn� 1 fois votre mise! :)", this.view)).showPopUp();
        	//On traite le choix du joueur
        	traiterChoix(choix);
        }
    	//Si la valeur de la main de la banque et du joueur sont �gales
        else if(this.joueur.getValeurMain()==this.banque.getValeurMain())
        {
        	//Alors il y a �galit� entre la banque est le joueur
        	joueur.ajouterJetons(miseJoueur);
        	//Une pop-up apparait pour laisser au joueur le choix de rejouer ou non
        	choix = (new VueOptionPopUp("Vous avez fait le m�me score que la banque\n�galit� :| Vous r�cup�rez votre mise", this.view)).showPopUp();
        	//On traite le choix du joueur
        	traiterChoix(choix);
        }
    	//Si la valeur de la main du joueur est sup�rieur � celle de la banque
        else if(this.joueur.getValeurMain()>this.banque.getValeurMain())
        {
        	//Alors le joueur a gagn�, la banque a perdu
        	joueur.ajouterJetons(miseJoueur*2);
        	//Une pop-up apparait pour laisser au joueur le choix de rejouer ou non
        	choix = (new VueOptionPopUp("Vous avez fait un meilleur score que la banque.\nVous avez gagn� 1 fois votre mise! :)", this.view)).showPopUp();
        	//On traite le choix du joueur
        	traiterChoix(choix);
        }
    	//Si la valeur de la main de la banque est sup�rieur � celle du joueur
        else if(this.joueur.getValeurMain()<this.banque.getValeurMain())
        {
        	//Alors la banque a gagn�, le joueur a perdu
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
     * Cette m�thode permet d'ajouter un observateur � la liste des observateurs qui �coute le controleur
     * 
     * @param ecouteur L'observateur � ajouter
     * 
     */
    public void ajouterEcouteur(Ecouteur ecouteur) {
    	ecouteurs.add(ecouteur);
    }
    
    /**
     * Cette m�thode permet de retirer un observateur � la liste des observateurs qui �coute le controleur
     * 
     * @param ecouteur L'observateur � retirer
     * 
     */
    public void retirerEcouteur(Ecouteur ecouteur) {
    	ecouteurs.remove(ecouteur);
    }
    
    
    /**
     * Cette m�thode permet de notifier l'ensemble des observateurs qui �coute le controleur d'un changement 
     * 
     */
    public void notifier() {
    	//On lance la m�thode update sur tous les �couteurs pour les mettre � jour
    	for(Ecouteur e : ecouteurs) e.update();
    }
    
}
