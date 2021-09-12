/*
 * 4 pessoas caminham, cada uma em um corredor diferente. Os 4 corredores ter_
 * minam em uma única porta. Apenas 1 pessoa pode cruzar a porta, por vez. Con_
 * sidere que cada corredor tem 200m. e cada pessoa anda de 4 a 6 m/s. Cada pes_
 * soa leva de 1 a 2 segundos para abrir e cruzar a porta. Faça uma aplicação em
 * java que simule essa situação.
 */

package view;

import java.util.concurrent.Semaphore;

import controller.ThreadPorta;

public class Principal {

	public static void main(String[] args) {
		int permissoes = 1;
		Semaphore mutex = new Semaphore(permissoes);
		
		for(int idCorredor = 1; idCorredor <= 4; idCorredor++) {
			Thread tPorta = new ThreadPorta(idCorredor, mutex);
			tPorta.start();
		}

	}

}
