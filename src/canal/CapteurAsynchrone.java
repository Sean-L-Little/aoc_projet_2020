package canal;

import java.util.concurrent.Future;

/**
 * Cette interface intègre la méthode getValue() permettant la création d'un
 * Future<Integer> dont la valeur doit être résolue par le ScheduledExecutorService via la
 * plannification du callable getValue() de Capteur (synchrone, donc).
 * @author Sean Little et Guillaume Fourniols.
 *
 */
public interface CapteurAsynchrone {

	/**
	 * Effectue un appel à la fonction getValue() du capteur à l'aide du Scheduled Executor Service.
	 * La valeur du Future est résolue après un temps variant de (DELAY_UNIT) 
	 * à (DELAY_UNIT*2).
	 *  
	 * @return un Future<Integer> contenant l'appel à la fonction getValue() d'un capteur.
	 */
    Future<Integer> getValue();
    
}
