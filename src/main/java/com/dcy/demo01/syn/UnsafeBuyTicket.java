package com.dcy.demo01.syn;

/**
 * @Author：dcy
 * @Description: 不安全的买票
 * @Date: 2021/1/12 13:15
 */
public class UnsafeBuyTicket {

    public static void main(String[] args) {
        BuyTicket station = new BuyTicket();

        new Thread(station, "苦逼的我").start();
        new Thread(station, "牛逼的你们").start();
        new Thread(station, "可恶的黄牛档").start();
    }

}


class BuyTicket implements Runnable {

    // 票数
    private int ticketNums = 10;
    // 外部停止方式
    private boolean flag = true;


    @Override
    public void run() {
        // 买票
        while (flag) {
            buy();
        }
    }

    // synchronized 同步方法，锁的是this
    private synchronized void buy() {
        if (ticketNums <= 0) {
            flag = false;
            return;
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "拿到" + ticketNums--);
    }
}