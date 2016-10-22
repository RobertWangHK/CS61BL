/**
 * Created by cs61bl-jr on 6/23/16.
 */

public class TriangleDrawer {
    public static void main(String[] args) {
        int SIZE = 10;
        int col = 0;
        int row = 0;
	System.out.println();
        while (row < SIZE) {
            while (col <= row) {
                System.out.print('*');
                col = col + 1;
            }
            col=0;
            System.out.println();
            row = row + 1;
        }
    }
}
