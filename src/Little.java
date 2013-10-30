/*! /file Little.java
 *  /author Duizabo Aymeric
 *  /version  1.0
 *  /date 15/10/13 11:24
 *
 *  /brief Description
 *
 *
 */

public class Little {
/*-------------------Variables------------------*/
	private Matrice matrice;
/*----------------------------------------------*/

/*-----------------Constructeurs----------------*/
	public Little(Matrice matrice) {
		this.matrice = matrice;
	}
/*----------------------------------------------*/

/*-------------------Methodes-------------------*/
	public void run() {
		this.reductionLigne();
		this.reductionColonne();
		this.couverture0();
	}

	public void reductionLigne() {
		int min;
		for(int i = 0; i < matrice.getTaille(); i++) {
			min = matrice.getValue(i, 0);
			for(int j = 0; j < matrice.getTaille(); j++) {
				min = (matrice.getValue(i, j) < min)? matrice.getValue(i, j): min;
			}
			for(int j = 0; j < matrice.getTaille(); j++) {
				matrice.setValue(i, j, matrice.getValue(i, j) - min);
			}
		}
	}

	public void reductionColonne() {
		int min;
		for(int j = 0; j < matrice.getTaille(); j++) {
			min = matrice.getValue(0, j);
			for(int i = 0; i < matrice.getTaille(); i++) {
				min = (matrice.getValue(i, j) < min)? matrice.getValue(i, j): min;
			}
			for(int i = 0; i < matrice.getTaille(); i++) {
				matrice.setValue(i, j, matrice.getValue(i, j) - min);
			}
		}
	}


	public void couverture0() {
		boolean[] ligne = new boolean[matrice.getTaille()];
		boolean[] colonne = new boolean[matrice.getTaille()];
		for(int i = 0; i < matrice.getTaille(); i++) {
			ligne[i] = true;
			colonne[i] = true;
		}



	}

	public Matrice getMatrice() {
		return(matrice);
	}
/*----------------------------------------------*/


}