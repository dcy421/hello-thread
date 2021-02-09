package com.dcy.demo01.lambda;

/**
 * @Author：dcy
 * @Description: 学习lambda
 * @Date: 2021/1/12 8:27
 */
public class TestLambda1 {

    // 静态内部类
    static class Like2 implements ILike{

        public void lambda() {
            System.out.println("i like lambda2");
        }
    }


    public static void main(String[] args) {
        ILike like = new Like();
        like.lambda();

        like = new Like2();
        like.lambda();

        // 局部内部类
        class Like3 implements ILike{

            public void lambda() {
                System.out.println("i like lambda3");
            }
        }

        like = new Like3();
        like.lambda();

        // 匿名内部类
        like = new ILike() {
            public void lambda() {
                System.out.println("i like lambda4");
            }
        };
        like.lambda();

        // lambda简化
        like = () -> System.out.println("i like lambda5");
        like.lambda();
    }
}


// 定义一个函数式接口
interface ILike{
    void lambda();
}

class Like implements ILike{

    public void lambda() {
        System.out.println("i like lambda1");
    }
}