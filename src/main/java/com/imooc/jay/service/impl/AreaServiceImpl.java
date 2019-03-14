package com.imooc.jay.service.impl;

import com.imooc.jay.anotations.BackLogParamsAnnotation;
import com.imooc.jay.dao.TbAreaDao;
import com.imooc.jay.entity.TbArea;
import com.imooc.jay.service.AreaService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {
    private static final Logger logger = LoggerFactory.getLogger(AreaServiceImpl.class);

    private static final String TOPIC_TEST = "test-kkk";

    @Autowired
    private TbAreaDao tbAreaDao;

    @Override
    public List<TbArea> getAreaList() {
        return tbAreaDao.queryAreaList();
    }

    @Override
    public TbArea getAreaById(int id) {
        return tbAreaDao.queryAreaById(id);
    }

    @Override
    @Transactional
    @BackLogParamsAnnotation
    public boolean insertArea(TbArea area) {
        if (area.getName() == null || "".equals(area.getName())) {
            throw new RuntimeException("区域信息不能为空！");
        }

        try {
            int affectedNum = tbAreaDao.insertArea(area);
            if (affectedNum != 1) {
                throw new RuntimeException("插入区域信息失败！");
            } else {
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException("插入区域信息失败：" + e.getMessage());
        }
    }

    @Override
    public boolean insertAreaGetId(TbArea area) {
        return false;
    }

    @Override
    public boolean updateArea(TbArea area) {
        if (area.getId() == null || area.getId() == 0) {
            throw new RuntimeException("区域ID不能为空！");
        }

        try {
            int affectedNum = tbAreaDao.updateArea(area);
            if (affectedNum != 1) {
                throw new RuntimeException("修改区域信息失败！");
            } else {
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException("修改区域信息失败：" + e.getMessage());
        }
    }

    @Override
    public boolean deleteArea(int id) {
        if (id <= 0) {
            throw new RuntimeException("区域ID不能为空！");
        }

        try {
            int affectedNum = tbAreaDao.deleteArea(id);
            if (affectedNum != 1) {
                throw new RuntimeException("删除区域信息失败！");
            } else {
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException("删除区域信息失败：" + e.getMessage());
        }
    }

//    @Override
//    @KafkaListener(topics = {TOPIC_TEST})
//    public void kafkaArea(ConsumerRecord<String, String> record) {
//        logger.info("---------------- test-kkk.record: {}", record);
//        logger.info("---------------- test-kkk.key: {}", record.key());
//        logger.info("---------------- test-kkk.value: {}", record.value());
//    }

    public int sayHello() {
        return 1;
    }
}
