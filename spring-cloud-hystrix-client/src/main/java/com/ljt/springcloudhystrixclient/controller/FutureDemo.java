package com.ljt.springcloudhystrixclient.controller;

import java.util.Random;
import java.util.concurrent.*;

/**
 * 理解@HystrixCommand原理,底层用的就是future
 */
public class FutureDemo {
    public static void main(String[] args) {
        Random random = new Random();


        ExecutorService service = Executors.newFixedThreadPool(1);

        Future<String> future = service.submit(() -> {
            // 如果随机时间 大于100，那么触发容错
            int value = random.nextInt(200);
            System.out.println("helloWord sleep "+value + " ms");
            Thread.sleep(value);

            return "Hello,World";
        });

        try {
            future.get(100, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            // 超时流程
            System.out.println("time out savee !!!");
        }
        service.shutdown();
    }
}
