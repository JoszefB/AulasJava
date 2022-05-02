package controller;

public class ExceptionCustomized extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ExceptionCustomized(String msg) {
		super(msg);
	}
}