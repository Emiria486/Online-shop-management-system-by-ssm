package com.bjpowernode.utils;

import java.util.UUID;

/**
 * 文件uuid名工具类
 */
public class FileNameUtil {
//    根据uuid生成文件名
    public static String getUUIDFileName(){
        UUID uuid=UUID.randomUUID();
        return uuid.toString().replace("-","");
    }
//   从请求头中提取文件名和类型
    public static String getRealFileName(String context){
//    content-Disposition:form-data;name='myfile';filename='a_left.jpg'
        int index=context.lastIndexOf("=");
        String filename=context.substring(index+2,context.length()-1);
        return filename;
    }
//    根据给定的文件名和后缀截取文件名
    public static String getFileType(String fileName){
//        95727s.jpg
        int index=fileName.lastIndexOf(".");
        return fileName.substring(index);
    }
}
