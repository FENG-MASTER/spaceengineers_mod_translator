package com.fengmaster.setranslator;

import cn.hutool.core.io.file.PathUtil;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FileHelper {

    public static List<String> getAllSBCFiles(String path){
        List<String> allSBCFiles = new ArrayList<>();
        File pathFile = new File(path);
        List<File> files = PathUtil.loopFiles(pathFile.toPath(), new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getAbsolutePath().endsWith(".sbc");
            }
        });
        allSBCFiles=files.stream().map(new Function<File, String>() {
            @Override
            public String apply(File file) {
                return file.getAbsolutePath();
            }
        }).collect(Collectors.toList());

        return allSBCFiles;

    }
}
