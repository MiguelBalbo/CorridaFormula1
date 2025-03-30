package controller;
import java.util.Arrays;
import java.util.concurrent.Semaphore;
import com.destny.quicksort.QuickSorting;

public class FormulaThr extends Thread{
	
	private Semaphore semaforo;
	private int tid = (int)threadId();
	private static int posVet = 0;
	private static int[] tempos = new int[45];
	private Semaphore semaforo2;
	private Semaphore semaforo3;
	private int escuderiaId = tid%7;
	private static int runQtd = 14;
	
	public FormulaThr(Semaphore semaforo, Semaphore semaforo2, Semaphore semaforo3) {
		this.semaforo = semaforo;
		this.semaforo2 = semaforo2;
		this.semaforo3 = semaforo3;
	}
	
	@Override
	public void run() {
		
		try {
			semaforo.acquire();
			CorridaCarr();
		}catch(Exception e) {
			System.err.println(e);
		}
		semaforo.release();
		runQtd--;
		
		if (runQtd < 0) {
			System.out.println("\nTempos organizados em segundos:");
			OrganizaTempos();
		}
	}
	
	private void OrganizaTempos() {
		QuickSorting qk = new QuickSorting();
		int vet[] = qk.QuickSort(tempos, 0, 44);
		System.out.println(Arrays.toString(vet));
	}

	private void CorridaCarr() throws Exception {
		int voltas;
			semaforo2.acquire();
			voltas = 1;
			//Vel media do carro de f1 varia entre 83 a 111m/s
			//tempo minimo pra 1 volta seria 51s e o max 38s considerando interlagos
			while(voltas<4) {
				int tempoVolta = (int) (Math.random()*13000)+38000;
				
				semaforo3.acquire();
					tempos[posVet] = tempoVolta/1000;
					posVet++;
				semaforo3.release();
				
				System.out.println("Volta " + voltas + ", do carro " + tid + ", da escuderia " + escuderiaId + " em " + (tempoVolta/1000) + "s");
				voltas++;
			}
			semaforo2.release();
		
	}
}
