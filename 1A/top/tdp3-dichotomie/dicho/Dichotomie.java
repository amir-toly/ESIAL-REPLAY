package dicho;

public class Dichotomie {
	
	public int find(int element, int[] tab, int borneInf, int borneSup) {
		// Retrieve actual indexes
		borneInf--;
		borneSup--;
		
		if (borneInf == borneSup) {
			return -1;
		} else {
			int median = borneInf + borneSup;
			int odd = median % 2 == 0 ? 0 : 1;
			median = (median + odd) / 2;
			switch (Integer.valueOf(element).compareTo(tab[median])) {
				// From the outside, indexes start at 1 (instead of 0), hence the +1
				case 0: return median + 1;
				case -1: return find(element, tab, borneInf + 1, median + 1);
				default: return find(element, tab, median + 1, borneSup + 1);
			}
		}
	}
}

