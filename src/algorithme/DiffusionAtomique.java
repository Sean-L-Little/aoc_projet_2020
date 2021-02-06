package algorithme;

import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Semaphore;

import canal.Canal;
import canal.ObservateurDeCapteurAsynchrone;
import capteur.Capteur;

/**
 * Concrete Algorithm dans le cadre du pattern Strategy.
 * Implémente l'algorithme de diffusion atomique :
 * - Chaque observateur de capteur possède le même ensemble de valeurs que le sujet (Capteur).
 * - Chaque observateur possède donc le même ensemble de valeurs.
 * @author Sean Little et Guillaume Fourniols
 *
 */
public class DiffusionAtomique implements AlgoDiffusion{

    private Capteur capteur;
    private Semaphore semaphore;
    private boolean configured = false;
    
	@Override
	public void execute() throws InterruptedException {
		assertTrue(configured);
		//		Initialement le rédacteur peut écrire dans la variable (= le mutateur peut modifier le sujet, un appel à setValue(v_i))
		//		À la fin de l'écriture, la phase de lecture commence.
		//		Pendant la phase de lecture, toute écriture est interdite.
		semaphore.acquire(capteur.getCanaux().size());
		this.capteur.saveValue();
		this.capteur.notifyObservateurs();
		//		Lorsque tous les lecteurs ont lu une fois (par appel à getValue()) la phase de lecture termine et la phase d'écriture commence.
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
