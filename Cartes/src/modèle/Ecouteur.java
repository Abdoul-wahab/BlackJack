package modèle;

/**
 *<b>Interface Ecouteur.</b>
 *<p>Cette interface permet de définir un observateur qui pourra écouter une classe Ecoutable pour être notifier de ses changements</p>
 *
 *@see Ecoutable
 *
 *@author 21606478, 21701844 et 21914280
 *
 */
public interface Ecouteur {
    
	/**
	 *Cette méthode permet de définir le comportement de la classe lors d'une notification
	 *
	 */
    public void update();
    
}
