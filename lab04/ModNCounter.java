public class ModNCounter extends Counter {
	
	int N;
	
    public ModNCounter(int n) {
        N = n;
    }

    public void increment() {
        super.increment();
        if(super.value()==N){
            super.reset();
        }
    }
}
