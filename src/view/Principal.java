package view;

import java.util.concurrent.Semaphore;

import controller.threadPrato;

public class Principal {

	public static void main(String[] args) {
		int fila = 1;
		Semaphore semaforo = new Semaphore(fila);
		for(int id = 1; id <= 5; id++) {
			Thread cozinha = new threadPrato(id,semaforo);
			cozinha.start();
		}
		
	}

}
