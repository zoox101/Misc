
//Runs a simulation for the Collatz Conjecture
public class CollatzConjecture {
	
	//Counts the number of times run has been called
	static int counter = 0;
	
	//Main method
	public static void main(String[] args) {
		run(User.getLong());
		System.out.println("\n" + counter);
	}
	
	//Recursive call. Prints each iteration.
	public static void run(long start) {
		counter++;
		System.out.print(start + " ");
		if(start == 1);
		else if(start%2 == 0) run(start/2); 
		else run(start*3 + 1);
	}

}
