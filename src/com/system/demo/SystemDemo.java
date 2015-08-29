package com.system.demo;

import java.lang.*;

public class SystemDemo {

   public static void main(String[] args) throws Exception {

      // gets the value of the specified environment variable "PATH"
      System.out.println("System.getenv(\"PATH\")" + "=");
      System.out.println(System.getenv("PATH"));

      // gets the value of the specified environment variable "TEMP"
      System.out.print("System.getenv(\"TEMP\")" + "=");
      System.out.println(System.getenv("TEMP"));

      // gets the value of the specified environment variable "USERNAME"
      System.out.print("System.getenv(\"USERNAME\")" + "=");
      System.out.println(System.getenv("USERNAME"));

      System.out.print("System.getenv(\"programfiles\")" + "=");
      System.out.println(System.getenv("programfiles"));

   }
} 