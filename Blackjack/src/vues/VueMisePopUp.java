package vues;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *<b>Classe VueMisePopUp.</b>
 *<p>Cette classe permet d'afficher une pop-up permettant à l'utilisateur de choisir sa mise avant la partie</p>
 *
 *@see VuePopUp
 *
 * @author 21606478, 21701844 et 21914280
 *
 */
public class VueMisePopUp extends VuePopUp{
	
	/**
	 * Le nombre de jetons restants du joueur
	 */
	public int nbJetons;
	
	/**
	 * Constructeur de VueMisePopUp
	 * 
	 * @param message Le message à afficher dans la pop-up
	 * @param parentFrame Le JFrame parent du pop-up
	 * @param nbJetons Le nombre de jetons restants du joueur 
	 */
	public VueMisePopUp(String message, JFrame parentFrame, int nbJetons) {
		super(message, parentFrame);
		this.nbJetons = nbJetons;
	}
	
	/**
	 * Méthode permettant d'afficher la pop-up
	 * 
	 * @return Object : le choix du joueur
	 */
	public Object showPopUp() {
		//Tableau contenant tous les choix de mise, de la taille du nombre de jetons que possède le joueur
		String choix[] = new String[nbJetons];
		//On rajoute autant de choix dans le tableau que le joueur possède de jetons
		for(int i=1 ; i<nbJetons+1 ; i++) choix[i-1]=""+i;
		///On affiche la pop-up et on retourne le choix
		return this.showInputDialog(parentFrame, "Vous avez "+nbJetons+" jetons\n"+message, "Choix de la mise !", JOptionPane.INFORMATION_MESSAGE, null, choix, choix[0]);
	}

}
