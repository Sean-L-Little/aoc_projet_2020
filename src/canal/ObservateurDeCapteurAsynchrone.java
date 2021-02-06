package canal;

import java.util.concurrent.Future;

import capteur.Capteur;

/**
 * Cette interface intègre la méthode update() permettant la création d'un
 * Future<?> permettant de simuler un délais lors de la mise à jour de l'afficheur.
 * @author Sean Little et Guillaume Fourniols.
 *
 */
public interface ObservateurDeCapteurAsynchrone {
	
	/**
	 * Effectue un appel à la fonction update() de l'afficheur lié à ce canal à l'aide du Scheduled Executor Service.
	 * Le Future est résolu après un temps variant de (DELAY_UNIT) à (DELAY_UNIT*2).
	 *  
	 * @return un Future<?> servant à suivre l'état de l'éxécution de la fonction.
	 * @param capteur, le sujet passé en paramètre dans le cadre du pattern Observer
	 */
    Future<?> update(Capteur capteur);
    
}
