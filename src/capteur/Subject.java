package capteur;

import afficheur.ObservateurDeCapteur;
import canal.ObservateurDeCapteurAsynchrone;

/**
 * Cette classe Subject permets d'illustrer l'utilisation du pattern Observer
 * au sein de l'application.
 * 
 *  @author Sean Little et Guillaume Fourniols
 */
public interface Subject {

	/**
	 * Attache un observer à la liste des observers du subject.
	 * @param canal, l'observateur qui sera lié au capteur.
	 */
    void attach(ObservateurDeCapteurAsynchrone canal);

    /**
     * Retire un observe de la liste des observers du subject.
     * @param canal, l'observateur qui sera retiré du capteur.
     */
    void detach(ObservateurDeCapteurAsynchrone canal);
    
    void notifyObservateurs();
}
