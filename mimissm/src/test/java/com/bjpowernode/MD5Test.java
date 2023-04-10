package com.bjpowernode;

import com.bjpowernode.utils.MD5Util;
import org.junit.Test;

public class MD5Test {
    @Test
    public void testMD5(){
        String mi= MD5Util.getMD5("000000");
//        管理员密码是六个0
        System.out.println(mi);
    }
}
