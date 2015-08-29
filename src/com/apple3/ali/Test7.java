package com.apple3.ali;

/*一个放一个拿，operationBox()锁住stack的操作，forsleep方法是进行休眠的，模拟一块一慢，如果需要无序时间，自己写随机函数进行线程休眠。*/
import java.util.Stack;
 
public class Test7 {
 
    private Stack<String> box;
 
    public String operationBox(boolean flag, String num) {
        String str = "";
        synchronized (Test7.class) {
            if (flag) {
                this.box.push(num);
            } else {
                str = this.box.pop();
            }
        }
        return str;
    }
 
    public static void main(String args[]) {
        Test7 a = new Test7();
        a.initApples();
    }
 
    public Test7() {
        this.box = new Stack<String>();
    }
 
    public void forSleep(int i) {
        try {
            Thread.sleep(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    /**
     * 放苹果的人
     */
    public void putApple() {
        int no = 1;
        while (true) {
            this.forSleep(1000);
            int check = this.box.size();
            System.out.println("---------现在有" + check + "个苹果---------");
            if (check >= 5) {
                System.out.println("------------箱子里有5个苹果无法放入------------");
                continue;
            } else {
                System.out
                        .println("------------放入第" + no + "个苹果--------------");
                this.operationBox(true, no + "");
                no = no + 1;
            }
        }
    }
 
    /**
     * 拿苹果的人
     */
    public void getApple() {
        while (true) {
            this.forSleep(800);
            int check = this.box.size();
            System.out.println("=========现在有" + check + "个苹果============");
            if (check == 0) {
                System.out.println("==========箱子里有0个苹果无法去除===========");
                continue;
            } else {
                String str = this.operationBox(false, null);
                System.out.println("==========从箱子出取出第" + str + "个苹果==========");
            }
        }
    }
 
    /*
     * 初始化两个人
     */
    public void initApples() {
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                try {
                    putApple();
                } catch (Exception e) {
 
                }
            }
        });
        t1.start();
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                try {
                    getApple();
                } catch (Exception e) {
 
                }
            }
        });
        t2.start();
    }
}
