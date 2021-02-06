package afficheur;

import canal.CapteurAsynchrone;

/**
 * Un observateur de capteur dans le cadre du pattern Observer.
 * Un observateur de capteur est mis à jour lors de l'appel à la fonction notifyObservateur de capteur.
 * @author groger
 *
 */
public interface ObservateurDeCapteur {
	
	/**
	 * Met à jour le capteur en allant récuperer la valeur mise en mémoire dans le canal
	 * passé en paramètre. Le canal retournant un Future d'Integer, cette fonction est asynchrone 
	 * dans le sens où elle effectue un get() bloquant le processus pendant jusqu'à l'obtention de cette valeur.
	 * 
	 * @param canal sur lequel est récupéré la valeur.
	 */
	void update(CapteurAsynchrone canal);
}
