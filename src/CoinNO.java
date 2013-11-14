/*! /file CoinNO.java
 *  /author Duizabo Aymeric
 *  /version  1.0
 *  /date 10/10/13 10:38
 *
 *  /brief Description
 *
 *
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;


public class CoinNO {
	/*-------------------Variables------------------*/
	public int[][] tableau;
/*----------------------------------------------*/

	/*-----------------Constructeurs----------------*/
	public CoinNO(int colonne, int ligne) {
		tableau = new int[colonne][ligne];
	}

	public CoinNO(int[][] tab) {
		tableau = tab;
	}

    public CoinNO(String fichier, int l, int col) {
        this(l, col);

        try{
            InputStream ips=new FileInputStream(fichier);
            InputStreamReader ipsr=new InputStreamReader(ips);
            BufferedReader br=new BufferedReader(ipsr);
            String ligne;

            int i = 0;
            int j = 0;

            while ((ligne=br.readLine())!=null){
                char[] carac = ligne.toCharArray();
                if(carac[0] != '#') {
                    String nb = "";
                    int c = 0;
                    while(c < ligne.length()) {
                        if(carac[c] != ' ') {
                            nb += carac[c];
                        } else {
                            this.tableau[i][j] = Integer.decode(nb);
                            nb = "";
                            j++;
                        }
                        c++;
                    }
                    this.tableau[i][j] = Integer.decode(nb);
                    j = 0;
                    i++;
                }
            }
            br.close();
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
    }
/*----------------------------------------------*/

	/*--------------------Getters-------------------*/
	public int getTableau(int colonne, int ligne) {
		return(this.tableau[colonne][ligne]);
	}

	public int getQuantDisp(int ligne) {
        if(ligne < tableau[0].length) {
		    return(tableau[tableau.length-1][ligne]);
        }
        return(0);
	}

	public int getDemande(int colonne) {
        if(colonne < tableau.length) {
            return(tableau[colonne][tableau[0].length-1]);
        }
        return(0);
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

			if(quantite > demande) {    //plus de quant que de demande
                tableau[x][tableau[0].length-1] -= demande;
                tableau[tableau.length-1][y] -= demande;
				sol += getTableau(x, y) * demande;
				tabSol[x][y] = demande;
				x++;
			} else if(quantite > 0) {   //plus ou autant de demande que de quantité, quantité positive
                tableau[x][tableau[0].length-1] -= quantite;
                tableau[tableau.length-1][y] -= quantite;
				sol += getTableau(x, y) * quantite;
				tabSol[x][y] = quantite;
				y++;
			}

            demande = getDemande(x);
            quantite = getQuantDisp(y);

            if(quantite == 0) {
                y++;
			}
            if(demande == 0) {
                x++;
            }
            demande = getDemande(x);
            quantite = getQuantDisp(y);

            /*System.out.println("tabsol");
            OperationMatrice.afficheTab(tabSol);
            System.out.println("tableau");
            OperationMatrice.afficheTab(tableau);*/
		}

        System.out.println("tabsol");
        OperationMatrice.afficheTab(tabSol);
		return(sol);
	}

/*----------------------------------------------*/


}