package com.bjpowernode.service.impl;


import com.bjpowernode.mapper.Produce_typeMapper;
import com.bjpowernode.pojo.Produce_infoExample;
import com.bjpowernode.pojo.Produce_type;
import com.bjpowernode.pojo.Produce_typeExample;
import com.bjpowernode.service.ProduceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProduceTypeServiceImpl implements ProduceTypeService {
    @Autowired
    Produce_typeMapper produceInfoMapper;
    @Override
    public List<Produce_type> getAll() {
        return produceInfoMapper.selectByExample(new Produce_typeExample());
    }
}
