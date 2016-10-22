public class ModNCounter {

	private int myCount;
	private int Count;

	public ModNCounter(int n) {
		Count = n;
		myCount = 0;
	}

	public void increment() {
		myCount++;
		myCount = myCount%Count;
	}

	public void reset() {
		myCount = 0;
	}

	public int value() {
		return Count;
	}

	public int value_() {
		return myCount;
	}

}
