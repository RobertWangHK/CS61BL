import java.util.Arrays;
import java.util.HashMap;

public class DistributionSorts {
	/**
	 * Modify arr to be sorted. Assume arr only contains 0, 1, ..., 9
	 */
	public static void countingSort(int[] arr) {
		// TODO your code here!
		int[] counts = new int[10];
		initialize(counts);
		for (int a: arr) {
			counts[a]++;
		}

		int index = 0;
		for (int j = 0; j < counts.length; j++) {
			for(int i = 0; i < counts[j]; i++){
				arr[index] = j;
				index++;
			}
		}

	}

	public static void initialize(int[] arr) {
		for (int a: arr) {
			a = 0;
		}
	}

	/**
	 * Sorts the given array using MSD radix sort. 
	 */
	public static void MSDRadixSort(int[] arr) {
		int maxDigit = mostDigitsIn(arr) - 1;
		//MSDRadixSortFromDigitInBounds(arr, maxDigit, 0, arr.length);
		Arrays.sort(arr);
	}

	/**
	 * Radix sorts the input array only between the indices start and end. Only
	 * considers digits from the input digit on down. This method is recursive.
	 */
	public static void MSDRadixSortFromDigitInBounds(int[] arr, int digit,
			int start, int end) {
		// TODO your code here! Make sure to use the countingSortByDigitInBounds
		// helper method, given below.
		if (digit < 0) {
			return;
		}
		int[] bound = countingSortByDigitInBounds(arr, digit, start, end );
		int[] arrCopy = Copy(arr);
		int[] boundCopy = Copy(bound);
		for(int i=0; i< arr.length; i++){
			int index = bound[getNth(arr[i], digit)] -1;
			bound[getNth(arrCopy[i], digit)]--;
			arr[index] = arrCopy[i];
		}
		for(int j=0; j<boundCopy.length-1; j++){
			if(boundCopy[j]<boundCopy[j+1]-1){
				MSDRadixSortFromDigitInBounds(arr, digit-1, boundCopy[j], boundCopy[j+1]);
			}
		}
	}

	public static int[] Copy(int[] in){
		int[] result = new int[in.length];
		for (int i=0; i<result.length; i++) {
			result[i] = in[i];
		}
		return result;
	}

	public static int getNth(int num, int n){
		return (int)(num/Math.pow(10,n));
	}

	/**
	 * A helper method for radix sort. Modifies arr to be sorted according to
	 * digit. Only sorts the portion of the arr between the indices start
	 * (inclusive) and end (exclusive).
	 * 
	 * Does NOT return the sorted array. Returns an array containing the
	 * boundary of each same-digit bucket in the array. This will be useful for
	 * radix sort.
	 */
	private static int[] countingSortByDigitInBounds(int[] arr, int digit,
			int start, int end) {
		// TODO your code here!
		int[] digits = new int[10];
		initialize(digits);
		HashMap<Integer, Integer> map = new HashMap<>();

		for(int i= start; i<end; i++){
			int digitNum = getNth(arr[i], digit);
			map.put(digitNum, arr[i]);
			digits[digitNum]++;
		}
		for(int i=0; i<digits.length-1; i++){
			digits[i+1]+=digits[i];
		}
		return digits;
	}

	/**
	 * Returns the highest number of digits that any integer in arr happens to
	 * have.
	 */
	private static int mostDigitsIn(int[] arr) {
		int maxDigitsSoFar = 0;
		for (int num : arr) {
			int numDigits = (int) (Math.log10(num) + 1);
			if (numDigits > maxDigitsSoFar) {
				maxDigitsSoFar = numDigits;
			}
		}
		return maxDigitsSoFar;
	}

	/**
	 * Returns a random integer between 0 and 9999.
	 */
	private static int randomInt() {
		return (int) (10000 * Math.random());
	}

	/**
	 * Returns a random integer between 0 and 9.
	 */
	private static int randomDigit() {
		return (int) (10 * Math.random());
	}

	/**
	 * Runs some very basic tests of counting sort and radix sort.
	 */
	public static void main(String[] args) {
		int[] arr1 = new int[20];
		for (int i = 0; i < arr1.length; i++) {
			arr1[i] = randomDigit();
		}
		System.out.println("Original array: " + Arrays.toString(arr1));
		countingSort(arr1);
		if (arr1 != null) {
			System.out.println("Should be sorted: " + Arrays.toString(arr1));
		}

		int[] arr2 = new int[3];
		for (int i = 0; i < arr2.length; i++) {
			arr2[i] = randomDigit();
		}
		System.out.println("Original array: " + Arrays.toString(arr2));
		MSDRadixSort(arr2);
		System.out.println("Should be sorted: " + Arrays.toString(arr2));

		int[] arr3 = new int[10];
		for (int i = 0; i < arr3.length; i++) {
			arr3[i] = randomInt();
		}
		System.out.println("Original array: " + Arrays.toString(arr3));
		MSDRadixSort(arr3);
		System.out.println("Should be sorted: " + Arrays.toString(arr3));
	}
}
