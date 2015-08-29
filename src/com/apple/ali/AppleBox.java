package com.apple.ali;
public class AppleBox {

/**
 * @param args
 */
public static void main(String[] args) {
AppleBox box = new AppleBox();
Thread consumer = new Thread(new AppleConsumer(box));
Thread producer = new Thread(new AppleProducer(box));
consumer.setName("[Consumer]");
producer.setName("[Producer]");
consumer.start();
producer.start();
}

// Max size of apple box
    private final int MAX_SIZE = 5;  
  
    // Box to put apple  
    private int appleNum;  
synchronized public boolean produce() { 
            if(appleNum == MAX_SIZE){
             System.out.println("Box is full,"+Thread.currentThread()+" release the box and wait");  
             return false;
            }            
            appleNum ++;    
            System.out.println(Thread.currentThread() + " put an apple!"); 
            return true;
        }  

synchronized public boolean consume(){
if(appleNum == 0){
System.out.println("Box is empty,"+Thread.currentThread()+" release the box and wait");
return false;
}
appleNum --;
System.out.println(Thread.currentThread() + " get an apple!");
return true;
}
}