/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 30/10/13
 * Time: 17:52
 * To change this template use File | Settings | File Templates.
 */
public class Coord {
    public int x;
    public int y;

    public Coord() {
        x = 0;
        y = 0;
    }

    public Coord inv(){
        int table;
        table = x;
        x = y ;
        y = table;
        return this;
    }
}


