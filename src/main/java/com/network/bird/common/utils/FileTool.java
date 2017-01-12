package com.network.bird.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 文件共同类
 * Created by zhoupeng on 2017/1/7.
 */
public class FileTool {
    private static Logger log = LoggerFactory.getLogger(FileTool.class);
    private FileTool(){}

    /**
     * 从文件名中截取文件扩展名
     * @param fileName 文件名
     * @return 文件扩展名的小写形式，如果文件没有扩展名，则返回长度为0的空字符串
     */
    public static String getFileExtension(String fileName) {
        int index = fileName.lastIndexOf(".");
        String extension = "";
        if(index > 0){
            extension = fileName.substring(index + 1).toLowerCase();
        }
        return extension;
    }
}
