package vues;

import javax.swing.*;

import modèle.*;

/**
 *<b>Classe VueBlackjack.</b>
 *<p>Cette classe permet de gérer toute la vue du jeu Blackjack</p>
 *
 *@see VuePaquetCache
 *@see VuePaquetVisible
 *@see JeuDeCartes
 *@see Joueur
 *@see JPanel
 *@see JButton
 *
 * @author 21606478, 21701844 et 21914280
 *
 */
public class VueBlackjack extends JFrame implements Ecouteur{
	
	/**
	 * La vue de la banque
	 */
    public VuePaquetCache vueBanque;
    /**
     * La vue du joueur humain
     */
    public VuePaquetVisible vueJoueur;
    /**
     * Le joueur humain
     */
    private Joueur joueur;
    /**
     * La banque ou le croupier
     */
    private Joueur banque;
    /**
     * Le jeu de cartes qui sera utilisé pendant tout le jeu
     */
    private JeuDeCartes jeu;
    private JPanel panel;
    /**
     * Le bouton permettant de piocher
     */
    private JButton piocher;
    /**
     * Le bouton "stand"
     */
    private JButton stand;

    /**
     * Constructeur de la VueBlackjack
     * 
     * @param joueur Le joueur humain
     * @param banque La banque
     * @param jeu	Le jeu de cartes
     */
    public VueBlackjack(Joueur joueur, Joueur banque, JeuDeCartes jeu) {
        this.joueur = joueur;
        this.banque = banque;
        this.jeu = jeu;
    }

    /**
     * Méthode permettant d'afficher l'ensemble de la vue du jeu
     */
    public void vueBJ() {
        VueBlackjack vue = this;
        // on crée une fenêtre dont le titre est "Blackjack"
        this.setTitle("Blackjack");
        // la fenêtre doit se fermer quand on clique sur la croix rouge
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.setLayout(null);

        //for(Carte c : j52.getLesCartes()) System.out.println(c);

        Tas mainJoueur = joueur.getMain();
        Tas mainBanque = banque.getMain();

        VuePaquetPioche paquet = new VuePaquetPioche(jeu);
        paquet.setBounds(10, 10, 300, 300);
        this.add(paquet);
        this.vueJoueur=new VuePaquetVisible(mainJoueur);
        this.vueJoueur.setBounds(400, 200, 900, 300);
        this.add(vueJoueur);

        this.vueBanque = new VuePaquetCache(mainBanque);
        this.vueBanque.setBounds(400, 40, 900, 300);
        this.add(vueBanque);

        this.piocher = creerBouton("Piocher");
        this.piocher.setBounds(250, 450, 300, 80);
        
        this.stand = creerBouton("Stand");
        this.stand.setBounds(650, 450, 300, 80);

        this.add(piocher);
        this.add(stand);
        
        
        this.setSize(1200, 800);

        // on demande d'attribuer une taille minimale à la fenêtre
        //  (juste assez pour voir tous les composants)
        //frame.pack();
        // on centre la fenêtre
        // on rend la fenêtre visible
        this.setVisible(true);

    }
    
    /**
     * Accesseur du bouton de pioche
     * 
     * @return JButton : le bouton "piocher"
     */
    public JButton getPiocher() {
    	return this.piocher;
    }
	
    /**
     * Accesseur du bouton "stand"
     * 
     * @return JButton : le bouton "stand"
     */
    public JButton getStand() {
    	return this.stand;
    }
    
    /**
     * Permet à la vue de récupérer la mise du joueur et de l'afficher
     * 
     * @param mise La nombre de jetons qui a été misé par le joueur
     */
	public void afficherMise(int mise) {
		vueJoueur.setMise(mise);
	}
	
	/**
	 * Permet de dire à la vue de retourner les cartes de la banque
	 */
	public void revelerBanque() {
		this.vueBanque.setRevele(true);
	}
	
	/**
	 * Permet de dire à la vue de cacher les cartes de la banque
	 */
	public void cacherBanque() {
		this.vueBanque.setRevele(false);
	}
       
	/**
	 * Permet de créer un bouton
	 * 
	 * @param label Chaine de caractères écrites dans le bouton
	 * @return JButton Le bouton crée
	 */
    public JButton creerBouton(String label) {
         JButton bouton = new JButton(label);
         //this.add(bouton);
         return bouton;
    }
    
    /**
     * Permet de mettre à jour la vue selon ce qui se passe dans le controleur
     */
    public void update() {
    	this.repaint();
    }

}
