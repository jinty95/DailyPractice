package cn.jinty.sensitive;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Description 敏感词过滤器
 * @Author jinty
 * @Date 2019年9月16日12点57分
 */
public class SensitiveWordFilter {

    private Map sensitiveWordMap;

    {
        sensitiveWordMap = new SensitiveWordLib().getSensitiveWordMap();
    }

    /**
     * 检测文本中的单个敏感词
     * @param content 待处理内容
     * @param start 开始匹配的位置
     * @return 匹配失败返回0，匹配成功返回敏感词的长度
     */
    private Integer getOneSensitiveWord(String content,Integer start){
        //命中敏感词长度
        Integer length = 0;
        //移动指针
        Map tempMap = sensitiveWordMap;
        for(int i=start;i<content.length();i++){
            char key = content.charAt(i);
            SensitiveWordTree word = (SensitiveWordTree)tempMap.get(key);
            //当前字非敏感词
            if(word==null){
                length = 0;
                break;
            }else{
                length++;
                //敏感词命中
                if(word.isSensitive()){
                    break;
                }
                tempMap = word.getMap();
            }
        }
        return length;
    }

    /**
     *  获取文本中的所有敏感词
     *  @param content 待处理文本
     *  @return 敏感词集合
     */
    private Set<String> getAllSensitiveWord(String content){
        Set<String> set = new HashSet<>();
        for(int i=0;i<content.length();i++){
            int end = getOneSensitiveWord(content,i);
            if(end>0){
                //截取敏感词
                set.add(content.substring(i,i+end));
                //跳过匹配成功的部分
                i=i+end-1;
            }
        }
        return set;
    }

    /**
     * 根据敏感词构建相同长度的*
     * @param sensitiveWord
     * @return 跟敏感词长度相等的*
     */
    private String convertSensitiveWord(String sensitiveWord){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<sensitiveWord.length();i++){
            sb.append('*');
        }
        return sb.toString();
    }

    /**
     *  敏感词过滤：用***替换敏感词
     *  @param content 待处理内容
     *  @return 过滤后的健康内容
     */
    public String filterSensitiveWord(String content){
        Set<String> strs = getAllSensitiveWord(content);
        for(String s:strs){
            String pure = convertSensitiveWord(s);
            content = content.replaceAll(s,pure);
        }
        return content;
    }
}
