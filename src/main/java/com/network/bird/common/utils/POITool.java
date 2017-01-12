package com.network.bird.common.utils;

import com.network.bird.common.convert.IHtmlConverter;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zhoupeng on 2017/1/7.
 */
@Slf4j
public class POITool implements IHtmlConverter {

    private List<String> supportedExtensionsForHtmlConverter;

    /**
     * 存放图片的路径
     */
    private final static String IMAGE_PATH = "image";

    public POITool() {
        supportedExtensionsForHtmlConverter = Arrays.asList("doc", "docx", "xls");
    }

    private void initFolder(String targetFileName) {
        File targetFile = new File(targetFileName);
        if (targetFile.exists()) {
            boolean f = targetFile.delete();
        }
        String targetPathStr = targetFileName.substring(0, targetFileName.lastIndexOf(File.separator));
        File targetPath = new File(targetPathStr);
        if (!targetPath.exists()) {
            boolean f = targetPath.mkdirs();
        }
    }

    private String initImageFolder(String targetFileName) {
        return null;
    }

    @Override
    public void docToHtml(String sourceFileName, String targetFileName) {

        initFolder(targetFileName);
        String imagePathStr = initImageFolder(targetFileName);
    }

    @Override
    public void docxToHtml(String sourceFileName, String targetFileName) {

    }

    @Override
    public void xlsToHtml(String sourceFileName, String targetFileName) {

    }

    @Override
    public void xlsxToHtml(String sourceFileName, String targetFileName) {

    }
}
