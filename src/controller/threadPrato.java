package controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class threadPrato extends Thread {
	private int dif = 0;
	private int i = 0;
	private int tempo;
	static int perc;
	static int tAtual = 0;
	private int id;
	static int tTotal;
	private Semaphore semaforo;

	public threadPrato(int id, Semaphore semaforo) {
		this.id = id;
		this.semaforo = semaforo;
	}

	@Override
	public void run() {
		if ((id % 2) == 0) {
			lasanha();
			try {
				semaforo.acquire();
				pratoPronto();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				semaforo.release();
			}
		} else if ((id % 2) == 1) {
			sopaDeCebola();
			try {
				semaforo.acquire();
				pratoPronto();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				semaforo.release();
			}
		}
	}

	private void sopaDeCebola() {
		Random r = new Random();
		tempo = r.nextInt(800);
		while (tempo < 500) {
			tempo = r.nextInt(800);
		}
		tTotal += tempo;
		System.out.println("ID#" + id + ": Sopa de Cebola iniciado.");
		while (i < tempo) {
			try {
				sleep(100);
				percentual();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			i += 100;
			if (i > tempo) {
				dif = i - tempo;
				i -= dif;
				tAtual += (100 - dif);
			} else
				tAtual += 100;
		}

	}

	private void pratoPronto() {
		try {
			sleep(500);
			System.out.println("ID#" + id + ": Prato Entregue!");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void lasanha() {
		Random r = new Random();
		tempo = r.nextInt(800);
		while (tempo < 500) {
			tempo = r.nextInt(800);
		}
		tTotal += tempo;
		System.out.println("ID#" + id + ": Lasanha a bolonhesa iniciado.");
		while (i < tempo) {
			try {
				sleep(100);
				percentual();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			i += 100;
			if (i > tempo) {
				dif = i - tempo;
				i -= dif;
				tAtual += (100 - dif);
			} else
				tAtual += 100;
		}

	}

	public void percentual() {
		perc = (tAtual * 100) / tTotal;
		System.out.println("Percentual do cozimento: " + perc + "%");
	}
}
