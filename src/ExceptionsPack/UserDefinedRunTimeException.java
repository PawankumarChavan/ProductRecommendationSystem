package ExceptionsPack;

public class UserDefinedRunTimeException extends ArithmeticException{

	private String s=null;
	
	public UserDefinedRunTimeException(String s) {
		this.s=s;
	}
	
	public String getUserDefinedException() {
		return s;
	}
 	
}
