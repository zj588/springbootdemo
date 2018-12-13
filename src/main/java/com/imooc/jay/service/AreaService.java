package com.imooc.jay.service;

import com.imooc.jay.entity.TbArea;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.List;

public interface AreaService {
    /**
     * 区域列表
     *
     * @return
     */
    List<TbArea> getAreaList();

    /**
     * 查询具体某个区域
     *
     * @param id
     * @return
     */
    TbArea getAreaById(int id);

    /**
     * 插入
     *
     * @param area
     * @return
     */
    boolean insertArea(TbArea area);

    /**
     * 插入数据返回主键
     *
     * @param area
     * @return
     */
    boolean insertAreaGetId(TbArea area);

    /**
     * 修改
     *
     * @param area
     * @return
     */
    boolean updateArea(TbArea area);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    boolean deleteArea(int id);

//    void kafkaArea(ConsumerRecord<String, String> record);
}
