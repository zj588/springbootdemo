package com.imooc.jay.dao;

import com.alibaba.fastjson.JSONObject;
import com.imooc.jay.entity.TbArea;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TbAreaDaoTest {

    @Autowired
    private TbAreaDao tbAreaDao;

    @Test
    @Ignore
    public void queryAreaList() {
        List<TbArea> areaList = tbAreaDao.queryAreaList();
        assertEquals(2, areaList.size());
    }

    @Test
    @Ignore
    public void queryAreaById() {
        TbArea area = tbAreaDao.queryAreaById(1);
        assertEquals("东苑", area.getName());
    }

    @Test
    @Ignore
    public void insertArea() {
        TbArea area = new TbArea();
        area.setName("南苑");
        area.setPriority(3);

        int affectedNum = tbAreaDao.insertArea(area);
        assertEquals(1, affectedNum);
    }

    @Test
    @Ignore
    public void insertAreaGetId() {
        TbArea area = new TbArea();
        area.setName("东北苑");
        area.setPriority(5);

        int affectedNum = tbAreaDao.insertAreaGetId(area);
        int lastId = area.getId();
        assertEquals(1, affectedNum);
        assertEquals(10, lastId);
    }

    @Test
    @Ignore
    public void updateArea() {
        TbArea area = new TbArea();
        area.setId(9);
        area.setName("东北苑");

        int affectedNum = tbAreaDao.updateArea(area);
        assertEquals(1, affectedNum);
    }

    @Test
    public void deleteArea() {
        List<Integer> list = JSONObject.parseArray("", Integer.class);
        System.out.println(list);

//        int affectedNum = tbAreaDao.deleteArea(9);
        assertEquals(null, list);
    }
}