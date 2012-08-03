package dicho;

public class DichotomieTest {
	
	public static void main(String[] args) {
		int[] tab = new int[100];
		Dichotomie dicho = new Dichotomie();
		
		for (int i=0;i<tab.length;i++) {
			tab[i] = i + 1;
		}
		System.out.println("Expected: 64");
		System.out.println("Looking for the index " +
				"corresponding to the value 65...");
		System.out.println(dicho.find(65, tab, 1, 100));
	}
}

