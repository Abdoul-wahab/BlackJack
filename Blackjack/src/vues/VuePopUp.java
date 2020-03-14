package vues;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import modèle.Tas;

/**
 *<b>Classe VuePopUp.</b>
 *<p>Cette classe abstraite permet de décrire la vue d'une pop-up</p>
 *
 *@see JOptionPane
 *@see JFrame
 *
 * @author 21606478, 21701844 et 21914280
 *
 */
public abstract class VuePopUp extends JOptionPane{
	
	/**
	 * Le message à afficher dans la pop-up
	 */
	public String message;
	/**
	 * Le JFrame parent du pop-up
	 */
	public JFrame parentFrame;
	
	
	/**
	 * Constructeur de VuePopUp
	 * 
	 * @param message Le message à afficher dans la pop-up
	 * @param parentFrame Le JFrame parent du pop-up
	 */
	public VuePopUp(String message, JFrame parentFrame) {
		this.message=message;
		this.parentFrame=parentFrame;
	}

}
