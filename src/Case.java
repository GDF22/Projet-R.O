/*! /file Case.java
 *  /author Duizabo Aymeric
 *  /version  1.0
 *  /date 15/10/13 11:31
 *
 *  /brief Description
 *
 *
 */

public class Case {
	/*-------------------Variables------------------*/
	public int valeur;
	public boolean marque;
/*----------------------------------------------*/

	/*-----------------Constructeurs----------------*/
    public Case(int valeur) {
        this.valeur = valeur;
        marque = false;
    }
    public Case() {
        this.valeur = 0;
        marque = false;
    }
/*----------------------------------------------*/

	/*--------------------Getters-------------------*/
	public int getValeur() {
	    return(this.valeur);
	}

	public boolean getMarque() {
	    return(this.marque);
	}
/*----------------------------------------------*/

	/*--------------------Setters-------------------*/
	public void setValeur(int valeur) {
	    this.valeur = valeur;
	}

	public void setMarque(boolean marque) {
		this.marque = marque;
	}
/*----------------------------------------------*/

	/*-------------------Methodes-------------------*/
/*----------------------------------------------*/
}