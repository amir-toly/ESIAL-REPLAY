package chaine;

public class ChaineVideException extends Exception {
	
	public ChaineVideException() {
		super("This operation cannot be performed on an empty string:");
	}
	
	public ChaineVideException(String msg) {
		super(msg);
	}
}

