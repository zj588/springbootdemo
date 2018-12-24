package com.imooc.jay.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.imooc.jay.entity.TbArea;
import com.imooc.jay.handler.ResponseData;
import com.imooc.jay.service.AreaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/area")
@Api(tags = "area管理")
public class AreaController {
    private static final Logger logger = LoggerFactory.getLogger(AreaController.class);
    @Autowired
    private AreaService areaService;

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ApiOperation("area列表查询")
    private ResponseData<List<TbArea>> getAreaList() {
        logger.info("++++++++++++list request received.: {}.", 1234567890);
        List<TbArea> list = areaService.getAreaList();

        return ResponseData.Builder.SUCC().initSuccData(list);
    }

    @RequestMapping(value = "/get_area", method = RequestMethod.GET)
    @ApiOperation("通过ID查询")
    @ApiImplicitParam(name = "id", value = "地区id", dataType = "integer", required = true, paramType = "query")
    private Map<String, Object> getAreaListById(int id) {
        TbArea area = areaService.getAreaById(id);

        Map<String, Object> modelMap = new HashMap<>();
        modelMap.put("area", area);

        return modelMap;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation("插入地区")
    private Map<String, Object> addArea(@RequestBody TbArea area) {
        Map<String, Object> modelMap = new HashMap<>();
        modelMap.put("success", areaService.insertArea(area));

        return modelMap;
    }

    @RequestMapping(value = "/sendKafka", method = RequestMethod.GET)
    public boolean sendKafka() {
        try {
//            TbArea tbArea = new TbArea();
//            tbArea.setId(Double.valueOf(Math.random()).intValue());
//            tbArea.setName("aaa");
//            tbArea.setPriority(1);

//            JSONObject object = JSON.parseObject(Math.random());
            for (int i = 0; i < 3; i++) {
                String value = "area-test" + i;
                kafkaTemplate.send("test-kkk", "area", value);
                logger.info("++++++++++++send message: {}.", value);
            }

            return true;
        } catch (Exception e) {
            logger.error("*************** error: {}.", e.getMessage());
            return false;
        }
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public void test(Integer time) {
        Date date = null;
        try {
            DateFormat ymdhmsFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String nowTimeStr = ymdhmsFormat.format(((long)time) * 1000);
            date = ymdhmsFormat.parse(nowTimeStr);

        } catch (Exception e) {

        }
        System.out.println(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        System.out.println(calendar);
        System.out.println(calendar.get(Calendar.DAY_OF_WEEK) -1);
    }
}
