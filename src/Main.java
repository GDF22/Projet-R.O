/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 10/10/13
 * Time: 11:33
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main (String[] args){
        /*int[][] tab = {{17, 15, 9, 5, 12}, {16, 16, 10, 5, 10}, {12, 15, 14, 11, 5}, {4, 8, 14, 7, 13}, {13, 9, 8, 12, 17}};
        int[][] tab2 = {{-1,11,1,7,9},{5,-1,3,12,3},{7,1,-1,9,13},{14,9,5,-1,4},{3,12,7,1,-1} };

        Matrice m = new Matrice(tab2);
        Matrice test = new Matrice("ft53.atsp", 53);

        int sommeRegret = test.reductionLigne() + test.reductionColonne();

        Little l = new Little();
        System.out.println(l.run(test, sommeRegret));
        */

        //CoinNO transport = new CoinNO("Data/Transport/transport100.txt", 101, 101);
        //CoinNO transport = new CoinNO("Data/Transport/transport50.txt", 51, 51);
        //CoinNO transport = new CoinNO("Data/test", 4, 5);
        //System.out.println(transport.solCoinNO());

        int[][] tab = {{5, 5, 9, 2, 7}, {7, 1, 2, 8, 39}, {8, 9, 5, 10, 172}, {93, 19, 60, 46, 0}};
        //BalasHammer test = new BalasHammer(tab);
        BalasHammer transport = new BalasHammer("Data/Transport/transport50.txt", 51, 51);
        System.out.println(transport.solBalasHammer());
    }
}
