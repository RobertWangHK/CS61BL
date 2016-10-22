import java.util.HashMap;

public class Fibonacci {
	int callsToFib;
	int result;
	public HashMap<Integer, Integer> hm = new HashMap<>();

	public Fibonacci(int n){
		this.callsToFib = 0;
		this.result = fib(n);
		hm.put(n,result);
	}
	
	private int fib(int n) {
		callsToFib++;
		if(hm.containsKey(n)){
			return hm.get(n);
		}
		if (n == 0) {
			hm.put(0,0);
			return 0;
		} else if (n == 1) {
			hm.put(1,1);
			return 1;
		} else {
			int returnValue = fib(n - 1) + fib(n - 2);
			hm.put(n,returnValue);
			return returnValue;
		}
	}

}
