package cn.jinty.sensitive;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @Description 构建敏感词库
 * @Author jinty
 * @Date 2019年9月16日09点34分.
 */
public class SensitiveWordLib {

    //敏感词库
    private HashMap<Character,SensitiveWordTree> sensitiveWordMap;

    //获取敏感词库
    public Map getSensitiveWordMap(){
        Set<String> set = readSensitiveWord();
        buildSensitiveWordLib(set);
        return sensitiveWordMap;
    }

    //读取敏感词文件
    private Set<String> readSensitiveWord(){
        //定义返回
        Set<String> sensitiveWordSet = null;
        //通过存放路径链接文件
        File file = new File("D:\\document\\sensitiveword.txt");
        //转换流
        InputStreamReader reader = null;
        try {
            //file存在且是文件而不是目录
            if(file.exists()&&file.isFile()){
                //字节流转字符流
                reader = new InputStreamReader(new FileInputStream(file),"UTF-8");
                //缓冲流，需要里面的读行
                BufferedReader br = new BufferedReader(reader);
                //读取一行字符串
                String s = br.readLine();
                if(s!=null && !"".equals(s)){
                    sensitiveWordSet = new HashSet<String>();
                    Collections.addAll(sensitiveWordSet,s.split("，"));
                }
            }else{
                System.out.println("目标文件不存在");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //关闭流
            if(reader!=null){
                try {
                    reader.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        //返回
        return sensitiveWordSet;
    }

    //构建敏感词库 -- 字典树方式
    private void buildSensitiveWordLib(Set<String> sensitiveWordSet){
        //建立敏感词map
        sensitiveWordMap = new HashMap();
        if(sensitiveWordSet!=null){
            //遍历敏感词
            for(String s:sensitiveWordSet){
                //移动指针 -- 单个敏感词有效
                HashMap<Character,SensitiveWordTree> tempMap = sensitiveWordMap;
                //遍历词的每个字
                for(int i=0;i<s.length();i++){
                    char key = s.charAt(i);
                    //第一个多余的字符需要去除
                    if(key == '\uFEFF'){
                        continue;
                    }
                    //词存在，往下走
                    if(tempMap.get(key)!=null){
                        //词的末尾，添加敏感标志
                        if(i==s.length()-1){
                            tempMap.get(key).setSensitive(true);
                        }
                        tempMap = tempMap.get(key).getMap();
                    }else{
                        //词不存在，构建新结点
                        SensitiveWordTree child =  new SensitiveWordTree();
                        child.setMap(new HashMap<>());
                        //词的末尾，添加敏感标志
                        if(i==s.length()-1){
                            child.setSensitive(true);
                        }
                        tempMap.put(key,child);
                        tempMap = child.getMap();
                    }
                }
            }
        }
    }
}
