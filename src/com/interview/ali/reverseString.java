package com.interview.ali;





public class reverseString {
	
	public static void main(String[] args){
        
         char[] s2={'I',32,'a','m',32,'a',32,'s','t','u','d','e','n','t',32};
         System.out.println(s2);
       
		 reverse(s2);
				 
	}
	
	
	public static void reverse(char[] s){
		    int z=0;
		    int j=s.length-1;
		    char[] s3=s.clone();
		for(int i=0;i<=s.length-1;i++){
			
			if(Character.isWhitespace(s[i])){
				
				
				for (int x=z;x < i; x++) {
					s3[j-i+x+1]=s[x]; 
					
				}
				
				j=j-i+z-1;
				z=i+1;
				s3[j+1]=32;
				
				
			}
			
			
			
		}
		System.out.print(s3);
		
		
		
	}
	
     

}
