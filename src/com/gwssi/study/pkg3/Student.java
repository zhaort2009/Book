package com.gwssi.study.pkg3;

import java.io.Serializable;

class Student implements Serializable {
	//1 首先定义了一个类Student，实现了Serializable接口
	    int id;  
	    String name; 
	    int age; 
	    transient String department; 
	// Java语言的关键字[保留字]，用来表示一个域不是该对象串行化的一部分。
	 
	    public Student(int id, String name, int age, String department) {
	       this.id = id;
	       this.name = name;
	       this.age = age;
	       this.department = department;
	    }
	}