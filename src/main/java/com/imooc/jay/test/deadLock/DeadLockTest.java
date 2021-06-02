package com.imooc.jay.test.deadLock;

public class DeadLockTest {
    public static final Object objA = new Object();
    public static final Object objB = new Object();

    public static void main(String[] args) {

        DieLock dl1 = new DieLock(true);
        DieLock dl2 = new DieLock(false);

        dl1.start();
        dl2.start();
    }


}

class DieLock extends Thread {
    private boolean flag;
    public DieLock(boolean flag) {
        this.flag = flag;
    }
    @Override
    public void run() {
        if (flag) {
            synchronized (DeadLockTest.objA) {
                System.out.println("if objA");
                synchronized (DeadLockTest.objB) {
                    System.out.println("if objB");
                }
            }
        } else {
            synchronized (DeadLockTest.objB) {
                System.out.println("else objB");
                synchronized (DeadLockTest.objA) {
                    System.out.println("else objA");
                }
            }
        }
    }


}