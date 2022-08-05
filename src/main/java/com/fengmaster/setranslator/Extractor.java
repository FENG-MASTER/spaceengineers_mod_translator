package com.fengmaster.setranslator;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileReader;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.fengmaster.setranslator.FileHelper.getAllSBCFiles;

public class Extractor {
    private String modPath;


    Pattern displayNamePattern = Pattern.compile("<DisplayName>(.*)</DisplayName>");
    Pattern descPattern = Pattern.compile("<Description>(.*)</Description>");

    public Extractor(String modPath){
        this.modPath=modPath;
    }


    /**
     * 提取物品名称
     * @return
     */
    public Set<String> extractDisplayNameList(){
        Set<String> textList = new HashSet<>();

        List<String> allSBCFiles = getAllSBCFiles(modPath);
        for (String allSBCFile : allSBCFiles) {
            FileReader fr = new FileReader(allSBCFile+".sbcbackup");
            String fileText = fr.readString();
            if (fileText.contains("<DisplayName>")){
                //有需要翻译的文本
                Matcher matcher = displayNamePattern.matcher(fileText);
                while (matcher.find()){
                    if (!matcher.group(1).startsWith("DisplayName_")){
                        textList.add(matcher.group(1));
                    }
                }

            }
        }

        return textList;
    }



    public Set<String> extractDescList(){
        Set<String> textList = new HashSet<>();

        List<String> allSBCFiles = getAllSBCFiles(modPath);
        for (String allSBCFile : allSBCFiles) {
            FileReader fr = new FileReader(allSBCFile+".sbcbackup");
            String fileText = fr.readString();
            if (fileText.contains("<Description>")){
                //有需要翻译的文本
                Matcher matcher = descPattern.matcher(fileText);
                while (matcher.find()){
                    if (!matcher.group(1).startsWith("Description_")){
                        textList.add(matcher.group(1));
                    }
                }

            }
        }

        return textList;
    }



}
