package com.bjpowernode.service.impl;

import com.bjpowernode.mapper.Produce_infoMapper;
import com.bjpowernode.pojo.Produce_info;
import com.bjpowernode.pojo.Produce_infoExample;
import com.bjpowernode.pojo.vo.ProduceInfoVo;
import com.bjpowernode.service.ProduceInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduceInfoServiceImpl implements ProduceInfoService {

    //    切记：业务逻辑层中一定要有数据访问层的对象
    @Autowired
    Produce_infoMapper produceInfoMapper;

    @Override
    public int save(Produce_info info) {
        return produceInfoMapper.insert(info);
    }

    @Override
    public List<Produce_info> getAll() {
        return produceInfoMapper.selectByExample(new Produce_infoExample());
    }

    @Override
    public PageInfo splitPage(int pageNum, int pageSize) {
//       分页插件使用PageHelper工具类完成分页设置
        PageHelper.startPage(pageNum, pageNum);
//       进行pageInfo的数据封装
//        进行有条件的查询操作，必须要插件Produce_infoExample对象
        Produce_infoExample example = new Produce_infoExample();
//        设置排序，按主键降序排序
        example.setOrderByClause("p_id desc");
//        设置完排序后取集合
        List<Produce_info> list = produceInfoMapper.selectByExample(example);
//        将查询到的集合封装到pageInfo对象中
        PageInfo<Produce_info> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public Produce_info getById(int pid) {
        return produceInfoMapper.selectByPrimaryKey(pid);
    }

    @Override
    public int update(Produce_info info) {
        return produceInfoMapper.updateByPrimaryKey(info);
    }

    @Override
    public int delete(int pid) {
        return produceInfoMapper.deleteByPrimaryKey(pid);
    }

    @Override
    public int deleteBatch(String[] ids) {
        return produceInfoMapper.deleteBatch(ids);
    }

    @Override
    public List<Produce_info> selectCondition(ProduceInfoVo vo) {
        return produceInfoMapper.selectCondition(vo);
    }

    @Override
    public PageInfo splitPageVo(ProduceInfoVo vo, int pageSize) {
//        取出集合之前，先要设置PageHelper.startPage
        PageHelper.startPage(vo.getPage(),pageSize);
        List<Produce_info> list = produceInfoMapper.selectCondition(vo);
        return new PageInfo<>(list);
    }

}
