package com.apple.ali;

import java.util.Random;

public class AppleProducer implements Runnable{

Random randomTimer = new Random();
    //  每次生产的产品数量 
    private int num;  
  
    // 所在放置的仓库  
    private AppleBox box;  
  
    // 构造函数，设置仓库  
    public AppleProducer(AppleBox box){  
        this.box = box;  
    }  
  
    //   
    public void run(){  
     while(true){
     try {
     Thread.sleep(randomTimer.nextInt(1000));
     box.produce(); 
     } catch (InterruptedException e) { 
     e.printStackTrace();
     }
     }       
    }  
   
  
    // get方法  
    /*public int getNum()  
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
  
    public void setBox(AppleBox box)  
    {  
        this.box = box;  
    }  
*/
} 
