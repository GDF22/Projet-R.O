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
/*----------------------------------------------*/

/*-----------------Constructeurs----------------*/
	public Little() {
	}
/*----------------------------------------------*/

/*-------------------Methodes-------------------*/
	public int run(Matrice mat , int cost ) {
        int eviction;
        Coord coord;
        Matrice mat2;

        if ( mat.getTaille() == 2 ){
            return cost + mat.getValue(0,1)+ mat.getValue(1,0);
        }
        else{
            mat.reductionLigne();
            mat.reductionColonne();
            coord = mat.EvictionCost().getMaxCoord();
            eviction = mat.EvictionCost().getMax();
            mat2 = mat.copy();
            mat.suppRowNColumn(coord);
            mat2.suppRowNColumn(coord.inv());

            System.out.println("cost : " + cost);
            System.out.println("eviction : " + eviction);
        return( Math.min( run( mat , cost) , run( mat2 , cost+eviction) ) );
        }
    }




	/*public void couverture0() {
		boolean[] ligne = new boolean[matrice.getTaille()];
		boolean[] colonne = new boolean[matrice.getTaille()];
		for(int i = 0; i < matrice.getTaille(); i++) {
			ligne[i] = true;
			colonne[i] = true;
		}



	}

	public Matrice getMatrice() {
		return(matrice);
	}*/
/*----------------------------------------------*/


}