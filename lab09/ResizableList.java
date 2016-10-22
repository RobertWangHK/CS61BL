import java.util.Arrays;

/**
 * Created by cs61bl-jr on 7/7/16.
 */
public class ResizableList extends FixedSizeList{
    public ResizableList(){
        super(20);
        //values = new int[20];
    }
    @Override
    public void add(int k) {
        // YOUR CODE HERE
        if(count==values.length){
            resize();
        }
        values[count]=k;
        count++;
    }
    @Override
    public void add(int i, int k) {
        if(count==values.length){
            resize();
        }
        if(i>=count){
            values[count]=k;
        }
        else{
            for (int h = i + 1; h <= count; h++) {
                values[h] = values[h-1];
            }
            values[i] = k;
        }
        count++;
    }
    public void resize(){
        values = Arrays.copyOf(values, values.length*2);
    }
}
