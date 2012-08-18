package dicho;

public class DichotomieTest {
	
	public static void main(String[] args) {
		int[] tab = new int[]{1, 5, 6, 100};
		Dichotomie dicho = new Dichotomie();
		
		System.out.println("Expected: 64");
		System.out.println("Looking for the index " +
				"corresponding to the value 65...");
		System.out.println(dicho.find(5, tab, 2, 4));
	}
}

