package com.bjpowernode.service;

import com.bjpowernode.pojo.Produce_info;
import com.bjpowernode.pojo.vo.ProduceInfoVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ProduceInfoService {
//    增加商品
     int save(Produce_info info);

    //    显示全部商品(不分页)
    List<Produce_info> getAll();

    /**分页功能显示
     * @param pageNum 当前数量
     * @param pageSize  当前页数
     * @return pagehelper对象
     */
    PageInfo splitPage(int pageNum,int pageSize);

//    按主键id查询商品
    Produce_info getById(int pid);
//    更新商品信息
    int update(Produce_info info);
//    单个商品删除
    int delete(int pid);
//    批量删除商品
    int deleteBatch(String[] ids);
//    多条件商品查询
    List<Produce_info> selectCondition(ProduceInfoVo vo);
//    多条件查询分页
    PageInfo splitPageVo(ProduceInfoVo vo,int pageSize);
}
