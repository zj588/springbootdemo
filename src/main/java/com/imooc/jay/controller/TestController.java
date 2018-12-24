package com.imooc.jay.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/test")
@Api(tags = "test测试")
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    @ApiOperation("index首页查询")
    public String index() {
        return "test/index";
    }

    @RequestMapping(value = "/testThreadPool")
    public void testThreadPool() {
//        // 我们使用了一个大小为5的ArrayBlockingQueue阻塞队列来保存等待执行的任务。接着我们提交了20个任务给线程池，由于每个线程执行任务的时候会睡眠0.1秒，因此当10个线程繁忙的时候，其他任务不会立即得到执行，我们提交的新任务会被保存在队列里。当等待任务的数量超过线程池阻塞队列的最大容量时，抛出了RejectedExecutionException异常。
//        // 由于ArrayBlockingQueue内部只使用了一个锁来隔离读和写的操作，因此效率没有使用了两个锁来隔离读写操作的LinkedBlockingQueue高，故而不推荐使用这种方式
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MINUTES, new ArrayBlockingQueue<>(5));


        // 推荐使用LinkedBlockingQueue队列，效率高
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MINUTES, new LinkedBlockingQueue<>());

        for (int i = 1; i <= 20; i++) {
            executor.execute(new TestThreadPool(i));
            logger.info("线程池中线程数目："+executor.getPoolSize()+"，队列中等待执行的任务数目："+
                    executor.getQueue().size()+"，已执行玩别的任务数目："+executor.getCompletedTaskCount());
        }
        executor.shutdown();
    }

    private class TestThreadPool implements Runnable {
        private Integer i;

        public TestThreadPool(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            Thread currentThread = Thread.currentThread();
            logger.info(currentThread.getName()+" 正在执行task "+i);
            try {
                currentThread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info(currentThread.getName()+" 执行完毕"+i);
        }
    }
}
