/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vues;

import modèle.*;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;

import controleurs.Controleur;

/**
 *<b>Classe VuePaquetVisible.</b>
 *<p>Cette classe permet de gérer l'affichage du main avec des cartes visibles</p>
 *
 *@see VuePaquet
 *@see Tas
 *@see Carte
 *
 * @author 21606478, 21701844 et 21914280
 *
 */
public class VuePaquetVisible extends VuePaquet {
	
	/**
	 * La mise du joueur humain
	 */
	public int misejoueur;
    
	/**
	 * Constructeur de VuePaquetVisible
	 * 
	 * @param tas Le main du joueur à afficher
	 */
    public VuePaquetVisible(Tas tas) {
        this.tas=tas;
    }
    
    /**
     * Permet de dessiner la main du joueur carte visible
     */
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        
        //x et y pour les coordonnées de la carte, w et h pour la largeur et la hauteur de la carte
        int x=10, y=10, w=70, h=100;
        Color cardColor=null;
        //Pour toutes les cartes de la main
        for(Carte c : tas.getLesCartes()) {
        	////On veut dessiner le paquet de carte face visible
        	
        	//On récupère la couleur à afficher pour les cartes selon la couleur de la carte
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
            
            //On incrémente x pour afficher les cartes les uns après les autres
            x+=w+10;
        }
        
        //On affiche la valeur du paquet et la mise du joueur en noir
        g2.setColor(Color.BLACK);
        g2.drawString("Valeur paquet : "+tas.getValeurPaquet(), 10, y+h+20);
        g2.drawString("Mise joueur : "+this.misejoueur, 10, y+h+40);
        
    }
    
    /**
     * Mutateur de mise, pour afficher la mise du joueur humain sur la vue
     * 
     * @param mise La mise du joueur
     */
    public void setMise(int mise) {
    	misejoueur=mise;
    }

}
