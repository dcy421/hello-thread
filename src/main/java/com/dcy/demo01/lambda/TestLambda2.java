package com.dcy.demo01.lambda;

/**
 * @Author：dcy
 * @Description: 学习lambda
 * @Date: 2021/1/12 8:27
 */
public class TestLambda2 {


    public static void main(String[] args) {

        ILove love = (a,b) -> System.out.println("i love you -->" + a+"b="+b);
        love.love(2,"sss");
    }
}


// 定义一个函数式接口
interface ILove {
    void love(int a,String b);
}
