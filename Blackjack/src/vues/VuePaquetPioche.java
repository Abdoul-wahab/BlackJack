/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vues;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import modèle.Carte;
import modèle.Tas;
import java.awt.Graphics;

/**
 *<b>Classe VuePaquetPioche.</b>
 *<p>Cette classe permet d'afficher la pioche du Blackjack</p>
 *
 *@see VuePaquet
 *@see Tas
 *@see Carte
 *
 * @author 21606478, 21701844 et 21914280
 *
 */
public class VuePaquetPioche extends VuePaquet {
	
	/**
	 * Constructeur de VuePaquetPioche
	 * 
	 * @param tas Le jeu de cartes à afficher
	 */
	public VuePaquetPioche(Tas tas) {
		this.tas=tas;
	}

	/**
	 * Pour dessiner la pioche
	 */
    public void paint(Graphics g) {
    	Graphics2D g2 = (Graphics2D) g;
    	
    	//x et y pour les coordonnées de la carte, w et h pour la largeur et la hauteur de la carte
        int x=10, y=10, w=70, h=100;
        //Pour toutes les cartes de la pioche
        for(Carte c : tas.getLesCartes()) {
        	//On veut dessiner le paquet de carte face caché
        	
        	//On dessine le contour de la carte en noir
        	g2.setColor(Color.BLACK);
        	g2.setStroke(new BasicStroke(2));
            g2.drawRect(x, y, w, h);
            
            //On dessine le fond de la carte en jaune
            g2.setColor(Color.WHITE);
            g2.fillRect(x, y, w, h);
            
            //On décale légèrement x et y pour afficher l'épaisseur de la pioche dans la vue
            x+=2; 
            y+=2;
        }
    }
    
}
