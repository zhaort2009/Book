package com.interview.ali;
import java.io.Console;
import java.util.*;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Test {

	public static void main(String[] args){
		  String input = "1 fish 2 fish red fish blue fish";
		     Scanner s = new Scanner(input).useDelimiter("\\s*fish\\s*");   //��������ʽ�е���˼����
		     
		    //  ����\\s����ƥ��һ���հ׷����հ׷�,* ������0����������
		     //useDelimiter ����������������µĶ������Ҳ����˵���Ѳ����е�pattern�����Ƿָ���
		     //�ָ����ǲ����������ġ�
		     //ͬʱע��useDelimiter���ص���һ��Scanner�����Բſ��������� Scanner s=
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
		    	 //֮���������������ô��֣�����Ϊfill��Arrays���һ����̬����
		    	 //Math�Ǿ�̬�࣬fill�Ǿ�̬��������̬��Ҳ�����½�����
		    	 //��̬�������ǲ����½�����ֱ���������Ϳ�����
		    	 //���׼���࣬�����������Ĺ��캯����������ö�����÷�����
		     }
		     
		 reverseString.main(null);  
		     
	}
	
	 
}
