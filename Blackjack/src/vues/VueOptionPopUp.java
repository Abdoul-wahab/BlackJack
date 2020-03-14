package vues;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *<b>Classe VueOptionPopUp.</b>
 *<p>Cette classe permet d'afficher une pop-up laissant le choix à l'utilisateur de relancer une partie ou non</p>
 *
 *@see VuePopUp
 *
 * @author 21606478, 21701844 et 21914280
 *
 */
public class VueOptionPopUp extends VuePopUp {
	
	/**
	 * Constructeur de VueOptionPopUp
	 * 
	 * @param message Le message à afficher dans la pop-up
	 * @param parentFrame Le JFrame parent du pop-up
	 */
	public VueOptionPopUp(String message, JFrame parentFrame) {
		super(message, parentFrame);
	}
	
	/**
	 * Méthode permettant d'afficher la pop-up
	 * 
	 * @return entier : le choix de l'utilisateur (1 il veut relancer une partie, 1 il veut arrêter)
	 */
	public int showPopUp() {
		//Les choix : Oui pour relancer une partie, sinon Non
		String choix[] = {"Oui !", "Non"};
		//On affiche la pop-up et on retourne le choix
		return this.showOptionDialog(parentFrame, message+"\n\nVoulez-vous relancez une partie ?", "Fin de partie !", JOptionPane.CLOSED_OPTION, JOptionPane.QUESTION_MESSAGE, null, choix, choix[0]);
	}

}
