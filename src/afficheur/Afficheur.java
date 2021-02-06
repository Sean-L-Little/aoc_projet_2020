package afficheur;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Logger;

import canal.CapteurAsynchrone;

/**
 * Un afficheur récuperant les données du capteur par le biais d'un canal.
 * Permet l'affichage et le stockage des données reçues.
 * 
 * 
 * @author Sean Little et Guillaume Fourniols
 *
 */
public class Afficheur implements ObservateurDeCapteur{

	private int currentMaxValue = -1;
	private List<Integer> receivedValues;

	
	public Afficheur() {
		this.receivedValues = new ArrayList<Integer>();
	}
	
	@Override
	public void update(CapteurAsynchrone canal) {
		Future<Integer> result = canal.getValue();
		
		try {
			Integer value = result.get();
			
			if(value > this.currentMaxValue) {
				this.currentMaxValue = value;
				this.receivedValues.add(value);
				//Logger.getGlobal().info(canal + " received : " + value);
				//System.out.println(canal + " received : " + value);
			}
			else {
				//System.out.println("Outdated value rejected.");
			}

		} 
		
		catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
	}
	
	public List<Integer> getReceivedValues() {
		return this.receivedValues;
	}
	
	public int getNumberOfValues() {
		return this.receivedValues.size();
	}

}
