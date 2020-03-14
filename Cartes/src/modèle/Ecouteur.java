package mod�le;

/**
 *<b>Interface Ecouteur.</b>
 *<p>Cette interface permet de d�finir un observateur qui pourra �couter une classe Ecoutable pour �tre notifier de ses changements</p>
 *
 *@see Ecoutable
 *
 *@author 21606478, 21701844 et 21914280
 *
 */
public interface Ecouteur {
    
	/**
	 *Cette m�thode permet de d�finir le comportement de la classe lors d'une notification
	 *
	 */
    public void update();
    
}
