package com.network.bird.core.schedule;

import com.network.bird.common.utils.PathTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * 删除临时文件的任务
 *
 * Created by zhoupeng on 2017/1/4.
 */
@Service
public class DeleteTmpFileTask {
    private Logger logger = LoggerFactory.getLogger(DeleteTmpFileTask.class);

    /**
     * 删除文件
     */
    public void deleteTmpFile(){
        logger.info("开始删除文件");
        int fileAmount = 0; // 计数器
        File[] tmpFiles;
        File uploadFile = new File(PathTool.getUploadPath());
        tmpFiles = uploadFile.listFiles();
        if(null == tmpFiles || 0 == tmpFiles.length)
            return;
        fileAmount = tmpFiles.length;
        for(File file : tmpFiles){
            deleteFile(file);
        }
        File converterFile = new File(PathTool.getConvertPath());
        tmpFiles = converterFile.listFiles();
        if(null == tmpFiles || 0 == tmpFiles.length)
            return;
        fileAmount += tmpFiles.length;
        for(File file : tmpFiles){
            deleteFile(file);
        }
        logger.info(String.format("删除临时文件完毕， 共删除了%d个文件", fileAmount));
    }

    private boolean deleteFile(File file){
        if(file.isDirectory()){
            File[] files = file.listFiles();
            for (int i = 0; files != null && i < files.length; i ++){
                deleteFile(files[i]);
            }
            return file.delete();
        }else{
            return file.delete();
        }
    }

    public static void main(String[] a) {
        System.out.println(String.format("删除临时文件完毕， 共删除了%d个文件", 1));
    }
}
