package com.imooc.jay.service.impl;

import com.imooc.jay.dao.TbAreaDao;
import com.imooc.jay.domainUtil.TbAreaTestUtil;
import com.imooc.jay.entity.TbArea;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.greaterThan;

public class AreaServiceImplTest {
    @Mock
    private TbAreaDao tbAreaDao;

    // 捕获参数，可用于验证
    @Captor
    private ArgumentCaptor<TbArea> areaArgument;

    @InjectMocks
    private AreaServiceImpl service = new AreaServiceImpl();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAreaList() {
        // 创建对象
        Integer areaId = 1;
        String name = "南苑";
        TbArea area = TbAreaTestUtil.createBean(areaId, name);
        List<TbArea> areaList = new ArrayList<>();
        areaList.add(area);

        // 设定假设
        when(tbAreaDao.queryAreaList()).thenReturn(areaList);
//        // thenReturn连续调用，表示按调用次序，依次返回结果
//        when(tbAreaDao.queryAreaList()).thenReturn(areaList).thenReturn(new ArrayList<>());

        // 执行验证
        List<TbArea> returned = service.getAreaList();

        // 验证假设执行的次数
         verify(tbAreaDao, times(1)).queryAreaList();

        // 验证假设条件全部执行
        verifyNoMoreInteractions(tbAreaDao);

        // 判断执行成功
        assertThat(returned.size(), greaterThan(0));
    }

    @Test
    public void insertArea() {
        String name = "南苑";
        TbArea area = TbAreaTestUtil.createBean(null, name);

        // 设定假设
        when(tbAreaDao.insertArea(area)).thenReturn(1);

        // 执行验证
        boolean b = service.insertArea(area);

        // 验证假设执行的次数
        verify(tbAreaDao, times(1)).insertArea(area);

        // 验证假设条件全部执行
        verifyNoMoreInteractions(tbAreaDao);

        // 判断执行成功
        assertTrue(b);
    }

    @Test
    public void insertAreaByName() {
        String name = "南苑";
        TbArea area = TbAreaTestUtil.createBean(null, name);

        // 设定假设
        when(tbAreaDao.queryByName(name)).thenReturn(new ArrayList<>());
        // 方式三：也可以使用参数捕获得到的对象，还可以对参数进行断言
        when(tbAreaDao.insertArea(areaArgument.capture())).thenReturn(1);

        // 方式二：对象作为dao层的参数传入，使用any，表示不关心任何参数传入，只关心返回，传入对象可能会报以下错误
//        when(tbAreaDao.insertArea(any(TbArea.class))).thenReturn(1);
        // 方式一：对象作为dao层的参数传入，要注意业务逻辑中是否重新new了这个对象，如果业务代码中重新new了对象，会报以下错误。因此此处可以使用any，表示不关心任何参数传入，只关心返回
        // when(tbAreaDao.insertArea(area)).thenReturn(1);
        /*
        ×××××××  报错： ×××××××
        Argument(s) are different! Wanted:
        tbAreaDao.insertArea(
            TbArea{id=null, name=南苑, priority=null, createTime=null, updateTime=null}
        );
        -> at com.imooc.jay.service.impl.AreaServiceImplTest.insertAreaByName(AreaServiceImplTest.java:91)
        Actual invocation has different arguments:
        tbAreaDao.insertArea(
            TbArea{id=null, name=南苑, priority=null, createTime=null, updateTime=null}
        );
        -> at com.imooc.jay.service.impl.AreaServiceImpl.insertAreaByName(AreaServiceImpl.java:104)
         */

        // 执行验证
        boolean b = service.insertAreaByName(name);

        // 验证假设执行的次数
        verify(tbAreaDao, times(1)).queryByName(name);
        // 方式三：使用参数捕获得到的对象
        verify(tbAreaDao, times(1)).insertArea(areaArgument.capture());

        // 方式二：使用any
        // verify(tbAreaDao, times(1)).insertArea(any(TbArea.class));
        // 方式一：此处错误同上
        //verify(tbAreaDao, times(1)).insertArea(area);

        // 验证假设条件全部执行
        verifyNoMoreInteractions(tbAreaDao);

        TbArea value = areaArgument.getValue();
        System.out.println(value);

        // 判断执行成功
        assertTrue(b);
        assertEquals(name, areaArgument.getValue().getName());
    }
}