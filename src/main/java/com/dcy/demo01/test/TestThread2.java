package com.dcy.demo01.test;

import com.dcy.demo01.util.WebDownloader;

/**
 * @Author：dcy
 * @Description:
 * @Date: 2021/1/11 15:31
 */
public class TestThread2 extends Thread {

    private String url;
    private String name;

    public TestThread2(String url, String name) {
        this.url = url;
        this.name = name;
    }

    @Override
    public void run() {
        WebDownloader webDownloader = new WebDownloader();
        webDownloader.downloader(url, name);
        System.out.println("下载了图片：" + name);
    }

    public static void main(String[] args) {
        TestThread2 testThread1 = new TestThread2("https://pics3.baidu.com/feed/fd039245d688d43f39be4f643b92321c0ff43ba1.jpeg?token=4b1ee76f5419df26d6794c95e2d450dd&s=FA905A85C34B094B0EB4A48B0300F082",
                "pic/2.jpg");
        TestThread2 testThread4 = new TestThread2("https://pics0.baidu.com/feed/a71ea8d3fd1f413483bc4fb3649375cdd3c85e83.jpeg?token=5a695169be0f0e31cdc845befc828f70&s=700A65BB16A20EA02C006D360300D0E2",
                "pic/3.jpg");
        TestThread2 testThread3 = new TestThread2("https://pics2.baidu.com/feed/63d9f2d3572c11df4b62aaac25ab82d7f603c226.jpeg?token=62ef2b936a07fa7e2127233c32d6c141&s=0FA6DD005C651A1BBC190C02030060C9",
                "pic/4.jpg");

        testThread1.start();
        testThread4.start();
        testThread3.start();
    }
}