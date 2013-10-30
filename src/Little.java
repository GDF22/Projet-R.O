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
        matrice.reductionLigne();
        matrice.reductionColonne();
		this.couverture0();
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