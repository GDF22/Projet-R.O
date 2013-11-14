/*! /file Matrice.java
 *  /author Duizabo Aymeric
 *  /version  1.0
 *  /date 15/10/13 11:45
 *
 *  /brief Description
 *
 *
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Matrice {

    public static final int INFINITE = 9999999;

    /*-------------------Variables------------------*/
	private Case[][] matrice;
	private int taille;
/*----------------------------------------------*/

	/*-----------------Constructeurs----------------*/
	public Matrice(int taille) {
		this.taille = taille;
		this.matrice = new Case[taille][taille];
        for(int i = 0; i < taille; i++) {
            for(int j = 0; j < taille; j++) {
                this.matrice[i][j] = new Case(0);
                this.matrice[i][j].marque = false;
            }
        }
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

    public Matrice(String fichier, int taille) {
        this(taille);

        try{
            InputStream ips=new FileInputStream(fichier);
            InputStreamReader ipsr=new InputStreamReader(ips);
            BufferedReader br=new BufferedReader(ipsr);
            String ligne;

            int i = 0;
            int j = 0;

            while ((ligne=br.readLine())!=null){
                char[] carac = ligne.toCharArray();
                String nb = "";
                int c = 0;
                while(c < ligne.length()) {
                    if(carac[c] != ',') {
                        nb += carac[c];
                    } else {
                        this.setValue(i, j, Integer.decode(nb));
                        nb = "";
                        j++;
                    }
                    c++;
                }
                this.setValue(i, j, Integer.decode(nb));
                j = 0;
                i++;
            }
            br.close();
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
    }
/*----------------------------------------------*/

	/*--------------------Getters-------------------*/
    public int getValue(int i, int j) {
        return(matrice[i][j].valeur);
    }

    public int getValue(Coord coord) {
        return(matrice[coord.x][coord.y].valeur);
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

	public int reductionLigne() {
        int sommeRegret = 0;
		int min;
		for(int i = 0; i < this.getTaille(); i++) {
			min = this.getValue(i, 0);
			if(i == 0) {
				min = this.getValue(i, 1);
			}
			for(int j = 0; j < this.getTaille(); j++) {
				if(this.getValue(i, j) != -1) {
					min = (this.getValue(i, j) < min)? this.getValue(i, j): min;
				}
			}
            sommeRegret += min;
			for(int j = 0; j < this.getTaille(); j++) {
				if(this.getValue(i, j) != -1) {
					this.setValue(i, j, this.getValue(i, j) - min);
				}
			}
		}
        return(sommeRegret);
	}

	public int reductionColonne() {
        int sommeRegret = 0;
		int min;
		for(int j = 0; j < this.getTaille(); j++) {
			min = this.getValue(0, j);
			if(j == 0) {
				min = this.getValue(1, j);
			}
			for(int i = 0; i < this.getTaille(); i++) {
				if(this.getValue(i, j) != -1) {
					min = (this.getValue(i, j) < min)? this.getValue(i, j): min;
				}
			}
            sommeRegret += min;
			for(int i = 0; i < this.getTaille(); i++) {
				if(this.getValue(i, j) != -1) {
					this.setValue(i, j, this.getValue(i, j) - min);
				}
			}
		}
        return(sommeRegret);
	}

	public Matrice copy() {
		Matrice copie = new Matrice(this.taille);
		for(int i = 0; i < taille; i++) {
			for(int j = 0; j < taille; j++) {
				copie.matrice[i][j] = new Case(this.getValue(i, j));
				copie.setValue(i, j, this.getValue(i, j));
			}
		}

		return(copie);
	}

    public int getMax(){
        int res = 0;
        for (int i = 0 ; i < taille; i++){
            for (int j = 0 ; j < taille; j++){
                res = ( res < getValue(i,j))? getValue(i,j) : res;
            }
        }
        return res;
    }

	public Matrice EvictionCost(){
        Matrice res = new Matrice(taille);
        int max, minRow,minColumn;

        max = getMax();

		for (int i = 0 ; i < taille; i++){
			for (int j = 0 ; j < taille; j++){
                minRow = max;
                minColumn = max;

                if(this.getValue(i,j) == 0){
                    for (int x = 0 ; x < taille ; x++){
                        if(x != i && getValue(x,j) != -1) minRow = (getValue(x,j) < minRow )?getValue(x,j) : minRow;
                        if(x != j && getValue(i,x) != -1) minColumn = (getValue(i,x) < minColumn )?getValue(i,x) : minColumn;
                    res.setValue(i,j,minRow+minColumn);
                    }
                }

                else {
                    res.setValue(i,j,0);
                }
			}
		}
		return res;
	}

    public void suppRowNColumn(Coord coord){
        this.suppRowNColumn(coord.x , coord.y);
    }

	public void suppRowNColumn(int row , int column){
		this.taille --;
		int x,y;
		Case[][] newmat = new Case[this.taille][this.taille];
		x = 0;
		for(int i = 0 ; i < this.taille ; i++ ){
			y = 0;
			if(i== row) x = 1;
			for(int j = 0; j < this.taille ; j++ ){
				if(j == column) y = 1;
				newmat[i][j] = new Case(this.getValue(i+x,j+y));
			}
		}
		this.matrice = newmat;
	}

    public Coord getMaxCoord(){
        int max = 0;
        Coord res = new Coord();
         for(int i = 0 ; i < taille ; i++){
             for(int j = 0 ; j < taille ; j++){
                if(getValue(i,j)> max){
                    max = getValue(i,j) ;
                    res.x = i;
                    res.y = j;
                }
             }
         }
        return res;
    }
/*----------------------------------------------*/


}