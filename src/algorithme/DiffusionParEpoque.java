package algorithme;

import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Semaphore;

import canal.ObservateurDeCapteurAsynchrone;
import capteur.Capteur;

/**
 * Concrete Algorithm dans le cadre du pattern Strategy.
 * Implémente l'algorithme de diffusion par époque :
 * - Chaque observateur possède une suite de valeur pouvant être différente des autres
 * - Les valeurs sont strictement croissantes.
 * - L'ensemble de valeurs de chaque observateur est un sous-ensemble des valeurs du sujet (Capteur)
 * @author Sean Little et Guillaume Fourniols
 *
 */
public class DiffusionParEpoque  implements AlgoDiffusion{

    private Capteur capteur;
    private Semaphore semaphore;
    private boolean configured = false;
    
	@Override
	public void execute() throws InterruptedException {
		assertTrue(configured);
		this.capteur.saveValue();
		this.capteur.notifyObservateurs();
	}

	@Override
	public void configure(Capteur capteur, int nbCanaux) {
		this.configured = true;
		this.capteur = capteur;
		this.semaphore = new Semaphore(nbCanaux);
	}

	@Override
	public void unlock(int n) {
		this.semaphore.release(n);
	}

}
