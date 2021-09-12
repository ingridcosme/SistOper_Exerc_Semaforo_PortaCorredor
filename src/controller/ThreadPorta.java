/*
 * 4 pessoas caminham, cada uma em um corredor diferente. Os 4 corredores ter_
 * minam em uma única porta. Apenas 1 pessoa pode cruzar a porta, por vez. Con_
 * sidere que cada corredor tem 200m. e cada pessoa anda de 4 a 6 m/s. Cada pes_
 * soa leva de 1 a 2 segundos para abrir e cruzar a porta. Faça uma aplicação em
 * java que simule essa situação.
 */

package controller;

import java.util.concurrent.Semaphore;

public class ThreadPorta extends Thread {

	private int idCorredor;
	private int distanciaMax = 200;
	private static int posChegada;
	private static int posSaida;
	private Semaphore semaforo;
	
	public ThreadPorta(int idPessoa, Semaphore semaforo) {
		this.idCorredor = idPessoa;
		this.semaforo = semaforo;
	}

	@Override
	public void run() {
		caminhandoCorredor();
		
		/*Início Seção Crítica*/
		try {
			semaforo.acquire();
			cruzandoPorta();  //Apenas 1 corredor pode cruzar a porta por vez
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}
		/*Fim Seção Crítica*/
		
		saindoPorta();
	}

	private void caminhandoCorredor() {
		int distanciaPercorrida = 0;
		/*
		 * Simulando a velocidade de cada corredor, sabendo que, eles deslocam-se 
		 * de 4 a 6 metros por segundo.
		 */
		int deslocamento = (int)((Math.random() * 3) + 4);  //Gerando um valor entre 4 e 6
		int tempo = 1000;  //1000 ms = 1 s
		
		while(distanciaPercorrida < distanciaMax) {
			distanciaPercorrida += deslocamento;  //A Thread percorre uma distância x
			try {
				sleep(tempo);  //E a Thread fica bloqueada por 1 s
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Corredor #"+idCorredor+" percorreu "
						+distanciaPercorrida+" m.");
		}
		
		posChegada++;
		System.out.println("Corredor #"+idCorredor+" foi o "+posChegada+ "o. a "
				+ "chegar na porta.");
	}

	private void cruzandoPorta() {
		System.out.println("Corredor #"+idCorredor+" abriu a porta.");
		/*
		 * Simulando o tempo que cada pessoa gasta para abrir e cruzar a porta.
		 * No caso, sabemos que o tempo varia entre 1 e 2 segundos.
		 */
		int tempoCruzar = (int)((Math.random() * 1001) + 1000);  //Gerando um valor entre 1 e 2 s
		try {
			sleep(tempoCruzar);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	private void saindoPorta() {
		posSaida++;
		System.out.println("Corredor #"+idCorredor+" foi o "+posSaida+ "o. a "
				+ "cruzar.");
	}

}
