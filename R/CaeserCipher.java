

import java.util.*;


public class CaeserCipher {
	
	static String enCrypt(String str, int m)
	{
		int l= str.length();
		char[] enc = str.toCharArray();
		for(int i=0;i<l;i++)
		{
			//enc[i] = (char)(((int)enc[i]-65+m)%26+65);
			// in java ((n%M)+M)%M = (n%M) in python 
			enc[i] = (char)(((((int)enc[i]-65+m)%26)+26)%26+65);
		}
		str = new String(enc);
		System.out.println("Encrypted String is "+str);
		return str;
		
	}
	
	static void deCrypt(String str, int m)
	{
		
		int l= str.length();
		char[] dec = str.toCharArray();
		
		for(int i=0;i<l;i++)
		{
			dec[i] = (char)(((((int)dec[i]-65-m)%26)+26)%26+65);
		}
		str = new String(dec);
		System.out.println("Decrypted String is "+str);
		
	}
	static void bruteForce(String str)
	{
		int key=0;
		String deStr;
		int l=str.length();
		char[] dec = str.toCharArray();
		char[] dec1= new char[l];
		for(;key<26;key++)
		{
			for(int i=0;i<l;i++)
			{
				dec1[i] = (char)(((((int)dec[i]-65-key)%26)+26)%26+65);
			}
			deStr = new String(dec1);
			System.out.println("String is "+deStr);
			
			
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the string(ENTER CAPITAL LETTERS");
		String str=sc.nextLine();
		System.out.println("Enter the operation\n 1.Encryption 2.Decryption");
		int m,n;
		String cipher=null;
		n = sc.nextInt();
		switch(n)
		{
		case 1 : System.out.println("Enter shift integer");  //key
		         m = sc.nextInt();
			     cipher=enCrypt(str,m); // caeser cipher
		         break;
		case 2 : System.out.println("Enter shift integer");   //key
		         m = sc.nextInt();
			     deCrypt(str,m);
		         break;
		
		}
        if(cipher!=null)		
		bruteForce(cipher);  //brute forcing the cipher text
			
	
	}
	

}
