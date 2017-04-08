
public class RecursiveProb {

	public static void main(String[] args) {
		for(int i=0; i<100; i++) {
			System.out.println(sProb(100, i));
		}
	}

	public static double sProb(int resources, int depth) {
		if(resources == 0) {return 0;}
		else if(depth == 0) {return 1;}
		else {return 0.5 * sProb(resources+200, depth-1) + 0.5 * sProb(resources-100, depth-1);}
	}

}
