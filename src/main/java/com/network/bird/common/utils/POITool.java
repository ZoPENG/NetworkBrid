package com.network.bird.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.w3c.dom.Document;

import com.network.bird.common.convert.IHtmlConverter;
import com.network.bird.common.exception.BaseException;

import lombok.extern.slf4j.Slf4j;

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
            targetFile.delete();
        }
        String targetPathStr = targetFileName.substring(0, targetFileName.lastIndexOf(File.separator));
        File targetPath = new File(targetPathStr);
        if (!targetPath.exists()) {
            targetPath.mkdirs();
        }
    }

    /**
     * 初始化存放图片的文件夹
     * @param htmlFileName html文件的文件名
     * @return 存放图片的文件夹路径
     */
    private String initImageFolder(String htmlFileName) {
        String targetPathStr = htmlFileName.substring(0, htmlFileName.lastIndexOf(File.separator));
        //创建存放图片的文件夹
        String imagePathStr = targetPathStr + File.separator + IMAGE_PATH + File.separator;
        File imagePath = new File(imagePathStr);
        if(imagePath.exists()){
            imagePath.delete();
        }
        imagePath.mkdirs();
        return imagePathStr;
    }

    @Override
    public void docToHtml(String sourceFileName, String targetFileName) {

        initFolder(targetFileName);
        String imagePathStr = initImageFolder(targetFileName);
        try{
            HWPFDocument wordDocument = new HWPFDocument(new FileInputStream(sourceFileName));
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(document);
            HtmlPicturesManager picturesManager = new HtmlPicturesManager();

        }catch (Exception e){
        	log.error("将doc文件转换为html时出错", e);
            throw new BaseException("将doc文件转换为html时出错!" + e.getMessage());
        }
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

    @Override
    public List<String> getSupportedExtensionsForHtmlConverter() {
        return supportedExtensionsForHtmlConverter;
    }

    @Override
    public void setSupportedExtensionsForHtmlConverter(List<String> supportedExtensionsForHtmlConverter) {
        this.supportedExtensionsForHtmlConverter = supportedExtensionsForHtmlConverter;
    }
}
