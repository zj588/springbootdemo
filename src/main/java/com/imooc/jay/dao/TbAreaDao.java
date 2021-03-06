package com.imooc.jay.dao;

import com.imooc.jay.entity.TbArea;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TbAreaDao {
    /**
     * 区域列表
     *
     * @return
     */
    List<TbArea> queryAreaList();

    /**
     * 查询具体某个区域
     *
     * @param id
     * @return
     */
    TbArea queryAreaById(int id);

    /**
     * 插入
     *
     * @param area
     * @return
     */
    int insertArea(TbArea area);

    /**
     * 插入数据返回主键
     *
     * @param area
     * @return
     */
    int insertAreaGetId(TbArea area);

    /**
     * 修改
     *
     * @param area
     * @return
     */
    int updateArea(TbArea area);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    int deleteArea(int id);

    /**
     * 查
     * @param name
     * @return
     */
    List<TbArea> queryByName(@Param("name") String name);


}
