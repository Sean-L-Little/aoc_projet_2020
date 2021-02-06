package canal;

import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import afficheur.Afficheur;
import capteur.Capteur;

/**
 * Canal représente une interface entre Capteur et Afficheur.
 * Permet d'inclure une simulation de délai entre les deux objets.
 * @author Sean Little et Guillaume Fourniols.
 *
 */
public class Canal implements ObservateurDeCapteurAsynchrone, CapteurAsynchrone {

	private final static int DELAY_UNIT = 1000;
    private ScheduledExecutorService scheduler;
    private Afficheur afficheur;
    private Capteur capteur;
    

    public Canal(ScheduledExecutorService scheduler, Afficheur afficheur) {
        this.scheduler = scheduler;
        this.afficheur = afficheur;
    }

	@SuppressWarnings("unchecked")
	@Override
	public Future<Integer> getValue() {
		return (Future<Integer>) scheduler.schedule(() ->{ return this.capteur.getValue();}, 
				(long)(Math.random()*DELAY_UNIT+DELAY_UNIT), TimeUnit.MILLISECONDS);
	}

	@Override
	public Future<?> update(Capteur capteur) {
		this.capteur = capteur;
		return scheduler.schedule(() ->{ afficheur.update(this);}, 
				(long)(Math.random()*DELAY_UNIT+DELAY_UNIT), TimeUnit.MILLISECONDS);
	}

}
