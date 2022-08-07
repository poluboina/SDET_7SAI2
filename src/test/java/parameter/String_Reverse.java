package parameter;

public class String_Reverse {
	public static void main(String[] args) {
		String str="saikrishna";
		String str1=" ";
		for(int i=0;i<str.length();i++) {
			char rev = str.charAt(i);
			str1=rev+str1;
			
		}
		System.out.println(str1);
	}

}
