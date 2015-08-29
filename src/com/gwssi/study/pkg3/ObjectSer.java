package com.gwssi.study.pkg3;
import java.io.*;
public class ObjectSer {
    public static void main(String args[]) throws IOException,ClassNotFoundException{
       Student stu = new Student(101972040, "YaoMing", 27, "basketball");
       FileOutputStream fo = new FileOutputStream("data.ser");
       ObjectOutputStream so = new ObjectOutputStream(fo);
       try {
//2 通过对象输出流的writeObject()方法将Student对象保存到文件 data.ser中
      so.writeObject(stu);
           so.close();
       } catch (IOException e) {
           System.out.println(e);
       }
       stu = null;
      
      
       FileInputStream fi = new FileInputStream("data.ser");
       ObjectInputStream si = new ObjectInputStream(fi);
       try {
//3 通过对家输入流的readObjcet()方法从文件data.ser中读出保存的Student对象
           stu = (Student)si.readObject();
           si.close();
       } catch (IOException ex) {
           System.out.println(ex);
       }
      
       //4 从运行结果可以看到，通过串行化机制，可以正确地保存和恢复对象的状态
       System.out.println("Student Info:");
       System.out.println("ID:" + stu.id);
       System.out.println("Name:" + stu.name);
       System.out.println("Age:" + stu.age);
       System.out.println("Dep:" + stu.department);
    }
}