package ExceptionsPack;

public class VerifyExceptions {

	public static void checkValidUserName(String s) {
		char ch[]=s.toCharArray();
		boolean flag=false;
		for(int i=0;i<ch.length;i++) {
			if(ch[i]>='0' && ch[i]<='9') {
				flag=true;
				break;
			}
		}
		
		if(flag) {
			throw new UserDefinedRunTimeException("Invalid Username");
		}
	}
	
	public static void contactValidation(String s) {
		char ch[]=s.toCharArray();
		
		if(ch.length==10) {
			for(int i=0;i<ch.length;i++) {
				if(ch[i]<49 && ch[i]>57) {
					throw new UserDefinedRunTimeException("Invalid Contact");
				}
			}
		}else {
			throw new UserDefinedRunTimeException("Invalid Contact");
		}
		
		// 49 to 57
	}
	
	public static void checkValidEmail(String s) {
		char ch[]=s.toCharArray();
		int i=0;
		if(s.endsWith(".com") || s.endsWith(".in")) {
			if(ch[i]<49 && ch[i]>57) {
				throw new UserDefinedRunTimeException("Invalid Email");
			}
				
		}else {
			throw new UserDefinedRunTimeException("Invalid Email");
		}
	}
	
	
}
