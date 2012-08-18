package dicho;

public class Dichotomie {
	
	/**
	 * Search the specified element in the tab array between borneInf & borneSup
	 * indexes, starting at 1.
	 * <p>
	 * Preconditions:
	 * - the tab array must be sorted in ascending order;
	 * - the borneInf parameter must be greater or equal to 1;
	 * - the borneInf parameter must be lower than the borneSup parameter;
	 * - the borneSup parameter must be lower or equal to tab.length.
	 * 
	 * @param	element		the element we are looking for
	 * @param	tab			the array we are looking in
	 * @param	borneInf	the lower bound (at least 1)
	 * @param	borneSup	the upper bound (at most tab.length)
	 * @return				the element index if found, -1 otherwise
	 */
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

