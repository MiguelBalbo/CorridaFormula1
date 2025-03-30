package view;
import java.util.concurrent.Semaphore;
import controller.FormulaThr;

public class main {
	public static void main(String[] args) {
		Semaphore semaforo = new Semaphore(5);
		Semaphore semaforo2 = new Semaphore(1);
		Semaphore semaforo3 = new Semaphore(1);
		
		for(int i=0; i<15; i++) {
			Thread thr = new FormulaThr(semaforo, semaforo2, semaforo3);
			thr.start();
		}
	}
}
