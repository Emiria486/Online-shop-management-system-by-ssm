package com.bjpowernode.controller;

import com.bjpowernode.pojo.Produce_info;
import com.bjpowernode.pojo.vo.ProduceInfoVo;
import com.bjpowernode.service.ProduceInfoService;
import com.bjpowernode.service.impl.ProduceInfoServiceImpl;
import com.bjpowernode.utils.FileNameUtil;
import com.github.pagehelper.PageInfo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/prod")
public class ProduceInfoAction {
//    异步上传的图片的名称
String saveFileName;
//    每页显示的记录数
    public static final int PAGE_SIZE=5;
    //    在界面层，一定会有业务逻辑层的对象
    @Autowired
    ProduceInfoServiceImpl produceInfoService;
//    显示全部商品不分页
    @RequestMapping("/getAll")
    public String getAll(HttpServletRequest request){
        List<Produce_info> list=produceInfoService.getAll();
        request.setAttribute("list",list);
        return "product";
    }
//    显示第一页的五条记录
    @RequestMapping("/split")
    public String split(HttpServletRequest request){
        PageInfo info=null;
        Object vo=request.getSession().getAttribute("prodVo");
        if(vo!=null){
            info=produceInfoService.splitPageVo((ProduceInfoVo) vo,PAGE_SIZE);
            request.getSession().removeAttribute("prodVo");
        }else {
            info = produceInfoService.splitPage(1, PAGE_SIZE);
        }
        request.setAttribute("info",info);
        return "product";
    }

    /**Ajax分页翻页处理
     * @param vo 多查询条件
     * @param session ajax传递
     */
    @ResponseBody
    @RequestMapping("/ajaxSplit")
    public void ajaxSplit(ProduceInfoVo vo, HttpSession session){
//        取得当前page参数的页面的数据
        PageInfo info=produceInfoService.splitPageVo(vo,PAGE_SIZE);
        session.setAttribute("info",info);
    }
    @ResponseBody
    @RequestMapping("/ajaxImg")
    public Object ajaxImg(MultipartFile pimage,HttpServletRequest request){
//        提取生成文件名uuid+上传图片的后缀
         saveFileName= FileNameUtil.getUUIDFileName()+FileNameUtil.getFileType(pimage.getOriginalFilename());
//        得到项目 中图片存储的路径
        String path=request.getServletContext().getRealPath("/image_big");
//        转存
        try {
            pimage.transferTo(new File(path+File.separator+saveFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
//        返回客户端JSON对象，封装图片的路径，在页面显示图片回显
        JSONObject object=new JSONObject();
        object.put("imgurl",saveFileName);
        return object.toString();
    }
    @RequestMapping("/save")
    public String save(Produce_info info,HttpServletRequest request){
        info.setpImage(saveFileName);
        info.setpDate(new Date());
//        info对象中有表单提交上来的5个数据，有异步Ajax上传的图片名称，有上传时间
        int num=-1;
        try{
            num= produceInfoService.save(info);
        }catch (Exception e){
            e.printStackTrace();
        }
        if (num>0){
            request.setAttribute("msg","增加成功");
        }else {
            request.setAttribute("msg","增加失败");
        }
//        清空saveFileName变量中的内容，为了下次增加或修改的异步ajax的上传处理
        saveFileName="";
//        增加成功后应该重新访问数据库，所以跳转到分页显示的action上
        return "forward:/prod/split.action";
    }
//    商品回显
    @RequestMapping("/one")
    public String one(int pid,ProduceInfoVo vo, Model model,HttpSession session){
        Produce_info info=produceInfoService.getById(pid);
        model.addAttribute("prod",info);
//        将多条件及页码放入session中，更新处理结束后时读取多条件和页码进行处理
        session.setAttribute("prodVo",vo);
        return "update";
    }
    @RequestMapping("update")
    public String update(Produce_info info,HttpServletRequest request){
//        因为Ajax的异步图片上传，如果有上传过
//        则saveFilename这里面有上传上来的图片的名称
//        如果没有使用异步Ajax上传过图片,者saveFileName=''
//        实体类info使用隐藏表单域提供上来的pImage原始图片的名称
        if (!saveFileName.equals("")){
            info.setpImage(saveFileName);
        }
//        完成更新处理
        int num=-1;
        try {
            num=produceInfoService.update(info);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(num>0){
            request.setAttribute("msg","更新成功!");
        }else {
            request.setAttribute("msg","更新失败!");
        }
//        清空saveFileName
        saveFileName="";
        return "forward:/prod/split.action";
    }
    @RequestMapping("/delete")
    public String delete(int pid,ProduceInfoVo vo,HttpServletRequest request){
        int num=-1;
        try {
            num=produceInfoService.delete(pid);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(num>0){
            request.setAttribute("msg","删除成功");
            request.getSession().setAttribute("deleteProdVo",vo);
        }else {
            request.setAttribute("msg","删除失败");
        }
//        删除结束后跳到分页显示
        return "forward:/prod/deleteAjaxSpit.action";
    }
    @ResponseBody
    @RequestMapping(value = "/deleteAjaxSplit",produces = "text/html;charset=UTF-8")
    public Object deleteAjaxSplit(HttpServletRequest request){
//        取得第一页得数据
        PageInfo info=null;
        Object vo = request.getSession().getAttribute("deleteProdVo");
        if (vo!=null){
            info=produceInfoService.splitPageVo((ProduceInfoVo) vo,PAGE_SIZE);
        }else {
            info=produceInfoService.splitPage(1,PAGE_SIZE);
        }
        request.getSession().setAttribute("info",info);
        return request.getAttribute("msg");
    }
    @RequestMapping("/deleteBatch")
    public String deleteBatch(String pids,HttpServletRequest request){
//       将上传上来的字符串截取，形成商品id的字符串数组
        String[] ps=pids.split(",");
        try{
            int num=produceInfoService.deleteBatch(ps);
            if(num>0){
                request.setAttribute("msg","批量删除失败");
            } else{
            request.setAttribute("msg","批量删除失败");
        }
    }catch (Exception e){
            request.setAttribute("msg","商品不可删除");
        }
        return "forward:/prod/deleteAjaxSplit.action";
    }
    @ResponseBody
    @RequestMapping("/condition")
    public void condition(ProduceInfoVo vo,HttpSession session){
        List<Produce_info> list=produceInfoService.selectCondition(vo);
        session.setAttribute("list",list);
    }
}
