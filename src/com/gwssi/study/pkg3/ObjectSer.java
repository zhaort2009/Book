package com.gwssi.study.pkg3;
import java.io.*;
public class ObjectSer {
    public static void main(String args[]) throws IOException,ClassNotFoundException{
       Student stu = new Student(101972040, "YaoMing", 27, "basketball");
       FileOutputStream fo = new FileOutputStream("data.ser");
       ObjectOutputStream so = new ObjectOutputStream(fo);
       try {
//2 ͨ�������������writeObject()������Student���󱣴浽�ļ� data.ser��
      so.writeObject(stu);
           so.close();
       } catch (IOException e) {
           System.out.println(e);
       }
       stu = null;
      
      
       FileInputStream fi = new FileInputStream("data.ser");
       ObjectInputStream si = new ObjectInputStream(fi);
       try {
//3 ͨ���Լ���������readObjcet()�������ļ�data.ser�ж��������Student����
           stu = (Student)si.readObject();
           si.close();
       } catch (IOException ex) {
           System.out.println(ex);
       }
      
       //4 �����н�����Կ�����ͨ�����л����ƣ�������ȷ�ر���ͻָ������״̬
       System.out.println("Student Info:");
       System.out.println("ID:" + stu.id);
       System.out.println("Name:" + stu.name);
       System.out.println("Age:" + stu.age);
       System.out.println("Dep:" + stu.department);
    }
}