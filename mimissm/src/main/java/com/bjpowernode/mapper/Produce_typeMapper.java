package com.bjpowernode.mapper;

import com.bjpowernode.pojo.Produce_type;
import com.bjpowernode.pojo.Produce_typeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Produce_typeMapper {
    long countByExample(Produce_typeExample example);

    int deleteByExample(Produce_typeExample example);

    int deleteByPrimaryKey(Integer typeId);

    int insert(Produce_type row);

    int insertSelective(Produce_type row);

    List<Produce_type> selectByExample(Produce_typeExample example);

    Produce_type selectByPrimaryKey(Integer typeId);

    int updateByExampleSelective(@Param("row") Produce_type row, @Param("example") Produce_typeExample example);

    int updateByExample(@Param("row") Produce_type row, @Param("example") Produce_typeExample example);

    int updateByPrimaryKeySelective(Produce_type row);

    int updateByPrimaryKey(Produce_type row);
}