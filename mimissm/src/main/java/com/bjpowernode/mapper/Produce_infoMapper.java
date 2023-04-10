package com.bjpowernode.mapper;

import com.bjpowernode.pojo.Produce_info;
import com.bjpowernode.pojo.Produce_infoExample;
import java.util.List;

import com.bjpowernode.pojo.vo.ProduceInfoVo;
import org.apache.ibatis.annotations.Param;

public interface Produce_infoMapper {
    long countByExample(Produce_infoExample example);

    int deleteByExample(Produce_infoExample example);

    int deleteByPrimaryKey(Integer pId);

    int insert(Produce_info row);

    int insertSelective(Produce_info row);

    List<Produce_info> selectByExample(Produce_infoExample example);

    Produce_info selectByPrimaryKey(Integer pId);

    int updateByExampleSelective(@Param("row") Produce_info row, @Param("example") Produce_infoExample example);

    int updateByExample(@Param("row") Produce_info row, @Param("example") Produce_infoExample example);

    int updateByPrimaryKeySelective(Produce_info row);

    int updateByPrimaryKey(Produce_info row);

    /**批量删除商品的功能
     * @param ids id数组
     * @return
     */
    int deleteBatch(String[] ids);
//    多条件查询商品
    List<Produce_info> selectCondition(ProduceInfoVo vo);
}