/*! /file CoinNO.java
 *  /author Duizabo Aymeric
 *  /version  1.0
 *  /date 10/10/13 10:38
 *
 *  /brief Description
 *
 *
 */

public class CoinNO {
	/*-------------------Variables------------------*/
	private int[][] tableau;
/*----------------------------------------------*/

	/*-----------------Constructeurs----------------*/
	public CoinNO(int colonne, int ligne) {
		tableau = new int[colonne][ligne];
	}

	public CoinNO(int[][] tab) {
		tableau = tab;
	}
/*----------------------------------------------*/

	/*--------------------Getters-------------------*/
	public int getTableau(int colonne, int ligne) {
		return(this.tableau[colonne][ligne]);
	}

	public int getQuantDisp(int ligne) {
		return(tableau[tableau.length-1][ligne]);
	}

	public int getDemande(int colonne) {
		return(tableau[colonne][tableau[0].length-1]);
	}
/*----------------------------------------------*/

	/*-------------------Methodes-------------------*/
	public int solCoinNO() {
		int[][] tabSol = new int[tableau.length-1][tableau[0].length-1];
		OperationMatrice.initTab(tabSol);
		int demande, quantite;
		int x = 0;
		int y = 0;
		int sol = 0;

		OperationMatrice.afficheTab(tableau);

		demande = getDemande(x);
		quantite = getQuantDisp(y);
		while(demande != 0) {
			if(quantite > demande) {
				quantite -= demande;
				sol += getTableau(x, y) * demande;
				tabSol[x][y] = demande;
				x++;
				if(x < tableau.length) {
					demande = getDemande(x);
				}
			} else if(quantite > 0) {
				demande -= quantite;
				sol += getTableau(x, y) * quantite;
				tabSol[x][y] = demande;
				y++;
				quantite = getQuantDisp(y);
			} else {

			}
		}

		OperationMatrice.afficheTab(tabSol);

		return(sol);
	}
/*----------------------------------------------*/


}