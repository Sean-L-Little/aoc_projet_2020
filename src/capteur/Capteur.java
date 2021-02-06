package capteur;

import java.util.HashSet;
import java.util.Set;

import canal.ObservateurDeCapteurAsynchrone;

/**
 *  Le capteur qui incrémente de 1 une fois par une unité de temps défini.
 *  (nous l'avons réglé sur 500ms pour les tests)
 *  
 *  Le capteur est le sujet du pattern Observer et à la fois Component et Client des patterns Proxy.
 *  
 *  @author Sean Little et Guillaume Fourniols
 *
 */
public interface Capteur extends Subject{
	
	/**
	 * Retourne la valeur du capteur enregistré lors du début de l'éxécution de 
	 * l'algorithme de diffusion.
	 * Déverouille également une des permissions du sémaphore de l'algorithme de diffusion.
	 * @return
	 */
	Integer getValue();
	
	/**
	 * Incrémente la valeur du capteur après avoir éxécuté l'algorithme de diffusion.
	 */
	void tick();
	
	/**
	 * Déverouille N permissions de l'algorithme de diffusion lié à ce capteur.
	 * @param n
	 */
    void unlock(int n);

    /**
     * Sauvegarde la valeur actuelle du capteur dans une variable désignée.
     * Cette fonction doit être appelée lors de l'éxécution des algorithmes de diffusions
     * pour prévenir des incohérences entre la valeur du capteur à l'entrée de l'algorithme et
     * la valeur enregistrée sur les afficheurs.
     */
    void saveValue();
    
    /**
     * Retourne la liste des canaux associés à ce capteur.
     * @return un HashSet contenant la liste des canaux auquel est associé le capteur.
     */
    HashSet<ObservateurDeCapteurAsynchrone> getCanaux();
}
