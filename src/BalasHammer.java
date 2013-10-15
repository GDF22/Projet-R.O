/*! /file BalasHammer.java
 *  /author Duizabo Aymeric
 *  /version  1.0
 *  /date 10/10/13 10:44
 *
 *  /brief Description
 *
 *
 */

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class BalasHammer {
	/*-------------------Variables------------------*/
	private int[][] tableau;
/*----------------------------------------------*/

	/*-----------------Constructeurs----------------*/
	public BalasHammer(int colonne, int ligne) {
		tableau = new int[colonne][ligne];
	}

	public BalasHammer(int[][] tab) {
		tableau = tab;
	}
/*----------------------------------------------*/

	/*--------------------Getters-------------------*/
/*----------------------------------------------*/

	/*--------------------Setters-------------------*/
/*----------------------------------------------*/

	/*-------------------Methodes-------------------*/
	public int solBalasHammer() {
		int[][] tabSol;
		int res = 0;
		boolean[] ligneDispo = new boolean[tableau.length-1];
		boolean[] colDispo = new boolean[tableau[0].length-1];
		OperationMatrice.initTab(colDispo);
		OperationMatrice.initTab(ligneDispo);
		OperationMatrice.afficheTab(tableau);

		int indiceMax = 0;

		while(somme(tableau[tableau.length-1]) > 0) {		// tant qu'il y a des stocks
			tabSol = calculRegret(tableau, ligneDispo, colDispo);			// calcul des regrets
			indiceMax = indiceMax(tabSol[tabSol.length - 1]);	// calcul du max des regrets (colonne)

			int indiceMax2 = 0;
			int valeurMin = 999999999;
			for(int i = 0; i < tableau.length; i++) {			// calcul du min de la colonne (ligne)
				if(valeurMin > tableau[i][indiceMax] && tableau[i][indiceMax] != 0) {
					valeurMin = tableau[i][indiceMax];
					indiceMax2 = i;
				}
			}

			int min = Math.min(tableau[indiceMax2][tableau[0].length-1], tableau[tableau.length-1][indiceMax]);	// calcul du min entre le stock et la demande

			if(tableau[tableau.length-1][indiceMax] < tableau[indiceMax2][tableau[0].length-1]) {		// on enlève une ligne/colonne
				colDispo[indiceMax] = false;
			} else {
				ligneDispo[indiceMax2] = false;
			}

			tabSol[indiceMax2][indiceMax] = min;
			res += valeurMin * min;
			tableau[indiceMax2][indiceMax] = 0;
			tableau[indiceMax2][tableau[0].length-1] -= min;
			tableau[tableau.length-1][indiceMax] -= min;

		}

		return(res);
	}


	public int[][] calculRegret(int[][] tableau, boolean[] ligne, boolean[] col) {
		int[][] tabSol = new int[tableau.length][tableau[0].length];

		for(int i = 0; i < tableau.length - 1; i++) {
			List<Integer> liste = new LinkedList<>();
			if(ligne[i]) {
				for(int j = 0; j < tableau.length -1; j++) {
					if((tableau[i][j]) != 0) {
						liste.add(tableau[i][j]);
					}
				}
				Collections.sort(liste);
				tabSol[i][tabSol[0].length-1] = liste.get(1) - liste.get(0);
			} else {
				tabSol[i][tabSol[0].length-1] = 0;
			}
		}

		for(int j = 0; j < tableau[0].length - 1; j++) {
			List<Integer> liste = new LinkedList<>();
			if(col[j]) {
				for(int i = 0; i < tableau.length -1; i++) {
					if((tableau[i][j]) != 0) {
						liste.add(tableau[i][j]);
					}
				}
				Collections.sort(liste);
				if(liste.size() > 1) {
					tabSol[tableau.length-1][j] = liste.get(1) - liste.get(0);
				} else {
					tabSol[tableau.length-1][j] = liste.get(0);
				}
			} else {
				tabSol[tableau.length-1][j] = 0;
			}
		}
		return(tabSol);
	}


	public int somme(int[] t) {
		int res = 0;
		for(int i = 0; i < t.length; i++) {
			res += t[i];
		}
		return(res);
	}

	public int indiceMax(int[] t) {
		int maximum = t[0];
		int indice = 0;
		for (int i=1; i<t.length; i++) {
			if (t[i] > maximum) {
				maximum = t[i];
				indice = i;
			}
		}
		return (indice);
	}
/*----------------------------------------------*/


}