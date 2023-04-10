package com.bjpowernode.listener;

import com.bjpowernode.pojo.Produce_type;
import com.bjpowernode.service.ProduceTypeService;
import com.bjpowernode.service.impl.ProduceTypeServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

@WebListener
public class ProduceTypeListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
//        手工从spring容器中取出produceTypeServiceImpl对象
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext_*.xml");
        ProduceTypeService produceTypeServiceImpl = (ProduceTypeService) applicationContext.getBean("produceTypeServiceImpl");
//        放入全局应用作用域中，供新增页面，修改页面，前台的查询功能提供全部商品种类
        List<Produce_type> typeList = produceTypeServiceImpl.getAll();
        servletContextEvent.getServletContext().setAttribute("typeList",typeList);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
