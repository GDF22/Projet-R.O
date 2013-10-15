/*! /file OperationMatrice.java
 *  /author Duizabo Aymeric
 *  /version  1.0
 *  /date 10/10/13 10:41
 *
 *  /brief Description
 *
 *
 */

public class OperationMatrice {

	public static void afficheTab(int[][] tab) {
		for(int i = 0; i < tab.length; i++) {
			for(int j = 0; j < tab[0].length; j++) {
				System.out.print(tab[i][j] + " ");
			}
			System.out.println(" ");
		}
		System.out.println(" ");
	}

	public static void afficheTab(boolean[] tab) {
		for(int i = 0; i < tab.length; i++) {
			System.out.print(tab[i] + " ");
		}
		System.out.println(" ");
	}


	public static void initTab(int[][] tab) {
		for(int i = 0; i < tab.length; i++) {
			for(int j = 0; j < tab[0].length; j++) {
				tab[i][j] = 0;
			}
		}
	}

	public static void initTab(boolean[] tab) {
		for(int i = 0; i < tab.length; i++) {
			tab[i] = true;
		}
	}


}