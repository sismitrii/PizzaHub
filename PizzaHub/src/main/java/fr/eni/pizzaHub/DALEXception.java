package fr.eni.pizzaHub;

public class DALEXception extends Exception {
	private static final long serialVersionUID = 1L;

	public DALEXception(String message, Throwable cause) {
		super(message, cause);
	}

	public DALEXception(String message) {
		super(message);
	}
}
