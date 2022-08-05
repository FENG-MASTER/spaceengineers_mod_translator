package com.fengmaster.setranslator;

import cn.hutool.core.io.file.FileWriter;
import cn.hutool.core.io.file.PathUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Main {

    // 在平台申请的APP_ID 详见 http://api.fanyi.baidu.com/api/trans/product/desktop?req=developer
    private static final String APP_ID = "20210508000818647";
    private static final String SECURITY_KEY = "ixbRL7pKLAAXAowrhMAa";

    private static final Map<String,String> translatorMap=new HashMap<>();
    static {
        translatorMap.put("Steam Power","蒸汽动力");
        translatorMap.put("Metal Grid","金属格栅");
        translatorMap.put("Electromagnet","电磁铁");
        translatorMap.put("Heating Element","加热元件");
        translatorMap.put("Lightbulb","灯泡");
        translatorMap.put("Steel Plate","钢板");
        translatorMap.put("Copper Wire","铜线");
        translatorMap.put("Small Steel Tube","小钢管");
        translatorMap.put("Large Steel Tube","大钢管");
        translatorMap.put("Basic Computer","基础电脑");
        translatorMap.put("Aluminum Plate","铝板");
        translatorMap.put("Girder","大梁");
        translatorMap.put("Concrete","混凝土");
        translatorMap.put("Display","显示屏");
        translatorMap.put("Motor","马达");
        translatorMap.put("Radio-Comm Component","无线电通信组件");
        translatorMap.put("Medical Component","医疗组件");
        translatorMap.put("Rubber","橡胶");
        translatorMap.put("Ceramic","陶瓷");
        translatorMap.put("Solar Cell","太阳能单元");
        translatorMap.put("Advanced Computer","高级计算机");
        translatorMap.put("Sensor Cluster","传感器单元");
        translatorMap.put("Gold Wire","金线");
        translatorMap.put("Superconductor","超导体");
        translatorMap.put("Synthetic Fabric","合成纤维");
        translatorMap.put("Parachute","降落伞");
        translatorMap.put("Titanium Plate","钛板");
        translatorMap.put("Armored Plate","装甲板");
        translatorMap.put("Cryocooler","冷冻机");
        translatorMap.put("Thermocouple","测温元件");
        translatorMap.put("Capacitor Cell","电容器");
        translatorMap.put("Reactor Component","反应器组件");
        translatorMap.put("Lithium Power Cell","锂能量单元");
        translatorMap.put("Laser Emitter","激光发射器");
        translatorMap.put("Composite Plate","混合板");
        translatorMap.put("Tokamak Blanket","托卡马克包层");
        translatorMap.put("Quantum Computer","量子计算机");
        translatorMap.put("Thruster Component","推进器单元");
        translatorMap.put("Gravity Generator Component","重力生成单元");
        translatorMap.put("Electron-Matrix Cell","电子矩阵单元");
        translatorMap.put("Superconducting Magnet","超导磁体");
        translatorMap.put("Survival Kit","求生装置");
        translatorMap.put("Smelter","冶炼厂");
        translatorMap.put("Assembling Bench","装配台");
        translatorMap.put("Fabricator","金属制造机");
        translatorMap.put("Wire Drawer","拉丝机");
        translatorMap.put("Plate Stamp","压板机");
        translatorMap.put("Extruder","挤压机");
        translatorMap.put("Ceramics Furnace","陶瓷炉");
        translatorMap.put("Cement Kiln","水泥窑");
        translatorMap.put("Incinerator","煅烧炉");
        translatorMap.put("Refinery","炼油厂");
        translatorMap.put("Assembler","流水线");
        translatorMap.put("Bitumen Extractor","沥青提取器");
        translatorMap.put("Oil Cracker","石油裂化器");
        translatorMap.put("Chemical Refinery","化工厂");
        translatorMap.put("Microelectronics Factory","微电子工厂");
        translatorMap.put("Auto-Loom","自动纺织机");
        translatorMap.put("Munitions Factory","军工厂");
        translatorMap.put("Advanced Assembler","高级流水线");
        translatorMap.put("Nanoassembler","纳米组装机");
        translatorMap.put("Large Alkaline Battery","一次性大型碱性电池");
        translatorMap.put("Small Alkaline Battery","一次性小型碱性电池");
        translatorMap.put("Large Acid Battery","大型酸性电池");
        translatorMap.put("Medium Acid Battery","中型酸性电池");
        translatorMap.put("Small Acid Battery","小型酸性电池");
        translatorMap.put("Large Lithium Battery","大型锂电池");
        translatorMap.put("Medium Lithium Battery","中型锂电池");
        translatorMap.put("Small Lithium Battery","小型锂电池");
        translatorMap.put("Large Gasoline Engine","大型汽油引擎");
        translatorMap.put("Small Gasoline Engine","小型汽油引擎");
        translatorMap.put("Large Hydrogen Engine","大型氢引擎");
        translatorMap.put("Small Hydrogen Engine","小型氢引擎");
        translatorMap.put("Large Solar Panel","大型太阳能板");
        translatorMap.put("Small Solar Panel","小型太阳能板");
        translatorMap.put("Large Compact Reactor","大型紧凑型核反应堆");
        translatorMap.put("Large RTG","大型放射性同位素热电式发电机");
        translatorMap.put("Small Compact Reactor","小型紧凑型核反应堆");
        translatorMap.put("Small RTG","小型放射性同位素热电式发电机");
        translatorMap.put("Steam Turbine","蒸汽涡轮");
        translatorMap.put("Coal Boiler","燃煤锅炉");
        translatorMap.put("Nuclear Reactor","核反应堆");
        translatorMap.put("Fast-Neutron Reactor","中子反应堆");

    }

    public static void main(String[] args) {
        String currentPath = System.getProperty("user.dir");
        if (args.length>1){
            currentPath=args[1];
        }
//        String currentPath ="/home/fengwu/temp/Data/";
        Extractor executor =new Extractor(currentPath);
        FileWriter displayNameOFileWriter = new FileWriter(currentPath+ File.separator+"displayName_o.txt");
        displayNameOFileWriter.writeLines(executor.extractDisplayNameList());

        FileWriter descOFileWriter = new FileWriter(currentPath+ File.separator+"desc_o.txt");
        descOFileWriter.writeLines(executor.extractDescList());

//        Translator translatorMap = new Translator(currentPath,"displayName_o.txt","displayName_t.txt","desc_o.txt","desc_t.txt");
//        translatorMap.translate();
//
//        TransApi api = new TransApi(APP_ID, SECURITY_KEY);
//
//        String query = "高度600米";
//        System.out.println(api.getTransResult(query, "auto", "en"));
    }

}
