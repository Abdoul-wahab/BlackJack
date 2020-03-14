package modèle;

/**
 *<b>Classe Ecoutable.</b>
 *<p>Cette classe abstraite permet de définir un classe qui peut être observée</p>
 *
 *@see Ecouteur
 *
 *@author 21606478, 21701844 et 21914280
 *
 */
public abstract class Ecoutable {
    
	/**
     * Cette méthode permet d'ajouter un observateur à la liste des observateurs qui écoute le controleur
     * 
     * @param ecouteur L'observateur à ajouter
     * 
     */
    public abstract void ajouterEcouteur(Ecouteur ecouteur);
    /**
     * Cette méthode permet de retirer un observateur à la liste des observateurs qui écoute le controleur
     * 
     * @param ecouteur L'observateur à retirer
     * 
     */
    public abstract void retirerEcouteur(Ecouteur ecouteur);
    /**
     * Cette méthode permet de notifier l'ensemble des observateurs qui écoute le controleur d'un changement
     * 
     */
    protected abstract void notifier();
    
}
