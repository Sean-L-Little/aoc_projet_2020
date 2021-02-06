package capteur;

import java.util.HashSet;
import java.util.Iterator;

import algorithme.AlgoDiffusion;
import canal.ObservateurDeCapteurAsynchrone;

/**
 *  {@inheritDoc}
 */
public class CapteurImpl implements Capteur{
	
	private final static int START_VALUE = 1;
	
	private HashSet<ObservateurDeCapteurAsynchrone> canaux = new HashSet<ObservateurDeCapteurAsynchrone>();
	private AlgoDiffusion algoDiffusion;
	private Integer value 		= Integer.valueOf(START_VALUE);
	private Integer savedValue 	= Integer.valueOf(START_VALUE);
	
	public CapteurImpl(AlgoDiffusion algo) {
		this.algoDiffusion = algo;
	}

	@Override
	public void attach(ObservateurDeCapteurAsynchrone canal) {
		this.canaux.add(canal);
	}

	@Override
	public void detach(ObservateurDeCapteurAsynchrone canal) {
		this.canaux.remove(canal);
	}

	@Override
	public Integer getValue() {
		Integer toReturn = Integer.valueOf(this.savedValue);
		this.unlock(1);
		return toReturn;
	}

	@Override
	public void tick() {
        try {
			this.algoDiffusion.execute();
	        this.value++;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void unlock(int n) {
		this.algoDiffusion.unlock(n);
	}

	@Override
	public void saveValue() {
		this.savedValue = this.value;
	}

	@Override
	public HashSet<ObservateurDeCapteurAsynchrone> getCanaux() {
		return this.canaux;
	}

	@Override
	public void notifyObservateurs() {
		Iterator<ObservateurDeCapteurAsynchrone> it = this.getCanaux().iterator();
	     while(it.hasNext()){
	        ObservateurDeCapteurAsynchrone canal = it.next();
           canal.update(this);
	     }
	}


}
