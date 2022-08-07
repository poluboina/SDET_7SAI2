package practice;

public class Swaping {
	public static void main(String[] args) {
		int i=10;
		int j=20;
		int k;
		System.out.println("BeforeSwap"+" "+i+" "+j);
		k=i;
		i=j;
		j=k;
		k=i;
		System.out.println("AfterSwap"+" "+ k+" "+ i +" "+j);
	}

}
