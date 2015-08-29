package com.readpasswd.test;

import java.io.Console;

public class Test {
	public static void main(String[] args){
		
		Console cons=System.console();
		if (cons!=null) {
			String username=cons.readLine("User name:");
			char[] passwd=cons.readPassword("Password:");
		}
		else {
			System.out.println("没有console");
		}
		
	}

}
//这个测试程序最开始的错误就在于 cons根本就没有，就是null，你有调用null就会出现问题
//在java中java.lang.NullPointerException一般是哪的问题啊
//这个异常都是因为调用null对象的方法 , ,就是一个对象还没有没有正常初始化 就先调用它的方法
//基本意思就是当java程序是从命令行中运行的时候，并且标准输入输出流没有被重定向过的话，console是存在的。当vm是自动运行的（应该是包含从ide运行这种情况，system.console()返回的就是null）