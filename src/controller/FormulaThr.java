package controller;
import java.util.concurrent.Semaphore;

public class FormulaThr extends Thread{
	
	public static Semaphore semaforo;
	private static int tamPista = 4309; //tamanho de interlagos
	
	public FormulaThr(Semaphore semaforo) {
		this.semaforo = semaforo;
	}
	
	@Override
	public void run() {
		int tid = (int)threadId();
		try {
			semaforo.acquire();
			CorridaCarr(tid);
		}catch(Exception e) {
			System.err.println(e);
		}
		semaforo.release();
	}
	
	private void CorridaCarr(int tid) {
		int voltas = 1;
		int percorrido = 1;
		long ini = 0;
		while (voltas <= 3) {
			ini = System.nanoTime();
			percorrido += (int) (Math.random()*100);
			System.out.println("Carro " + tid +" percorreu " + percorrido + "m");
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (percorrido >= (tamPista * voltas)) {
				voltas++;
				long difTempo = ini - System.nanoTime();
				System.out.printf("Tempo da volta anterior %.9f segundos%n",difTempo);
				System.out.println("volta " + voltas);
			}
		}

	}
}
