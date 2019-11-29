
import java.io.*;
import java.util.*;


public class AffinCipher {
	
	String encrypt(String str)
	{
		int a,b,l;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter key(a,b) ");
		a = sc.nextInt();
		b = sc.nextInt();
		if(gcd(a,26)==1 && a<26 && b>=0 &&b<26) //condition check
		{
			char[] en = str.toCharArray();
			l = str.length();
			for(int i=0;i<l;i++)
			{
				en[i] = (char)((a*((int)en[i]-65)+b)%26+65);  //encryption
			}
			String encrypted = new String(en);
			return encrypted;
		}
		else
			return "Affin condition is not satisfying";
	}
	// function to find gcd
	 int gcd(int a, int b) {
		// TODO Auto-generated method stub
		 int x,y,r=1;
		 if(a<b)
		 {
			 x = b;
			 y = a;
		 }
		 else
		 {
			 x = a;
			 y = b;
		 }
		 while(r!=0)
		 {
		 r = x%y;
		 if(r==0)
			 break;
		 x = y;
		 y = r;
		 }
		// System.out.println("===="+y);
		return y;
	}
	String decrypt(String str)
	{
		int a,b,l;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter key(a,b) ");
		a = sc.nextInt();
		b = sc.nextInt();
		if(gcd(a,26)==1 && a<26 && b>=0 &&b<26) //condition check
		{
			char[] dec = str.toCharArray();
			l = str.length();
			for(int i=0;i<l;i++)
			{
				dec[i] = (char)(((((int)dec[i]-65-b)*inverse(a))%26+26)%26+65);  //decryption
			}
			String encrypted = new String(dec);
			return encrypted;
		}
		else
			return "Affin condition is not satisfying";
		
	}
	int inverse(int a)
	{
		for(int i=0;i<26;i++)
		{
			if(a*i%26==1)
			{
				
				//System.out.println("^^^^"+i);
				return i;
			}
		}
		return 0;
	}
	void bruteforce(String str)
	{
		int i,j,count=0;
		char[] dec = str.toCharArray();
		int l= str.length();
		for(i=1;i<25;i++)
		{
			if(gcd(i,26)==1)
			{
				for(j=1;j<26;j++)
				{
					for(int z=0;z<l;z++)
					{
						dec[z] = (char)(((((int)dec[z]-65-j)*inverse(i))%26+26)%26+65);  //decryption
					}
					String decry = new String(dec);
					System.out.println((count++)+". "+decry);
				}
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AffinCipher ac = new AffinCipher();
		String p,e,Encrypt,Decrypt;
		int n;
		Scanner sc = new Scanner(System.in);
		System.out.println("Choose one option\n 1.Encryption\n 2.Decryption\n 3.brute force");
		n = sc.nextInt();
		switch(n)
		{
		case 1 : System.out.println("Enter plaintext");
		         p = sc.next();
		         Encrypt = ac.encrypt(p);
		         System.out.println("Encrypted text : "+Encrypt);
		         ac.bruteforce(Encrypt);
		         break;
		case 2 : System.out.println("Enter encrypted text");
		         e = sc.next();
		         Decrypt = ac.decrypt(e);
		         System.out.println("Decrypted text : "+Decrypt);
		         //break;
		//case 3 : System.out.println("Enter encrypted text");
          //       e = sc.next();
                 ac.bruteforce(e);
                 break;
		}
		
		sc.close();
     
	}

}
