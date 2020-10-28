package com.baizhi.proxy;

public class MyProxy implements ProxyInterface {

    private ProxyInterface proxyInterface;


    public MyProxy(ProxyInterface proxyInterface){
        this.proxyInterface = proxyInterface;

    }


    public void visit( ){
        System.out.println("额外功能执行");
            proxyInterface.visit();
    }

}
