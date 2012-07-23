package chaine;

public class Chaine {
	public Element tete;
	
	public Chaine() {
	}
	
	public Chaine(Chaine chaine) {
		this.tete = chaine.tete;
	}
	
	public Chaine(String chaine) {
		if (chaine.length() > 0) {
			tete = new Element(chaine.charAt(0));
			Element suivant = tete;
			
			for (int i=1;i<chaine.length();i++) {
				suivant.suite = new Element(chaine.charAt(i));
				suivant = suivant.suite;
			}
		}
	}
	
	public boolean estVide() {
		return tete == null;
	}
	
	public Chaine reste() {
		Chaine result = new Chaine(this);
		result.tete = result.tete.suite;
		return result;
	}
	
	public char premier() throws ChaineVideException {
		if (estVide()) {
			throw new ChaineVideException();
		}
		return tete.val;
	}
	
	public Chaine adjT(char c) {
		Element element = new Element(c);
		element.suite = tete;
		tete = element;
		return this;
	}
	
	public Chaine adjQ(char c) {
		Element element = new Element(c);
		
		if (tete == null) {
			tete = element;
		} else {
			Element dernierVisible = tete;
			while (dernierVisible.suite != null) {
				dernierVisible = dernierVisible.suite;
			}
			dernierVisible.suite = element;
		}
		
		return this;
	}
	
	public int longueur() {
		if (estVide()) {
			return 0;
		} else {
			return 1 + reste().longueur();
		}
	}
	
	public boolean estMembre(char c) throws ChaineVideException {
		if (estVide()) {
			return false;
		} else if (premier() == c) {
			return true;
		} else {
			return reste().estMembre(c);
		}
	}
	
	public int occurence(char c) throws ChaineVideException {
		if (estVide()) {
			return 0;
		} else if (premier() == c) {
			return 1 + reste().occurence(c);
		} else {
			return reste().occurence(c);
		}
	}
	
	public Chaine supprime(char c) throws ChaineVideException {
		if (estVide()) {
			return this;
		} else if (premier() == c) {
			return reste();
		} else {
			return reste().supprime(c).adjT(premier());
		}
	}
	
	public Chaine supprimeAdjQ(char c) throws ChaineVideException {
		if (estVide()) {
			return this;
		} else {
			Chaine tempChaine = this;
			Chaine temp = new Chaine();
			boolean trouve = false;
			while (!tempChaine.reste().estVide()) {
				if (tempChaine.premier() != c) { // Need to do a check here...
					temp = temp.adjQ(tempChaine.premier());
				} else { // ... or we'll search for the character from the end
					trouve = true;
				}
				tempChaine = tempChaine.reste();
			}
			if (c == tempChaine.premier() && !trouve) {
				return temp;
			} else {
				return temp.supprimeAdjQ(c).adjQ(tempChaine.premier());
			}
		}
	}
	
	public Chaine supprimeTout(char c) throws ChaineVideException {
		if (estVide()) {
			return this;
		} else if (premier() == c) {
			return reste().supprimeTout(c);
		} else {
			return reste().supprimeTout(c).adjT(premier());
		}
	}
	
	public Chaine supprimeToutAdjQ(char c) throws ChaineVideException {
		if (estVide()) {
			return this;
		} else {
			Chaine tempChaine = this;
			Chaine temp = new Chaine();
			while (!tempChaine.reste().estVide()) {
				temp = temp.adjQ(tempChaine.premier());
				tempChaine = tempChaine.reste();
			}
			if (c == tempChaine.premier()) {
				return temp.supprimeToutAdjQ(c);
			} else {
				return temp.supprimeToutAdjQ(c).adjQ(tempChaine.premier());
			}
		}
	}
	
	public boolean tousDifferents() throws ChaineVideException {
		if (estVide()) {
			return true;
		} else if (reste().estVide()) {
			return true;
		} else {
			return !reste().estMembre(premier()) && reste().tousDifferents();
		}
	}
	
	public char dernier() throws ChaineVideException {
		if (estVide()) {
			throw new ChaineVideException();
		}
		
		if (reste().estVide()) {
			return premier();
		} else {
			return reste().dernier();
		}
	}
	
	public char deuxieme() throws ChaineVideException {
		if (estVide() || reste().estVide()) {
			throw new ChaineVideException("Can be performed only on, "+
					"at least, 2 characters long Chaine");
		}
		return reste().premier();
	}
	
	public Chaine saufDernier() throws ChaineVideException {
		if (reste().estVide()) {
			return new Chaine();
		} else {
			return reste().saufDernier().adjT(premier());
		}
	}
	
	public char nieme(int n) throws ChaineVideException {
		if (n == 1) {
			return premier();
		} else {
			return reste().nieme(n-1);
		}
	}
	
	public Chaine nPremiers(int n) throws ChaineVideException {
		if (n == 0) {
			return new Chaine();
		} else {
			return reste().nPremiers(n-1).adjT(premier());
		}
	}
	
	public Chaine nDerniers(int n) {
		if (n == longueur()) {
			return this;
		} else {
			return reste().nDerniers(n);
		}
	}
	
	public Chaine retourne() throws ChaineVideException {
		if (estVide() || longueur() == 1) {
			return this;
		} else {
			return saufDernier().retourne().adjT(dernier());
		}
	}
	
	public Chaine retourneAdjQ() throws ChaineVideException {
		if (estVide() || longueur() == 1) {
			return this;
		} else {
			return reste().retourneAdjQ().adjQ(premier());
		}
	}
	
	public Chaine concat(Chaine chaine) throws ChaineVideException {
		if (estVide()) {
			return chaine;
		} else {
			return saufDernier().concat(chaine.adjT(dernier()));
		}
	}
	
	public Chaine concatAdjQ(Chaine chaine) throws ChaineVideException {
		if (estVide()) {
			return chaine;
		} else if (chaine.estVide()) {
			return this;
		} else {
			return adjQ(chaine.premier()).concatAdjQ(chaine.reste());
		}
	}
	
	public char minCh() throws ChaineVideException {
		if (estVide()) {
			throw new ChaineVideException();
		} else if (longueur() == 1) {
			return premier();
		} else {
			return Character.valueOf(premier()).compareTo(reste().minCh()) < 0 ?
					premier() : reste().minCh();
		}
	}
	
	public boolean croissante() throws ChaineVideException {
		if (estVide() || longueur() == 1) {
			return true;
		} else {
			return Character.valueOf(premier()).compareTo(deuxieme()) <= 0
					&& reste().croissante();
		}
	}
	
	public Chaine nNaturels(int n) throws ChaineVideException {
		if (n == 0) {
			return new Chaine();
		} else {
			return nNaturels(n-1).concat(new Chaine(""+n));
		}
	}
	
	public boolean palindrome() throws ChaineVideException {
		if (estVide() || longueur() == 1) {
			return true;
		} else if (premier() == dernier()) {
			return reste().saufDernier().palindrome();
		} else {
			return false;
		}
	}
	
	public boolean palindromeEspaces() throws ChaineVideException {
		if (estVide() || longueur() == 1) {
			return true;
		} else if (premier() == dernier()) {
			return saufDernier().reste().palindromeEspaces();
		} else if (premier() == ' ') {
			return reste().palindromeEspaces();
		} else if (dernier() == ' ') {
			return saufDernier().palindromeEspaces();
		} else {
			return false;
		}
	}
	
	public boolean palindromeWhile() throws ChaineVideException {
		if (estVide() || longueur() == 1) {
			return true;
		} else {
			Chaine temp = this; // Cannot assign final variable this
			while (temp.premier() == ' ' && temp.longueur() > 2) {
				temp = temp.reste();
			}
			while (temp.dernier() == ' ' && temp.longueur() > 2) {
				temp = temp.saufDernier();
			}
			if (temp.premier() == temp.dernier()) {
				return temp.saufDernier().reste().palindromeWhile();
			} else {
				return false;
			}
		}
	}
	
	public boolean anagramme(Chaine chaine) throws ChaineVideException {
		if (longueur() != chaine.longueur()) {
			return false;
		} else if (estVide()) {
			return true;
		} else if (chaine.supprime(premier()).longueur() == chaine.longueur() - 1) {
			return reste().anagramme(chaine.supprime(premier()));
		} else {
			return false;
		}
	}
	
	public boolean anagrammeEspaces(Chaine chaine) throws ChaineVideException {
		if (estVide() && chaine.estVide()) {
			return true;
		} else if (estVide() && !chaine.estVide()) {
			return false;
		} else if (premier() == ' ' ||
					longueur() - chaine.supprimeTout(premier()).longueur() ==
					chaine.longueur() - chaine.supprimeTout(premier()).longueur()) {
			return supprimeTout(premier()).anagrammeEspaces(chaine.supprimeTout(premier()));
		} else {
			return false;
		}
	}
	
	public Chaine union(Chaine chaine) throws ChaineVideException {
		if (estVide() && chaine.estVide()) {
			return this;
		} else {
			Chaine temp = concat(chaine);
			return temp.supprimeTout(temp.premier()).union(new Chaine())
					.adjT(temp.premier());
		}
	}
	
	public Chaine difference(Chaine chaine) throws ChaineVideException {
		if (chaine.estVide()) {
			return union(new Chaine());
		} else {
			return supprimeTout(chaine.premier()).difference(chaine.supprimeTout(chaine.premier()));
		}
	}
	
	public String toString() {
		return tete == null ? "" : tete.toString();
	}
}

