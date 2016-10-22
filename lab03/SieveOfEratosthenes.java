public class SieveOfEratosthenes {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("You need to enter an argument!");
            //throw new ArrayIndexOutOfBoundsException();
	    return;
        }

        int upperBound = Integer.parseInt(args[0]);
        boolean[] isNotPrime = new boolean[upperBound];
        //isNotPrime[0]=true;
        //isNotPrime[1]=true;
        for (int i = 2; i < upperBound; i++) {
            if (isNotPrime[i] == false) {
                //THIS DATA HAS BEEN CORRUPTED; REPLACE IT!
                for(int j=i*i; j<upperBound;j+=i){
                    isNotPrime[j]=true;
                    if(j+i>upperBound){
                        break;
                    }
                }
            }
        }
        for (int i = 2; i < upperBound; i++) {
            if (!isNotPrime[i]) {
                System.out.println(i + " is a prime number.");
            }
        }
    }
}
