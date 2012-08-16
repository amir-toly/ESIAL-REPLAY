package dicho;

public class Dichotomie {
	
	public int find(int element, int[] tab, int borneInf, int borneSup) {
		if (borneInf >= borneSup) {
			return -1;
		} else {
			int median = borneInf + borneSup;
			int odd = median % 2 == 0 ? 0 : 1;
			median = (median + odd) / 2;
			switch (Integer.valueOf(element).compareTo(tab[median])) {
				case 0: return median;
				case -1: return find(element, tab, borneInf, median);
				default: return find(element, tab, median, borneSup);
			}
		}
	}
}

