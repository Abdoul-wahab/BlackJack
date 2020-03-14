/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vues;

import mod�le.*;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JScrollPane;

/**
 *<b>Classe VuePaquetCache.</b>
 *<p>Cette classe permet de g�rer l'affichage du main avec des cartes fache cach�es</p>
 *
 *@see VuePaquet
 *@see Tas
 *@see Carte
 *
 * @author 21606478, 21701844 et 21914280
 *
 */
public class VuePaquetCache extends VuePaquet {
	
	/**
	 * bool�en �gal � true si les cartes doivent �tre retourn�s
	 */
	public boolean revele;
    
	/**
	 * Constructeur de VuePaquetCache
	 * 
	 * @param tas Le jeu de cartes � afficher
	 */
    public VuePaquetCache(Tas tas) {
        this.tas=tas;
        //Par d�faut, les cartes sont cach�es
        revele=false;
    }
    
    /**
     * Permet de dessiner la main du joueur carte visible
     */
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
      //x et y pour les coordonn�es de la carte, w et h pour la largeur et la hauteur de la carte
        int x=10, y=10, w=70, h=100;
        //Si les cartes doit �tre cach�es
        if(!revele)
        	//Alors on veut dessiner le paquet de carte face cach�e
        	//Pour toutes les cartes de la main
	        for(Carte c : tas.getLesCartes()) {
	        	
	        	//On dessine le contour de la carte en noir
	        	g2.setColor(Color.BLACK);
	        	g2.setStroke(new BasicStroke(2));
	            g2.draw(new RoundRectangle2D.Double(x, y, w, h, 15, 15));
	            
	            //On dessine le fond de la carte en jaune
	            g2.setColor(Color.WHITE);
	            g2.fill(new RoundRectangle2D.Double(x, y, w, h, 15, 15));
	            
	          //On incr�mente x pour afficher les cartes les uns apr�s les autres
	            x+=w+10;
	        }
        //sinon
        else {
        	//Alors on veut dessiner le paquet de carte face visible
        	Color cardColor=null;
        	//Pour toutes les cartes de la main
            for(Carte c : tas.getLesCartes()) {
            	//On r�cup�re la couleur � afficher pour les cartes selon la couleur de la carte
                if(c.getSymbole().getCouleur()==Couleur.NOIR) cardColor = Color.BLACK;
                else if(c.getSymbole().getCouleur()==Couleur.ROUGE) cardColor = Color.RED;
                
                //On dessine le contour de la carte de la couleur de la carte
                g2.setColor(cardColor);
            	g2.setStroke(new BasicStroke(2));
                g2.draw(new RoundRectangle2D.Double(x, y, w, h, 15, 15));
                
                //On dessine le fond de la carte en blanc
                g2.setColor(Color.WHITE);
                g2.fill(new RoundRectangle2D.Double(x, y, w, h, 15, 15));
                
                //On affiche le hauteur et le symbole de la carte de la couleur de la carte
                g2.setColor(cardColor);
                g2.drawString(c.getHauteur().toString(), x+5, y+15);
                g2.drawString(c.getSymbole().toString(), x+5, y+30);
                
                //On incr�mente x pour afficher les cartes les uns apr�s les autres
                x+=w+10;
            }
            
            //Puis affiche la valeur du paquet et la mise du joueur en noir
            g2.setColor(Color.BLACK);
            g2.drawString("Valeur paquet "+tas.getValeurPaquet(), 10, y+h+20);
        }
        
    }
    
    /**
     * Mutateur de revele, pour retourner et cacher les cartes du paquet
     * 
     * @param revele Bool�en pour r�veler et cacher les cartes
     */
    public void setRevele(boolean revele) {
    	this.revele = revele;
    }

}
