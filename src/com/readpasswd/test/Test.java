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
			System.out.println("û��console");
		}
		
	}

}
//������Գ����ʼ�Ĵ�������� cons������û�У�����null�����е���null�ͻ��������
//��java��java.lang.NullPointerExceptionһ�����ĵ����Ⱑ
//����쳣������Ϊ����null����ķ��� , ,����һ������û��û��������ʼ�� ���ȵ������ķ���
//������˼���ǵ�java�����Ǵ������������е�ʱ�򣬲��ұ�׼���������û�б��ض�����Ļ���console�Ǵ��ڵġ���vm���Զ����еģ�Ӧ���ǰ�����ide�������������system.console()���صľ���null��