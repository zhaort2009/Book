package com.apple.ali;

import java.util.Random;

public class AppleConsumer implements Runnable  
{  
Random randomTimer = new Random();
    // 每次生产的产品数量  
    private int num;  
  
    // 所在放置的仓库  
    private AppleBox box;  
  
    // 构造函数，设置仓库  
    public AppleConsumer(AppleBox box){  
        this.box = box;  
    }  
  
    // 线程run函数  
    public void run(){  
     while(true){
     try {
Thread.sleep(randomTimer.nextInt(1000));
box.consume(); 
     } catch (InterruptedException e) { 
     e.printStackTrace();
     }
     }       
    }  
  
    // 调用仓库Storage的生产函数  
   public void produce()  
    {  
        box.produce();  
    }  
  
    // gett方法  
   /* public int getNum()  
    {  
        return num;  
    }  
  
    public void setNum(int num)  
    {  
        this.num = num;  
    }  
  
    public AppleBox getBox()  
    {  
        return this.box;  
    }  
  
    public void setStorage(AppleBox box)  
    {  
        this.box = box;  
    }  */
}  
