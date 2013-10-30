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

    public void reductionLigne() {
        int min;
        for(int i = 0; i < this.getTaille(); i++) {
            min = this.getValue(i, 0);
            for(int j = 0; j < this.getTaille(); j++) {
                min = (this.getValue(i, j) < min)? this.getValue(i, j): min;
            }
            for(int j = 0; j < this.getTaille(); j++) {
                this.setValue(i, j, this.getValue(i, j) - min);
            }
        }
    }

    public void reductionColonne() {
        int min;
        for(int j = 0; j < this.getTaille(); j++) {
            min = this.getValue(0, j);
            for(int i = 0; i < this.getTaille(); i++) {
                min = (this.getValue(i, j) < min)? this.getValue(i, j): min;
            }
            for(int i = 0; i < this.getTaille(); i++) {
                this.setValue(i, j, this.getValue(i, j) - min);
            }
        }
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

    public Matrice EvictionCost(){
        Matrice res = this.copy();
        int[] minByRow = new int[res.taille];
        int[] minByColumn = new int[res.taille];

        for (int i = 0 ; i < res.taille; i++){
            for (int j = 0 ; j < res.taille; j++){
                minByRow[i] = (this.getValue(i,j)<minByRow[i])? this.getValue(i,j) : minByRow[i];
                minByColumn[j] = (this.getValue(i,j)<minByColumn[j])? this.getValue(i,j) : minByColumn[j];
            }
        }
        for (int i = 0 ; i < res.taille; i++){
            for (int j = 0 ; j < res.taille; j++){
                res.setValue(i,j,minByRow[i]+minByColumn[j]);
            }
        }
       return res;
    }

    public void suppRowNColumn(int row , int column){
        this.taille --;
        Case[][] newmat = new Case[this.taille][this.taille];
        for(int i = 0 ; i < this.taille+1 ; i++ ){
            for(int j = 0; j < this.taille+1 ; j++ ){
                if(i != row && j != column){
                    newmat[i][j]
                }
            }
        }
    }
/*----------------------------------------------*/


}