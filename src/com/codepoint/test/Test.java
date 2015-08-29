package com.codepoint.test;

import java.math.BigDecimal;



public class Test{
	public static void main(String...args){		
	      BigDecimal bigDecimal=new BigDecimal(2);
	      bigDecimal=BigDecimal.valueOf(100,2);
	      System.out.println(bigDecimal);
	      //如上是测试一下这个函数的功能，果真返回的是100*10^(-2)  变小了
	     
	      
		
		
		
		char[] ch = Character.toChars(0x10400);
		//在unicode当中，基本的多语言级别，最多只有16位，共65536个，这里helloworld，这些字符都是处于基本的级别
		//它们的unicode的代码点，都处在U+ 0000 到 U+ 0x FFFF   也就是0000 到FFFF
		//题目中的这个0x10400，明显超过了这个范围，就并不是基本的多语言级别的字符。那么char的话是只有16位的，那么转化成两个
		//就是两个char了，有个编码算法D800~DBFF用于第一个代码单元，DC00-DFFF用于第二个代码单元。经过一个编码算法，组合成一个
		//附加级别的代码点，辅助字符。U+10000到10FFFF，
		//本题就是10400这个辅助字符，由两个代码单元D801和DC00来编码。
		System.out.println(ch[0]);
		System.out.println(ch[1]);  //这样输出的就是问号，因为unicode的D801就是没有相应的可打印的字符，这个区域就是用来编码其它代码点的
		System.out.printf("U+10400 高代理字符: %04x\n", (int)ch[0]);//d801
        System.out.printf("U+10400 低代理字符: %04x\n", (int)ch[1]);//dc00   
        String str = new String(ch);
        System.out.println("代码单元长度: " + str.length());//2
        System.out.println("代码点数量: " + str.codePointCount(0, str.length()));//1
		System.out.println(str.codePointAt(0));//返回给定位置开始或结束的代码点,66560
		System.out.println(str.charAt(1));//返回给定位置的代码单元,由于未定义，返回?
		
		//遍历一个字符串,打印出所有字符的代码点
		System.out.println(str);
		System.out.println(str.length());
		
		str += "Hello,world!";
		String greeting ="Hello,world!";
		int i = 0;
		
		System.out.println(str.length());
		System.out.println(greeting.length());
		int index=str.offsetByCodePoints(0, 0);
		int index1=str.offsetByCodePoints(0, 1);
		int index2=str.offsetByCodePoints(0, 2);
		System.out.println(index);   //得到0
		System.out.println(index1);   //得到2   这就是给的参数是第二个代码点，下标1，然后计算出来它在字符串中的下标，一个字符一个char，
		                             //由于第一个代码点是占据了两个字符，所以第二个代码点自然是从2开始的

		System.out.println(index2);   //得到3
		
		while(i < str.length()){
			int cp = str.codePointAt(i);  //codePointAt 可以取出对应的第几个char的代码点，D801 DC
			                             //codePointAt直接取出的就是第一个代码点，取的不是D801 DC00 而是66560 就是0x10400
			                               // 所以说codePointAt是很智能的取代码点的。这个i是一个代码点起始的字符char index，所以遇到辅助字符，得跳两个
			                                //才是下一个代码点的起始位置
			System.out.println(str.codePointAt(i));
			if(Character.isSupplementaryCodePoint(cp))
				i += 2;//如果cp所在的位置是代码点的第一部分，执行此处 
			           //Determines whether the specified character (Unicode code point) is in the supplementary character range.
			           //因为代码点66560就是辅助字符。也就是i应该加2，从这个位置开始取下一个代码点。也就是跳过D801和DC00
			else i++;
		}
		/*
		 * 66560 
		 * 72 
		 * 108 
		 * 111 
		 * 119 
		 * 114 
		 * 100
		 */
	}
}
