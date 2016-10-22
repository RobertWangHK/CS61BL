/**
 * Created by cs61bl-jr on 6/23/16.
 */
public class TriangleDrawer2 {
    public static void main(String[] args) {
        int SIZE = 10;
        int col = 0;
        int row = 0;
	System.out.println();
        for (;row<SIZE;row++){
            for(;col<=row;col++){
                System.out.print('*');
            }
            col=0;
            System.out.println();
        }
    }
}
