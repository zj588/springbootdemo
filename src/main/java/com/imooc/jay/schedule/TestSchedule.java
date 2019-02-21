package com.imooc.jay.schedule;

import com.imooc.jay.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TestSchedule {

    private static final Logger logger = LoggerFactory.getLogger(TestSchedule.class);

    @Autowired
    private TestService testService;

//    @Scheduled(cron = "1 * * * * *")
    public void testSchedule() {
        logger.info("current time: {}", new Date());

        testService.testSchedule();
    }
}
