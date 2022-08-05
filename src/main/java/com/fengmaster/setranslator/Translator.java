package com.fengmaster.setranslator;

import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.io.file.FileWriter;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.fengmaster.setranslator.FileHelper.getAllSBCFiles;

public class Translator {

    private Map<String, String> displayNameMap=new HashMap<>();
    private Map<String, String> descMap=new HashMap<>();
    private String modPath;

    Pattern displayNamePattern = Pattern.compile("<DisplayName>(.*)</DisplayName>");
    Pattern descPattern = Pattern.compile("<Description>(.*)</Description>");

    public Translator(String modPath,String displayNameOFile,String displayNameTFile,String descOFile,String descTFile){
        this.modPath=modPath;
        FileReader displayNameOFileFr = new FileReader(modPath+ File.separator +displayNameOFile);
        List<String> displayNameOList = displayNameOFileFr.readLines();
        FileReader displayNameTFileFr = new FileReader(modPath+ File.separator +displayNameTFile);
        List<String> displayNameTList =displayNameTFileFr.readLines();

        for (int i = 0; i < displayNameOList.size(); i++) {
            displayNameMap.put(displayNameOList.get(i),displayNameTList.get(i));
        }

        FileReader descOFileFr = new FileReader(modPath+ File.separator +descOFile);
        List<String> descOFileList = descOFileFr.readLines();
        FileReader descTFileFr = new FileReader(modPath+ File.separator +descTFile);
        List<String> descTFileList =descTFileFr.readLines();

        for (int i = 0; i < descOFileList.size(); i++) {
            descMap.put(descOFileList.get(i),descTFileList.get(i));
        }
    }


    public void translate(){
        List<String> allSBCFiles = getAllSBCFiles(modPath);
        for (String allSBCFile : allSBCFiles) {
            FileReader fr = new FileReader(allSBCFile+".sbcbackup");
            String fileText = fr.readString();
            if (fileText.contains("<DisplayName>")){
                //有需要翻译的文本
                Matcher matcher = displayNamePattern.matcher(fileText);
                while (matcher.find()){
                    if (!matcher.group(1).contains("DisplayName_")){
                        if (displayNameMap.containsKey(matcher.group(1))){
                            fileText=fileText.replace("<DisplayName>"+matcher.group(1)+"</DisplayName>","<DisplayName>"+displayNameMap.get(matcher.group(1))+"</DisplayName>");
                        }

                    }
                }

            }


            if (fileText.contains("<Description>")){
                //有需要翻译的文本
                Matcher matcher = descPattern.matcher(fileText);
                while (matcher.find()){
                    if (!matcher.group(1).contains("Description_")){
                        if (descMap.containsKey(matcher.group(1))){
                            fileText=fileText.replace("<Description>"+matcher.group(1)+"</Description>","<Description>"+descMap.get(matcher.group(1))+"</Description>");
                        }

                    }
                }

            }


            FileWriter writer = new FileWriter(allSBCFile);
            writer.write(fileText,false);
        }
    }


}
