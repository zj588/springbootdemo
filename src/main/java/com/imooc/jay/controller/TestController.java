package com.imooc.jay.controller;

import com.alibaba.fastjson.JSON;
import com.imooc.jay.entity.TbArea;
import com.imooc.jay.handler.ResponseData;
import com.imooc.jay.service.TestService;
import com.imooc.jay.util.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/test")
@Api(tags = "test测试")
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private TestService testService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    @ApiOperation("index首页查询")
    public ResponseData index() {
        return ResponseData.Builder.SUCC().initSuccData("Hello World");
    }

    @RequestMapping(value = "/setRedis", method = RequestMethod.GET)
    public ResponseData testRedis() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        redisTemplate.opsForList().leftPushAll("test_list", list);
        return ResponseData.Builder.SUCC();
    }

    @RequestMapping(value = "/getRedis", method = RequestMethod.GET)
    public ResponseData getRedis() {
        int i = (int)redisTemplate.opsForList().leftPop("test_list");
        return ResponseData.Builder.SUCC().initSuccData(i);
    }


    /**
     * excel导出
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/export", method = RequestMethod.GET)
    @ResponseBody
    public void export(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long start = System.currentTimeMillis();

        ServletOutputStream out = response.getOutputStream();
        try {
            String filename = "test_export_" + DateUtil.transForDate((int) (System.currentTimeMillis() / 1000), "yyyyMMdd_HHmmss") + ".xls";
            HSSFWorkbook workbook = testService.exportTest();
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
//            response.setContentType("application/octet-stream;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=\"" + new String(filename.getBytes("gb2312"), "ISO8859-1"));
            workbook.write(out);
            out.close();
        } catch (Exception e) {
            logger.error("Export teacher response fail, error {}.", e);
            ResponseData responseData =  ResponseData.Builder.FAIL().initErrCodeAndMsg(1, "Export error");
            out.println("{\"code\": " + responseData.getCode() + ", \"message\": \"" + responseData.getMessage() + "\", \"data\": {}}");
            out.close();
        }finally {
            logger.info("Export data finish. cost time: {}ms",System.currentTimeMillis() - start);
        }
    }


    @RequestMapping(value = "/testThreadPool")
    public void testThreadPool() {
        List<Integer> array = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            array.add(i);
        }
//        // 我们使用了一个大小为5的ArrayBlockingQueue阻塞队列来保存等待执行的任务。接着我们提交了20个任务给线程池，由于每个线程执行任务的时候会睡眠0.1秒，因此当10个线程繁忙的时候，其他任务不会立即得到执行，我们提交的新任务会被保存在队列里。当等待任务的数量超过线程池阻塞队列的最大容量时，抛出了RejectedExecutionException异常。
//        // 由于ArrayBlockingQueue内部只使用了一个锁来隔离读和写的操作，因此效率没有使用了两个锁来隔离读写操作的LinkedBlockingQueue高，故而不推荐使用这种方式
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MINUTES, new ArrayBlockingQueue<>(5));


        // 推荐使用LinkedBlockingQueue队列，效率高
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 2, TimeUnit.MINUTES, new LinkedBlockingQueue<>());

        int index = 0;
        while (true) {
            if (index >= array.size()) {
                break;
            }

            if (executor.getPoolSize() >= 20) {
                Integer value = array.get(index);
                index++;

                executor.execute(new TestThreadPool(value));
                logger.info("线程池中线程数目："+executor.getPoolSize()+"，队列中等待执行的任务数目："+
                        executor.getQueue().size()+"，已执行玩别的任务数目："+executor.getCompletedTaskCount());
            }
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
