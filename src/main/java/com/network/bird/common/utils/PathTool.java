package com.network.bird.common.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 保存系统路径配置
 * Created by zhoupeng on 2017/1/4.
 */
public class PathTool {
    /**
     * 应用根目录
     */
    private static String basePath;
    /**
     * 存放上传文件的临时目录
     */
    private static String uploadPath;
    /**
     * 存放文件格式转换后新文件的目录
     */
    private static String convertPath;
    /**
     * 允许的文件扩展名
     */
    private static List<String> allowedExtensions = new ArrayList<>();

    public static String getBasePath() {
        return basePath;
    }

    public static void setBasePath(String basePath) {
        PathTool.basePath = basePath;
    }

    public static String getUploadPath() {
        return uploadPath;
    }

    public static void setUploadPath(String uploadPath) {
        PathTool.uploadPath = uploadPath;
    }

    public static String getConvertPath() {
        return convertPath;
    }

    public static void setConvertPath(String convertPath) {
        PathTool.convertPath = convertPath;
    }

    public static List<String> getAllowedExtensions() {
        return allowedExtensions;
    }

    public static void setAllowedExtensions(List<String> allowedExtensions) {
        PathTool.allowedExtensions = allowedExtensions;
    }

    /**
     * @return true 允许的文件扩展名
     */
    public static boolean isAllowedExtensions(String extension) {
        if(allowedExtensions.isEmpty()){
            String[] extensions = new String[]{"jpg","png","gif","doc","docx","xls","xlsx","ppt","pptx","pdf","txt"};
            allowedExtensions.addAll(Arrays.asList(extensions));
        }
        return allowedExtensions.contains(extension);
    }
}
