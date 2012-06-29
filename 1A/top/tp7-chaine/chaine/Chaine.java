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
	
	public Chaine supprimeTout(char c) throws ChaineVideException {
		if (estVide()) {
			return this;
		} else if (premier() == c) {
			return reste().supprimeTout(c);
		} else {
			return reste().supprimeTout(c).adjT(premier());
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
	
	public Chaine concat(Chaine chaine) throws ChaineVideException {
		if (estVide()) {
			return chaine;
		} else {
			return saufDernier().concat(chaine.adjT(dernier()));
		}
	}
	
	public char minCh() throws ChaineVideException {
		if (estVide()) {
			throw new ChaineVideException();
		} else if (longueur() == 1) {
			return premier();
		} else {
			return Character.compare(premier(), reste().minCh()) < 0 ?
					premier() : reste().minCh();
		}
	}
	
	public boolean croissante() throws ChaineVideException {
		if (estVide() || longueur() == 1) {
			return true;
		} else {
			return Character.compare(premier(), deuxieme()) <= 0
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
	
	public Chaine union(Chaine chaine) throws ChaineVideException {
		if (estVide() && chaine.estVide()) {
			return this;
		} else {
			Chaine temp = concat(chaine);
			return temp.supprimeTout(temp.premier()).union(new Chaine())
					.adjT(temp.premier());
		}
	}
	
	public String toString() {
		return tete == null ? "" : tete.toString();
	}
}

