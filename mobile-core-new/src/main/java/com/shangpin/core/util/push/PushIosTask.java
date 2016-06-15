package com.shangpin.core.util.push;

public class PushIosTask {
    public static void sendPush(){
        new PushTaskThread("1").start();
        new PushTaskThread("2").start();
        new PushTaskThread("3").start();
        new PushTaskThread("4").start();
    }
}
