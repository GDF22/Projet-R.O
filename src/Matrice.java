/*! /file Matrice.java
 *  /author Duizabo Aymeric
 *  /version  1.0
 *  /date 15/10/13 11:45
 *
 *  /brief Description
 *
 *
 */

public class Matrice {
/*-------------------Variables------------------*/
	private Case[][] matrice;
	private int taille;
/*----------------------------------------------*/

/*-----------------Constructeurs----------------*/
	public Matrice(int taille) {
		this.taille = taille;
		this.matrice = new Case[taille][taille];
	}

	public Matrice(int[][] matrice) {
		taille = matrice.length;
		this.matrice = new Case[taille][taille];
		for(int i = 0; i < taille; i++) {
			for(int j = 0; j < taille; j++) {
				if(i == j) {
					this.matrice[i][j].setValeur(-1);
				}


				this.matrice[i][j] = new Case(matrice[i][j]);
				this.matrice[i][j].marque = false;
			}
		}
	}
/*----------------------------------------------*/

/*--------------------Getters-------------------*/
	public int getValue(int i, int j) {
		return(matrice[i][j].valeur);
	}

	public boolean getMarque(int i, int j) {
		return(matrice[i][j].marque);
	}

	public int getTaille() {
	    return(this.taille);
	}
/*----------------------------------------------*/

/*--------------------Setters-------------------*/
	public void setValue(int i, int j, int value) {
		matrice[i][j].valeur = value;
	}

	public void setMarque(int i, int j, boolean marque) {
		matrice[i][j].marque = marque;
	}
/*----------------------------------------------*/

/*-------------------Methodes-------------------*/
	public String toString() {
		String ret = "";

		for(int i = 0; i < taille; i++) {
			for(int j = 0; j < taille; j++) {
				ret += matrice[i][j].valeur + "   ";
			}
			ret += "\n";
		}

		return(ret);
	}

	public Matrice copy() {
		Matrice copie = new Matrice(this.taille);
		for(int i = 0; i < taille; i++) {
			for(int j = 0; j < taille; j++) {
				copie.setValue(i, j, this.getValue(i, j));
			}
		}

		return(copie);
	}
/*----------------------------------------------*/


}