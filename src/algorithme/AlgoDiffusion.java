package algorithme;

import java.util.List;

import canal.ObservateurDeCapteurAsynchrone;
import capteur.Capteur;

/**
 * Cette classe fait partie du pattern Strategy permettant au programme d'employer différents algorithmes de diffusion
 * pour le capteur.
 * @author Sean Little et Guillaume Fourniols
 *
 */
public interface AlgoDiffusion {
	
	/**
	 * Sert à définir l'état du système lors de l'execution des algorithmes de diffusion.
	 * @author Sean Little et Guillaume Fourniols
	 *
	 */
	public enum SystemState {
		READ, WRITE;
	}

	/**
	 * Execute l'algorithme de diffusion.
	 * Appeler l'éxécution de l'algorithme avant de l'avoir configuré avec la fonction prévue à cet effet
	 * resulte en une erreur java.lang.AssertionError.
	 * @throws InterruptedException
	 * @throws java.lang.AssertionError
	 */
	void execute() throws InterruptedException;
	
	/**
	 * Permet de déverouiller N sémaphores pour permettre l'éxécution de l'algorithme de diffusion suivant.
	 * @param n, nombre de sémaphore
	 */
	void unlock(int n);

	/**
	 * Configure l'algorithme de diffusion.
	 * Doit être éxécuté avant de faire appel à l'execution de celui-ci.
	 * @param capteur
	 * @param nbCanaux
	 */
	void configure(Capteur capteur, int nbCanaux);

}