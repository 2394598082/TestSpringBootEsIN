package com.threadDemo;

public class Test1 implements Runnable {
    public static void main(String[] args) throws InterruptedException {


        Thread thread = new Thread(new Test1());
        thread.start();
        for (int i = 0; i < 100; i++) {
            if(i==50){
                thread.join();
            }
            System.out.println("-----");
        }


    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("vip==");
        }
    }
}
