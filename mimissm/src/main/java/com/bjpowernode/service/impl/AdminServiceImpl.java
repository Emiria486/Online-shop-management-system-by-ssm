package com.bjpowernode.service.impl;

import com.bjpowernode.mapper.AdminMapper;
import com.bjpowernode.pojo.Admin;
import com.bjpowernode.pojo.AdminExample;
import com.bjpowernode.service.AdminService;
import com.bjpowernode.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AdminServiceImpl implements AdminService {
    //    在业务逻辑层中，一定会有数据访问层的对象
    @Autowired
    AdminMapper adminMapper;
    @Override
    public Admin login(String name, String pwd) {
//        根据传入的用户或到DB中查询相应用户对象
//        如果有条件，则一定要创建AdminExample的对象，用来封装条件
        AdminExample example=new AdminExample();
//        添加用户名a_name条件
        example.createCriteria().andANameEqualTo(name);
//        如果查询到用户对象，再进行密码的比对
        List<Admin> list = adminMapper.selectByExample(example);
        if(list.size()>0){
            Admin admin=list.get(0);
//            如果查询到用户对象，再进行密码的比对，密码是密文
//            在进行密码对比时，要将传入的pwd进行md5加密，再和数据量里面查到的密码对比
            String miPwd= MD5Util.getMD5(pwd);
            if(miPwd.equals(admin.getaPass())){
                return admin;
            }
        }
        return null;
    }
}
