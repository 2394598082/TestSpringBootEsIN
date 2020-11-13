package com.threadDemo;

public class TestState {
    public static void main(String[] args)throws Exception{
        Thread t1 = new Thread(()->{
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            System.out.println("///");
        });
        //刚创建完线程
        Thread.State state = t1.getState();
        System.out.println("刚创建完线程"+state);
        //启动
        t1.start();
        state = t1.getState();
        System.out.println("启动后"+state);
        //线程不终止就一直输出状态
        while(state!=Thread.State.TERMINATED){
            Thread.sleep(100);
            state = t1.getState();
            System.out.println("线程不终止就"+state);
        }


    }
}
