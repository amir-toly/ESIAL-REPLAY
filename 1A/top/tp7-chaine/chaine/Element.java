package chaine;

public class Element {
	public char val;
	public Element suite;
	
	public Element(char val) {
		this.val = val;
	}
	
	public String toString() {
		return val + "" + (suite == null ? "" : suite.toString());
	}
}

