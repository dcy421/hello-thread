package com.dcy.demo01.test;

/**
 * @Author：dcy
 * @Description: 模拟龟兔赛跑
 * @Date: 2021/1/11 16:11
 */
public class Race implements Runnable {

    // 胜利者
    private static String winner;


    public void run() {
        for (int i = 0; i <= 100; i++) {
            if ("兔子".equals(Thread.currentThread().getName()) && i % 10 == 0) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 判断比啥是否结束
            if (gameOver(i)) {
                break;
            }
            System.out.println(Thread.currentThread().getName() + "--跑了" + i + "步");
        }
    }

    /**
     * 判断是否完成比赛
     *
     * @return
     */
    public boolean gameOver(int steps) {
        if (winner != null) {
            return true;
        } else {
            if (steps == 100) {
                winner = Thread.currentThread().getName();
                System.out.println("winner is " + winner);
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        Race race = new Race();
        new Thread(race, "兔子").start();
        new Thread(race, "乌龟").start();
    }
}
