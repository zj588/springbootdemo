package com.imooc.jay.domainUtil;

import com.imooc.jay.entity.TbArea;

public class TbAreaTestUtil {
    public static TbArea createBean(Integer id, String name) {
        TbArea area = new TbArea();

        area.setId(id);
        area.setName(name);

        return area;
    }
}
