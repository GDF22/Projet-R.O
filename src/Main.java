/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 10/10/13
 * Time: 11:33
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main (String[] args){
        System.out.println("Hello Worlds");

        int[][] tab = {{17, 15, 9, 5, 12}, {16, 16, 10, 5, 10}, {12, 15, 14, 11, 5}, {4, 8, 14, 7, 13}, {13, 9, 8, 12, 17}};
        int[][] tab2 = {{-1,11,1,7,9},{5,-1,3,12,3},{7,1,-1,9,13},{14,9,5,-1,4},{3,12,7,1,-1} };

        Matrice m = new Matrice(tab2);
        int sommeRegret = m.reductionLigne() + m.reductionColonne();
        System.out.println(sommeRegret);

        Little l = new Little();
        System.out.println(l.run(m, sommeRegret));

    }
}
