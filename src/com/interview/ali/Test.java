package com.interview.ali;
import java.io.Console;
import java.util.*;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Test {

	public static void main(String[] args){
		  String input = "1 fish 2 fish red fish blue fish";
		     Scanner s = new Scanner(input).useDelimiter("\\s*fish\\s*");   //在正则表达式中的意思就是
		     
		    //  就是\\s可以匹配一个空白符，空白符,* 可以是0到无穷多个，
		     //useDelimiter 翻译过来就是用如下的定界符，也就是说，把参数中的pattern当做是分隔符
		     //分隔符是不会进行输出的。
		     //同时注意useDelimiter返回的是一个Scanner，所以才可以这样用 Scanner s=
		     System.out.println(s.nextInt());
		     System.out.println(s.nextInt());
		     System.out.println(s.next());
		     System.out.println(s.next());
		     String pString=s.delimiter().toString();
		     System.out.println(pString);
		     String lString=s.locale().toString();
		     System.out.println(lString);
		     int radix=s.radix();
		     System.out.println(radix);
		     s.close(); 
		     
		     
		     
		     String input1 = "1 fish 2 fish red fish blue fish";
		     Scanner s1 = new Scanner(input1);
		     s1.findInLine("(\\d+) fish (\\d+) fish (\\w+) fish (\\w+)");
		     
		     MatchResult result = s1.match();
		     for (int i=1; i<=result.groupCount(); i++)
		         System.out.println(result.group(i));
		     s1.close(); 
		     
		     Pattern p = Pattern.compile("a*b");
		     Matcher m = p.matcher("aaaaab");
		     boolean b = m.matches();
		     
		     boolean b1 = Pattern.matches("a*b", "aaaaab");
		     System.out.println(b1);
		     System.out.println(b);
		     
		     System.out.println("\r");
		     
		     
		     Console cons;
		     char[] passwd;
		     if ((cons = System.console()) != null &&
		         (passwd = cons.readPassword("[%s]", "Password:")) != null) {
		 
		    	 java.util.Arrays.fill(passwd, ' ');
		    	 //之所以这个函数名这么奇怪，是因为fill是Arrays类的一个静态函数
		    	 //Math是静态类，fill是静态函数，静态类也不用新建对象
		    	 //静态函数就是不用新建对象，直接用类名就可以用
		    	 //最标准的类，就是用类名的构造函数构造对象，用对象调用方法。
		     }
		     
		 reverseString.main(null);  
		     
	}
	
	 
}
