package tests;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

import afficheur.Afficheur;
import algorithme.AlgoDiffusion;
import algorithme.DiffusionAtomique;
import algorithme.DiffusionParEpoque;
import algorithme.DiffusionSequentielle;
import canal.Canal;
import capteur.CapteurImpl;

public class Oracle {

	private final static int NB_ITERATIONS = 30;
	
	ScheduledExecutorService scheduler;
	AlgoDiffusion strategy;
	CapteurImpl capteur;

	private List<Afficheur> afficheurs;

	private Canal canal1;
	private Canal canal2;
	private Canal canal3;
	private Canal canal4;

	private void displayAfficheursValues() {
		for(int i = 0; i<afficheurs.size();i++) {
			System.out.print("Contenu de l'afficheur " + i + " : ");
			for(int n = 0;n<afficheurs.get(i).getReceivedValues().size();n++) {
				System.out.print(afficheurs.get(i).getReceivedValues().get(n) + ", ");
			}
			System.out.println();
		}
	}

	@Test
	public void testAtomique() throws InterruptedException {
		//Test setup 		
		scheduler = Executors.newScheduledThreadPool(50);

		afficheurs = new ArrayList<Afficheur>();
		Afficheur afficheur1 = new Afficheur();
		afficheurs.add(afficheur1);
		Afficheur afficheur2 = new Afficheur();
		afficheurs.add(afficheur2);
		Afficheur afficheur3 = new Afficheur();
		afficheurs.add(afficheur3);
		Afficheur afficheur4 = new Afficheur();
		afficheurs.add(afficheur4);

		canal1 = new Canal(scheduler, afficheur1);
		canal2 = new Canal(scheduler, afficheur2);
		canal3 = new Canal(scheduler, afficheur3);
		canal4 = new Canal(scheduler, afficheur4);
		Logger.getGlobal().info("Tests diffusion atomique");
		this.strategy = new DiffusionAtomique();
		capteur = new CapteurImpl(strategy);
		capteur.attach(canal1);
		capteur.attach(canal2);
		capteur.attach(canal3);
		capteur.attach(canal4);
		strategy.configure(capteur, 4);
		int iterations = NB_ITERATIONS;
		for(int i = 0; i<iterations;i++) {
			capteur.tick();
			Thread.sleep(500);
		}
		scheduler.awaitTermination(10, TimeUnit.SECONDS);
		scheduler.shutdown();
		int afficheurToCheck = 0;
		for(int i = 0; i<afficheurs.size();i++) {
			if(afficheurs.get(i).getNumberOfValues() < afficheurs.get(afficheurToCheck).getNumberOfValues()) {
				afficheurToCheck = i;
			}
		}
		//Specific algorithm part
		for(int i = 0; i<afficheurs.get(afficheurToCheck).getNumberOfValues();i++) {
			//int valueToCompare = afficheurs.get(afficheurToCheck).getReceivedValues().get(i);
			for(int n = 0;n<afficheurs.size();n++) {
				//Pour tout i, n, L(i)[n] = V[n]
				assertTrue(afficheurs.get(n).getReceivedValues().get(i) == (i+1));
			}
		}
		displayAfficheursValues();
		Logger.getGlobal().info("Tests diffusion atomique terminés sans avoir rencontré d'erreurs.");
	}

	@Test
	public void testSequentiel() throws InterruptedException {
		//Test setup 		
		scheduler = Executors.newScheduledThreadPool(50);

		afficheurs = new ArrayList<Afficheur>();
		Afficheur afficheur1 = new Afficheur();
		afficheurs.add(afficheur1);
		Afficheur afficheur2 = new Afficheur();
		afficheurs.add(afficheur2);
		Afficheur afficheur3 = new Afficheur();
		afficheurs.add(afficheur3);
		Afficheur afficheur4 = new Afficheur();
		afficheurs.add(afficheur4);

		canal1 = new Canal(scheduler, afficheur1);
		canal2 = new Canal(scheduler, afficheur2);
		canal3 = new Canal(scheduler, afficheur3);
		canal4 = new Canal(scheduler, afficheur4);
		Logger.getGlobal().info("Tests diffusion sequentielle");
		this.strategy = new DiffusionSequentielle();
		capteur = new CapteurImpl(strategy);
		capteur.attach(canal1);
		capteur.attach(canal2);
		capteur.attach(canal3);
		capteur.attach(canal4);
		strategy.configure(capteur, 4);
		int iterations = NB_ITERATIONS;
		for(int i = 0; i<iterations;i++) {
			capteur.tick();
			Thread.sleep(500);
		}
		scheduler.awaitTermination(10, TimeUnit.SECONDS);
		scheduler.shutdown();
		int afficheurToCheck = 0;
		for(int i = 0; i<afficheurs.size();i++) {
			if(afficheurs.get(i).getNumberOfValues() < afficheurs.get(afficheurToCheck).getNumberOfValues()) {
				afficheurToCheck = i;
			}
		}
		//Specific algorithm part
		for(int i = 0; i<afficheurs.get(afficheurToCheck).getNumberOfValues();i++) {
			int valueToCompare = afficheurs.get(afficheurToCheck).getReceivedValues().get(i);
			//L(i) est un sous-ensemble de V
			assertTrue(valueToCompare >= i);
			for(int n = 0;n<afficheurs.size();n++) {
				//Pour tout x, y, L(x) = L(y)
				assertTrue(afficheurs.get(n).getReceivedValues().get(i) == valueToCompare);
			}
		}
		displayAfficheursValues();
		Logger.getGlobal().info("Tests diffusion séquentielle terminés sans avoir rencontré d'erreurs.");
	}

	@Test
	public void testEpoque() throws InterruptedException {
		//Test setup 		
		scheduler = Executors.newScheduledThreadPool(50);

		afficheurs = new ArrayList<Afficheur>();
		Afficheur afficheur1 = new Afficheur();
		afficheurs.add(afficheur1);
		Afficheur afficheur2 = new Afficheur();
		afficheurs.add(afficheur2);
		Afficheur afficheur3 = new Afficheur();
		afficheurs.add(afficheur3);
		Afficheur afficheur4 = new Afficheur();
		afficheurs.add(afficheur4);

		canal1 = new Canal(scheduler, afficheur1);
		canal2 = new Canal(scheduler, afficheur2);
		canal3 = new Canal(scheduler, afficheur3);
		canal4 = new Canal(scheduler, afficheur4);
		Logger.getGlobal().info("Tests diffusion par epoque");
		this.strategy = new DiffusionParEpoque();
		capteur = new CapteurImpl(strategy);
		capteur.attach(canal1);
		capteur.attach(canal2);
		capteur.attach(canal3);
		capteur.attach(canal4);
		strategy.configure(capteur, 4);
		int iterations = NB_ITERATIONS;
		for(int i = 0; i<iterations;i++) {
			capteur.tick();
			Thread.sleep(500);
		}
		scheduler.awaitTermination(10, TimeUnit.SECONDS);
		scheduler.shutdown();
		int afficheurToCheck = 0;
		for(int i = 0; i<afficheurs.size();i++) {
			//System.out.println("Lenght is " + afficheurs.get(i).getNumberOfValues());
			if(afficheurs.get(i).getNumberOfValues() < afficheurs.get(afficheurToCheck).getNumberOfValues()) {
				afficheurToCheck = i;
			}
		}
		//Specific algorithm part
		for(int n = 0;n<afficheurs.size();n++) {
			int lastValue = 0;
			for(int i = 0; i<afficheurs.get(afficheurToCheck).getNumberOfValues();i++) {
				//Pour tout x, y, i : (x>y) => L(i)[x] > L(i)[y]
				assertTrue(afficheurs.get(n).getReceivedValues().get(i) > lastValue);
				lastValue = afficheurs.get(n).getReceivedValues().get(i);
			}
		}
		displayAfficheursValues();
		Logger.getGlobal().info("Tests diffusion par époque terminés sans avoir rencontré d'erreurs.");
	}

}
