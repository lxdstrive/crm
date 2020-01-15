package com.itheima.crm.utils;

import java.util.UUID;

/**
 * 文件上传的工具类
 * @author BJXT-LXD
 */
public class UploadUtils {
    /**
     * 解决目录下文件名冲突的方法
     * @param fileName
     * @return
     */
    public static String  getUuitFileName(String fileName){
        int idx = fileName.lastIndexOf(".");  //aa.txt

        String extions = fileName.substring(idx);//得到文件的扩展名 .txt
        String name = fileName.substring(0,idx);//得到文件名

        return  UUID.randomUUID().toString().replace("-","")+extions;
    }

    /**
     * 目录分离的方法
     * @return
     */
    public static String getPath(String uuidFileName){
        int code1 = uuidFileName.hashCode();
        int d1= code1 & 0xf;//作为一级目录
        int code2 = d1 >>>4;
        int d2 = code2 &0xf;//作为二级目录

        return "/"+d1+"/"+d2;
    }

    public static void main(String[] args) {
        System.out.println(getUuitFileName("lxd.txt"));
    }
}
