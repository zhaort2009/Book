package com.apple4.ali;

public class Test {
    public static void main(String[] args) {
        Box box = new Box();
        Thread producer = new Thread(new ProduceApple(box));
        Thread  customer = new Thread(new CustomApple(box));
        
        producer.start();
        customer.start();
        
    }
}

class Box{
    int boxLength = 5;                                                                        //box容量
    //int buff[] = new int[boxLength];
    int putPoint = 0;
    int getPoint = 0;
    int boxCount = 0;                                                                        //box已经放的数量
}

//生产苹果，生产者
class ProduceApple implements Runnable{
    Box box;
    
    public ProduceApple(Box box) {
        this.box = box;
    }
    @Override
    public void run() {
        while(true){
            synchronized (box) {            
                //box.putPoint = box.putPoint % box.boxLength;
                if(box.boxCount == 5){                                                        //没有放苹果的地方，等待消费者消费苹果
                    try {
                        box.wait();                        
                    } catch (InterruptedException e) {                    
                        e.printStackTrace();
                    }
                }//if
                else{                                                                        //生产一个苹果放到箱子中
                    //box.buff[(++box.putPoint) % box.boxLength] = 1;
                    box.boxCount++;
                    System.out.println("放进一个苹果");
                    System.out.println("苹果总数：" + box.boxCount);
                    box.notify();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }//else
                    
            }
        }
        
    }    
}

//消费苹果，消费者
class CustomApple implements Runnable{
    Box box;
    public CustomApple(Box box){
        this.box = box;
    }
    @Override
    public void run() {
        while(true){
            synchronized (box) {
                //box.getPoint = box.getPoint % box.boxLength;
                if(box.boxCount == 0){                                                            //没有可以吃的苹果，等待生产者生产苹果
                    try {
                        box.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }//if
                else{                                                                            //吃掉一个苹果，唤醒生产者
                    //box.buff[(++box.getPoint) % box.boxLength] = 0;
                    System.out.println("吃掉一个苹果");
                    box.boxCount--;
                    System.out.println("苹果总数：" + box.boxCount);
                    box.notify();
                    
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }//else
            }
        }
        
    }
    
}