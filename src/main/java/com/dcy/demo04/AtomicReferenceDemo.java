package com.dcy.demo04;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author：dcy
 * @Description:
 * @Date: 2021/2/8 9:19
 */
public class AtomicReferenceDemo {
    public static void main(String[] args) {
        User user1 = new User("张三", 25);
        User user2 = new User("李四", 21);
        AtomicReference<User> userAtomicReference = new AtomicReference<>();
        // 设置主物理内存
        userAtomicReference.set(user1);
        // 比较并交互
        System.out.println(userAtomicReference.compareAndSet(user1, user2) + " \t " + userAtomicReference.get().toString());
        System.out.println(userAtomicReference.compareAndSet(user1, user2) + " \t " + userAtomicReference.get().toString());
    }
}

@Getter
@ToString
@AllArgsConstructor
class User {
    String username;
    int age;
}
