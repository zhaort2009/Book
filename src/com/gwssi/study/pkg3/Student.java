package com.gwssi.study.pkg3;

import java.io.Serializable;

class Student implements Serializable {
	//1 ���ȶ�����һ����Student��ʵ����Serializable�ӿ�
	    int id;  
	    String name; 
	    int age; 
	    transient String department; 
	// Java���ԵĹؼ���[������]��������ʾһ�����Ǹö����л���һ���֡�
	 
	    public Student(int id, String name, int age, String department) {
	       this.id = id;
	       this.name = name;
	       this.age = age;
	       this.department = department;
	    }
	}