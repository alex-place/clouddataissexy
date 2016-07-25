package co.porkopolis.exceptions;

public class SummonerNotFoundException extends Exception {

	private static final long serialVersionUID = 1997753363232807009L;

	public SummonerNotFoundException() {
	}

	public SummonerNotFoundException(String message) {
		super(message);
	}

	public SummonerNotFoundException(Throwable cause) {
		super(cause);
	}

	public SummonerNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public SummonerNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}