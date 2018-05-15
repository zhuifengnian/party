package com.hust.party.service;


import com.hust.party.common.Page;

import java.util.List;

/**
 * @Description
 * @Author:李越
 * @Date:2018/5/12
 */
public interface BaseService<T> {
    int selectCount(T record);

    //根据主键进行查询,必须保证结果唯一
    //单个字段做主键时,可以直接写主键的值
    //联合主键时,key可以是实体类,也可以是Map
    T selectByPrimaryKey(Integer key);
    List<T> select(T record, Page page);
    //插入一条数据
    //支持Oracle序列,UUID,类似Mysql的INDENTITY自动增长(自动回写)
    //优先使用传入的参数值,参数值空时,才会使用序列、UUID,自动增长
    int insert(T record);

    //插入一条数据,只插入不为null的字段,不会影响有默认值的字段
    //支持Oracle序列,UUID,类似Mysql的INDENTITY自动增长(自动回写)
    //优先使用传入的参数值,参数值空时,才会使用序列、UUID,自动增长
    int insertSelective(T record);

    //通过主键进行删除,这里最多只会删除一条数据
    //单个字段做主键时,可以直接写主键的值
    //联合主键时,key可以是实体类,也可以是Map
    int deleteByPrimaryKey(Integer key);

    //根据主键进行更新,这里最多只会更新一条数据
    //参数为实体类
    int updateByPrimaryKey(T record);

    //根据主键进行更新
    //只会更新不是null的数据
    int updateByPrimaryKeySelective(T record);


}
