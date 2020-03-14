package mod�le;

/**
 *<b>Classe Ecoutable.</b>
 *<p>Cette classe abstraite permet de d�finir un classe qui peut �tre observ�e</p>
 *
 *@see Ecouteur
 *
 *@author 21606478, 21701844 et 21914280
 *
 */
public abstract class Ecoutable {
    
	/**
     * Cette m�thode permet d'ajouter un observateur � la liste des observateurs qui �coute le controleur
     * 
     * @param ecouteur L'observateur � ajouter
     * 
     */
    public abstract void ajouterEcouteur(Ecouteur ecouteur);
    /**
     * Cette m�thode permet de retirer un observateur � la liste des observateurs qui �coute le controleur
     * 
     * @param ecouteur L'observateur � retirer
     * 
     */
    public abstract void retirerEcouteur(Ecouteur ecouteur);
    /**
     * Cette m�thode permet de notifier l'ensemble des observateurs qui �coute le controleur d'un changement
     * 
     */
    protected abstract void notifier();
    
}
