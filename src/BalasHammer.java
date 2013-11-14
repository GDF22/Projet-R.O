/*! /file BalasHammer.java
 *  /author Duizabo Aymeric
 *  /version  1.0
 *  /date 10/10/13 10:44
 *
 *  /brief Description
 *
 *
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
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

    public BalasHammer(String fichier, int l, int col) {
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

		int indiceMaxLigne = 0;
		int indiceMaxCol = 0;

		while(somme(tableau[tableau.length-1]) > 0) {		// tant qu'il y a des stocks
			tabSol = calculRegret(tableau, ligneDispo, colDispo);			// calcul des regrets

			System.out.println("tabsol");
			OperationMatrice.afficheTab(tabSol);
			System.out.println("tableau");
			OperationMatrice.afficheTab(tableau);

			indiceMaxLigne = indiceMax(tabSol[tabSol.length - 1]);	// calcul du max des regrets (ligne : stock)

			int[] colonne = new int[tableau.length];
			for(int i = 0; i < tableau.length; i++) {
				colonne[i] = tableau[i][tableau[0].length -1];
			}
			indiceMaxCol = indiceMax(colonne);	// calcul du max des regrets (colonne : demande)



			if(indiceMaxLigne > indiceMaxCol) {		// si le max des regrets est dans les stocks
				int indiceMax2 = 0;
				int valeurMin = 999999999;
				for(int i = 0; i < tableau.length - 1; i++) {			// calcul du min de la colonne
					if(valeurMin > tableau[i][indiceMaxLigne] && tableau[i][indiceMaxLigne] != 0) {
						valeurMin = tableau[i][indiceMaxLigne];
						indiceMax2 = i;
					}
				}

				int min = Math.min(tableau[indiceMax2][tableau[0].length-1], tableau[tableau.length-1][indiceMaxLigne]);	// calcul du min entre le stock et la demande
					if(tableau[tableau.length-1][indiceMaxLigne] < tableau[indiceMax2][tableau[0].length-1]) {		// on enlève une ligne/colonne
					colDispo[indiceMaxLigne] = false;
				} else {
					ligneDispo[indiceMax2] = false;
				}

				tabSol[indiceMax2][indiceMaxLigne] = min;
				res += valeurMin * min;
				tableau[indiceMax2][indiceMaxLigne] = 0;
				tableau[indiceMax2][tableau[0].length-1] -= min;
				tableau[tableau.length-1][indiceMaxLigne] -= min;
			} else {		// Si le max des regrets est dans les demandes
				int indiceMax2 = 0;
				int valeurMin = 999999999;
				for(int i = 0; i < tableau[0].length - 1; i++) {			// calcul du min de la ligne
					if(valeurMin > tableau[indiceMaxCol][i] && tableau[indiceMaxCol][i] != 0) {
						valeurMin = tableau[indiceMaxCol][i];
						indiceMax2 = i;
					}
				}

				int min = Math.min(tableau[indiceMaxCol][tableau[0].length-1], tableau[tableau.length-1][indiceMax2]);	// calcul du min entre le stock et la demande
				if(tableau[tableau.length-1][indiceMax2] < tableau[indiceMaxCol][tableau[0].length-1]) {		// on enlève une ligne/colonne
					colDispo[indiceMax2] = false;
				} else {
					ligneDispo[indiceMaxCol] = false;
				}

				tabSol[indiceMaxCol][indiceMax2] = min;
				res += valeurMin * min;
				tableau[indiceMaxCol][indiceMax2] = 0;
				tableau[indiceMaxCol][tableau[0].length-1] -= min;
				tableau[tableau.length-1][indiceMax2] -= min;
			}
		}


		return(res);
	}


	public int[][] calculRegret(int[][] tableau, boolean[] ligne, boolean[] col) {
		int[][] tabSol = new int[tableau.length][tableau[0].length];

		for(int i = 0; i < tableau.length - 2; i++) {
			List<Integer> liste = new LinkedList<>();
			if(ligne[i]) {
				for(int j = 0; j < tableau.length -1; j++) {
					if((tableau[i][j]) != 0) {
						liste.add(tableau[i][j]);
					}
				}
				Collections.sort(liste);
				if(liste.size() > 1) {
					tabSol[i][tabSol[0].length-1] = liste.get(1) - liste.get(0);
				} else {
					tabSol[i][tabSol[0].length-1] = liste.get(0);
				}
			} else {
				tabSol[i][tabSol[0].length-1] = 0;
			}
		}

		for(int j = 0; j < tableau[0].length - 2; j++) {
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