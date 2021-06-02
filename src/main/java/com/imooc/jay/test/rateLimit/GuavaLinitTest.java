package com.imooc.jay.test.rateLimit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class GuavaLinitTest {
    public static void main(String[] args) {
        countTest();
    }

    public static void guavaTest() {
        RateLimiter rateLimiter = RateLimiter.create(2);
        for (int i = 0; i < 20; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    long start = System.currentTimeMillis();
                    System.out.println(start);
                    double acquire = rateLimiter.acquire();
                    System.out.println("+++ spend time: " + (System.currentTimeMillis() - start));

                }
            }).start();
        }
    }

    public static void guavaNoBlockingTest() {
        RateLimiter rateLimiter = RateLimiter.create(2);
        for (int i = 0; i < 20; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    long start = System.currentTimeMillis();
                    System.out.println(start);
                    while (true) {
                        if (rateLimiter.tryAcquire()) {
                            System.out.println("+++ spend time: " + (System.currentTimeMillis() - start));
                            return;
                        } else {
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            System.out.println("try again...");
                        }
                    }



                }
            }).start();
        }
    }


    public static void countTest() {
        LoadingCache<Long, AtomicLong> count = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.SECONDS).build(new CacheLoader<Long, AtomicLong>() {
            @Override
            public AtomicLong load(Long o) throws Exception {
                System.out.println("Load call!");
                return new AtomicLong(0L);
            }
        });
        long limits = 5;
//        int counter = 0;

        for (int i = 0; i < 20; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    long start = System.currentTimeMillis();
                    System.out.println(start);
                    while (true) {
                        //获取当前的时间戳作为key
                        Long currentSeconds = System.currentTimeMillis() / 1000;
                        try {
                            if (count.get(currentSeconds).getAndIncrement() > limits) {
                                Thread.sleep(100);
//                                System.out.println("try again...");
                                continue;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        System.out.println("+++ spend time: " + (System.currentTimeMillis() - start));
                        return;
                    }



                }
            }).start();
        }
    }


}
