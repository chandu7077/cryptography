import java.util.*;
public class DES {
	int [] stringTobitArray(String s)
	{
		char []arr=s.toCharArray();
		int []bit_array=new int[arr.length*8];
		for(int i=0;i<arr.length;i++)
		{
			int temp = (int)arr[i];int k=i+1;
			String t =Integer.toBinaryString(temp);
			while(t.length()<8)
			{
				t ="0"+t;
			}
			for(int j=0;j<8;j++)
			{
				bit_array[j*k]=Integer.parseInt(t.charAt(j)+"");
			}
			
			
			
		}
		return bit_array;
		
	}
	int[] InitialPermutation(int b[])
	{
		
		int permuted_array[]=new int[b.length];
		 int initial_perm[]=  
			    {   58,50,42,34,26,18,10,2, 
			        60,52,44,36,28,20,12,4, 
			        62,54,46,38,30,22,14,6, 
			        64,56,48,40,32,24,16,8, 
			        57,49,41,33,25,17,9,1, 
			        59,51,43,35,27,19,11,3, 
			        61,53,45,37,29,21,13,5, 
			        63,55,47,39,31,23,15,7 
			    }; 
		 
		 for(int i=0;i<b.length;i++)
		 {
			 permuted_array[initial_perm[i]-1]=b[i];
		 }
		
		return permuted_array;
	}
	
	int[] FinalPermutation(int b[])
	{
		
		int permuted_array[]=new int[b.length];
		int final_perm[]=  
		    {   40,8,48,16,56,24,64,32, 
		        39,7,47,15,55,23,63,31, 
		        38,6,46,14,54,22,62,30, 
		        37,5,45,13,53,21,61,29, 
		        36,4,44,12,52,20,60,28, 
		        35,3,43,11,51,19,59,27, 
		        34,2,42,10,50,18,58,26, 
		        33,1,41,9,49,17,57,25 
		    }; 
		 
		 for(int i=0;i<b.length;i++)
		 {
			 permuted_array[final_perm[i]-1]=b[i];
		 }
		
		return permuted_array;
	}
	int [] F(int []b)
	{
		int Expanded_Pbox[]=new int[48];
		
		int index1=1;int index2=0;
		for(int i=0;i<8;i++)
		{
			if(index1<48&&index2<32)
			{
				if(i<7)
				{
			for(int j=0;j<5;j++)
			{
		     Expanded_Pbox[index1++]=b[index2++];
			}}
				if(i==7)
				{
					for(int j=0;j<4;j++)
					{
				     Expanded_Pbox[index1++]=b[index2++];
					}
				}
			index2=index2-2;
			Expanded_Pbox[index1++]=b[index2++];
			}
			else
				break;
		}
		Expanded_Pbox[0]=b[31];
		Expanded_Pbox[47]=b[0];
		int key[]=generate_key();
		int after_xor[]=new int[48];
		for(int i=0;i<48;i++)
		{
			if(Expanded_Pbox[i]==key[i])
			after_xor[i]=0;
			else
				after_xor[i]=1;
		}
		int s[][][]= 
		    {{ 
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
		  int sbox_result[] = new int[32];int index=0;index1=0;
		  for(int i=0;i<8;i++)
		  {
			  String vertical = String.valueOf(after_xor[index])+String.valueOf(after_xor[index+5]);
			  index++;
			  String horizontal=String.valueOf(after_xor[index++])+String.valueOf(after_xor[index++])+String.valueOf(after_xor[index++])+String.valueOf(after_xor[index++]);
			  index++;
			  int v=Integer.parseInt(vertical,2);
			  int h=Integer.parseInt(horizontal,2);
			  int k=s[i][v][h];
			  String t=Integer.toBinaryString(k);
			  while(t.length()<4)
			  {
				  t="0"+t;
			  }
			  char arr[]=t.toCharArray();
			  for(int j=0;j<4;j++)
			  {
				  sbox_result[index1++]=Integer.parseInt(arr[j]+"");
			  }
			  
		  }
		  
		  
		return sbox_result; 
		
		
	}
	int [] generate_key()
	{
	    int b[]=new int[48];
	    Random r =new Random();
	    for(int i=0;i<48;i++)
	    	b[i]=r.nextInt(2);
		return b;
	}
	
	int[] rounds_16(int []b)
	{
		int L[]=new int[32];
		int R[]=new int[32];
		for(int r=0;r<16;r++)
		{
		for(int i=0;i<=31;i++)
		{
			L[i]=b[i];
		}
		for(int i=32;i<=63;i++)
		{
			R[i-32]=b[i];
		}
	    int function_output[]=F(R);	
	 	for(int i=0;i<32;i++)
		{
	 		int temp=R[i];
	 		R[i]=L[i]^function_output[i];
	 		L[i]=temp;
	 		
		}
			
		}
	 	
	 	
		int output[]=new int[64];
		for(int i=0;i<32;i++)
		{
			output[i]=L[i];
		}
		for(int i=0;i<32;i++)
		{
			output[i+32]=R[i];
		}
	 return output;
		
		
	}
	String encrypt(String p)
	{
		String d="";
		int bitArray[]=stringTobitArray(p);
		int Initial_permuted[]=InitialPermutation(bitArray);
		int temp[] = rounds_16(Initial_permuted);
		int Final_permuted[]=FinalPermutation(temp);
		int index=0;
	
		for(int i=0;i<8;i++)
		{
			String s="";
			for(int j=0;j<8;j++)
			{
				
				s+= Final_permuted[index++]+""; 
			}
	
			int t=Integer.parseInt(s,2);
			char c = (char)t;
			d +=c+"";
			
		}
		
			return d;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Input is in 1)Hexadecimal 2)String");
		int choice=sc.nextInt();
		System.out.println("Enter text:");
		String P=sc.next();
		if(choice==1)
		{
			String tt="";
			char hex[]=P.toCharArray();
			for(int i=0;i<hex.length;i =i+2)
			{
				String temp=hex[i]+hex[i+1]+"";
				int t=Integer.parseInt(temp,16);
				tt += (char)t+"";
				
				
			}
			P=tt;
			
		}
		DES d=new DES();
		System.out.println("Encrpted:"+" "+d.encrypt(P));
		

	}

}
