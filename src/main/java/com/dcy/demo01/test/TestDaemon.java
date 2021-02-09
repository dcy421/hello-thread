package com.dcy.demo01.test;

/**
 * @Author：dcy
 * @Description: 测试守护线程
 * @Date: 2021/1/12 10:26
 */
public class TestDaemon {

    public static void main(String[] args) {
        You you = new You();
        God god = new God();

        Thread thread = new Thread(god);
        // 设置守护线程
        // 默认是false，表示是用户线程，正常的线程都是用户线程。。
        thread.setDaemon(true);
        thread.start();


        new Thread(you).start();
    }

}


// 上帝
class God implements Runnable {

    @Override
    public void run() {
        while (true) {
            System.out.println("上帝保佑着你");
        }
    }
}


// 你
class You implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 36500; i++) {
            System.out.println("你一生都开心的活着");
        }
        System.out.println("=========goodbye! world!==========");
    }
}