package client;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import afficheur.Afficheur;
import algorithme.AlgoDiffusion;
import algorithme.DiffusionAtomique;
import algorithme.DiffusionParEpoque;
import algorithme.DiffusionSequentielle;
import canal.Canal;
import capteur.Capteur;
import capteur.CapteurImpl;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(50);
		
		AlgoDiffusion strategy = new DiffusionSequentielle();
		CapteurImpl capteur = new CapteurImpl(strategy);
		
		Afficheur afficheur1 = new Afficheur();
		Afficheur afficheur2 = new Afficheur();
		Afficheur afficheur3 = new Afficheur();
		Afficheur afficheur4 = new Afficheur();
		
		Canal canal1 = new Canal(scheduler, afficheur1);
		Canal canal2 = new Canal(scheduler, afficheur2);
		Canal canal3 = new Canal(scheduler, afficheur3);
		Canal canal4 = new Canal(scheduler, afficheur4);
		
		capteur.attach(canal1);
		capteur.attach(canal2);
		capteur.attach(canal3);
		capteur.attach(canal4);
		
		strategy.configure(capteur, 4);
		
		int iterations = 20;
		for(int i = 0; i<iterations;i++) {
			capteur.tick();
			Thread.sleep(500);
		}
		
		
		scheduler.awaitTermination(10, TimeUnit.SECONDS);
		scheduler.shutdown();
	}
}
