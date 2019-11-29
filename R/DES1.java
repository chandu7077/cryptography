import java.util.*;
public class DES1 {
	String plain="";
	String plain1="";
	String ip="";
	String ip1="";
	
	String key="";
	String key1="";
	//String nkey="";
	String cipher="";
	String subkey[]=new String[16];
	int intialp[]= {58,50,42,34,26,18,10,2, 
	        60,52,44,36,28,20,12,4, 
	        62,54,46,38,30,22,14,6, 
	        64,56,48,40,32,24,16,8, 
	        57,49,41,33,25,17,9,1, 
	        59,51,43,35,27,19,11,3, 
	        61,53,45,37,29,21,13,5, 
	        63,55,47,39,31,23,15,7 };
	int finalp[]= {40,8,48,16,56,24,64,32, 
	        39,7,47,15,55,23,63,31, 
	        38,6,46,14,54,22,62,30, 
	        37,5,45,13,53,21,61,29, 
	        36,4,44,12,52,20,60,28, 
	        35,3,43,11,51,19,59,27, 
	        34,2,42,10,50,18,58,26, 
	        33,1,41,9,49,17,57,25 
	    }; 
	int keyp[]={14,17,11,24,1,5, 
	        3,28,15,6,21,10, 
	        23,19,12,4,26,8, 
	        16,7,27,20,13,2, 
	        41,52,31,37,47,55, 
	        30,40,51,45,33,48, 
	        44,49,39,56,34,53, 
	        46,42,50,36,29,32 
	    }; 
	int keyp1[]=  
	    {   57,49,41,33,25,17,9, 
	        1,58,50,42,34,26,18, 
	        10,2,59,51,43,35,27, 
	        19,11,3,60,52,44,36,           
	        63,55,47,39,31,23,15, 
	        7,62,54,46,38,30,22, 
	        14,6,61,53,45,37,29, 
	        21,13,5,28,20,12,4 
	    }; 
	int shift_table[]= 
	    {   1, 1, 2, 2, 
	        2, 2, 2, 2,  
	        1, 2, 2, 2,  
	        2, 2, 2, 1 
	    };
	int exp_d[]=  
	    {   32,1,2,3,4,5,4,5, 
	        6,7,8,9,8,9,10,11, 
	        12,13,12,13,14,15,16,17, 
	        16,17,18,19,20,21,20,21, 
	        22,23,24,25,24,25,26,27, 
	        28,29,28,29,30,31,32,1 
	    }; 
	int[][][] s= 
		{ { 
		    {14,4,13,1,2,15,11,8,3,10,6,12,5,9,0,7}, 
	        {0,15,7,4,14,2,13,1,10,6,12,11,9,5,3,8}, 
	        {4,1,14,8,13,6,2,11,15,12,9,7,3,10,5,0}, 
	        {15,12,8,2,4,9,1,7,5,11,3,14,10,0,6,13} 
	    }, 
		{ 
	    	{15,1,8,14,6,11,3,4,9,7,2,13,12,0,5,10},
	    	{3,13,4,7,15,2,8,14,12,0,1,10,6,9,11,5},
	        {0,14,7,11,10,4,13,1,5,8,12,6,9,3,2,15}, 
	        {13,8,10,1,3,15,4,2,11,6,7,12,0,5,14,9} 
	    },  
		{ 
	        {10,0,9,14,6,3,15,5,1,13,12,7,11,4,2,8}, 
	        {13,7,0,9,3,4,6,10,2,8,5,14,12,11,15,1}, 
	        {13,6,4,9,8,15,3,0,11,1,2,12,5,10,14,7}, 
	        {1,10,13,0,6,9,8,7,4,15,14,3,11,5,2,12} 
	    }, 
	    { 
	        {7,13,14,3,0,6,9,10,1,2,8,5,11,12,4,15}, 
	        {13,8,11,5,6,15,0,3,4,7,2,12,1,10,14,9}, 
	        {10,6,9,0,12,11,7,13,15,1,3,14,5,2,8,4}, 
{3,15,0,6,10,1,13,8,9,4,5,11,12,7,2,14} 
	    }, 
	    { 
	        {2,12,4,1,7,10,11,6,8,5,3,15,13,0,14,9}, 
	        {14,11,2,12,4,7,13,1,5,0,15,10,3,9,8,6}, 
	        {4,2,1,11,10,13,7,8,15,9,12,5,6,3,0,14}, 
	        {11,8,12,7,1,14,2,13,6,15,0,9,10,4,5,3} 
	    }, 
	    { 
	        {12,1,10,15,9,2,6,8,0,13,3,4,14,7,5,11}, 
	        {10,15,4,2,7,12,9,5,6,1,13,14,0,11,3,8}, 
	        {9,14,15,5,2,8,12,3,7,0,4,10,1,13,11,6}, 
	        {4,3,2,12,9,5,15,10,11,14,1,7,6,0,8,13} 
	    }, 
	    { 
	        {4,11,2,14,15,0,8,13,3,12,9,7,5,10,6,1}, 
	        {13,0,11,7,4,9,1,10,14,3,5,12,2,15,8,6}, 
	        {1,4,11,13,12,3,7,14,10,15,6,8,0,5,9,2}, 
	        {6,11,13,8,1,4,10,7,9,5,0,15,14,2,3,12} 
	    }, 
	    { 
	        {13,2,8,4,6,15,11,1,10,9,3,14,5,0,12,7}, 
	        {1,15,13,8,10,3,7,4,12,5,6,11,0,14,9,2}, 
	        {7,11,4,1,9,12,14,2,0,6,10,13,15,3,5,8}, 
	        {2,1,14,7,4,10,8,13,15,12,9,0,3,5,6,11} 
	    }}; 
	int per[]= 
	    {   16,7,20,21, 
	        29,12,28,17, 
	        1,15,23,26, 
	        5,18,31,10, 
	        2,8,24,14, 
	        32,27,3,9, 
	        19,13,30,6, 
	        22,11,4,25 
	    }; 

	String initial_permutation()
	
	{
		int i;
		String a="";
		for(i=0;i<64;i++) {
		a+=plain1.charAt(intialp[i]-1);
		}
		return a;
		//System.out.println(ip1);
		
	}
	
	String hex2bin(String text)
	{
		int i;
		String rough="";
		String a="";
		int k;
		String hex="0123456789ABCDEF";
		for(i=0;i<text.length();i++)
		{
			rough="";
			k=hex.indexOf(text.charAt(i));
			rough=Integer.toBinaryString(k);
			while(rough.length()<8)
			{
				rough="0"+rough;
			}
			a+=rough.substring(4,8);
			
		}
		return a;
		//System.out.println(plain1);
	}
	String bin2hex(String text)
	{
		int i;
		int k;
		String a="";
		String hex="0123456789ABCDEF";
		for(i=0;i<text.length();i=i+4)
		{
			k=Integer.parseInt(text.substring(i,i+4),2);
			a+=hex.charAt(k);
			
			
		}
		return a;
		//System.out.println(ip);
	}
	String permutekey(String k1)
	{
		int k;
		String a="";
		for(k=0;k<48;k++)
		{
			a+=k1.charAt(keyp[k]-1);
		}
		return a;
	}

	String des_decry(String l,String r,boolean isdecrypt)
	{
		String k1="",k2="",k3="",k4="",k5="";
		int i,j,k;
		String fp="";
	if(!isdecrypt) {
		k1=key.substring(0,28); 
	    k2=key.substring(28,56);
		}
		for(i=0;i<16;i++)
		{
			if(isdecrypt)
			{
			
			System.out.println("ROUND "+(i+1)+" :  ");
			}
			//System.out.println();
			if(!isdecrypt)
			{
			j=shift_table[i];
			
		
		    k3="";
		    k4="";
			for(k=j;k<28;k++)
			{
				k3+=k1.charAt(k);
				k4+=k2.charAt(k);
			}
			if(j==1)
			{
				k3+=k1.charAt(0);
				k4+=k2.charAt(0);
			}
			if(j==2)
			{
				k3+=Character.toString(k1.charAt(0))+Character.toString(k1.charAt(1));
				k4+=Character.toString(k2.charAt(0))+Character.toString(k2.charAt(1));
			}
			
			//permute key
			k5=k3+k4;
			k5=permutekey(k5);
			subkey[i]=k5;
			//System.out.println("key :  "+bin2hex(k5));
			
			}
			else
			{
				k5=subkey[15-i];
				System.out.println("key :"+bin2hex(k5));
			}
			//expand p box
			//String r1=d.hex2bin(r0);
			String r2="";
			for(k=0;k<48;k++)
			{
				r2+=Character.toString(r.charAt(exp_d[k]-1));
			}
			
			String p="";
			for(j = 0; j< k5.length(); j++) {
	            
	         p+=k5.charAt(j)^r2.charAt(j);
			}
			
			
			
			String w="";
			int y=0;
			for(k=0;k<48;k=k+6)
			{
				r2="";
				//System.out.println(r2.length());
				String b1=p.substring(k,k+6);
				
				int x1=Integer.parseInt(Character.toString(b1.charAt(0))+Character.toString(b1.charAt(5)),2);
				
				int x2=Integer.parseInt(b1.substring(1,5),2);
				
				r2=Integer.toBinaryString(s[y][x1][x2]);
				
				while(r2.length()<4)
				{
				r2="0"+r2;
				}
				w+=r2;
				y+=1;
			
			}
		
			r2="";
			for(j=0;j<32;j++)
			{
				r2+=Character.toString(w.charAt(per[j]-1));
				
			}
			
			p="";
			
			for(j = 0; j< r2.length(); j++) {
	            
		         p+=r2.charAt(j)^l.charAt(j);
				}
			
			
			k1=k3;
			k2=k4;
			l=r;
			r=p;
			if(isdecrypt)
			{
			System.out.println("L : "+bin2hex(l)+"  R  : "+bin2hex(r));
			System.out.println();
			}//System.out.println("-----------------------");
			
		}
		String temp=l;
		l=r;
		r=temp;
		System.out.println("L : "+bin2hex(l)+" R : "+bin2hex(r));
		k1=l+r;
		for(i=0;i<64;i++)
		{
		fp+=k1.charAt(finalp[i]-1);
		}
		if(!isdecrypt)
		{
			//System.out.println();
			//System.out.println("encryption : "+bin2hex(fp));
		}
		else
		{
			System.out.println();
			System.out.println("decryption : "+bin2hex(fp));
		}
		return fp;
		
	}
	String des_encry(String l,String r,boolean isdecrypt)
	{
		String k1="",k2="",k3="",k4="",k5="";
		int i,j,k;
		String fp="";
	if(!isdecrypt) {
		k1=key.substring(0,28); 
	    k2=key.substring(28,56);
		}
		for(i=0;i<16;i++)
		{
			
			System.out.println("ROUND "+(i+1)+" :  ");
			//System.out.println();
			if(!isdecrypt)
			{
			j=shift_table[i];
			
		
		    k3="";
		    k4="";
			for(k=j;k<28;k++)
			{
				k3+=k1.charAt(k);
				k4+=k2.charAt(k);
			}
			if(j==1)
			{
				k3+=k1.charAt(0);
				k4+=k2.charAt(0);
			}
			if(j==2)
			{
				k3+=Character.toString(k1.charAt(0))+Character.toString(k1.charAt(1));
				k4+=Character.toString(k2.charAt(0))+Character.toString(k2.charAt(1));
			}
			
			//permute key
			k5=k3+k4;
			k5=permutekey(k5);
			subkey[i]=k5;
			System.out.println("key :  "+bin2hex(k5));
			
			}
			else
			{
				k5=subkey[15-i];
				System.out.println("key :"+bin2hex(k5));
			}
			//expand p box
			//String r1=d.hex2bin(r0);
			String r2="";
			for(k=0;k<48;k++)
			{
				r2+=Character.toString(r.charAt(exp_d[k]-1));
			}
			
			String p="";
			for(j = 0; j< k5.length(); j++) {
	            
	         p+=k5.charAt(j)^r2.charAt(j);
			}
			
			
			
			String w="";
			int y=0;
			for(k=0;k<48;k=k+6)
			{
				r2="";
				//System.out.println(r2.length());
				String b1=p.substring(k,k+6);
				
				int x1=Integer.parseInt(Character.toString(b1.charAt(0))+Character.toString(b1.charAt(5)),2);
				
				int x2=Integer.parseInt(b1.substring(1,5),2);
				
				r2=Integer.toBinaryString(s[y][x1][x2]);
				
				while(r2.length()<4)
				{
				r2="0"+r2;
				}
				w+=r2;
				y+=1;
			
			}
		
			r2="";
			for(j=0;j<32;j++)
			{
				r2+=Character.toString(w.charAt(per[j]-1));
				
			}
			
			p="";
			
			for(j = 0; j< r2.length(); j++) {
	            
		         p+=r2.charAt(j)^l.charAt(j);
				}
			
			
			k1=k3;
			k2=k4;
			l=r;
			r=p;
			System.out.println("L : "+bin2hex(l)+"  R  : "+bin2hex(r));
			System.out.println();
			//System.out.println("-----------------------");
			
		}
		String temp=l;
		l=r;
		r=temp;
		System.out.println("L : "+bin2hex(l)+" R : "+bin2hex(r));
		k1=l+r;
		for(i=0;i<64;i++)
		{
		fp+=k1.charAt(finalp[i]-1);
		}
		if(!isdecrypt)
		{
			System.out.println();
			System.out.println("encryption : "+bin2hex(fp));
		}
		else
		{
			System.out.println();
			System.out.println("decryption : "+bin2hex(fp));
		}
		return fp;
		
	}
	public static void main(String[] args) {
		
		int k;
		DES1 d=new DES1();
		Scanner sc=new Scanner(System.in);
		System.out.println("enetr text in hexadecimal");
		d.plain=sc.next();
		System.out.println("Key in hexadecimal");
		d.key=sc.next();
		//System.out.println(d.key);
		
		
		d.key1=d.hex2bin(d.key);
		//System.out.println(d.key1.length());
		d.key="";
		for(k=0;k<56;k++)
		{
				d.key+=d.key1.charAt(d.keyp1[k]-1);
		}
	    
	   
	   
		//d.nkey=d.bin2hex(d.key);
		
		//System.out.println(d.ip);
		//System.out.println(d.nkey);
		//done with initial permutation.Now go for rounds
		System.out.println("1.Encryption");
		System.out.println("2.Decryption");
		int sahi=sc.nextInt();
		switch(sahi)
		{
		case 1:
			d.plain1=d.hex2bin(d.plain);
			d.plain1=d.initial_permutation();
			d.ip=d.bin2hex(d.plain1);
		String l0=d.plain1.substring(0,32);
		String r0=d.plain1.substring(32,64);
		System.out.println("L0: "+d.bin2hex(l0)+"  R0: "+d.bin2hex(r0));
		
		
	    
	    
	    System.out.println("-------------ENCRYPTION----------------");
	    System.out.println();
		
		d.plain1=d.des_encry(l0,r0,false);
		
		System.out.println();
		break;
		case 2:
			d.plain1=d.hex2bin(d.plain);
		d.plain1=d.initial_permutation();
		d.ip=d.bin2hex(d.plain1);
		l0=d.plain1.substring(0,32);
		r0=d.plain1.substring(32,64);
		System.out.println("L0: "+d.bin2hex(l0)+"  R0: "+d.bin2hex(r0));
		
		System.out.println("-------------DECRYPTION----------------");
	    System.out.println();
		
		d.cipher=d.des_decry(l0,r0,false);
		d.cipher=d.des_decry(l0,r0,true);
		
		break;
		}
	
	}

}
