/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vues;

import mod�le.*;

import java.awt.Graphics;

import javax.swing.*;

/**
 *<b>Classe VuePaquet.</b>
 *<p>Cette classe abstraite permet de d�crire une vue d'un paquet de carte</p>
 *
 *@see VuePaquet
 *@see Tas
 *@see JPanel
 *
 * @author 21606478, 21701844 et 21914280
 *
 */
public abstract class VuePaquet extends JPanel {
    
	/**
	 * Le paquet de carte � afficher
	 */
	public Tas tas;
        
	/**
	 * M�thode permettant de dessiner le paquet de carte
	 */
	public abstract void paint(Graphics g);
    
}
