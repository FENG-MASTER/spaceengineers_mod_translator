package com.fengmaster.setranslator;

import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.nio.file.StandardCopyOption;
import java.util.List;

public class BackupCenter {

    private String modPath;

    public BackupCenter(String modPath){
        this.modPath = modPath;
    }

    public void backup(){
        List<String> allSBCFiles =
                FileHelper.getAllSBCFiles(modPath);
        for (String sbcFile : allSBCFiles) {
            if (FileUtil.exist(sbcFile+".sbcbackup")){
                //备份文件存在
            }else {
                FileUtil.copyFile(sbcFile,sbcFile+".sbcbackup");
            }

        }
    }


}
