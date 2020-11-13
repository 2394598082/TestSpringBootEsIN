package com.demo;

import com.baizhi.proxy.Core;
import com.baizhi.proxy.MyProxy;

public class TestProxy {
    public static void main(String[] args) {
        Core core = new Core();
        MyProxy myProxy = new MyProxy(core);
        myProxy.visit();

    }
}
