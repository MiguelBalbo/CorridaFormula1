package view;
import java.util.concurrent.Semaphore;
import controller.FormulaThr;

public class main {
	public static void main(String[] args) {
		Semaphore semaforo = new Semaphore(5);
		
		for(int i=0; i<14; i++) {
			Thread thr = new FormulaThr(semaforo);
			thr.start();
		}
	}
}
