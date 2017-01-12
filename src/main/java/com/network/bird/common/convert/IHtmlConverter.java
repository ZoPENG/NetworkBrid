package com.network.bird.common.convert;

import com.network.bird.common.exception.BaseException;
import com.network.bird.common.utils.FileTool;

/**
 * 将其它格式的文件转换为html
 * Created by zhoupeng on 2017/1/7.
 */
public interface IHtmlConverter {

    default void toHtml(String sourceFileName, String targetFileName){
        String extension = FileTool.getFileExtension(sourceFileName);
        switch (extension) {
            case "doc":
                docToHtml(sourceFileName, targetFileName);
                break;
            case "docx":
                docxToHtml(sourceFileName, targetFileName);
                break;
            case "xls":
                xlsToHtml(sourceFileName, targetFileName);
                break;
            case "xlsx":
                xlsxToHtml(sourceFileName, targetFileName);
                break;
            default:
                throw new BaseException("不支持的文件类型：" + extension);
        }
    }
    /**
     * doc文件转换为html文件
     * @param sourceFileName 源文件的文件名
     * @param targetFileName 目标文件的文件名
     */
    void docToHtml(String sourceFileName, String targetFileName);
    /**
     * docx文件转换为html文件
     * @param sourceFileName 源文件的文件名
     * @param targetFileName 目标文件的文件名
     */
    void docxToHtml(String sourceFileName, String targetFileName);
    /**
     * xls文件转换为html文件
     * @param sourceFileName 源文件的文件名
     * @param targetFileName 目标文件的文件名
     */
    void xlsToHtml(String sourceFileName, String targetFileName);
    /**
     * xlsx文件转换为html文件
     * @param sourceFileName 源文件的文件名
     * @param targetFileName 目标文件的文件名
     */
    void xlsxToHtml(String sourceFileName, String targetFileName);
}
